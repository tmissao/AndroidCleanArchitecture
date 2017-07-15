package br.com.missao.cleanarchitecture.exceptions

import java.io.IOException

/**
 * Exception indicating that there is not internet connection
 */
class NoConnectivityException : IOException() {

    override val message: String?
        get() = "No Connectivity Exception"
}