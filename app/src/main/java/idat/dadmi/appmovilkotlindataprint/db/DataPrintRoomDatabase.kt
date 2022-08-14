package idat.dadmi.appmovilkotlindataprint.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import idat.dadmi.appmovilkotlindataprint.db.dao.UsuarioDao
import idat.dadmi.appmovilkotlindataprint.db.entity.UsuarioEntity
import idat.dadmi.appmovilkotlindataprint.retrofit.DataPrintCliente

@Database(entities = [UsuarioEntity::class], version = 1)
public abstract class DataPrintRoomDatabase:RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDao

    companion object{
        @Volatile
        private var INSTANCE: DataPrintRoomDatabase?= null

        fun getDatabase(context: Context) : DataPrintRoomDatabase{
            val tempInstance= INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataPrintRoomDatabase::class.java,
                    "dataprintdb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}