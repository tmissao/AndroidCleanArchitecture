package br.com.missao.cleanarchitecture.utils

import java.security.SecureRandom
import java.util.*

/**
 * Provides random values for tests
 */
object TestValues {

    private val r = SecureRandom()

    /**
     * Provides a ranged [Int] between [min] and [max]
     */
    fun range(min: Int, max: Int): Int {
        val delta = max - min
        return min + r.nextInt(delta)
    }

    /**
     * Provides a UUID [String]
     */
    fun uuid(): String
            = UUID.randomUUID().toString().replace("-", "").toUpperCase()

    /**
     * Provides a nonEmpty [String]
     */
    fun nonEmptyString(): String
            = nonEmptyString(32)


    /**
     * Provides a nonEmpty [String] with a [maxLength] length
     */
    fun nonEmptyString(maxLength: Int): String
            = string(1, maxLength)

    /**
     * Provides a [String] with length between [minLength] and [maxLength]
     */
    fun string(minLength: Int, maxLength: Int): String {
        val delta = maxLength - minLength

        val length = minLength + r.nextInt(delta)

        val sb = StringBuilder()
        for (i in 0..length - 1) {
            val ch = range(32, 125).toChar()
            sb.append(ch)
        }
        return sb.toString()
    }

    /**
     * Provides an alphabetic [String]
     */
    fun alphabeticString(length: Int): String {
        val characters = "abcdefghijklmnopqrstuvwxyz"
        return string(characters, length)
    }

    /**
     * Provides an alphaNumberic [String]
     */
    fun alphaNumericString(length: Int): String {
        val characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return string(characters, length)
    }

    /**
     * Provides a [String] containing specific characters defined by [characters] and specific length
     * described by [length]
     */
    fun string(characters: String, length: Int): String {
        val random = Random()

        val sb = StringBuilder(length)
        for (i in 0..length - 1) {
            sb.append(characters[random.nextInt(characters.length)])
        }

        return sb.toString()
    }

    /**
     * Provides an random Email
     */
    fun email(): String {
        return alphabeticString(10) + "@" + alphabeticString(5) + ".com"
    }

    /**
     * Provides a random boolean
     */
    fun boolean(): Boolean {
        return r.nextBoolean()
    }

    /**
     * Provides a [Double]
     */
    fun double(): Double
            = (range(1, 10000000) / 1000).toDouble()


    /**
     * Provides a random IP address
     */
    fun ip(): String
            = range(1, 255).toString() + "." + range(0, 255) + "." + range(0, 255) + "." + range(0, 255)

    /**
     * Provides a random url address
     */
    fun url(): String
            = "http://" + alphaNumericString(12) + ".com"

    /**
     * Picks a random element from [array]
     */
    fun <A> pick(array: Array<A>): A {
        val i = r.nextInt(array.size)
        return array[i]
    }

    /**
     * Picks a random element from [colletion]
     */
    fun <A> pick(colletion: Collection<A>): A {
        val i = r.nextInt(colletion.size)
        return colletion.elementAt(i)
    }

}