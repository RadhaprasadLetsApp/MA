package com.letsappit.mineautomation.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.letsappit.mineautomation.Activity.Activity;
import com.letsappit.mineautomation.Common.ListItem;
import com.letsappit.mineautomation.Common.LocationType;
import com.letsappit.mineautomation.Machine.Machine;
import com.letsappit.mineautomation.MobileTagReader.MobileTagReader;
import com.letsappit.mineautomation.Ore.Ore;
import com.letsappit.mineautomation.Ore.OreType;
import com.letsappit.mineautomation.PrimaryLocation.PrimaryLocation;
import com.letsappit.mineautomation.Rate.Rate;
import com.letsappit.mineautomation.Route.Route;
import com.letsappit.mineautomation.SubZone.SubZone;
import com.letsappit.mineautomation.Truck.Truck;
import com.letsappit.mineautomation.User.User;
import com.letsappit.mineautomation.User.UserProfile;
import com.letsappit.mineautomation.Zone.Zone;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import utils.Util;

/**
 * Created by radhaprasadborkar on 02/11/15.
 */
public class DBOps {
//normalize julian date
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);

        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

//region PrimaryLocation

    public static long insertNewLocation(Context context,PrimaryLocation primaryLocation) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues loc_values = new ContentValues();
        loc_values.put(MAContract.PrimaryLocation.COLUMN_CODE, primaryLocation.getCode());
        loc_values.put(MAContract.PrimaryLocation.COLUMN_DESCRIPTION, primaryLocation.getDescription());
        loc_values.put(MAContract.PrimaryLocation.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.PrimaryLocation.TABLE_NAME, null, loc_values);


    }

    public static int updateLocation(Context context, PrimaryLocation primaryLocation) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAContract.PrimaryLocation.COLUMN_CODE, primaryLocation.getCode());
        values.put(MAContract.PrimaryLocation.COLUMN_DESCRIPTION, primaryLocation.getDescription());
        values.put(MAContract.PrimaryLocation.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        // updating row
        return db.update(MAContract.PrimaryLocation.TABLE_NAME, values, MAContract.PrimaryLocation._ID+ " = ?",
                new String[]{String.valueOf(primaryLocation.getId())});
    }

    public static ArrayList<PrimaryLocation> getAllLocations(Context context) throws ParseException {
        ArrayList<PrimaryLocation> allPrimaryLocations = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.PrimaryLocation.TABLE_NAME,
                new String[]{MAContract.PrimaryLocation.COLUMN_CODE, MAContract.PrimaryLocation.COLUMN_DESCRIPTION, MAContract.PrimaryLocation.COLUMN_UPDATED_ON,MAContract.PrimaryLocation._ID},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            allPrimaryLocations.add(new PrimaryLocation(cursor.getString(0),cursor.getString(1),Util.getDateFromString(cursor.getString(2)),cursor.getInt(3)));
        }
        return allPrimaryLocations;
    }

    public static PrimaryLocation getLocationByCode(Context context,String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.PrimaryLocation.TABLE_NAME,
                new String[]{MAContract.PrimaryLocation.COLUMN_CODE, MAContract.PrimaryLocation.COLUMN_DESCRIPTION, MAContract.PrimaryLocation.COLUMN_UPDATED_ON,MAContract.PrimaryLocation._ID},
                MAContract.PrimaryLocation.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new PrimaryLocation(cursor.getString(0),cursor.getString(1),Util.getDateFromString(cursor.getString(2)),cursor.getInt(3));
        else
            return new PrimaryLocation("","",new Date(),0);
    }

    //endregion
    //region Truck

    public static long insertNewTruck(Context context,Truck truck) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues truck_values = new ContentValues();
        truck_values.put(MAContract.Truck.COLUMN_CODE, truck.getCode());
        truck_values.put(MAContract.Truck.COLUMN_REGISTRATION_NUMBER, truck.getReg_no());
        truck_values.put(MAContract.Truck.COLUMN_TRANSPORT_CONTRACTOR_CODE, truck.getTransport_cont_code());
        truck_values.put(MAContract.Truck.COLUMN_DRIVER_CODE, truck.getDriverCode());
        truck_values.put(MAContract.Truck.COLUMN_GROUP_CODE, truck.getGroup_code());
        truck_values.put(MAContract.Truck.COLUMN_CARD_ID, truck.getCard_id());
        truck_values.put(MAContract.Truck.COLUMN_CATEGORY_CODE, truck.getCat_code());
        truck_values.put(MAContract.Truck.COLUMN_UPDATED_ON, Util.getFormatedDate(truck.getReg_date()));
        truck_values.put(MAContract.Truck.COLUMN_TARE_WEIGHT, truck.getTare_wt());
        truck_values.put(MAContract.Truck.COLUMN_CAPACITY, truck.getCapacity());
        return db.insert(MAContract.Truck.TABLE_NAME, null, truck_values);


    }

    public static ArrayList<String> getAllTruckRegNo(Context context) {
        ArrayList<String> allTruckRegNo = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Truck.TABLE_NAME,
                new String[]{MAContract.Truck.COLUMN_REGISTRATION_NUMBER},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            allTruckRegNo.add(cursor.getString(0));
        }
        return allTruckRegNo;
    }

    public static Truck getTruckByRegNo(Context context,String regNo) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Truck.TABLE_NAME,
                new String[]{
                        MAContract.Truck.COLUMN_CODE,
                        MAContract.Truck.COLUMN_REGISTRATION_NUMBER,
                        MAContract.Truck.COLUMN_TARE_WEIGHT,
                        MAContract.Truck.COLUMN_TRANSPORT_CONTRACTOR_CODE,
                        MAContract.Truck.COLUMN_DRIVER_CODE,
                        MAContract.Truck.COLUMN_CARD_ID,
                        MAContract.Truck.COLUMN_CAPACITY,
                        MAContract.Truck.COLUMN_CATEGORY_CODE,
                        MAContract.Truck.COLUMN_GROUP_CODE,
                        MAContract.Truck.COLUMN_UPDATED_ON},
                MAContract.Truck.COLUMN_REGISTRATION_NUMBER + " = ?", new String[]{regNo}, null, null, null);

        if(cursor.moveToFirst())
            return new Truck(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(8),
                    cursor.getString(5),
                    cursor.getString(7),
                    Util.getDateFromString(cursor.getString(9)),
                    cursor.getInt(2),
                    cursor.getInt(6));
        else
            return null;
    }

    //endregion
    //region Machine

    public static long insertMachine(Context context, Machine machine) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues machine_values = new ContentValues();
        machine_values.put(MAContract.Machine.COLUMN_CODE, machine.getCode());
        machine_values.put(MAContract.Machine.COLUMN_REG_NUMBER, machine.getRegNo());
        machine_values.put(MAContract.Machine.COLUMN_CONTRACTOR, machine.getContractor());
        machine_values.put(MAContract.Machine.COLUMN_CATEGORY_CODE, machine.getCategory());
        machine_values.put(MAContract.Machine.COLUMN_DESCRIPTION, machine.getDescription());
        machine_values.put(MAContract.Machine.COLUMN_CARD_ID, machine.getCardId());
        machine_values.put(MAContract.Machine.COLUMN_UPDATED_ON, Util.getFormatedDate(machine.getRegDate()));
        return db.insert(MAContract.Machine.TABLE_NAME, null, machine_values);
    }

    public static ArrayList<String> getAllMachines(Context context) {
        ArrayList<String> machineList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Machine.TABLE_NAME,
                new String[]{MAContract.Machine.COLUMN_CODE+" "+ MAContract.Machine.COLUMN_REG_NUMBER+"["+MAContract.Machine.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            machineList.add(cursor.getString(0));
        }
        return machineList;
    }

    public static Machine getMachineByRegNo(Context context, String regNo) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Machine.TABLE_NAME,
                new String[]{
                        MAContract.Machine.COLUMN_CODE,
                        MAContract.Machine.COLUMN_REG_NUMBER,
                        MAContract.Machine.COLUMN_DESCRIPTION,
                        MAContract.Machine.COLUMN_CONTRACTOR,
                        MAContract.Machine.COLUMN_CARD_ID,
                        MAContract.Machine.COLUMN_CATEGORY_CODE,
                        MAContract.Machine.COLUMN_UPDATED_ON},
                MAContract.Machine.COLUMN_REG_NUMBER + " = ?", new String[]{regNo}, null, null, null);

        if(cursor.moveToFirst())
            return new Machine(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(3),
                    Util.getDateFromString(cursor.getString(6)));
        else
            return null;
    }

    public static Machine getMachineByCode(Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Machine.TABLE_NAME,
                new String[]{
                        MAContract.Machine.COLUMN_CODE,
                        MAContract.Machine.COLUMN_REG_NUMBER,
                        MAContract.Machine.COLUMN_DESCRIPTION,
                        MAContract.Machine.COLUMN_CONTRACTOR,
                        MAContract.Machine.COLUMN_CARD_ID,
                        MAContract.Machine.COLUMN_CATEGORY_CODE,
                        MAContract.Machine.COLUMN_UPDATED_ON},
                MAContract.Machine.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Machine(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(3),
                    Util.getDateFromString(cursor.getString(6)));
        else
            return null;
    }

    //endregion

    //region Route

    public static long insertRoute(Context context, Route route) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues route_values = new ContentValues();
        route_values.put(MAContract.Route.COLUMN_CODE, route.getCode());
        route_values.put(MAContract.Route.COLUMN_DESCRIPTION, route.getDescription());
        route_values.put(MAContract.Route.COLUMN_RATE_CODE, route.getRateCode());
        route_values.put(MAContract.Route.COLUMN_DISTANCE, route.getDistance());
        route_values.put(MAContract.Route.COLUMN_START_PRIMARY_LOCATION_CODE, route.getStartPrimaryLocationCode());
        route_values.put(MAContract.Route.COLUMN_START_SECONDARY_LOCATION_CODE, route.getStartSecondaryLocationCode());
        route_values.put(MAContract.Route.COLUMN_START_SECONDARY_LOCATION_TYPE, route.getStartSecondaryLocationType().toString());
        route_values.put(MAContract.Route.COLUMN_END_SECONDARY_LOCATION_CODE, route.getEndPrimaryLocationCode());
        route_values.put(MAContract.Route.COLUMN_END_SECONDARY_LOCATION_CODE, route.getEndSecondaryLocationCode());
        route_values.put(MAContract.Route.COLUMN_END_SECONDARY_LOCATION_TYPE, route.getEndSecondaryLocationType().toString());
        route_values.put(MAContract.Route.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Route.TABLE_NAME, null, route_values);
    }

    public static ArrayList<String> getAllRoutes(Context context) {
        ArrayList<String> routeList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Route.TABLE_NAME,
                new String[]{MAContract.Route.COLUMN_CODE+" "+ MAContract.Route.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            routeList.add(cursor.getString(0));
        }
        return routeList;
    }

    public static Route getRouteByCode(Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Route.TABLE_NAME,
                new String[]{
                        MAContract.Route.COLUMN_CODE,
                        MAContract.Route.COLUMN_DESCRIPTION,
                        MAContract.Route.COLUMN_DISTANCE,
                        MAContract.Route.COLUMN_RATE_CODE,
                        MAContract.Route.COLUMN_START_PRIMARY_LOCATION_CODE,
                        MAContract.Route.COLUMN_START_SECONDARY_LOCATION_CODE,
                        MAContract.Route.COLUMN_START_SECONDARY_LOCATION_TYPE,
                        MAContract.Route.COLUMN_END_PRIMARY_LOCATION_CODE,
                        MAContract.Route.COLUMN_END_SECONDARY_LOCATION_CODE,
                        MAContract.Route.COLUMN_END_SECONDARY_LOCATION_TYPE,
                        MAContract.Route.COLUMN_UPDATED_ON},
                MAContract.Route.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Route(
                    cursor.getString(0),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    LocationType.valueOf(cursor.getString(6)),
                    cursor.getString(7),
                    cursor.getString(8),
                    LocationType.valueOf(cursor.getString(9)),
                    Util.getDateFromString(cursor.getString(10)));
        else
            return null;
    }

    //endregion

    //region Rate

    public static long insertRate(Context context, Rate rate) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues rate_values = new ContentValues();
        rate_values.put(MAContract.Rate.COLUMN_CODE, rate.getCode());
        rate_values.put(MAContract.Rate.COLUMN_DESCRIPTION, rate.getDescription());
        rate_values.put(MAContract.Rate.COLUMN_DISTANCE_FROM, rate.getDistanceFrom());
        rate_values.put(MAContract.Rate.COLUMN_DISTANCE_TO, rate.getDistanceTo());
        rate_values.put(MAContract.Rate.COLUMN_RATE_PER_KM, rate.getRatePerKm());
        rate_values.put(MAContract.Rate.COLUMN_FUEL_ELIGIBILITY, rate.getFuelEligibility());
        rate_values.put(MAContract.Route.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Route.TABLE_NAME, null, rate_values);
    }

    public static ArrayList<String> getAllRates(Context context) {
        ArrayList<String> rateList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Rate.TABLE_NAME,
                new String[]{MAContract.Rate.COLUMN_CODE+" "+ MAContract.Rate.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            rateList.add(cursor.getString(0));
        }
        return rateList;
    }

    public static Rate getRateByCode(Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Rate.TABLE_NAME,
                new String[]{
                        MAContract.Rate.COLUMN_CODE,
                        MAContract.Rate.COLUMN_DESCRIPTION,
                        MAContract.Rate.COLUMN_DISTANCE_FROM,
                        MAContract.Rate.COLUMN_DISTANCE_TO,
                        MAContract.Rate.COLUMN_RATE_PER_KM,
                        MAContract.Rate.COLUMN_FUEL_ELIGIBILITY,
                        MAContract.Route.COLUMN_UPDATED_ON},
                MAContract.Route.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Rate(
                    cursor.getString(0),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Float.parseFloat(cursor.getString(4)),
                    Float.parseFloat(cursor.getString(5)),
                    Util.getDateFromString(cursor.getString(6)));
        else
            return null;
    }

    //endregion

    //region Activity

    public static long insertActivity(Context context, Activity activity) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues activity_values = new ContentValues();
        activity_values.put(MAContract.Activity.COLUMN_CODE, activity.getCode());
        activity_values.put(MAContract.Activity.COLUMN_CATEGORY_CODE, activity.getCategory());
        activity_values.put(MAContract.Activity.COLUMN_DESCRIPTION, activity.getDescription());
        activity_values.put(MAContract.Activity.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Activity.TABLE_NAME, null,  activity_values);
    }

    public static ArrayList<String> getAllActivities(Context context) {
        ArrayList<String> activityList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Activity.TABLE_NAME,
                new String[]{MAContract.Activity.COLUMN_CODE+" ["+MAContract.Activity.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            activityList.add(cursor.getString(0));
        }
        return activityList;
    }

    public static Activity getActivityByCode (Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Activity.TABLE_NAME,
                new String[]{
                        MAContract.Activity.COLUMN_CODE,
                        MAContract.Activity.COLUMN_DESCRIPTION,
                        MAContract.Activity.COLUMN_CATEGORY_CODE,
                        MAContract.Activity.COLUMN_UPDATED_ON},
                MAContract.Activity.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Activity(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    Util.getDateFromString(cursor.getString(3)));
        else
            return null;
    }

    //endregion

    //region Ore

    public static long insertOre(Context context,Ore ore) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues ore_values = new ContentValues();
        ore_values.put(MAContract.Ore.COLUMN_CODE, ore.getCode());
        ore_values.put(MAContract.Ore.COLUMN_DESCRIPTION, ore.getDescription());
        ore_values.put(MAContract.Ore.COLUMN_CATEGORY_CODE, ore.getCategoryCode().toString());
        ore_values.put(MAContract.Ore.COLUMN_GRADE, ore.getGrade());
        ore_values.put(MAContract.Ore.COLUMN_TYPE, ore.getOreType().toString());
        ore_values.put(MAContract.Ore.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Ore.TABLE_NAME, null, ore_values);
    }

    public static ArrayList<String> getAllOres(Context context) {
        ArrayList<String> oreList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Ore.TABLE_NAME,
                new String[]{MAContract.Ore.COLUMN_CODE,MAContract.Ore.COLUMN_DESCRIPTION
                },
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            oreList.add(cursor.getString(0)+" ["+cursor.getString(1)+"]");
        }
        return oreList;
    }

    public static Ore getOreByCode(Context context,String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Ore.TABLE_NAME,
                new String[]{
                        MAContract.Ore.COLUMN_CODE,
                        MAContract.Ore.COLUMN_DESCRIPTION,
                        MAContract.Ore.COLUMN_CATEGORY_CODE,
                        MAContract.Ore.COLUMN_GRADE,
                        MAContract.Ore.COLUMN_TYPE,
                        MAContract.Ore.COLUMN_UPDATED_ON},
                MAContract.Ore.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Ore(
                    cursor.getString(0),
                    cursor.getString(1),
                    Util.getDateFromString(cursor.getString(5)),
                    OreType.valueOf(cursor.getString(4)),
                    cursor.getString(2),
                    cursor.getString(3));
        else
            return null;
    }

    //endregion

    //region User

    public static long insertUser(Context context, User user) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues user_values = new ContentValues();
        user_values.put(MAContract.User.COLUMN_CODE, user.getCode());
        user_values.put(MAContract.User.COLUMN_FIRST_NAME, user.getFirstName());
        user_values.put(MAContract.User.COLUMN_LAST_NAME, user.getLastName());
        user_values.put(MAContract.User.COLUMN_ADDRESS, user.getAddress());
        user_values.put(MAContract.User.COLUMN_EMAIL_ID, user.getEmailID());
        user_values.put(MAContract.User.COLUMN_MOBILE_NUMBER, user.getMobileNumber());
        user_values.put(MAContract.User.COLUMN_PRIMARY_LOCATION_CODE, user.getPrimaryLocationCode());
        user_values.put(MAContract.User.COLUMN_CARD_ID, user.getCardID());
        user_values.put(MAContract.User.COLUMN_LOGIN_NAME, user.getLoginName());
        user_values.put(MAContract.User.COLUMN_LOGIN_PASSWORD, user.getLoginPassword());
        user_values.put(MAContract.User.COLUMN_CATEGORY_CODE, user.getUserProfile().toString());
        user_values.put(MAContract.User.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.User.TABLE_NAME, null, user_values);
    }

    public static ArrayList<String> getAllUsers(Context context) {
        ArrayList<String> userList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.User.TABLE_NAME,
                new String[]{MAContract.User.COLUMN_CODE,MAContract.User.COLUMN_FIRST_NAME,MAContract.User.COLUMN_LAST_NAME
                },
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            userList.add(cursor.getString(0)+" ["+cursor.getString(1)+" "+cursor.getString(2)+"]");
        }
        return userList;
    }

    public static User getUserByCode(Context context,String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.User.TABLE_NAME,
                new String[]{
                        MAContract.User.COLUMN_CODE,
                        MAContract.User.COLUMN_FIRST_NAME,
                        MAContract.User.COLUMN_LAST_NAME,
                        MAContract.User.COLUMN_ADDRESS,
                        MAContract.User.COLUMN_EMAIL_ID,
                        MAContract.User.COLUMN_MOBILE_NUMBER,
                        MAContract.User.COLUMN_PRIMARY_LOCATION_CODE,
                        MAContract.User.COLUMN_CARD_ID,
                        MAContract.User.COLUMN_LOGIN_NAME,
                        MAContract.User.COLUMN_LOGIN_PASSWORD,
                        MAContract.User.COLUMN_CATEGORY_CODE,
                        MAContract.User.COLUMN_UPDATED_ON},
                MAContract.User.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(5)),
                    cursor.getString(4),
                    Util.getDateFromString(cursor.getString(11)),
                    cursor.getString(8),
                    cursor.getString(9),
                    UserProfile.valueOf(cursor.getString(10)),
                    cursor.getString(7),
                    cursor.getString(6));
        else
            return null;
    }

    //endregion

    //region Zone

    public static long insertZone(Context context, Zone zone) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues zone_values = new ContentValues();
        zone_values.put(MAContract.Zone.COLUMN_CODE, zone.getCode());
        zone_values.put(MAContract.Zone.COLUMN_PRIMARY_LOCATION, zone.getPrimaryLocationCode());
        zone_values.put(MAContract.Zone.COLUMN_DESCRIPTION, zone.getDescription());
        zone_values.put(MAContract.Zone.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Zone.TABLE_NAME, null,  zone_values);
    }
    public static int updateZone(Context context, Zone zone) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAContract.Zone.COLUMN_CODE, zone.getCode());
        values.put(MAContract.Zone.COLUMN_DESCRIPTION, zone.getDescription());
        values.put(MAContract.Zone.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        values.put(MAContract.Zone.COLUMN_PRIMARY_LOCATION, zone.getPrimaryLocationCode());
        // updating row
        return db.update(MAContract.Zone.TABLE_NAME, values, MAContract.Zone._ID + " = ?",
                new String[]{String.valueOf(zone.getId())});
    }

    public static ArrayList<String> getAllZones(Context context) {
        ArrayList<String> zoneList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Zone.TABLE_NAME,
                new String[]{MAContract.Zone.COLUMN_CODE+" ["+MAContract.Zone.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            zoneList.add(cursor.getString(0));
        }
        return zoneList;
    }

    public static Zone getZoneByCode (Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Zone.TABLE_NAME,
                new String[]{

                        MAContract.Zone.COLUMN_CODE,
                        MAContract.Zone.COLUMN_DESCRIPTION,
                        MAContract.Zone.COLUMN_PRIMARY_LOCATION,
                        MAContract.Zone.COLUMN_UPDATED_ON,
                        MAContract.Zone._ID,

                },
                MAContract.Zone.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new Zone(cursor.getString(0),
                    cursor.getString(1),
                    Util.getDateFromString(cursor.getString(3)),
                            cursor.getInt(4),
                            cursor.getString(2));
        else
            return null;
    }



    //endregion

    //region SubZone

    public static long insertSubZone(Context context, SubZone subZone) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues subZone_values = new ContentValues();
        subZone_values.put(MAContract.SubZone.COLUMN_CODE, subZone.getCode());
        subZone_values.put(MAContract.SubZone.COLUMN_ZONE, subZone.getZoneCode());
        subZone_values.put(MAContract.SubZone.COLUMN_DESCRIPTION, subZone.getDescription());
        subZone_values.put(MAContract.SubZone.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.SubZone.TABLE_NAME, null,  subZone_values);
    }

    public static ArrayList<String> getAllSubZones(Context context) {
        ArrayList<String> subZoneList = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.SubZone.TABLE_NAME,
                new String[]{MAContract.SubZone.COLUMN_CODE+" ["+MAContract.SubZone.COLUMN_DESCRIPTION +"]"},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            subZoneList.add(cursor.getString(0));
        }
        return subZoneList;
    }

    public static SubZone getSubZoneByCode (Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.Zone.TABLE_NAME,
                new String[]{
                        MAContract.SubZone.COLUMN_CODE,
                        MAContract.SubZone.COLUMN_DESCRIPTION,
                        MAContract.SubZone.COLUMN_ZONE,
                        MAContract.SubZone.COLUMN_UPDATED_ON,
                        MAContract.SubZone._ID},
                MAContract.SubZone.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new SubZone(
                    cursor.getString(0),
                    cursor.getString(1),
                    Util.getDateFromString(cursor.getString(3)),
                    cursor.getInt(3),
                    cursor.getString(2));
        else
            return null;
    }

    //endregion

    //region MobileTagReader

    public static long insertMobileTagReader(Context context, MobileTagReader mobileTagReader) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues mobileTagReader_values = new ContentValues();
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_CODE, mobileTagReader.getCode());
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_DESCRIPTION, mobileTagReader.getDescription());
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_APPLICATION_TOKEN, mobileTagReader.getCode());
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_IMEI_NUMBER, mobileTagReader.getDescription());
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_PRIMARY_LOCATION_CODE, mobileTagReader.getPrimaryLocationCode());
        mobileTagReader_values.put(MAContract.MobileTagReader.COLUMN_UPDATED_ON, Util.getFormatedCurrentDate());
        return db.insert(MAContract.Zone.TABLE_NAME, null,  mobileTagReader_values);
    }

    public static MobileTagReader getMobileTagReaderByCode (Context context, String code) throws ParseException {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MAContract.MobileTagReader.TABLE_NAME,
                new String[]{
                        MAContract.MobileTagReader.COLUMN_CODE,
                        MAContract.MobileTagReader.COLUMN_DESCRIPTION,
                        MAContract.MobileTagReader.COLUMN_UPDATED_ON,
                        MAContract.MobileTagReader.COLUMN_APPLICATION_TOKEN,
                        MAContract.MobileTagReader.COLUMN_IMEI_NUMBER,
                        MAContract.MobileTagReader.COLUMN_PRIMARY_LOCATION_CODE},
                MAContract.MobileTagReader.COLUMN_CODE + " = ?", new String[]{code}, null, null, null);

        if(cursor.moveToFirst())
            return new MobileTagReader(
                    cursor.getString(0),
                    cursor.getString(1),
                    Util.getDateFromString(cursor.getString(2)),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(3)
            );
        else
            return null;
    }

    //endregion

    //region Common Methods for all tables

    public static ArrayList<ListItem> getAllRows(Context context,String tableName,String code,String description)
    {
        ArrayList<ListItem> allRows = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{code,description},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            allRows.add(new ListItem(cursor.getString(0),cursor.getString(1)));
        }
        return allRows;
    }
    public static ArrayList<String> getAllRecords(Context context,String tableName,String code,String description)
    {
        ArrayList<String> allRows = new ArrayList<>();
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{code,description},
                null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            allRows.add((cursor.getString(0)+" "+cursor.getString(1)));
        }
        return allRows;
    }

    public static int deleteRow(Context context, String code,String tableName,String codeColumnName) {
        MADbHelper dbHelper = new MADbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int i = db.delete(tableName, codeColumnName + " = ?",
                new String[]{code});
        db.close();
        return i;
    }
    //end region
}
