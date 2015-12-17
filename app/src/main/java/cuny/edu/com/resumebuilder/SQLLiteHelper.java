package cuny.edu.com.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SQLLiteHelper extends SQLiteOpenHelper {

    private static int id = 1;
    public static final String TABLE_RESUME     = "resume";
    public static final String TABLE_EDUCATION  = "education";
    public static final String TABLE_EMPLOYMENT = "employment";
    public static final String TABLE_OBJECTIVE  = "objective";
    public static final String TABLE_CLASS_SCHEDULE  = "class_schedule";
    public static final String TABLE_SELECTED_CLASS_SCHEDULE  = "selected_schedule";



    public static final String RESUME_ID = "res_id";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS_1 = "address1";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city1";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIP = "zip";
    public static final String COLUMN_EMAIL      = "email";
    public static final String COLUMN_LANGUAGES  = "languages";
    public static final String COLUMN_STRENGTH   = "strength";
    public static final String COLUMN_HOBBIES    = "hobbies";
    public static final String COLUMN_OBJECTIVES = "objectives";
    public static final String COLUMN_SKILLS     = "skills";

    public static final String COLUMN_EDUCATION = "education";
    public static final String COLUMN_EMPLOYMENT = "employment";

    public static final String COLUMN_EMPLOYMENT_WHEN        = "timespan";
    public static final String COLUMN_EMPLOYMENT_WHERE       = "company";
    public static final String COLUMN_EMPLOYMENT_DESCRIPTION = "description";


    public static final String COLUMN_EDUCATION_WHEN        = "timespan";
    public static final String COLUMN_EDUCATION_WHERE       = "company";
    public static final String COLUMN_EDUCATION_DESCRIPTION = "description";


    public static final String COLUMN_EDUCATION_LINE1 = "education_1";
    public static final String COLUMN_EDUCATION_LINE2 = "education_2";
    public static final String COLUMN_EDUCATION_LINE3 = "education_3";
    public static final String COLUMN_EDUCATION_LINE4 = "education_4";
    public static final String COLUMN_EDUCATION_LINE5 = "education_5";
    public static final String COLUMN_EDUCATION_LINE6 = "education_6";
    public static final String COLUMN_EDUCATION_LINE7 = "education_7";

    public static final String COLUMN_EMPLOYMENT_LINE1 = "employment_1";
    public static final String COLUMN_EMPLOYMENT_LINE2 = "employment_2";
    public static final String COLUMN_EMPLOYMENT_LINE3 = "employment_3";
    public static final String COLUMN_EMPLOYMENT_LINE4 = "employment_4";
    public static final String COLUMN_EMPLOYMENT_LINE5 = "employment_5";
    public static final String COLUMN_EMPLOYMENT_LINE6 = "employment_6";
    public static final String COLUMN_EMPLOYMENT_LINE7 = "employment_7";

    public static final String COLUMN_DEPT       = "dept";
    public static final String COLUMN_CLASS_NAME = "name";
    public static final String COLUMN_INSTRUCTOR = "instructor";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME   = "end_time";
    public static final String COLUMN_DAYS       = "days";
    public static final String COLUMN_ROOM       = "room";
    public static final String COLUMN_REQ        = "req";
    public static final String COLUMN_DESC       = "desc";
    public static final String COLUMN_COMP       = "comp";
    public static final String COLUMN_PREREQ     = "prerequisite";
    public static final String COLUMN_CREDITS    = "credits";


    //
    private static final String DATABASE_NAME = "resume.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLLiteHelper instance = null;
    // Database creation sql statement

    private static final String DATABASE_CREATE_EDUCATION = "create table " + TABLE_EDUCATION
            + "("
            + COLUMN_ID               + " integer primary key autoincrement, "
            + RESUME_ID               + " text not null,"
            + COLUMN_EDUCATION_WHEN   + " text not null,"
            + COLUMN_EDUCATION_WHERE  + " text not null,"
            + COLUMN_EDUCATION_DESCRIPTION  + " text not null);";

    private static final String DATABASE_CREATE_EMPLOYMENT = "create table " + TABLE_EMPLOYMENT
            + "("
            + COLUMN_ID                + " integer primary key autoincrement, "
            + RESUME_ID                + " text not null,"
            + COLUMN_EMPLOYMENT_WHEN   + " text not null,"
            + COLUMN_EMPLOYMENT_WHERE  + " text not null,"
            + COLUMN_EMPLOYMENT_DESCRIPTION  + " text not null);";


    private static final String DATABASE_CREATE_OBJECTIVES = "create table " + TABLE_OBJECTIVE
            + "("
            + COLUMN_ID               + " integer primary key autoincrement, "
            + RESUME_ID               + " text not null,"
            + COLUMN_OBJECTIVES       + " text not null);";

    private static final String DATABASE_CREATE = "create table " + TABLE_RESUME
            + "("
            + COLUMN_ID               + " integer primary key autoincrement, "
            + COLUMN_NAME             + " text not null, "
            + COLUMN_ADDRESS_1        + " text not null, "
            + COLUMN_STREET           + " text not null, "
            + COLUMN_CITY             + " text not null, "
            + COLUMN_STATE            + " text not null, "
            + COLUMN_ZIP              + " text not null, "
            + COLUMN_EMAIL            + " text not null, "
            + COLUMN_LANGUAGES        + " text not null, "
            + COLUMN_OBJECTIVES       + " text not null, "
            + COLUMN_STRENGTH         + " text not null, "
            + COLUMN_HOBBIES          + " text not null,"
            + COLUMN_SKILLS           + " text not null);";


    private static final String DATABASE_CREATE_CLASS_SCHEDULE = "create table " + TABLE_CLASS_SCHEDULE
            + "("
            + COLUMN_ID                  + " integer primary key autoincrement, "
            + COLUMN_DEPT                + " text not null,"
            + COLUMN_CLASS_NAME          + " text not null,"
            + COLUMN_INSTRUCTOR          + " text not null,"
            + COLUMN_START_TIME          + " text not null,"
            + COLUMN_END_TIME            + " text not null,"
            + COLUMN_DAYS                + " text not null,"
            + COLUMN_ROOM                + " text not null,"
            + COLUMN_REQ                 + " text not null,"
            + COLUMN_DESC                + " text not null,"
            + COLUMN_COMP                + " text not null,"
            + COLUMN_PREREQ              + " text not null,"
            + COLUMN_CREDITS             + " text not null);";


    private static final String DATABASE_CREATE_SELECTED_SCHEDULE = "create table " + TABLE_SELECTED_CLASS_SCHEDULE
            + "("
            + COLUMN_ID                  + " integer primary key autoincrement, "
            + COLUMN_DEPT                + " text not null,"
            + COLUMN_CLASS_NAME          + " text not null,"
            + COLUMN_INSTRUCTOR          + " text not null,"
            + COLUMN_START_TIME          + " text not null,"
            + COLUMN_END_TIME            + " text not null,"
            + COLUMN_DAYS                + " text not null,"
            + COLUMN_ROOM                + " text not null,"
            + COLUMN_REQ                 + " text not null,"
            + COLUMN_DESC                + " text not null,"
            + COLUMN_COMP                + " text not null,"
            + COLUMN_PREREQ              + " text not null,"
            + COLUMN_CREDITS             + " text not null);";

    public static SQLLiteHelper getInstance() {
        return instance;
    }

    public static SQLLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SQLLiteHelper(context);
        }
        return instance;
    }

    private SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        saveTemplateResumeInformation();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS resume");
        db.execSQL("DROP TABLE IF EXISTS education");
        db.execSQL("DROP TABLE IF EXISTS employment");
        db.execSQL("DROP TABLE IF EXISTS objective");
        db.execSQL("DROP TABLE IF EXISTS class_schedule");
        db.execSQL("DROP TABLE IF EXISTS selected_schedule");

        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_EDUCATION);
        db.execSQL(DATABASE_CREATE_EMPLOYMENT);
        db.execSQL(DATABASE_CREATE_OBJECTIVES);
        db.execSQL(DATABASE_CREATE_CLASS_SCHEDULE);
        db.execSQL(DATABASE_CREATE_SELECTED_SCHEDULE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }


    public void saveTemplateResumeInformation() {
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, 1);
        contentValues.put(COLUMN_NAME,      "_name");
        contentValues.put(COLUMN_ADDRESS_1, "_adress");
        contentValues.put(COLUMN_STREET,    "_street");
        contentValues.put(COLUMN_CITY,      "_city");
        contentValues.put(COLUMN_STATE,     "_state");
        contentValues.put(COLUMN_ZIP,       "_zip");
        contentValues.put(COLUMN_EMAIL, "_email");
        contentValues.put(COLUMN_LANGUAGES, "_languages");
        contentValues.put(COLUMN_OBJECTIVES, "TST1");
        contentValues.put(COLUMN_STRENGTH,  "_strength");
        contentValues.put(COLUMN_HOBBIES,   "_hobbies");
        contentValues.put(COLUMN_SKILLS, "_skills");

        long newRowId = db.insert(TABLE_RESUME, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
//        saveTemplateEmploymentInformation();
    }

    public void saveTemplateEmploymentInformation() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, 1);
        contentValues.put(RESUME_ID, 1);

        contentValues.put(COLUMN_EMPLOYMENT_WHEN, "when");
        contentValues.put(COLUMN_EMPLOYMENT_WHERE, "where");
        contentValues.put(COLUMN_EMPLOYMENT_DESCRIPTION, "description");
        long newRowId = db.insert(TABLE_EMPLOYMENT, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void savePersonalInformation(ResumeInformation resumeInformation) {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ID, 1);
            contentValues.put(COLUMN_NAME, resumeInformation.getName());
            contentValues.put(COLUMN_ADDRESS_1, resumeInformation.getAddress());
            contentValues.put(COLUMN_STREET, resumeInformation.getStreet());
            contentValues.put(COLUMN_CITY, resumeInformation.getCity());
            contentValues.put(COLUMN_STATE, resumeInformation.getState());
            contentValues.put(COLUMN_ZIP, resumeInformation.getZipCode());
            contentValues.put(COLUMN_EMAIL, resumeInformation.getEmail());
            contentValues.put(COLUMN_LANGUAGES, resumeInformation.getLanguagesKnown());
            contentValues.put(COLUMN_OBJECTIVES, resumeInformation.getCareerObjectives().get(0));
            contentValues.put(COLUMN_STRENGTH,   resumeInformation.getStrength());
            contentValues.put(COLUMN_HOBBIES,    resumeInformation.getHobbies());
            contentValues.put(COLUMN_SKILLS,     resumeInformation.getSkills());

            long newRowId = db.update(TABLE_RESUME, contentValues, "_id = " + 1, null);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        close();
    }

    public ResumeInformation findByName(String name) {
        return null;
    }


    public void createNewEmployment(String when, String where, String desc) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESUME_ID, 1);

        contentValues.put(COLUMN_EMPLOYMENT_WHEN, when);
        contentValues.put(COLUMN_EMPLOYMENT_WHERE, where);
        contentValues.put(COLUMN_EMPLOYMENT_DESCRIPTION, desc);
        long newRowId = db.insert(TABLE_EMPLOYMENT, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void createNewEducation(String when, String where, String desc) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESUME_ID, 1);
        contentValues.put(COLUMN_EDUCATION_WHEN, when);
        contentValues.put(COLUMN_EDUCATION_WHERE, where);
        contentValues.put(COLUMN_EDUCATION_DESCRIPTION, desc);
        long newRowId = db.insert(TABLE_EDUCATION, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteEmploymentFromDatabase(String item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        System.out.println("EMPLOYMENT " + item);
        db.delete(TABLE_EMPLOYMENT, COLUMN_EMPLOYMENT_WHERE + "= ?", new String[]{item});
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    public void deleteEducationFromDatabase(String item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.delete(TABLE_EDUCATION, COLUMN_EMPLOYMENT_WHERE + "= ?", new String[]{item});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public ResumeInformation findResume() {
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RESUME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResumeInformation resumeInformation = setObjectStateFrom(cursor);
                return resumeInformation;
            } while (cursor.moveToNext());
        }

        return null;
    }

    public List<Employment> findEmployments() {
        SQLiteDatabase db = getReadableDatabase();

        List<Employment> employments = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYMENT;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                employments.add(getEmployment(cursor));
            } while (cursor.moveToNext());
        }

        return employments;
    }

    public List<Education> findEducations() {
        SQLiteDatabase db = getReadableDatabase();

        List<Education> educations = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_EDUCATION;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                educations.add(getEducation(cursor));
            } while (cursor.moveToNext());
        }

        return educations;
    }

    private ResumeInformation setObjectStateFrom(Cursor cursor) {

        ResumeInformation information = new ResumeInformation();
        information.setId(Long.parseLong(cursor.getString(0)));
        information.setName(cursor.getString(1));
        information.setAddress(cursor.getString(2));
        information.setStreet(cursor.getString(3));
        information.setCity(cursor.getString(4));
        information.setState(cursor.getString(5));
        information.setZipCode(cursor.getString(6));
        information.setEmail(cursor.getString(7));
        information.setLanguagesKnown(cursor.getString(8));
        information.addCareerObjectives(cursor.getString(9));
        information.setStrength(cursor.getString(10));
        information.setHobbies(cursor.getString(11));
        information.setSkills(cursor.getString(12));


        System.out.println("Object hydrated " + information);
        return information;
    }

    private Employment getEmployment(Cursor cursor) {

        Employment information = new Employment();
        information.setResumeId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(RESUME_ID))));
        information.setWhen(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYMENT_WHEN)));
        information.setWhere(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYMENT_WHERE)));
        information.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYMENT_DESCRIPTION)));
        return information;
    }


    private Education getEducation(Cursor cursor) {

        Education information = new Education();
        information.setResumeId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(RESUME_ID))));
        information.setWhen(cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION_WHEN)));
        information.setWhere(cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION_WHERE)));
        information.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION_DESCRIPTION)));
        return information;
    }

    private Employment createEmploymentsFrom(String str) {

        if (str != null) {
            String[] strings = str.split("|");
            Employment employment = new Employment();
            employment.setWhen(strings[0]);
            employment.setWhere(strings[1]);
            employment.setDescription(strings[2]);
            return employment;
        }

        return null;
    }

    private static int getNextId() {
        return id++;
    }


    public void deleteAllCourseData() {
        deleteAllSelectedCourseData();
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.beginTransaction();
            int deletedRows = db.delete(TABLE_CLASS_SCHEDULE, "1", null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    public void deleteAllSelectedCourseData() {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.beginTransaction();
            int deletedRows = db.delete(TABLE_SELECTED_CLASS_SCHEDULE, "1", null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void saveCourseData(ClassData schedule) {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.beginTransaction();
            ContentValues contentValues = getContentValues(schedule);

            long newRowId = db.insert(TABLE_CLASS_SCHEDULE, null, contentValues);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        close();
    }


    public void saveSelectedCourseData(ClassData schedule) {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.beginTransaction();

            ContentValues contentValues = getContentValues(schedule);

            long newRowId = db.insert(TABLE_SELECTED_CLASS_SCHEDULE, null, contentValues);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        close();
    }

    @NonNull
    private ContentValues getContentValues(ClassData schedule) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, getNextId());
        contentValues.put(COLUMN_DEPT,       schedule.getDept());
        contentValues.put(COLUMN_CLASS_NAME, schedule.getName());
        contentValues.put(COLUMN_INSTRUCTOR, schedule.getInstructor());
        contentValues.put(COLUMN_START_TIME, schedule.getTime());
        contentValues.put(COLUMN_END_TIME,   schedule.getEndTime());
        contentValues.put(COLUMN_DAYS,       schedule.getDays());
        contentValues.put(COLUMN_ROOM,       schedule.getRoom());
        contentValues.put(COLUMN_REQ,        schedule.getReq());
        contentValues.put(COLUMN_DESC,       schedule.getDesc());
        contentValues.put(COLUMN_COMP,       schedule.getComp());
        contentValues.put(COLUMN_PREREQ,     schedule.getPrerequisite());
        contentValues.put(COLUMN_CREDITS,    schedule.getCredits());
        return contentValues;
    }

    public List<ClassData> findAllClassData() {

        List<ClassData> dataList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CLASS_SCHEDULE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClassData classData = setClassDataObjectStateFrom(cursor);
                dataList.add(classData);
            } while (cursor.moveToNext());
        }

        return dataList;
    }


    public List<ClassData> findAllSelectedClassData() {

        List<ClassData> dataList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SELECTED_CLASS_SCHEDULE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClassData classData = setClassDataObjectStateFrom(cursor);
                dataList.add(classData);
            } while (cursor.moveToNext());
        }

        return dataList;
    }

    private ClassData setClassDataObjectStateFrom(Cursor cursor) {

        ClassData information = new ClassData();
        information.setCredits(cursor.getString(cursor.getColumnIndex(COLUMN_CREDITS)));
        information.setInstructor(cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTOR)));
        information.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
        information.setDays(cursor.getString(cursor.getColumnIndex(COLUMN_DAYS)));
        information.setEndTime(cursor.getString(cursor.getColumnIndex(COLUMN_END_TIME)));
        information.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_START_TIME)));
        information.setComp(cursor.getString(cursor.getColumnIndex(COLUMN_COMP)));
        information.setDept(cursor.getString(cursor.getColumnIndex(COLUMN_DEPT)));
        information.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_DESC)));
        information.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        information.setReq(cursor.getString(cursor.getColumnIndex(COLUMN_REQ)));
        information.setPrerequisite(cursor.getString(cursor.getColumnIndex(COLUMN_PREREQ)));

        System.out.println("Object hydrated " + information);
        return information;

    }

}
