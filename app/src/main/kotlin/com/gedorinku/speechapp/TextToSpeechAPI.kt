package com.gedorinku.speechapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by gedorinku on 2017/02/25.
 */
interface TextToSpeechAPI {

    @FormUrlEncoded
    @POST("crayon/v1/textToSpeech")
    fun convertToSpeech(@Query("APIKEY") apiKey: String,
                        @Field("TextData") textData: String,
                        @Field("Command") command: String = "AP_Synth",
                        @Field("SpeakerID") speakerID: Int = 1,
                        @Field("StyleID") styleID: Int = 1,
                        @Field("AudioFileFormat") audioFileFormat: Int = 2): Call<ResponseBody>
}