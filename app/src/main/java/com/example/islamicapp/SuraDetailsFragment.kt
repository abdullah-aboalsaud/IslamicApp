package com.example.islamicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.islamicapp.databinding.FragmentHomeBinding
import com.example.islamicapp.databinding.FragmentSuraDetailsBinding


class SuraDetailsFragment : Fragment() {
    private var _binding: FragmentSuraDetailsBinding? = null
    private val binding get() = _binding!!

    val args: SuraDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuraDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}