package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import menu.Menu;
import order.Oder;

public class SQLListOder extends SQLiteOpenHelper {
    static final String DB_NAME = "ListOder.db";
    static final String DB_TABLE_ODER = "ListOder";
    static final int DB_VERSION = 1;
    private String ODER_ID = "id";
    private String ODER_LOCATION = "location";
    private String ODER_AMOUNT = "amount";
    private String ODER_DAY = "day";
    private String ODER_NOTE = "note";
    private String ODER_HOURS = "hours";
    private String ODER_TOTAL = "total";
    private String ODER_STATUS = "status";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;



    public SQLListOder( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE ListOder(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "location TEXT," +
                "amount TEXT," +
                "day TEXT," +
                "hours TEXT," +
                "total TEXT," +
                "note TEXT," +
                "status TEXT)";
        db.execSQL(queryCrateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
        }
    }
    public void insertListOder(Oder oder) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(ODER_LOCATION, oder.getLocation());
        contentValues.put(ODER_AMOUNT, oder.getAmount());
        contentValues.put(ODER_DAY, oder.getDay());
        contentValues.put(ODER_HOURS, oder.getHours());
        contentValues.put(ODER_TOTAL, oder.getTotalBill());
        contentValues.put(ODER_NOTE, oder.getNote());
        contentValues.put(ODER_STATUS, oder.getStatus());
        sqLiteDatabase.insert(DB_TABLE_ODER, null, contentValues);
    }

    public List<Oder> getAllListOder() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Oder> oderList = new ArrayList<>();
        Oder oder;
        String query = "SELECT id, location, amount, day, hours, total, note, status FROM "+DB_TABLE_ODER;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ODER_ID));
            String location = cursor.getString(cursor.getColumnIndex(ODER_LOCATION));
            int amount = cursor.getInt(cursor.getColumnIndex(ODER_AMOUNT));
            String day = cursor.getString(cursor.getColumnIndex(ODER_DAY));
            String housr = cursor.getString(cursor.getColumnIndex(ODER_HOURS));
            int total = cursor.getInt(cursor.getColumnIndex(ODER_TOTAL));
            String note = cursor.getString(cursor.getColumnIndex(ODER_NOTE));
            int status = cursor.getInt(cursor.getColumnIndex(ODER_STATUS));
            oder = new Oder(id,location,amount,day,housr,total,note,status);
            oderList.add(oder);

        }
        closeDB();
        return oderList;
    }
    public void deleteAllOder() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_ODER,null,null);
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }
}
