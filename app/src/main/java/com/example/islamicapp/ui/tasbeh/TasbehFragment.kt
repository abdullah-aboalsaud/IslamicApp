package com.example.islamicapp.ui.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentTasbehBinding
import com.example.islamicapp.utils.hideBtnBack
import com.example.islamicapp.utils.showAppBar


class TasbehFragment : Fragment() {

    private var _binding: FragmentTasbehBinding? = null
    val binding get() = _binding!!

    private var doaaList = mutableListOf<String>()
    var currentRotation = 0f
    var tasbehCounter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasbehBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAppBar()
        hideBtnBack()

        doaaList = mutableListOf(
            getString(R.string.alhamd_llah),
            getString(R.string.la_elah_ela_allah),
            getString(R.string.allah_akbar),
            getString(R.string.sobhan_allah)
        )

        binding.ivSebhaBody.setOnClickListener {
            rotateImage()
            increaseCountWithDoaa()
        }

    }


    var listCounter = 0

    private fun increaseCountWithDoaa() {

        if (tasbehCounter <= 33) {
            setTasbehCounter()
            tasbehCounter++
        } else {
            tasbehCounter = 0

            if (listCounter < doaaList.size) {
                binding.tvDoaa.text = doaaList[listCounter]
                setTasbehCounter()
                listCounter++
            } else {
                listCounter = 0
                binding.tvDoaa.text = doaaList[listCounter]
                setTasbehCounter()
            }

        }


    }

    private fun setTasbehCounter() {
        binding.tvCounter.text = tasbehCounter.toString()
    }


    private fun rotateImage() {

        currentRotation += 20f

        binding.ivSebhaBody.animate()
            .rotation(currentRotation)
            .setDuration(500)
            .start()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}