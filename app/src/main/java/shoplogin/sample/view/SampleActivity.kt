package shoplogin.sample.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.shop.commons.ShopAnalytics
import br.com.shop.commons.ShopCache
import br.com.shop.commons.ShopDesignSystem
import br.com.shop.commons.ShopNavigate
import br.com.shop.commons.ShopNetwork
import br.com.shop.login.LoginModuleDependencies
import br.com.shop.login.ShoploginInitializer
import br.com.shop.login.repository.LoginResponse
import shoplogin.sample.R
import shoplogin.sample.databinding.ActivitySampleBinding
import java.io.Serializable

class SampleActivity : AppCompatActivity(), ShopAnalytics, ShopDesignSystem, ShopNetwork, ShopNavigate, ShopCache {

    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startModule()
    }

    private fun startModule() {
        val dependencies = LoginModuleDependencies(
            this,
            this,
            this,
            this,
            this
        )

        binding.btnGoToModule.setOnClickListener {
            ShoploginInitializer.Builder()
                .setContext(this)
                .setTheme(0)
                .setLoginModuleDependencies(dependencies)
                .build()
        }
    }

    override fun trackAction(screenName: String, action: String) {
        Log.d(screenName, action)
    }

    override fun trackScreen(screenName: String) {
        Log.d(screenName, screenName)
    }

    override fun primaryColor(): Int = R.color.design_default_color_error

    override fun secondaryColor(): Int = R.color.cardview_shadow_start_color

    override suspend fun getNetwork(url: String, responseClass: Any): Any {
        return Any()
    }

    override suspend fun postNetwork(url: String, params: Any, responseClass: Any): Any {
        return LoginResponse("123456789", "test", "10/10")
    }

    override fun navigate(context: Context, route: String, params: HashMap<String, Serializable>) {
        Toast.makeText(context, "Navigate to $route", Toast.LENGTH_SHORT).show()
    }

    override fun deleteCache(key: String) {
        // nothing
    }

    override fun getCache(key: String, cacheClass: Any): Any? {
        return null
    }

    override fun putCache(key: String, data: Any) {
        // nothing
    }
}