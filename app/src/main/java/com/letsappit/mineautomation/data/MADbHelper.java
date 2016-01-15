/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.letsappit.mineautomation.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages a local database for weather data.
 */
public class MADbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    Boolean loaded = false;
    static final String DATABASE_NAME = "VMSB.db";
    Context context;

    public MADbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        //region Token
        final String SQL_CREATE_TOKEN_TABLE = "CREATE TABLE " + MAContract.Token.APP_TABLE + " (" +
                 MAContract.Token._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                MAContract.Token.COLUMN_APP_ID + " INTEGER NOT NULL," +
                // the ID of the location entry associated with this weather data
                MAContract.Token.COLUMN_APP_TOKEN + " TEXT NOT NULL " +

                " );";
        //endregion

        //region Primary Location
        //this is the query for creating the table
        //this will be executed only once
        final String SQL_CREATE_PRIMARY_LOCATION_TABLE = "CREATE TABLE " + MAContract.PrimaryLocation.TABLE_NAME + " (" +

                MAContract.PrimaryLocation._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.PrimaryLocation.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.PrimaryLocation.COLUMN_DESCRIPTION + " TEXT," +
                MAContract.PrimaryLocation.COLUMN_UPDATED_ON + " TEXT, " +
                " UNIQUE (" + MAContract.PrimaryLocation.COLUMN_CODE +  ") ON CONFLICT REPLACE);";
        //endregion

        //region Zone
        final String SQL_CREATE_ZONE_TABLE = "CREATE TABLE " + MAContract.Zone.TABLE_NAME + " (" +

                MAContract.Zone._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Zone.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.Zone.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.Zone.COLUMN_PRIMARY_LOCATION + " TEXT ," +
                MAContract.Zone.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.Zone.COLUMN_PRIMARY_LOCATION + ") REFERENCES " +
                MAContract.PrimaryLocation.TABLE_NAME + " (" + MAContract.PrimaryLocation.COLUMN_CODE + ") " +
                ");";
        //endregion

        //region Sub Zone
        final String SQL_CREATE_SUB_ZONE_TABLE = "CREATE TABLE " + MAContract.SubZone.TABLE_NAME + " (" +

                MAContract.SubZone._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.SubZone.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.SubZone.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.SubZone.COLUMN_ZONE + " TEXT ," +
                MAContract.SubZone.COLUMN_UPDATED_ON + " TEXT ," +
                // Set up the location column as a foreign key to location table.
                " FOREIGN KEY (" + MAContract.SubZone.COLUMN_ZONE + ") REFERENCES " +
                MAContract.Zone.TABLE_NAME + " (" + MAContract.Zone.COLUMN_CODE + ") " +
                ");";
        //endregion

        //region Weighbridge
        final String SQL_CREATE_WEIGHBRIDGE_TABLE = "CREATE TABLE " + MAContract.Weighbridge.TABLE_NAME + " (" +

                MAContract.Weighbridge._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Weighbridge.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.Weighbridge.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.Weighbridge.COLUMN_PRIMARY_LOCATION_CODE + " TEXT ," +
                MAContract.Weighbridge.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.Weighbridge.COLUMN_PRIMARY_LOCATION_CODE + ") REFERENCES " +
                MAContract.PrimaryLocation.TABLE_NAME + " (" + MAContract.PrimaryLocation.COLUMN_CODE + ") " +
                " );";
        //endregion
