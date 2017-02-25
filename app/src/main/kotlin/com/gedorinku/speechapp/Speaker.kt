package com.gedorinku.speechapp

import android.content.Context
import android.content.pm.ApplicationInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by gedorinku on 2017/02/23.
 */
class Speaker(private val applicationInfo: ApplicationInfo) {

    fun startSpeaking(script: SpeechScript) {
        startConvertingToSpeech(script.getScript()) { audioData ->
            val audioTrack = AudioTrack(
                    AudioManager.STREAM_MUSIC, 22050, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, audioData.size, AudioTrack.MODE_STREAM)
            audioTrack.play()
            audioTrack.write(audioData.toByteArray(), 0, audioData.size)
        }
    }

    private fun startConvertingToSpeech(text: String, onConvertedHandler: (Array<Byte>) -> Unit) {
        val apiKey = applicationInfo.metaData.getString("apiKey")
        val url = "https://api.apigw.smt.docomo.ne.jp"

        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .build()

        retrofit.create(TextToSpeechAPI::class.java).convertToSpeech(apiKey, text)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        onConvertedHandler(response!!.body().bytes().toTypedArray())
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        throw t!!
                    }

                })
    }
}