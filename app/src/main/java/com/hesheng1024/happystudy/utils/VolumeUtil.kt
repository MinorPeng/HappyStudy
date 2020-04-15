package com.hesheng1024.happystudy.utils

import android.content.Context
import android.media.AudioManager
import com.hesheng1024.base.utils.ContextHolder

/**
 *
 * @author hesheng1024
 * @date 2020/4/15 16:31
 */
object VolumeUtil {

    fun adjustVolume(volume: Int) {
        val audioManager = ContextHolder.getMainContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume + volume, AudioManager.FLAG_PLAY_SOUND )
    }
}