package smarthouse.smartronic2;

/**
 * Created by burak on 19/03/15.
 */

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "database";

    private static final String TABLE_NAME = "users";
    private static String ID = "id";
    private static String CUSTOMER_ID = "customer_id";
    private static String PASSWORD = "password";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id TEXT, password TEXT)";
        /*String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CUSTOMER_ID + " TEXT,"
                + PASSWORD + " TEXT,"
                + ")";*/
        System.out.println("DATABASE");
        db.execSQL(CREATE_TABLE);
    }

    public void deleteUser(int id) {

        /*SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();*/
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_TABLE = "SELECT * FROM users";
        db.execSQL(CREATE_TABLE);
        Cursor cursor = db.rawQuery(CREATE_TABLE, null);
        System.out.println(cursor);
    }

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CUSTOMER_ID, username);
        values.put(PASSWORD, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public HashMap<String, String> getUsers(String username, String password) {

        HashMap<String, String> user = new HashMap<>();
        System.out.println(username + password);
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE customer_id=" + username;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(ID, cursor.getString(1));
            user.put(CUSTOMER_ID, cursor.getString(2));
            user.put(PASSWORD, cursor.getString(3));
        }
        cursor.close();
        db.close();
        return user;
    }

    /*public ArrayList<HashMap<String, String>> listUsers() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                userList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }*/

    public void updateUser(int id, String customerId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(CUSTOMER_ID, customerId);
        values.put(PASSWORD, password);

        db.update(TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }


    public void resetTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);

    }
}
