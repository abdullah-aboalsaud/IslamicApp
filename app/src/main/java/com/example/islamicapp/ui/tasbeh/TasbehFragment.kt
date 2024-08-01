package com.example.islamicapp.ui.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.databinding.FragmentTasbehBinding
import com.example.islamicapp.utils.hideBtnBack
import com.example.islamicapp.utils.showAppBar


class TasbehFragment : Fragment() {

    private var _binding: FragmentTasbehBinding? = null
    val binding get() = _binding!!
    var currentRotation = 0f
    var tasbehCounter = 0

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

        binding.ivSebhaBody.setOnClickListener {
            rotateImage()
            increaseCountWithDoaa()
        }

    }

    val doaaList = mutableListOf("alhamd llah", "la elah ela allah", "allah akbar", "sobhan allah")
    var listCounter = 0

    private fun increaseCountWithDoaa() {

        if (tasbehCounter <= 33) {
            binding.tvCounter.text = tasbehCounter.toString()
            tasbehCounter++
        } else {
            tasbehCounter = 0

            if (listCounter < doaaList.size) {
                binding.tvDoaa.text = doaaList[listCounter]
                listCounter++
            } else {
                listCounter = 0
                binding.tvDoaa.text = doaaList[listCounter]
            }

        }


    }

    private fun rotateImage() {

        // val rotation = AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_animation)

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