package adams.sheek.rapcloud

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
    }


    
    companion object {
        lateinit var instance: App
            private set
        fun context(): Context = instance.applicationContext
    }

}