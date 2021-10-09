package adams.sheek.rapcloud.data.datasource.local

import adams.sheek.rapcloud.data.model.Rap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LocalRapDataSource {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRapList(rapList: List<Rap>)

}