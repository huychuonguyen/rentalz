package com.example.rentalz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentalz.model.Event;

import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDatabaseHelper";

    private static final String TABLE_NAME = "apartment_table";
    private static final String COLUMN_EVENT_ID = "ID";
    private static final String COLUMN_ACTIVITY_NAME = "activity_name";
    private static final String COLUMN_LOCATION = "location"; // it means Type
    private static final String COLUMN_EVENT_DATE = "event_date";
    private static final String COLUMN_ATTENDING_TIME = "attending_time";
    private static final String COLUMN_REPORTER_NAME = "reporter_name";
    private static final String COLUMN_FURNITURE_TYPE = "furniture_type";
    private static final String COLUMN_MONTHLY_RENT_PRICE = "monthly_rent_price";
    private static final String COLUMN_NOTE = "notes";
    private static final String COLUMN_BEDROOM = "bedroom";
    private static final String COLUMN_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME +
                "(" +
                COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ACTIVITY_NAME + " TEXT," +
                COLUMN_LOCATION + " TEXT," +
                COLUMN_EVENT_DATE + " TEXT," +
                COLUMN_ATTENDING_TIME + " TEXT," +
                COLUMN_REPORTER_NAME + " TEXT," +
                COLUMN_FURNITURE_TYPE + " TEXT," +
                COLUMN_MONTHLY_RENT_PRICE + " TEXT," +
                COLUMN_NOTE + " TEXT," +
                COLUMN_BEDROOM + " TEXT," +
                COLUMN_ADDRESS + " TEXT" +
                ")";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ACTIVITY_NAME, event.getActivityName());
        contentValues.put(COLUMN_LOCATION, event.getLocation());
        contentValues.put(COLUMN_EVENT_DATE, event.getEventDate());
        contentValues.put(COLUMN_ATTENDING_TIME, event.getAttendingTime());
        contentValues.put(COLUMN_REPORTER_NAME, event.getReporterName());

        contentValues.put(COLUMN_FURNITURE_TYPE, event.getFurnitureType());
        contentValues.put(COLUMN_MONTHLY_RENT_PRICE, event.getMonthlyRentPrice());
        contentValues.put(COLUMN_NOTE, event.getNotes());
        contentValues.put(COLUMN_BEDROOM, event.getBedroom());
        contentValues.put(COLUMN_ADDRESS, event.getAddress());

        long results = db.insert(TABLE_NAME, null, contentValues);

        return results != -1;
    }

    public Boolean updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ACTIVITY_NAME, event.getActivityName());
        contentValues.put(COLUMN_LOCATION, event.getLocation());
        contentValues.put(COLUMN_EVENT_DATE, event.getEventDate());
        contentValues.put(COLUMN_ATTENDING_TIME, event.getAttendingTime());
        contentValues.put(COLUMN_REPORTER_NAME, event.getReporterName());

        contentValues.put(COLUMN_FURNITURE_TYPE, event.getFurnitureType());
        contentValues.put(COLUMN_MONTHLY_RENT_PRICE, event.getMonthlyRentPrice());
        contentValues.put(COLUMN_NOTE, event.getNotes());
        contentValues.put(COLUMN_BEDROOM, event.getBedroom());
        contentValues.put(COLUMN_ADDRESS, event.getAddress());

        int results = db.update(TABLE_NAME, contentValues, COLUMN_EVENT_ID + "=" + event.getEventId(), null);

        return results != -1;
    }

    public Boolean deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, COLUMN_EVENT_ID + "=" + event.getEventId(), null) > 0;
    }

    public List<Event> getEventsByName(String eventName) {
        List<Event> returnList = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_EVENT_ID,
                        COLUMN_ACTIVITY_NAME,
                        COLUMN_LOCATION,
                        COLUMN_EVENT_DATE,
                        COLUMN_ATTENDING_TIME,
                        COLUMN_REPORTER_NAME,
                        COLUMN_FURNITURE_TYPE,
                        COLUMN_MONTHLY_RENT_PRICE,
                        COLUMN_NOTE,
                        COLUMN_BEDROOM,
                        COLUMN_ADDRESS
                },
                COLUMN_ACTIVITY_NAME + " LIKE ?", new String[]{"%" + eventName + "%"},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int eventId = cursor.getInt(0);
                String activityName = cursor.getString(1);
                String location = cursor.getString(2);
                String eventDate = cursor.getString(3);
                String attendingTime = cursor.getString(4);
                String reporterName = cursor.getString(5);
                String furnitureType = cursor.getString(6);
                String monthlyRentPrice = cursor.getString(7);
                String notes = cursor.getString(8);
                String bedroom = cursor.getString(9);
                String address = cursor.getString(10);

                Event event = new Event(
                        eventId,
                        activityName,
                        location,
                        eventDate,
                        reporterName,
                        furnitureType,
                        monthlyRentPrice,
                        notes,
                        bedroom,
                        address
                );

                returnList.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }

    public List<Event> getAllEvents() {
        List<Event> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int eventId = cursor.getInt(0);
                String activityName = cursor.getString(1);
                String location = cursor.getString(2);
                String eventDate = cursor.getString(3);
                String attendingTime = cursor.getString(4);
                String reporterName = cursor.getString(5);
                String furnitureType = cursor.getString(6);
                String monthlyRentPrice = cursor.getString(7);
                String notes = cursor.getString(8);
                String bedroom = cursor.getString(9);
                String address = cursor.getString(10);

                Event event = new Event(
                        eventId,
                        activityName,
                        location,
                        eventDate,
                        reporterName,
                        furnitureType,
                        monthlyRentPrice,
                        notes,
                        bedroom,
                        address
                );

                returnList.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }
}
