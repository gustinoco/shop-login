package br.com.shop.login

import kotlin.properties.Delegates

object LoginModuleSession {

    var dependencies: LoginModuleDependencies by Delegates.notNull()
    var theme: Int by Delegates.notNull()

}