// region category

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + MAContract.Category.TABLE_NAME + " (" +

                MAContract.Category._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Category.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.Category.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.Category.COLUMN_TYPE + " TEXT ," +
                MAContract.Category.COLUMN_UPDATED_ON + " TEXT " +
                " );";
        //end region


        //region Group


            final String SQL_CREATE_GROUP_TABLE = "CREATE TABLE " + MAContract.Group.TABLE_NAME + " (" +

                    MAContract.Group._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MAContract.Group.COLUMN_CODE + " TEXT NOT NULL, " +
                    MAContract.Group.COLUMN_DESCRIPTION + " TEXT ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_SUNDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_MONDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_TUESDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_WEDNESDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_THURSDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_FRIDAY + " BLOB ," +
                    MAContract.Group.COLUMN_PRIMARY_LOCATIONS_SATURDAY + " BLOB ," +

                    MAContract.Group.COLUMN_UPDATED_ON + " TEXT " +
                    " );";

        //end region
        //region Truck
        final String SQL_CREATE_TRUCK_TABLE = "CREATE TABLE " + MAContract.Truck.TABLE_NAME + " (" +
                MAContract.Truck._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Truck.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Truck.COLUMN_REGISTRATION_NUMBER + " TEXT UNIQUE NOT NULL," +
                MAContract.Truck.COLUMN_TARE_WEIGHT + " INT ," +
                MAContract.Truck.COLUMN_DRIVER_CODE + " TEXT , " +
                MAContract.Truck.COLUMN_CARD_ID + " TEXT ," +
                MAContract.Truck.COLUMN_CAPACITY+ " INT ," +
                MAContract.Truck.COLUMN_CATEGORY_CODE + " TEXT ," +
                MAContract.Truck.COLUMN_GROUP_CODE + " TEXT ," +
                MAContract.Truck.COLUMN_UPDATED_ON + " TEXT ," +
                MAContract.Truck.COLUMN_TRANSPORT_CONTRACTOR_CODE + " TEXT ," +
                " FOREIGN KEY (" + MAContract.Truck.COLUMN_DRIVER_CODE + ") REFERENCES " +
                MAContract.Driver.TABLE_NAME + " (" + MAContract.Driver.COLUMN_CODE + ") ," +
                " FOREIGN KEY (" + MAContract.Truck.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") ," +
                " FOREIGN KEY (" + MAContract.Truck.COLUMN_GROUP_CODE + ") REFERENCES " +
                MAContract.Group.TABLE_NAME + " (" + MAContract.Group.COLUMN_CODE + ") ," +
                " FOREIGN KEY (" + MAContract.Truck.COLUMN_TRANSPORT_CONTRACTOR_CODE + ") REFERENCES " +
                MAContract.TransportContractor.TABLE_NAME + " (" + MAContract.TransportContractor.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region User
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + MAContract.User.TABLE_NAME + " (" +
                MAContract.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.User.COLUMN_CODE + " TEXT UNIQUE NOT NULL," +
                MAContract.User.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                MAContract.User.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                MAContract.User.COLUMN_ADDRESS + " TEXT , " +
                MAContract.User.COLUMN_EMAIL_ID + " TEXT ," +
                MAContract.User.COLUMN_MOBILE_NUMBER+ " INT ," +
                MAContract.User.COLUMN_LOGIN_NAME + " TEXT UNIQUE NOT NULL, " +
                MAContract.User.COLUMN_LOGIN_PASSWORD + " TEXT UNIQUE NOT NULL," +
                MAContract.User.COLUMN_CATEGORY_CODE + " TEXT ," +
                MAContract.User.COLUMN_CARD_ID + " TEXT ," +
                MAContract.User.COLUMN_PRIMARY_LOCATION_CODE + " TEXT ," +
                MAContract.User.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.User.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") ," +
                " FOREIGN KEY (" + MAContract.User.COLUMN_PRIMARY_LOCATION_CODE + ") REFERENCES " +
                MAContract.PrimaryLocation.TABLE_NAME + " (" + MAContract.PrimaryLocation.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region Driver
        final String SQL_CREATE_DRIVER_TABLE = "CREATE TABLE " + MAContract.Driver.TABLE_NAME + " (" +
                MAContract.Driver._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Driver.COLUMN_CODE + " TEXT UNIQUE NOT NULL," +
                MAContract.Driver.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                MAContract.Driver.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                MAContract.Driver.COLUMN_ADDRESS + " TEXT , " +
                MAContract.Driver.COLUMN_EMAIL_ID + " TEXT ," +
                MAContract.Driver.COLUMN_MOBILE_NUMBER+ " INT ," +
                MAContract.Driver.COLUMN_CATEGORY_CODE + " TEXT ," +
                MAContract.Driver.COLUMN_CARD_ID + " TEXT ," +
                MAContract.Driver.COLUMN_BLOOD_GROUP + " TEXT ," +
                MAContract.Driver.COLUMN_TRUCK_CODE + " TEXT ," +
                MAContract.Driver.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.Driver.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region Material Contractor
        final String SQL_CREATE_MATERIAL_CONTRACTOR_TABLE = "CREATE TABLE " + MAContract.MaterialContractor.TABLE_NAME + " (" +
                MAContract.MaterialContractor._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.MaterialContractor.COLUMN_CODE + " TEXT UNIQUE NOT NULL," +
                MAContract.MaterialContractor.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                MAContract.MaterialContractor.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                MAContract.MaterialContractor.COLUMN_ADDRESS + " TEXT , " +
                MAContract.MaterialContractor.COLUMN_EMAIL_ID + " TEXT ," +
                MAContract.MaterialContractor.COLUMN_MOBILE_NUMBER+ " INT ," +
                MAContract.MaterialContractor.COLUMN_CATEGORY_CODE + " TEXT ," +
                MAContract.MaterialContractor.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.MaterialContractor.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region Transport Contractor
        final String SQL_CREATE_TRANSPORT_CONTRACTOR_TABLE = "CREATE TABLE " + MAContract.TransportContractor.TABLE_NAME + " (" +
                MAContract.TransportContractor._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.TransportContractor.COLUMN_CODE + " TEXT UNIQUE NOT NULL," +
                MAContract.TransportContractor.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                MAContract.TransportContractor.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                MAContract.TransportContractor.COLUMN_ADDRESS + " TEXT , " +
                MAContract.TransportContractor.COLUMN_EMAIL_ID + " TEXT ," +
                MAContract.TransportContractor.COLUMN_MOBILE_NUMBER+ " INT ," +
                MAContract.TransportContractor.COLUMN_CATEGORY_CODE + " TEXT ," +
                MAContract.TransportContractor.COLUMN_TRUCK_CODE + " TEXT ," +
                MAContract.TransportContractor.COLUMN_UPDATED_ON + " TEXT ," +
                " FOREIGN KEY (" + MAContract.TransportContractor.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region MobileTagReader
        final String SQL_CREATE_MOBILE_TAG_READER_TABLE = "CREATE TABLE " + MAContract.MobileTagReader.TABLE_NAME + " (" +
                MAContract.MobileTagReader._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.MobileTagReader.COLUMN_CODE + " TEXT UNIQUE NOT NULL," +
                MAContract.MobileTagReader.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                MAContract.MobileTagReader.COLUMN_APPLICATION_TOKEN + " TEXT NOT NULL, " +
                MAContract.MobileTagReader.COLUMN_IMEI_NUMBER + " TEXT NOT NULL, " +
                MAContract.MobileTagReader.COLUMN_PRIMARY_LOCATION_CODE + " TEXT ," +
                MAContract.MobileTagReader.COLUMN_UPDATED_ON + " TEXT " +
                " FOREIGN KEY (" + MAContract.User.COLUMN_PRIMARY_LOCATION_CODE + ") REFERENCES " +
                MAContract.PrimaryLocation.TABLE_NAME + " (" + MAContract.PrimaryLocation.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region Rate
        final String SQL_CREATE_RATE_TABLE = "CREATE TABLE " + MAContract.Rate.TABLE_NAME + " (" +
                MAContract.Rate._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Rate.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Rate.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.Rate.COLUMN_DISTANCE_FROM + " INT ," +
                MAContract.Rate.COLUMN_DISTANCE_TO + " INT , " +
                MAContract.Rate.COLUMN_RATE_PER_KM + " FLOAT ," +
                MAContract.Rate.COLUMN_FUEL_ELIGIBILITY+ " FLOAT ," +
                MAContract.Rate.COLUMN_UPDATED_ON+ " TEXT " +
                " );";
        //endregion

        //region Route
        final String SQL_CREATE_ROUTE_TABLE = "CREATE TABLE " + MAContract.Route.TABLE_NAME + " (" +
                MAContract.Route._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Route.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Route.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                MAContract.Route.COLUMN_RATE_CODE + " INT NOT NUll," +
                MAContract.Route.COLUMN_DISTANCE + " INT NOT NULL, " +
                MAContract.Route.COLUMN_START_PRIMARY_LOCATION_CODE + " TEXT NOT NULL," +
                MAContract.Route.COLUMN_START_SECONDARY_LOCATION_CODE+ " TEXT ," +
                MAContract.Route.COLUMN_START_SECONDARY_LOCATION_TYPE + " TEXT ," +
                MAContract.Route.COLUMN_END_PRIMARY_LOCATION_CODE+ " TEXT NOT NULL," +
                MAContract.Route.COLUMN_END_SECONDARY_LOCATION_CODE + " TEXT ," +
                MAContract.Route.COLUMN_END_SECONDARY_LOCATION_TYPE+ " TEXT ," +
                MAContract.Route.COLUMN_UPDATED_ON+ " TEXT " +
                " FOREIGN KEY (" + MAContract.Route.COLUMN_RATE_CODE + ") REFERENCES " +
                MAContract.Rate.TABLE_NAME + " (" + MAContract.Rate.COLUMN_CODE + ") " +
                " );";
        //endregion

        //region Permit
        final String SQL_CREATE_PERMIT_TABLE = "CREATE TABLE " + MAContract.Permit.TABLE_NAME + " (" +
                MAContract.Permit._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Permit.COLUMN_CODE + " TEXT NOT NULL, " +
                MAContract.Permit.COLUMN_DESCRIPTION + " TEXT ," +
                MAContract.Permit.COLUMN_PERMIT_NUMBER + " TEXT ," +
                MAContract.Permit.COLUMN_TYPE + " TEXT ," +
                MAContract.Permit.COLUMN_UPDATED_ON + " TEXT " +
                " );";
        //endregion

        //region Ore
        final String SQL_CREATE_ORE_TABLE = "CREATE TABLE " + MAContract.Ore.TABLE_NAME + " (" +

                MAContract.Ore._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Ore.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Ore.COLUMN_DESCRIPTION + " TEXT," +
                MAContract.Ore.COLUMN_UPDATED_ON + " TEXT, " +
                MAContract.Ore.COLUMN_GRADE + " TEXT," +
                MAContract.Ore.COLUMN_TYPE + " TEXT," +
                MAContract.Ore.COLUMN_CATEGORY_CODE + " TEXT, "+
                " FOREIGN KEY (" + MAContract.Ore.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                ");";
        //endregion

        //region Machine
        final String SQL_CREATE_MACHINE_TABLE = "CREATE TABLE " + MAContract.Machine.TABLE_NAME + " (" +

                MAContract.Machine._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Machine.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Machine.COLUMN_DESCRIPTION + " TEXT," +
                MAContract.Machine.COLUMN_UPDATED_ON + " TEXT, " +
                MAContract.Machine.COLUMN_CARD_ID + " TEXT," +
                MAContract.Machine.COLUMN_CONTRACTOR + " TEXT," +
                MAContract.Machine.COLUMN_REG_NUMBER + " TEXT, " +
                MAContract.Machine.COLUMN_CATEGORY_CODE + " TEXT, " +
                " FOREIGN KEY (" + MAContract.Machine.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                ");";
        //endregion

        //region Activity
        final String SQL_CREATE_ACTIVITY_TABLE = "CREATE TABLE " + MAContract.Activity.TABLE_NAME + " (" +

                MAContract.Activity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAContract.Activity.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                MAContract.Activity.COLUMN_DESCRIPTION + " TEXT," +
                MAContract.Activity.COLUMN_UPDATED_ON + " TEXT, " +
                MAContract.Activity.COLUMN_CATEGORY_CODE + " TEXT, " +
                " FOREIGN KEY (" + MAContract.Activity.COLUMN_CATEGORY_CODE + ") REFERENCES " +
                MAContract.Category.TABLE_NAME + " (" + MAContract.Category.COLUMN_CODE + ") " +
                ");";
        //endregion

        //region Create Tables
        //execute the queries to create the table
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PRIMARY_LOCATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ZONE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TOKEN_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_WEIGHBRIDGE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PERMIT_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_DRIVER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRANSPORT_CONTRACTOR_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_GROUP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRUCK_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_ORE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MACHINE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ACTIVITY_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);


        sqLiteDatabase.execSQL(SQL_CREATE_MATERIAL_CONTRACTOR_TABLE);
        //endregion
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Token.APP_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Permit.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.PrimaryLocation.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Zone.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Weighbridge.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Truck.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Ore.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Machine.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Activity.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.Rate.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAContract.User.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

}
