package br.com.shop.login

import android.content.Context
import android.content.Intent
import br.com.shop.login.view.LoginActivity
import kotlin.properties.Delegates

class ShoploginInitializer private constructor(builder: Builder) {

    init {
        LoginModuleSession.dependencies = builder.loginModuleDependencies
        LoginModuleSession.theme = builder.theme
        startModuleActivity(builder.context)
    }

    private fun startModuleActivity(context: Context, ) {
        context.startActivity(
            Intent(context, LoginActivity::class.java)
        )
    }

    class Builder {
        internal var context: Context by Delegates.notNull()
        internal var theme: Int by Delegates.notNull()
        internal var loginModuleDependencies: LoginModuleDependencies by Delegates.notNull()

        fun setContext(context: Context) = apply {
            this.context = context
        }

        fun setTheme(theme: Int) = apply {
            this.theme = theme
        }

        fun setLoginModuleDependencies(loginModuleDependencies: LoginModuleDependencies) = apply {
            this.loginModuleDependencies = loginModuleDependencies
        }

        fun build() {
            ShoploginInitializer(this)
        }
    }
}
