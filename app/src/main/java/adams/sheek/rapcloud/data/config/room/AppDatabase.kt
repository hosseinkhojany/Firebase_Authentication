package adams.sheek.rapcloud.data.config.room

import adams.sheek.rapcloud.data.datasource.local.LocalRapDataSource
import adams.sheek.rapcloud.data.model.Rap
import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [
    Rap::class

                     ], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): LocalRapDataSource
}