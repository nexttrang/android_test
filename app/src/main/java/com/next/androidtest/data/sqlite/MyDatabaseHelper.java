package com.next.androidtest.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.next.androidtest.data.model.Visitor;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Visitor_Manager";

    // Table name: Note.
    private static final String TABLE_VISITOR = "visitor";

    private static final String COLUMN_VISITOR_ID ="visitor_id";
    private static final String COLUMN_TIMESTAMP ="timestamp";
    private static final String COLUMN_VISITOR_NAME ="visitor_name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_STATUS = "status";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_VISITOR + "("
                + COLUMN_VISITOR_ID + " INTEGER PRIMARY KEY," + COLUMN_TIMESTAMP + " TEXT,"
                + COLUMN_VISITOR_NAME + " TEXT," + COLUMN_TYPE + " TEXT," + COLUMN_STATUS + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITOR);

        // Create tables again
        onCreate(db);
    }


    // If table has no data
    // default, Insert 4 records.
    public void createDefaultNotesIfNeed()  {
        int count = this.getVisitorsCount();
        if(count ==0 ) {
            Visitor visitor1 = new Visitor("13:15","Mr. MikeTaler","Visitor","REG");
            Visitor visitor2 = new Visitor("13:01","Mr. Peter Lava","STC","IN");
            Visitor visitor3 = new Visitor("13:30","Mrs. Sara Corner","STC-Group","IN");
            Visitor visitor4 = new Visitor("14:30","Mr. Johny Depth","LTC","OUT");

            this.addVisitor(visitor1);
            this.addVisitor(visitor2);
            this.addVisitor(visitor3);
            this.addVisitor(visitor4);
        }
    }


    public void addVisitor(Visitor visitor) {
        Log.i(TAG, "MyDatabaseHelper.addVisitor ... " + visitor.getVisitorName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMESTAMP, visitor.getTimeStamp());
        values.put(COLUMN_VISITOR_NAME, visitor.getVisitorName());
        values.put(COLUMN_TYPE, visitor.getType());
        values.put(COLUMN_STATUS, visitor.getStatus());

        // Inserting Row
        db.insert(TABLE_VISITOR, null, values);

        // Closing database connection
        db.close();
    }


    public Visitor getVisitor(int id) {
        Log.i(TAG, "MyDatabaseHelper.getVisitor ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VISITOR, new String[] { COLUMN_VISITOR_ID,
                        COLUMN_TIMESTAMP, COLUMN_VISITOR_NAME,COLUMN_TYPE,COLUMN_STATUS }, COLUMN_VISITOR_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Visitor visitor = new Visitor(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));

        // return visitor
        return visitor;
    }

    public int getVisitorsCount(String status) {
        Log.i(TAG, "MyDatabaseHelper.getVisitor ... " + status);

        List<Visitor> visitorList = new ArrayList<Visitor>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VISITOR, new String[] { COLUMN_VISITOR_ID,
                        COLUMN_TIMESTAMP, COLUMN_VISITOR_NAME,COLUMN_TYPE,COLUMN_STATUS }, COLUMN_STATUS + "=?",
                new String[] { String.valueOf(status) }, null, null, null, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }


    public List<Visitor> getAllVisitors() {
        Log.i(TAG, "MyDatabaseHelper.getAllVisitors ... " );

        List<Visitor> visitorList = new ArrayList<Visitor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VISITOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Visitor visitor = new Visitor(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));

                visitorList.add(visitor);
            } while (cursor.moveToNext());
        }

        // return note list
        return visitorList;
    }

    public int getVisitorsCount() {
        Log.i(TAG, "MyDatabaseHelper.getVisitorsCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_VISITOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateVisitor(Visitor visitor) {
        Log.i(TAG, "MyDatabaseHelper.updateVisitor ... "  + visitor.getVisitorName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMESTAMP, visitor.getTimeStamp());
        values.put(COLUMN_VISITOR_NAME, visitor.getVisitorName());
        values.put(COLUMN_TYPE, visitor.getType());
        values.put(COLUMN_STATUS, visitor.getStatus());

        // updating row
        return db.update(TABLE_VISITOR, values, COLUMN_VISITOR_ID + " = ?",
                new String[]{String.valueOf(visitor.getId())});
    }

    public void deleteVisitor(Visitor visitor) {
        Log.i(TAG, "MyDatabaseHelper.deleteVisitor ... " + visitor.getVisitorName() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VISITOR, COLUMN_VISITOR_ID + " = ?",
                new String[] { String.valueOf(visitor.getId()) });
        db.close();
    }
}
