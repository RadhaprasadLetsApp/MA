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

import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database.
 */
public class MAContract {
    // definr a new class for each table you need to create
    // the class should estend BaseColumns which adds a predefined column _id to this table
//this is how a single table is defined along with the table name and atributes
    // Every table is defined as a class so that it becomes easy to refer clumn names and table names from the instance of this class
    //this makes the code more robust

//create a new class for every other table
    //1. the class name should start with Uppercase Letter and camelCase can be followed
    //2. all the String refering table name  and attributes should be static
    //3. no need to add id feild as the class extends base columns

    //region Token
    //token is needed to register to the server
    public static final class Token implements BaseColumns {
        public static final String APP_TABLE = "token";
        public static final String COLUMN_APP_ID = "id";
        public static final String COLUMN_APP_TOKEN = "token";
    }
    //endregion

    //region Weighbridge

    public static final class Weighbridge implements BaseColumns {
        public static final String TABLE_NAME = "weighbridge"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_PRIMARY_LOCATION_CODE= "Primary_location_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }

    //endregion

    //region Truck
    public static final class Truck implements BaseColumns {
        public static final String TABLE_NAME = "truck"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_REGISTRATION_NUMBER= "registration_number";
        public static final String COLUMN_TARE_WEIGHT= "tare_weight";
        public static final String COLUMN_TRANSPORT_CONTRACTOR_CODE = "transport_contractor_code";
        public static final String COLUMN_DRIVER_CODE = "driver_code";
        public static final String COLUMN_CAPACITY= "capacity";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_CATEGORY_CODE= "category_code";
        public static final String COLUMN_GROUP_CODE = "group_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Permit

    public static final class Permit implements BaseColumns {
        public static final String TABLE_NAME = "permit"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_PERMIT_NUMBER= "permit_number";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_TYPE= "type";
        public static final String COLUMN_UPDATED_ON = "updated_on";

//this is how a single table is defined along with the table name and atributes
        // Every table is defined as a class so that it becomes easy to refer clumn names and table names from the instance of this class
        //this makes the code more robust
    }

    //endregion

    //region Primary Location
    public static final class PrimaryLocation implements BaseColumns {
        public static final String TABLE_NAME = "location"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Zone

    public static final class Zone implements BaseColumns {
        public static final String TABLE_NAME = "zone";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_PRIMARY_LOCATION= "primary_location_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";

    }

    //endregion

    //region Sub Zone

    public static final class SubZone implements BaseColumns {
        public static final String TABLE_NAME = "sub_zone";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_ZONE= "zone_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }

    //endregion

