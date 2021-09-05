package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import location.Location;

public class SQLLocation extends SQLiteOpenHelper {
    static final String DB_NAME =" ListLocation.db";
    static final String DB_TABLE_LOCATION = "Location";
     static final int DB_VERSION = 1;
    private String LOCATION_ID = "id";
    private String LOCATION_NAME = "name";
    private String LOCATION_ADDRESS = "address";
    private String LOCATION_ICON = "icon";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;
    public SQLLocation( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE Location("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "address TEXT," +
                "icon TEXT)";
        db.execSQL(queryCrateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
        }

    }
    public void insertLocation(Location location ) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(LOCATION_NAME,location.getName());
        contentValues.put(LOCATION_ADDRESS,location.getAddress());
        contentValues.put(LOCATION_ICON,location.getIcon());
        sqLiteDatabase.insert(DB_TABLE_LOCATION,null,contentValues);
    }
    public void deleteAllLocation() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_LOCATION,null,null);
    }
    public List<Location> getAllLocation() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Location> locationList = new ArrayList<>();
        Location location;
        String query = "SELECT id, name, address, icon FROM " + DB_TABLE_LOCATION;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LOCATION_ID));
            String name = cursor.getString(cursor.getColumnIndex(LOCATION_NAME));
            String address = cursor.getString(cursor.getColumnIndex(LOCATION_ADDRESS));
            String icon = cursor.getString(cursor.getColumnIndex(LOCATION_ICON));
            location = new Location(id,name,address,icon);
            locationList.add(location);
        }
        closeDB();
        return locationList;

    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }

}
