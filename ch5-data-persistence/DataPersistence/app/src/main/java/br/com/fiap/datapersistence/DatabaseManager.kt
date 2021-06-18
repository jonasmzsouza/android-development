package br.com.fiap.datapersistence

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context, name: String?) : SQLiteOpenHelper(context,name,null,1)  {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE GREETING(" +
                    "	ID_GREETING INT NOT NULL," +
                    "	NAME VARCHAR(20)," +
                    "	TREATMENT VARCHAR(20)," +
                    "	PRIMARY KEY (ID_GREETING)" +
                    "	);"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS GREETING")
        p0?.execSQL("CREATE TABLE GREETING(" +
                "	ID_GREETING INT NOT NULL," +
                "	NAME VARCHAR(20)," +
                "	TREATMENT VARCHAR(20)," +
                "	PRIMARY KEY (ID_GREETING)" +
                "	);")
    }

    fun insertGreeting(id: Int, name: String, treatment: String){
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put("ID_GREETING",id)
        cv.put("NAME",name)
        cv.put("TREATMENT",treatment)
        db.insert("GREETING","ID_GREETING",cv)
    }
    fun listGreeting(): Cursor {
        var db = this.readableDatabase
        var cur = db.rawQuery("select name,treatment from greeting",null)
        return cur
    }
    fun removeGreeting(){
        var db = this.writableDatabase
        db.delete("GREETING","ID_GREETING=1",null)
    }
}