    //region Ore
    public static final class Ore implements BaseColumns {
        public static final String TABLE_NAME = "ore";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_GRADE= "grade";
        public static final String COLUMN_CATEGORY_CODE= "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Machine
    public static final class Machine implements BaseColumns {
        public static final String TABLE_NAME = "machine";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_REG_NUMBER= "registration_number";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_CONTRACTOR = "loading_contractor_code";
        public static final String COLUMN_CARD_ID= "card_id";
        public static final String COLUMN_CATEGORY_CODE= "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Rate
    public static final class Rate implements BaseColumns {
        public static final String TABLE_NAME = "rate";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_DISTANCE_FROM= "distance_from";
        public static final String COLUMN_DISTANCE_TO= "distance_to";
        public static final String COLUMN_RATE_PER_KM= "rate_per_km";
        public static final String COLUMN_FUEL_ELIGIBILITY= "fuel_eligibility";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Route
    public static final class Route implements BaseColumns {
        public static final String TABLE_NAME = "route";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_RATE_CODE = "rate_code";
        public static final String COLUMN_DISTANCE = "distance_code";
        public static final String COLUMN_START_PRIMARY_LOCATION_CODE= "start_primary_location_code";
        public static final String COLUMN_START_SECONDARY_LOCATION_CODE= "start_secondary_location_code";
        public static final String COLUMN_START_SECONDARY_LOCATION_TYPE= "start_secondary_location_type";
        public static final String COLUMN_END_PRIMARY_LOCATION_CODE= "end_primary_location_code";
        public static final String COLUMN_END_SECONDARY_LOCATION_CODE= "end_secondary_location_code";
        public static final String COLUMN_END_SECONDARY_LOCATION_TYPE= "end_secondary_location_type";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Activity
    public static final class Activity implements BaseColumns {
        public static final String TABLE_NAME = "activity";
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_CATEGORY_CODE= "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region User
    public static final class User implements BaseColumns {
        public static final String TABLE_NAME = "user"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_FIRST_NAME= "first_name";
        public static final String COLUMN_LAST_NAME= "last_name";
        public static final String COLUMN_ADDRESS= "address";
        public static final String COLUMN_MOBILE_NUMBER= "mobile_number";
        public static final String COLUMN_LOGIN_NAME= "login_name";
        public static final String COLUMN_LOGIN_PASSWORD= "login_password";
        public static final String COLUMN_EMAIL_ID= "email_id";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_CATEGORY_CODE= "category_code";
        public static final String COLUMN_PRIMARY_LOCATION_CODE= "primary_location_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Mobile Tag Reader
    public static final class MobileTagReader implements BaseColumns {
        public static final String TABLE_NAME = "mobileTagReader"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_APPLICATION_TOKEN= "application_token";
        public static final String COLUMN_IMEI_NUMBER= "imei_number";
        public static final String COLUMN_PRIMARY_LOCATION_CODE= "primary_location_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Driver
    public static final class Driver implements BaseColumns {
        public static final String TABLE_NAME = "driver"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_FIRST_NAME= "first_name";
        public static final String COLUMN_LAST_NAME= "last_name";
        public static final String COLUMN_ADDRESS= "address";
        public static final String COLUMN_MOBILE_NUMBER= "mobile_number";
        public static final String COLUMN_EMAIL_ID= "email_id";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_BLOOD_GROUP= "blood_group";
        public static final String COLUMN_TRUCK_CODE = "truck_code";
        public static final String COLUMN_CATEGORY_CODE = "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Material Contractor
    public static final class MaterialContractor implements BaseColumns {
        public static final String TABLE_NAME = "material_contractor"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_FIRST_NAME= "first_name";
        public static final String COLUMN_LAST_NAME= "last_name";
        public static final String COLUMN_ADDRESS= "address";
        public static final String COLUMN_MOBILE_NUMBER= "mobile_number";
        public static final String COLUMN_EMAIL_ID= "email_id";
        public static final String COLUMN_CATEGORY_CODE = "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Transport Contractor
    public static final class TransportContractor implements BaseColumns {
        public static final String TABLE_NAME = "transport_contractor"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";  //attributes for table
        public static final String COLUMN_FIRST_NAME= "first_name";
        public static final String COLUMN_LAST_NAME= "last_name";
        public static final String COLUMN_ADDRESS= "address";
        public static final String COLUMN_MOBILE_NUMBER= "mobile_number";
        public static final String COLUMN_EMAIL_ID= "email_id";
        public static final String COLUMN_TRUCK_CODE = "truck_code";
        public static final String COLUMN_CATEGORY_CODE = "category_code";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Category
    public static final class Category implements BaseColumns {
        public static final String TABLE_NAME = "category"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_TYPE= "type";
        public static final String COLUMN_UPDATED_ON = "updated_on";
    }
    //endregion

    //region Group
    public static final class Group implements BaseColumns {
        public static final String TABLE_NAME = "truck_group"; // table name that can be accessed from all the classes
        public static final String COLUMN_CODE= "code";
        public static final String COLUMN_DESCRIPTION= "description";
        public static final String COLUMN_UPDATED_ON = "updated_on";
        public static final String COLUMN_PRIMARY_LOCATIONS_SUNDAY= "p_l_sun";
        public static final String COLUMN_PRIMARY_LOCATIONS_MONDAY= "p_l_mon";
        public static final String COLUMN_PRIMARY_LOCATIONS_TUESDAY= "p_l_tue";
        public static final String COLUMN_PRIMARY_LOCATIONS_WEDNESDAY= "p_l_wed";
        public static final String COLUMN_PRIMARY_LOCATIONS_THURSDAY= "p_l_thu";
        public static final String COLUMN_PRIMARY_LOCATIONS_FRIDAY= "p_l_fri";
        public static final String COLUMN_PRIMARY_LOCATIONS_SATURDAY= "p_l_sat";
    }
    //endregion

}
