package adams.sheek.rapcloud.ui.login

import adams.sheek.rapcloud.R
import adams.sheek.rapcloud.ui.widgets.progressbutton.DrawableButton
import adams.sheek.rapcloud.ui.widgets.progressbutton.hideProgress
import adams.sheek.rapcloud.ui.widgets.progressbutton.showProgress
import android.graphics.Color
import android.widget.Button
import androidx.databinding.BindingAdapter

object binding {

    @JvmStatic
    @BindingAdapter("loadingGo")
    fun loadingGo(view: Button, isLoading: Boolean) {
        if (isLoading){
            view.showProgress {
                progressColor = Color.WHITE
                gravity = DrawableButton.GRAVITY_CENTER
            }
        }else{
            view.hideProgress(view.context.getString(R.string.go_label))
        }
    }

    @JvmStatic
    @BindingAdapter("loadingSignWithGoogle")
    fun loadingSignWithGoogle(view: Button, isLoading: Boolean) {
        if (isLoading){
            view.showProgress {
                progressColor = Color.WHITE
                gravity = DrawableButton.GRAVITY_CENTER
            }
        }else{
            view.hideProgress(view.context.getString(R.string.fast_login_with_google))
        }
    }

}