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

public class SQLMenu extends SQLiteOpenHelper {
    static final String DB_NAME = "ListMenu.db";
    static final String DB_TABLE_MENU = "Menu";
    static final int DB_VESION = 1;
    private String MENU_ID = "id";
    private String MENU_NAME = "name";
    private String MENU_CONTENT = "content";
    private String MENU_ICON = "icon";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLMenu(Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE Menu(" +
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

    public void insertMenu(Menu menu) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(MENU_NAME, menu.getName());
        contentValues.put(MENU_CONTENT, menu.getContent());
        contentValues.put(MENU_ICON, menu.getIcon());
        sqLiteDatabase.insert(DB_TABLE_MENU, null, contentValues);
    }

    public void updateMenu(Menu menu) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(MENU_NAME, menu.getName());
        contentValues.put(MENU_CONTENT, menu.getContent());
        contentValues.put(MENU_ICON, menu.getIcon());
        sqLiteDatabase.update(DB_TABLE_MENU, contentValues, "id=?",
                new String[]{String.valueOf(menu.getId())});
    }

    public void deleteMenu(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_MENU, "id=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteAllMenu() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_MENU,null,null);
    }
    public List<Menu> getAllMenu() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Menu> menuList = new ArrayList<>();
        Menu menu;
        String query = "SELECT id, name, content, icon FROM "+ DB_TABLE_MENU;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MENU_ID));
            String name = cursor.getString(cursor.getColumnIndex(MENU_NAME));
            String content = cursor.getString(cursor.getColumnIndex(MENU_CONTENT));
            String icon = cursor.getString(cursor.getColumnIndex(MENU_ICON));
            menu = new Menu(id,name,content,icon);
            menuList.add(menu);
        }
        closeDB();
        return menuList;
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }
}
