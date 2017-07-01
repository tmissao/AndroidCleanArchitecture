package br.com.missao.cleanarchitecture.loggers

/**
 * Interface to Log purposes, useful to provides different behaviours
 * on production and tests
 */
interface Logger {

    /**
     * Verbose Log, identified by [tag], and its content is [msg]
     */
    fun v(tag: String, msg: String)

    /**
     * Verbose Log, identified by [tag], and its content is [tr]
     */
    fun v(tag: String, tr: Throwable)

    /**
     * Verbose Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun v(tag: String, msg: String, tr: Throwable)

    /**
     * Debug Log, identified by [tag], and its content is [msg]
     */
    fun d(tag: String, msg: String)

    /**
     * Debug Log, identified by [tag], and its content is [tr]
     */
    fun d(tag: String, tr: Throwable)

    /**
     * Debug Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun d(tag: String, msg: String, tr: Throwable)

    /**
     * Error Log, identified by [tag], and its content is [msg]
     */
    fun e(tag: String, msg: String)

    /**
     * Verbose Log, identified by [tag], and its content is [tr]
     */
    fun e(tag: String, tr: Throwable)

    /**
     * Error Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun e(tag: String, msg: String, tr: Throwable)

    /**
     * Warning Log, identified by [tag], and its content is [msg]
     */
    fun w(tag: String, msg: String)

    /**
     * Warning Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun w(tag: String, msg: String, tr: Throwable)

    /**
     * Warning Log, identified by [tag], and its content is [tr]
     */
    fun w(tag: String, tr: Throwable)

    /**
     * Info Log, identified by [tag], and its content is [msg]
     */
    fun i(tag: String, msg: String)

    /**
     * Verbose Log, identified by [tag], and its content is [tr]
     */
    fun i(tag: String, tr: Throwable)

    /**
     * Info Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun i(tag: String, msg: String, tr: Throwable)

    /**
     * What Terrible Failure Log, identified by [tag], and its content is [msg]
     */
    fun wtf(tag: String, msg: String)

    /**
     * What Terrible Failure Log, identified by [tag], and its content are [msg] and [tr]
     */
    fun wtf(tag: String, msg: String = "", tr: Throwable)

    /**
     * What Terrible Failure Log, identified by [tag], and its content is [tr]
     */
    fun wtf(tag: String, tr: Throwable)
}