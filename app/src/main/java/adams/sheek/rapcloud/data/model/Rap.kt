package adams.sheek.rapcloud.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Rap(
    @field:Json(name = "id") @PrimaryKey val id: Int,
) : Parcelable