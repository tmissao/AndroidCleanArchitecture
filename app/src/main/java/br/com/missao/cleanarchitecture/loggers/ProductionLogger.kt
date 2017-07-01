package br.com.missao.cleanarchitecture.loggers

import android.util.Log

/**
 * Logger for production. By default just Logs of type Error and Wtf are executed.
 * Also is useful integrates it with Crash/Error frameworks as Crashlytics or FirebaseCrashRepost
 */
class ProductionLogger : Logger {

    /**
     * When debug value is true just logs of type Error and Wtf are executed
     */
    private val debug = true

    override fun v(tag: String, msg: String) {
        if (debug) Log.v(tag, msg)
    }

    override fun v(tag: String, tr: Throwable) {
        if (debug) Log.v(tag, "", tr)
    }

    override fun v(tag: String, msg: String, tr: Throwable) {
        if (debug) Log.v(tag, msg, tr)
    }

    override fun d(tag: String, msg: String) {
        if (debug) Log.d(tag, msg)
    }

    override fun d(tag: String, tr: Throwable) {
        if (debug) Log.d(tag, "", tr)
    }

    override fun d(tag: String, msg: String, tr: Throwable) {
        if (debug) Log.d(tag, msg, tr)
    }

    override fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    override fun e(tag: String, tr: Throwable) {
        if (debug) Log.e(tag, "", tr)
    }

    override fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(tag, msg, tr)
    }

    override fun w(tag: String, msg: String) {
        if (debug) Log.w(tag, msg)
    }

    override fun w(tag: String, tr: Throwable) {
        if (debug) Log.w(tag, tr)
    }

    override fun w(tag: String, msg: String, tr: Throwable) {
        if (debug) Log.w(tag, msg, tr)
    }

    override fun i(tag: String, msg: String) {
        if (debug) Log.i(tag, msg)
    }

    override fun i(tag: String, tr: Throwable) {
        if (debug) Log.v(tag, "", tr)
    }

    override fun i(tag: String, msg: String, tr: Throwable) {
        if (debug) Log.i(tag, msg, tr)
    }

    override fun wtf(tag: String, msg: String) {
        Log.wtf(tag, msg)
    }

    override fun wtf(tag: String, tr: Throwable) {
        Log.wtf(tag, tr)
    }

    override fun wtf(tag: String, msg: String, tr: Throwable) {
        Log.wtf(tag, msg, tr)
    }
}