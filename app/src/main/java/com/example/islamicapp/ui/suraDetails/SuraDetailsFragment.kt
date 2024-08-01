package com.example.islamicapp.ui.suraDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.islamicapp.MainActivity
import com.example.islamicapp.databinding.FragmentSuraDetailsBinding
import com.example.islamicapp.utils.btnBack
import com.example.islamicapp.utils.hideBottomNavigationView
import com.example.islamicapp.utils.showAppBar
import com.example.islamicapp.utils.showBtnBack


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
        showAppBar()
        hideBottomNavigationView()
        showBtnBack()
        btnBack()

        binding.tvTitle.text = args.title

        readSura()

    }

    private fun readSura() {
        val allFileContent = (activity as MainActivity).assets.open("${args.position+1}.txt")
            .bufferedReader().use { it.readText() }

        val versesList = allFileContent.split("\n")

        bindVersesToRecyclerViewAdapter(versesList)
    }

    private fun bindVersesToRecyclerViewAdapter(versesList: List<String>) {
        val versesAdapter = VersesAdapter(versesList)
        binding.versesRecycler.adapter = versesAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}