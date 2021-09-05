package sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import promotion.Promotion;

public class SQLPromotionUser extends SQLiteOpenHelper {
    static final String DB_NAME = "ListPromotionUser.db";
    static final String DB_TABLE_PROMOTION = "Promotion";
    static final int DB_VERSION = 1;
    private String PROMOTION_ID ="id";
    private String PROMOTION_TITLE = "title";
    private String PROMOTION_CONTENT = "content";
    private String PROMOTION_CODE = "code";
    private String PROMOTION_ICON ="icon";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;


    public SQLPromotionUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrateTable = "CREATE TABLE Promotion("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "code TEXT," +
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
    public void insertPromotion (Promotion promotion) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(PROMOTION_TITLE,promotion.getTitle());
        contentValues.put(PROMOTION_CONTENT,promotion.getContent());
        contentValues.put(PROMOTION_CODE,promotion.getCode());
        contentValues.put(PROMOTION_CODE,promotion.getIcon());
        sqLiteDatabase.insert(DB_TABLE_PROMOTION,null,contentValues);
    }
    public void updatePromotion(Promotion promotion) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(PROMOTION_TITLE,promotion.getTitle());
        contentValues.put(PROMOTION_CONTENT,promotion.getContent());
        contentValues.put(PROMOTION_CODE,promotion.getCode());
        contentValues.put(PROMOTION_CODE,promotion.getIcon());
        sqLiteDatabase.update(DB_TABLE_PROMOTION,contentValues,"id=?",
                new String[]{String.valueOf(promotion.getId())});
    }
    public void deletePromotion(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_PROMOTION,"id=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteAllPromotion() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_PROMOTION,null,null);
    }
    public List<Promotion> getAllPromotion() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Promotion> promotionList = new ArrayList<>();
        Promotion promotion;
        String query = "SELECT id, title, content, code, icon FROM " + DB_TABLE_PROMOTION;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PROMOTION_ID));
            String title = cursor.getString(cursor.getColumnIndex(PROMOTION_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(PROMOTION_CONTENT));
            String code = cursor.getString(cursor.getColumnIndex(PROMOTION_CODE));
            String icon = cursor.getString(cursor.getColumnIndex(PROMOTION_ICON));
            promotion = new Promotion(id, title, content, code, icon);
            promotionList.add(promotion);
        }
        closeDB();
        return promotionList;
    }
    private void closeDB() {
        if(contentValues != null) contentValues.clear();
        if(cursor != null) cursor.close();
        if(sqLiteDatabase!=null) sqLiteDatabase.close();
    }
}