package work.pegase.android.demo.tp6.database;

import android.content.Context;
import android.database.sqlite.*;

import work.pegase.android.demo.tp6.database.model.MarkerPoint;

/**
 * Created by pereiraan on 4/21/17.
 */
public class DemoDBHelper extends SQLiteOpenHelper {
    private DemoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        for (String sql : MarkerPoint.SQL.CREATE_SCHEMA.split(";")) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



        db.execSQL(MarkerPoint.SQL.CREATE_SCHEMA);

    }

    /* Signle TON Access to the DATABASE */
    private static SQLiteDatabase db = null;

    public static SQLiteDatabase getInstance(Context context) {
        if (db == null) {
/* Create instance */
            DemoDBHelper demo = new
                    DemoDBHelper(context, "demo_db", null, 1);
            db = demo.getWritableDatabase();
        }
        return db;
    }
}