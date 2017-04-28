package work.pegase.android.demo.tp6.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import work.pegase.android.demo.tp6.database.DemoDBHelper;

/**
 * Created by pereiraan on 4/21/17.
 */
public class MarkerPoint {
    /* Attribute of the Data */
    public int _id;
    public String title;
    public double latitude;
    public double longitude;
    public static class SQL {
        /* The Scheme */
        public static String CREATE_SCHEMA = "DROP TABLE IF EXISTS MarkerPoint; CREATE TABLE MarkerPoint (\n" +
                "\n" +
                " _id INTEGER PRIMARY KEY,\n" + "\n" +
                " title TEXT NOT NULL,\n" +
                "\n" +
                " latitude REAL NOT NULL,\n" + "\n" +
                " longitude REAL NOT NULL)\n" +
                "\n";
        /* Single TON Access */
        private static SQL sql;
        public static SQL getInstance(Context context) {
            if (sql == null)
                sql = new SQL(context);
            return sql; }
            private SQL(final Context context) {
            this.context = context; }
        private Context context;
        /*REQUETE POUR RECUPERER UNE LIGNE PAR RAPPORT AU TITRE*/
        public Cursor getMarkerPoint(String nameTitle) {

            Cursor res = DemoDBHelper.getInstance(context).rawQuery(
                    "SELECT MarketPoint._id,MarketPoint.title,MarketPoint.latitude,MarketPoint.longitude FROM MarkerPoint WHERE title = '" + nameTitle +
                            "' ORDER BY MarkerPoint.title ASC",null);

            String table = "MarkerPoint";
            String[] projection = new String[]{"MarkerPoint._id", "MarketPoint.title", "MarkerPoint.latitute", "MarketPoint.longitude"};
            String selection = null;
            String[] selectionArgs = null;
            String groupBy = null;
            String having = null;
            String orderBy = "MarkerPoint.title" ;
            String limit = null;
            return DemoDBHelper.getInstance(context).query(table,projection,selection,selectionArgs,groupBy,having,orderBy,limit);
        }


        public MarkerPoint fromCursor(final Cursor cursor) { MarkerPoint res = null;
            if (cursor == null || cursor.getPosition() <= -1 || cursor.getPosition() >= cursor.getCount() || cursor.getCount() == 0)
                throw new IllegalArgumentException("Your cursor is empty or has an invalid position. Have you move the position of the cursor ?");
            res = new MarkerPoint();
            res._id = cursor.getInt(0);


            return res; }
        public int delete(long id) {

            return DemoDBHelper.getInstance(context).delete("MarkerPoint","_id=?", new String []{"_id"});
         }

        public int update(MarkerPoint marker) {

            ContentValues values = new ContentValues();
            values.put("title", marker.title);
            values.put("latitude", marker.latitude);
            values.put("longitude", marker.longitude);

            return DemoDBHelper.getInstance(context).update("MarketPoint", values, "_id=?", new String[]{String.valueOf(marker._id)});
        }

        public long insert(MarkerPoint marker) {

            ContentValues values = new ContentValues();
            values.put("title",marker.title);
            values.put("latitude",marker.latitude);
            values.put("longitude",marker.longitude);

            return DemoDBHelper.getInstance(context).insert("MarketPoint", null,values);

        }
    } }