package com.gedorinku.speechapp

import android.content.pm.ApplicationInfo
import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * Created by gedorinku on 2017/02/21.
 */
class SpeechScript(private val applicationInfo: ApplicationInfo) : BaseObservable() {

    private var script: String = ""

    @Bindable
    fun getScript(): String = script

    fun setScript(script: String): Unit {
        this.script = script
        notifyPropertyChanged(BR.script)
    }

    fun onSpeechButtonClick() {
        val speaker = Speaker(applicationInfo)
        speaker.startSpeaking(this)
    }
}