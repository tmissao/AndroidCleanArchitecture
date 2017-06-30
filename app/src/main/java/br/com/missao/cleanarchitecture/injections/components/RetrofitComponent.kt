package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.injections.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Retrofit Client Component
 */
@Singleton
@Component(modules = arrayOf(RetrofitModule::class))
interface RetrofitComponent {
}