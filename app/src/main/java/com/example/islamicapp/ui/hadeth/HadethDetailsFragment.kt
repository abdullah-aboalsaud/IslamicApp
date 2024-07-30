package com.example.islamicapp.ui.hadeth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.islamicapp.databinding.FragmentHadethDetailsBinding
import com.example.islamicapp.utils.showAppBar

class HadethDetailsFragment : Fragment() {
    private var _binding: FragmentHadethDetailsBinding? = null
    private val binding get() = _binding!!

    val args: HadethDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHadethDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAppBar()

        binding.tvTitle.text = args.hadeth.title
        binding.tvHadethContent.text = args.hadeth.content


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}