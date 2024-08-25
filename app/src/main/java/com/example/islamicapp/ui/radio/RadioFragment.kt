package com.example.islamicapp.ui.radio


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentRadioBinding
import com.example.islamicapp.utils.hideBtnBack
import com.example.islamicapp.utils.showAppBar


class RadioFragment : Fragment() {
    private var _binding: FragmentRadioBinding? = null
    private val binding get() = _binding!!
    var radioService: RadioService? = null
    var bound = false
    private lateinit var radioList: List<ModelRadio>
    private var currentRadioIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAppBar()
        hideBtnBack()

        getRadiosList()
        initViews()
        onPlayClick()
        onPreviousClick()
        onNextClick()

    }

    private fun onNextClick() {
        binding.ivNext.setOnClickListener {
            // pauseMediaPlayer()
            // binding.ivPlay.setImageResource(R.drawable.ic_play)

            currentRadioIndex =
                if (currentRadioIndex == radioList.size - 1) 0 else ++currentRadioIndex

            radioService?.mediaPlayer?.reset()
            radioService?.initMediaPlayer(
                radioList[currentRadioIndex].url, radioList[currentRadioIndex].name
            )

            binding.tvTitle.text = radioList[currentRadioIndex].name
        }

    }

    private fun onPreviousClick() {
        binding.ivPrevious.setOnClickListener {
            currentRadioIndex =
                if (currentRadioIndex == 0) radioList.size - 1 else --currentRadioIndex
            radioService?.mediaPlayer?.reset()
            radioService?.initMediaPlayer(
                radioList[currentRadioIndex].url, radioList[currentRadioIndex].name
            )
            binding.tvTitle.text = radioList[currentRadioIndex].name
        }

    }

    private fun onPlayClick() {
        binding.ivPlay.setOnClickListener {
            radioService?.let { radioService ->
                if (radioService.isPrepared) {
                    if (!radioService.getIsPlaying()) {
                        radioService.startMediaPlayer()
                        binding.ivPlay.setImageResource(R.drawable.ic_pause)
                    } else {
                        pauseMediaPlayer()
                        binding.ivPlay.setImageResource(R.drawable.ic_play)
                    }
                } else {
                    radioService.mediaPlayer.reset()
                    radioService.initMediaPlayer(
                        radioList[currentRadioIndex].url, radioList[currentRadioIndex].name
                    )
                    binding.ivPlay.setImageResource(R.drawable.ic_pause)
                }

            }

        }
    }

    private fun pauseMediaPlayer() {
        if (bound) {
            radioService?.playPauseMediaPlayer()
        }

    }

    private fun initViews() {
        binding.tvTitle.text = radioList[currentRadioIndex].name
        radioService?.initMediaPlayer(
            radioList[currentRadioIndex].url, radioList[currentRadioIndex].name
        )
    }

    private fun getRadiosList() {
        radioList = listOf(
            ModelRadio("إذاعة القارئ ياسين", "https://backup.qurango.net/radio/alqaria_yassen"),
            ModelRadio("إذاعة أحمد الطرابلسي", "https://backup.qurango.net/radio/ahmed_altrabulsi"),
            ModelRadio("إذاعة تفسير القران الكريم", "https://backup.qurango.net/radio/tafseer"),
            ModelRadio("إذاعة مشاري العفاسي", "https://backup.qurango.net/radio/mishary_alafasi"),
            ModelRadio(
                "إذاعة محمود علي البنا",
                "https://backup.qurango.net/radio/mahmoud_ali__albanna_mojawwad"
            ),
            ModelRadio(
                "إذاعة محمود خليل الحصري",
                "https://backup.qurango.net/radio/mahmoud_khalil_alhussary_warsh"
            ),
            ModelRadio(
                "الإذاعة العامة - اذاعة متنوعة لمختلف القراء",
                "https://backup.qurango.net/radio/mix"
            ),
            ModelRadio(
                "المختصر في تفسير القرآن الكريم",
                "https://backup.qurango.net/radio/mukhtasartafsir"
            )

        )
    }

    override fun onStart() {
        super.onStart()
        bindService()
        startService()
    }


    override fun onResume() {
        super.onResume()
        if (radioService?.getIsPlaying()!!)
            binding.ivPlay.setImageResource(R.drawable.ic_pause)
    }


    fun startService() {
        val intent = Intent(requireContext(), RadioService::class.java)
        requireActivity().startService(intent)
    }

    fun bindService() {
        val intent = Intent(requireContext(), RadioService::class.java)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RadioService.MyBinder
            radioService = binder.getService()
            bound = true
            radioService?.onPlayClick = RadioService.OnPlayClick { isPlayed ->
                if (isPlayed) {
                    binding.ivPlay.setImageResource(R.drawable.ic_pause)
                } else {
                    binding.ivPlay.setImageResource(R.drawable.ic_play)
                }
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            radioService = null
            bound = false
        }

    }


    override fun onStop() {
        super.onStop()
        //    requireActivity().unbindService(connection)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}