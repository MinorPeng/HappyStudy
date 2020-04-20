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

    private val mMediaPlayer: MediaPlayer = MediaPlayer()

    init {
        mMediaPlayer.setOnCompletionListener { mp ->
            mp.stop()
        }
        mMediaPlayer.setOnErrorListener { mp, what, extra ->
            logE(msg = "media error:$what extra:$extra")
            mp.stop()
            return@setOnErrorListener true
        }
        mMediaPlayer.setOnPreparedListener { mp ->
            mp.start()
        }
    }

    fun play() {
        play(R.raw.voice1)
    }

    fun play(rawId: Int) {
        if (!mMediaPlayer.isPlaying) {
            mMediaPlayer.reset()
            val asd = ContextHolder.getMainContext().resources.openRawResourceFd(rawId)
            mMediaPlayer.setDataSource(asd.fileDescriptor, asd.startOffset, asd.length)
            mMediaPlayer.prepareAsync()
        }
    }

    fun pause() {
        if (mMediaPlayer.isPlaying) {
            mMediaPlayer.pause()
        }
    }

    fun stop() {
        mMediaPlayer.stop()
        mMediaPlayer.release()
    }
}