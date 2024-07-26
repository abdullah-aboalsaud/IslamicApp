package com.example.islamicapp.ui.hadeth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.MainActivity
import com.example.islamicapp.databinding.FragmentHadethBinding


class HadethFragment : Fragment() {
    private var _binding: FragmentHadethBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHadethBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radHadethFile()
    }

    private fun radHadethFile() {
        val allHadethList: MutableList<HadethModel> = mutableListOf()

        val allFileContent = (activity as MainActivity).assets.open("ahadeth.txt")
            .bufferedReader().use { it.readText() }

        val ahadethList = allFileContent.split("#")
        ahadethList.forEach { hadeth ->
            val hadethLines = hadeth.trim().split("\n").toMutableList()
            val title = hadethLines[0]
            hadethLines.removeAt(0)
            val h = HadethModel(
                title = title,
                content = hadethLines.joinToString("\n")
            )
            allHadethList.add(h)

        }

        bindHadethList(allHadethList)

    }

    private fun bindHadethList(allHadethList: MutableList<HadethModel>) {
        binding.hadethRecycler.adapter = HadethAdapter(allHadethList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
