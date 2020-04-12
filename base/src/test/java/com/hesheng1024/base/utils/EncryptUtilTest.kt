package com.hesheng1024.base.utils

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author hesheng1024
 * @date 2020/3/4 10:24
 */
class EncryptUtilTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun aesTest() {
        val content = "AESTest"
        val pwd = "123456"
        val enResult = EncryptUtil.aesEncrypt(content, pwd)
        println(enResult)
        val deResult = EncryptUtil.aesDecrypt(enResult, pwd)
        println(deResult)
        Assert.assertEquals(content, deResult)
    }

    @Test
    fun desTest() {
        val content = "DESTest"
        val pwd = "01234567"
        val enResult = EncryptUtil.desEncrypt(content, pwd)
        println(enResult)
        val deResult = EncryptUtil.desDecrypt(enResult, pwd)
        println(deResult)
        Assert.assertEquals(content, deResult)
    }

    @Test
    fun tripleDesTest() {
        val content = "3DESTest"
        val pwd = "f510b8737344cddbca1c8564"
        val enResult = EncryptUtil.tripleDesEncrypt(content, pwd)
        println(enResult)
        val deResult = EncryptUtil.tripleDesDecrypt(enResult, pwd)
        println(deResult)
        Assert.assertEquals(content, deResult)
    }

}