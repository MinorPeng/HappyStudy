package com.hesheng1024.base.utils

import android.annotation.SuppressLint
import android.util.Base64
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.*

/**
 *
 * @author hesheng1024
 * @date 2020/3/4 10:11
 */
object EncryptUtil {

    private const val AES_ITERATION_COUNT = 1000
    private const val AES_SALT_LEN = 32  // bytes 128 / 8 --> AES 128
    private const val IV = "0000000000000000"
    private var salt: ByteArray  //此处的盐值需要自己保存，或者自己保存密钥

    init {
        val random = SecureRandom()
        salt = ByteArray(AES_SALT_LEN)
        random.nextBytes(salt)
    }

    /**
     * AES加密
     *
     * @param data 待加密数据
     * @param password key
     * @return str，加密后的数据
     */
    fun aesEncrypt(data: String?, password: String, iv: String = IV): String? {
        if (data.isNullOrEmpty()) {
            return data
        }
        val result = aesEncrypt(data.toByteArray(), password.toCharArray(), iv.toByteArray())
        return String(Base64.encode(result, Base64.DEFAULT))
    }

    /**
     * AES解密
     *
     * @param data 加密后的数据
     * @param password key
     * @return str，解密后的数据
     */
    fun aesDecrypt(data: String?, password: String, iv: String = IV): String? {
        if (data.isNullOrEmpty()) {
            return data
        }
        val result = aesDecrypt(Base64.decode(data, Base64.DEFAULT), password.toCharArray(), iv.toByteArray())
        return String(result)
    }

    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun aesEncrypt(data: ByteArray, key: CharArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(getRawKey(key), "AES"), IvParameterSpec(iv))
        return cipher.doFinal(data)
    }

    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun aesDecrypt(data: ByteArray, key: CharArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(getRawKey(key), "AES"), IvParameterSpec(iv))
        return cipher.doFinal(data)
    }

    private fun getRawKey(key: CharArray): ByteArray {
        val keySpec = PBEKeySpec(key, salt, AES_ITERATION_COUNT, AES_SALT_LEN * 8)
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val rawKey = keyFactory.generateSecret(keySpec).encoded
        logE(msg = rawKey.toList().toString())
        return rawKey
    }

    /**
     * DES加密
     *
     * @param data 未加密的data
     * @param key 长度必须大于等于8
     * @return 加密后base64编码的data
     */
    fun desEncrypt(data: String, key: String): String {
        val result = desEncrypt(data.toByteArray(Charset.defaultCharset()), key.toByteArray())
        return String(Base64.encode(result, Base64.DEFAULT))
    }

    /**
     * DES解密
     *
     * @param data 加密后的data
     * @param key key
     * @return str 未加密的data
     */
    fun desDecrypt(data: String, key: String): String {
        return String(desDecrypt(Base64.decode(data, Base64.DEFAULT), key.toByteArray()))
    }

    @SuppressLint("GetInstance")
    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun desEncrypt(data: ByteArray, key: ByteArray): ByteArray {
        val random = SecureRandom()
        val desKey = DESKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance("DES")
        val secureKey = keyFactory.generateSecret(desKey)
        val cipher = Cipher.getInstance("DES")
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, random)
        return cipher.doFinal(data)
    }

    @SuppressLint("GetInstance")
    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun desDecrypt(data: ByteArray, key: ByteArray): ByteArray {
        val random = SecureRandom()
        val desKey = DESKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance("DES")
        val secureKey = keyFactory.generateSecret(desKey)
        val cipher = Cipher.getInstance("DES")
        cipher.init(Cipher.DECRYPT_MODE, secureKey, random)
        return cipher.doFinal(data)
    }

    /**
     * triple DES 3DES加密
     *
     * @param data 待加密的数据
     * @param key key 长度大于等于24
     * @return str，加密后Base64编码的字符串
     */
    fun tripleDesEncrypt(data: String, key: String): String {
        val result = tripleDesEncrypt(data.toByteArray(), key.toByteArray())
        return String(Base64.encode(result, Base64.DEFAULT))
    }

    /**
     * triple DES解密
     *
     * @param data 加密后的数据
     * @param key key
     * @return str，解密后的数据
     */
    fun tripleDesDecrypt(data: String, key: String): String {
        return String(tripleDesDecrypt(Base64.decode(data, Base64.DEFAULT), key.toByteArray()))
    }

    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun tripleDesEncrypt(data: ByteArray, key: ByteArray): ByteArray {
        val desKey = DESedeKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance("desede")
        val secureKey = keyFactory.generateSecret(desKey)
        val cipher = Cipher.getInstance("desede")
        cipher.init(Cipher.ENCRYPT_MODE, secureKey)
        return cipher.doFinal(data)
    }

    @Throws(BadPaddingException::class, IllegalBlockSizeException::class)
    fun tripleDesDecrypt(data: ByteArray, key: ByteArray): ByteArray {
        val desKey = DESedeKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance("desede")
        val secureKey = keyFactory.generateSecret(desKey)
        val cipher = Cipher.getInstance("desede")
        cipher.init(Cipher.DECRYPT_MODE, secureKey)
        return cipher.doFinal(data)
    }
}