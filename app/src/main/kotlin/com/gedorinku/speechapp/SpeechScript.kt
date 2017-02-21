package com.gedorinku.speechapp

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * Created by gedorinku on 2017/02/21.
 */
class SpeechScript(private var script: String = "") : BaseObservable() {

    @Bindable
    fun getScript(): String = script

    fun setScript(script: String): Unit {
        this.script = script
        notifyPropertyChanged(BR.script)
    }
}