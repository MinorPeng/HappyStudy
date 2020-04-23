package com.hesheng1024.happystudy.utils

import android.media.MediaPlayer
import com.hesheng1024.base.utils.ContextHolder
import com.hesheng1024.base.utils.logE
import com.hesheng1024.happystudy.R

/**
 *
 * @author hesheng1024
 * @date 2020/4/15 16:38
 */
object MediaPlayerUtil {

    private var mMediaPlayer: MediaPlayer? = null

    fun init() {
        mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setOnCompletionListener { mp ->
            mp.stop()
        }
        mMediaPlayer?.setOnErrorListener { mp, what, extra ->
            logE(msg = "media error:$what extra:$extra")
            mp.stop()
            return@setOnErrorListener true
        }
        mMediaPlayer?.setOnPreparedListener { mp ->
            mp.start()
        }
    }

    fun play() {
        play(R.raw.voice1)
    }

    fun play(rawId: Int) {
        mMediaPlayer?.let {
            if (!it.isPlaying) {
                it.reset()
                val asd = ContextHolder.getMainContext().resources.openRawResourceFd(rawId)
                it.setDataSource(asd.fileDescriptor, asd.startOffset, asd.length)
                it.prepareAsync()
            }
        }
    }

    fun pause() {
        mMediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            }
        }
    }

    fun stop() {
        mMediaPlayer?.stop()
    }

    fun release() {
        mMediaPlayer?.release()
    }
}