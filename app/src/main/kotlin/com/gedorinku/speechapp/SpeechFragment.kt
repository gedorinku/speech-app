package com.gedorinku.speechapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.gedorinku.speechapp.databinding.FragmentSpeechBinding


class SpeechFragment : Fragment() {

    private lateinit var binding: FragmentSpeechBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_speech, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding = FragmentSpeechBinding.bind(view)
        binding.fragment = this
        binding.speechScript = SpeechScript()
    }

    fun onSpeechButtonClick(view: View): Unit {
        val applicationInfo = activity.packageManager
                .getApplicationInfo(activity.packageName, PackageManager.GET_META_DATA)
        val speaker = Speaker(applicationInfo)
        speaker.startSpeaking(binding.speechScript)
    }

    companion object {
        fun newInstance(): SpeechFragment {
            val fragment = SpeechFragment()
            return fragment
        }
    }
}
