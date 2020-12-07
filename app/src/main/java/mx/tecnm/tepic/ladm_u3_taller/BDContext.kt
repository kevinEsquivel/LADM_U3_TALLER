package mx.tecnm.tepic.ladm_u3_taller

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.prefs.PreferencesFactory

@RequiresApi(Build.VERSION_CODES.P)
class BDContext (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
): SQLiteOpenHelper(context, name, factory,version ){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("Create table propietario(IDP INTEGER not null primary key,nombre varchar(200),celular varchar(50),domicilio varchar(200))")
        db.execSQL("Create table coche(IDC INTEGER not null primary key,placa varchar(20),marca varchar(100),modelo varchar(100),IDP INTEGER,CONSTRAINT fk_persona FOREIGN KEY(IDP) REFERENCES persona(IDP))")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}