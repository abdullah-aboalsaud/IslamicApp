package com.example.islamicapp.ui.radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.databinding.FragmentRadioBinding
import com.example.islamicapp.utils.hideBtnBack
import com.example.islamicapp.utils.showAppBar


class RadioFragment : Fragment() {
    private var _binding: FragmentRadioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAppBar()
        hideBtnBack()
    }


}