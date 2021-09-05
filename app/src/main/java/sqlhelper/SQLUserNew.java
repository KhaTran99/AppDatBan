package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class SQLUserNew extends SQLiteOpenHelper {
    static final String DB_NAME = "Usernew.db";
    static  final String DB_TABLE_USER = "User";
    static final int DB_VERSION = 1;
    private String USER_ID = "id";
    private String USER_USERNAME = "username";
    private String USER_PASSWORD = "password";
    private String USER_PHONENUMBER = "phonenumber";
    private String USER_EMAIL = "email";
    private String USER_AVATAR = "avatar";
    private String USER_SCORES = "scores";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;



    public SQLUserNew(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable= "CREATE TABLE User(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT," +
                "phonenumber TEXT," +
                "email TEXT," +
                "avatar TEXT," +
                "scores TEXT)";
        db.execSQL(queryCrateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ DB_NAME);
            onCreate(db);
        }
    }
    public void insertUser(User user) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(USER_USERNAME,user.getUsername());
        contentValues.put(USER_PASSWORD,user.getPassword());
        contentValues.put(USER_PHONENUMBER,user.getPhoneNumber());
        contentValues.put(USER_EMAIL,user.getEmail());
        contentValues.put(USER_AVATAR,user.getAvatar());
        contentValues.put(USER_SCORES,user.getScores());
        sqLiteDatabase.insert(DB_TABLE_USER,null,contentValues);
    }
    public void updateUser(User user) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(USER_USERNAME,user.getUsername());
        contentValues.put(USER_PASSWORD,user.getPassword());
        contentValues.put(USER_PHONENUMBER,user.getPhoneNumber());
        contentValues.put(USER_EMAIL,user.getEmail());
        contentValues.put(USER_AVATAR,user.getAvatar());
        contentValues.put(USER_SCORES,user.getScores());
        sqLiteDatabase.update(DB_TABLE_USER,contentValues,"id=?",
                new String[]{String.valueOf(user.getId())});
    }
    public void deleteUser(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_USER,"id=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteAllUser() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_USER,null,null);
    }
    public List<User> getAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> userList = new ArrayList<>();
        User user;
        String query = "SELECT id, username, password, phonenumber, email, avatar, scores FROM "+DB_TABLE_USER;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
            String username = cursor.getString(cursor.getColumnIndex(USER_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String phonenumber = cursor.getString(cursor.getColumnIndex(USER_PHONENUMBER));
            String email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
            String avatar = cursor.getString(cursor.getColumnIndex(USER_AVATAR));
            String scores = cursor.getString(cursor.getColumnIndex(USER_SCORES));
            user = new User(id,username,password,phonenumber,email,avatar,scores);
            userList.add(user);
        }
        closeDB();
        return userList;

    }
    public User getUser(int ID) {
        User user = null;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT id, username, password, phonenumber, email, avatar, scores FROM "+DB_TABLE_USER+" where id = ? ";
        Cursor cursor = db.rawQuery(query,new String[]{ID + ""});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
            String username = cursor.getString(cursor.getColumnIndex(USER_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String phonenumber = cursor.getString(cursor.getColumnIndex(USER_PHONENUMBER));
            String email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
            String avatar = cursor.getString(cursor.getColumnIndex(USER_AVATAR));
            String scores = cursor.getString(cursor.getColumnIndex(USER_SCORES));
            user = new User(id,username,password,phonenumber,email,avatar,scores);
        }
        closeDB();
        return user;

    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }

}