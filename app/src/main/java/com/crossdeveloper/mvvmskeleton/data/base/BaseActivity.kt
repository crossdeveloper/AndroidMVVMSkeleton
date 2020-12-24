package com.crossdeveloper.mvvmskeleton.data.base

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseActivity : AppCompatActivity() {

    private var hud: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun showHUD(): Boolean {
        if (hud == null) {
            try {
                hud = KProgressHUD.create(this@BaseActivity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show()
            } catch (ex: Exception) {
                Log.d("HUD", "Trying to show HUD for dead activity")
            }
        }

        return true
    }

    fun dismissHUD() {
        if (hud != null){
            hud?.dismiss()
            hud = null
        }
    }
}