package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import notification.Notification;

public class SQLNotification extends SQLiteOpenHelper {
    static final String DB_NAME = "ListNotification.db";
    static final String DB_TABLE_NOTIFICATION = "Notification";
    static final int DB_VERSION = 1;
    private String NOTIFICATION_ID = "id";
    private String NOTIFICATION_TITLE = "title";
    private String NOTIFICATION_CONTENT = "content";
    private String NOTIFICATION_ICON = "icon";
    private String NOTIFICATION_STATUS = "status";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLNotification(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE Notification(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "icon TEXT," +
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
    public void insertNotification (Notification notification) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(NOTIFICATION_TITLE,notification.getTitle());
        contentValues.put(NOTIFICATION_CONTENT,notification.getContent());
        contentValues.put(NOTIFICATION_ICON,notification.getIcon());
        contentValues.put(NOTIFICATION_STATUS,notification.getStatus());
        sqLiteDatabase.insert(DB_TABLE_NOTIFICATION,null,contentValues);
    }
    public void updateNOtification(Notification notification) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(NOTIFICATION_TITLE,notification.getTitle());
        contentValues.put(NOTIFICATION_CONTENT,notification.getContent());
        contentValues.put(NOTIFICATION_ICON,notification.getIcon());
        contentValues.put(NOTIFICATION_STATUS,notification.getStatus());
        sqLiteDatabase.update(DB_TABLE_NOTIFICATION,contentValues,"id=?",
                new String[] {String.valueOf(notification.getId())});
    }
    public void deleteNOtification(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NOTIFICATION,"id=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteAllNotification() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NOTIFICATION,null,null);
    }
    public List<Notification> getAllNotification() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Notification> notificationList = new ArrayList<>();
        Notification notification;
        String query = "SELECT id, title, content, icon, status FROM "+ DB_TABLE_NOTIFICATION;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(NOTIFICATION_ID));
            String title = cursor.getString(cursor.getColumnIndex(NOTIFICATION_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(NOTIFICATION_CONTENT));
            String icon = cursor.getString(cursor.getColumnIndex(NOTIFICATION_ICON));
            int status = cursor.getInt(cursor.getColumnIndex(NOTIFICATION_STATUS));
            notification = new Notification(id,title,content,icon,status);
            notificationList.add(notification);
        }
        closeDB();
        return notificationList;
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }

}
