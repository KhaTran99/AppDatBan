package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import news.News;

public class SQLNews extends SQLiteOpenHelper {
    static final String DB_NAME = "ListNews.db";
    static  final String DB_TABLE_NEWS = "News";
    static final int DB_VERSION = 1;
    private String NEWS_ID = "id";
    private String NEWS_TITLE = "title";
    private String NEWS_CONTENT = "content";
    private String NEWS_ICON = "icon";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLNews( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE News("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "icon TEXT)";
        db.execSQL(queryCrateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ DB_NAME);
            onCreate(db);
        }

    }
    public void insertNews (News news) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(NEWS_TITLE,news.getTitle());
        contentValues.put(NEWS_CONTENT,news.getContent());
        contentValues.put(NEWS_ICON,news.getIcon());

        sqLiteDatabase.insert(DB_TABLE_NEWS,null,contentValues);
    }
    public void deleteAllMenu() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NEWS,null,null);
    }
    public List<News> getAllNews() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<News> newsList = new ArrayList<>();
        News news;
        String query = "SELECT id, title, content, icon FROM "+ DB_TABLE_NEWS;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(NEWS_ID));
            String title = cursor.getString(cursor.getColumnIndex(NEWS_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(NEWS_CONTENT));
            String icon = cursor.getString(cursor.getColumnIndex(NEWS_ICON));
            news = new News(id,title,content,icon);
            newsList.add(news);
        }
        closeDB();
        return newsList;
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }

}

