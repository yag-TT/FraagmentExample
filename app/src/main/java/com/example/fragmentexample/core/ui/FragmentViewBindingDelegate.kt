package com.example.fragmentexample.core.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Fragment の View ライフサイクルだけ ViewBinding を保持する delegate です。
 * 画面を破棄した後に binding が残ることを防ぎます。
 */
class FragmentViewBindingDelegate<T : ViewBinding>(
    fragment: Fragment,
    private val bind: (View) -> T,
) : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    init {
        // Fragment 自体ではなく、作り直される可能性がある View の LifecycleOwner を監視します。
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(this)
        }
    }

    // 最初の参照時だけ Binding を生成し、同じ View が存在する間は再利用します。
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        binding ?: bind(thisRef.requireView()).also { binding = it }

    override fun onDestroy(owner: LifecycleOwner) {
        // Fragment がバックスタックに残っても、破棄された View を参照し続けないようにします。
        binding = null
    }
}

/** Fragment から `private val binding by viewBinding(...)` と宣言するための入口です。 */
fun <T : ViewBinding> Fragment.viewBinding(bind: (View) -> T): ReadOnlyProperty<Fragment, T> =
    FragmentViewBindingDelegate(this, bind)
