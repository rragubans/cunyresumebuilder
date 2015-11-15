package cuny.edu.com.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RESUME     = "resume";
    public static final String TABLE_EDUCATION  = "education";
    public static final String TABLE_EMPLOYMENT = "employment";
    public static final String TABLE_OBJECTIVE  = "objective";

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
            + COLUMN_EDUCATION_LINE1  + " text not null, "
            + COLUMN_EDUCATION_LINE2  + " text not null, "
            + COLUMN_EDUCATION_LINE3  + " text not null, "
            + COLUMN_EDUCATION_LINE4  + " text not null, "
            + COLUMN_EDUCATION_LINE5  + " text not null, "
            + COLUMN_EDUCATION_LINE6  + " text not null, "
            + COLUMN_EDUCATION_LINE7  + " text not null, "
            + COLUMN_EMPLOYMENT_LINE1 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE2 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE3 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE4 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE5 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE6 + " text not null, "
            + COLUMN_EMPLOYMENT_LINE7 + " text not null, "
            + COLUMN_SKILLS           + " text not null);";


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
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_EDUCATION);
        db.execSQL(DATABASE_CREATE_EMPLOYMENT);
        db.execSQL(DATABASE_CREATE_OBJECTIVES);
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
        contentValues.put(COLUMN_EMAIL,     "_email");
        contentValues.put(COLUMN_LANGUAGES, "_languages");
        contentValues.put(COLUMN_OBJECTIVES, "TST1");
        contentValues.put(COLUMN_STRENGTH,  "_strength");
        contentValues.put(COLUMN_HOBBIES,   "_hobbies");
        contentValues.put(COLUMN_EDUCATION_LINE1, "_education1");
        contentValues.put(COLUMN_EDUCATION_LINE2, "_education2");
        contentValues.put(COLUMN_EDUCATION_LINE3, "_education3");
        contentValues.put(COLUMN_EDUCATION_LINE4, "_education4");
        contentValues.put(COLUMN_EDUCATION_LINE5, "_education5");
        contentValues.put(COLUMN_EDUCATION_LINE6, "_education6");
        contentValues.put(COLUMN_EDUCATION_LINE7, "_education7");
        contentValues.put(COLUMN_EMPLOYMENT_LINE1, "_employment1");
        contentValues.put(COLUMN_EMPLOYMENT_LINE2, "_employment2");
        contentValues.put(COLUMN_EMPLOYMENT_LINE3, "_employment3");
        contentValues.put(COLUMN_EMPLOYMENT_LINE4, "_employment4");
        contentValues.put(COLUMN_EMPLOYMENT_LINE5, "_employment5");
        contentValues.put(COLUMN_EMPLOYMENT_LINE6, "_employment6");
        contentValues.put(COLUMN_EMPLOYMENT_LINE7, "_employment7");
        contentValues.put(COLUMN_SKILLS, "_skills");

        long newRowId = db.insert(TABLE_RESUME, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        saveTemplateEmploymentInformation();
    }


    public void saveTemplateEmploymentInformation() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, 1);
        contentValues.put(RESUME_ID, 1);

        contentValues.put(COLUMN_EMPLOYMENT_WHEN, "when");
        contentValues.put(COLUMN_EMPLOYMENT_WHEN, "where");
        contentValues.put(COLUMN_EMPLOYMENT_WHEN, "description");
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


    public void saveEducationInformation(ResumeInformation resumeInformation) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_EDUCATION_LINE1, resumeInformation.getEducationLine1());
            contentValues.put(COLUMN_EDUCATION_LINE2, resumeInformation.getEducationLine2());
            contentValues.put(COLUMN_EDUCATION_LINE3, resumeInformation.getEducationLine3());
            contentValues.put(COLUMN_EDUCATION_LINE4, resumeInformation.getEducationLine4());
            contentValues.put(COLUMN_EDUCATION_LINE5, resumeInformation.getEducationLine5());
            contentValues.put(COLUMN_EDUCATION_LINE6, resumeInformation.getEducationLine6());
            contentValues.put(COLUMN_EDUCATION_LINE7, resumeInformation.getEducationLine7());

            long newRowId = db.update(TABLE_RESUME, contentValues, "_id = " + 1, null);
            db.setTransactionSuccessful();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        close();
    }


    public void saveEmploymentInformation(ResumeInformation resumeInformation) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            int i=1;
            String[] employmentLines = new String[6];

            for (Employment employment : resumeInformation.getEmployments()) {
                String employmentLine = employment.getWhere() + "|" + employment.getWhen() + "|" +
                                employment.getDescription();
                employmentLines[i] = employmentLine;
            }


        contentValues.put(COLUMN_EMPLOYMENT_LINE1, employmentLines[0]);
        contentValues.put(COLUMN_EMPLOYMENT_LINE2, resumeInformation.getEmploymentLine2());
        contentValues.put(COLUMN_EMPLOYMENT_LINE3, resumeInformation.getEmploymentLine3());
        contentValues.put(COLUMN_EMPLOYMENT_LINE4, resumeInformation.getEmploymentLine4());
        contentValues.put(COLUMN_EMPLOYMENT_LINE5, resumeInformation.getEmploymentLine5());
        contentValues.put(COLUMN_EMPLOYMENT_LINE6, resumeInformation.getEmploymentLine6());
        contentValues.put(COLUMN_EMPLOYMENT_LINE7, resumeInformation.getEmploymentLine7());
        contentValues.put(COLUMN_SKILLS, resumeInformation.getSkills());

        long newRowId = db.update(TABLE_RESUME, contentValues, "_id = " + 1, null);
            db.setTransactionSuccessful();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        close();
    }

    public ResumeInformation findByName(String name) {
        return null;
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


    public ResumeInformation findEmployment() {
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYMENT;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResumeInformation resumeInformation = setObjectStateFrom(cursor);
                return resumeInformation;
            } while (cursor.moveToNext());
        }

        return null;
    }

    public ResumeInformation findEducation() {
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_EDUCATION;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResumeInformation resumeInformation = setObjectStateFrom(cursor);
                return resumeInformation;
            } while (cursor.moveToNext());
        }

        return null;
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
        information.setEducationLine1(cursor.getString(12));
        information.setEducationLine2(cursor.getString(13));
        information.setEducationLine3(cursor.getString(14));
        information.setEducationLine4(cursor.getString(15));
        information.setEducationLine5(cursor.getString(16));
        information.setEducationLine6(cursor.getString(17));
        information.setEducationLine7(cursor.getString(18));

        String str = cursor.getString(19);

        information.setEmploymentLine1(cursor.getString(19));
        information.setEmploymentLine2(cursor.getString(20));
        information.setEmploymentLine3(cursor.getString(21));
        information.setEmploymentLine4(cursor.getString(22));
        information.setEmploymentLine5(cursor.getString(23));
        information.setEmploymentLine6(cursor.getString(24));
        information.setEmploymentLine7(cursor.getString(25));
        information.setSkills(cursor.getString(26));


        System.out.println("Object hydrated " + information);
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
}
