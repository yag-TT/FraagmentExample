package com.example.fragmentexample.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentexample.R
import com.example.fragmentexample.core.ui.viewBinding
import com.example.fragmentexample.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openSlidePageButton.setOnClickListener {
            it.isEnabled = false
            findNavController().navigate(R.id.action_home_to_slide_page)
        }
    }
}
