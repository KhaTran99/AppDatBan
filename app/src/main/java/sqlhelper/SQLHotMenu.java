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

public class SQLHotMenu extends SQLiteOpenHelper {
    static final String DB_NAME = "ListHotMenu.db";
    static final String DB_TABLE_HOTMENU = "HotMenu";
    static final int DB_VERSION = 1;
    private String HOTMENU_ID = "id";
    private String HOTMENU_NAME = "name";
    private String HOTMENU_CONTENT = "content";
    private String HOTMENU_ICON = "icon";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHotMenu(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE HotMenu(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "content TEXT," +
                "icon TEXT)";
        db.execSQL(queryCrateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
        }
    }

    public void insertHotMenu(Menu menu) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(HOTMENU_NAME, menu.getName());
        contentValues.put(HOTMENU_CONTENT, menu.getContent());
        contentValues.put(HOTMENU_ICON, menu.getIcon());
        sqLiteDatabase.insert(DB_TABLE_HOTMENU, null, contentValues);
    }

    public void updateHotMenu(Menu menu) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(HOTMENU_NAME, menu.getName());
        contentValues.put(HOTMENU_CONTENT, menu.getContent());
        contentValues.put(HOTMENU_ICON, menu.getIcon());
        sqLiteDatabase.update(DB_TABLE_HOTMENU, contentValues, "id=?",
                new String[]{String.valueOf(menu.getId())});
    }

    public void deleteHotMenu(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_HOTMENU, "id=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteAllHotMenu() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_HOTMENU,null,null);
    }
    public List<Menu> getAllHotMenu() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Menu> hotMenuList = new ArrayList<>();
        Menu menu;
        String query = "SELECT id, name, content, icon FROM "+ DB_TABLE_HOTMENU;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(HOTMENU_ID));
            String name = cursor.getString(cursor.getColumnIndex(HOTMENU_NAME));
            String content = cursor.getString(cursor.getColumnIndex(HOTMENU_CONTENT));
            String icon = cursor.getString(cursor.getColumnIndex(HOTMENU_ICON));
            menu = new Menu(id,name,content,icon);
            hotMenuList.add(menu);
        }
        closeDB();
        return hotMenuList;
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }
}
