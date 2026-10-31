package com.example.fragmentexample.feature.slide

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.fragmentexample.R
import com.example.fragmentexample.core.ui.viewBinding
import com.example.fragmentexample.databinding.FragmentSlidePageBinding

class SlidePageFragment : Fragment(R.layout.fragment_slide_page) {

    private val binding by viewBinding(FragmentSlidePageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.slideCardList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SlideCardAdapter()
            // スワイプ後は必ず1枚のカードがページ中央に収まるようにします。
            PagerSnapHelper().attachToRecyclerView(this)
        }

        binding.closeSlidePageButton.setOnClickListener {
            it.isEnabled = false
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        binding.slideCardList.adapter = null
        super.onDestroyView()
    }
}
