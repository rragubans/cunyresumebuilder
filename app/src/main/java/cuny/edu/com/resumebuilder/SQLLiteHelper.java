package cuny.edu.com.resumebuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RESUME = "resume";
    public static final String COLUMN_ID                = "_id";
    public static final String COLUMN_NAME              = "name";
    public static final String COLUMN_ADDRESS           = "address";
    public static final String COLUMN_EMAIL             = "email";
    public static final String COLUMN_LANGUAGES         = "languages";
    public static final String COLUMN_OBJECTIVES        = "objectives";
    public static final String COLUMN_STRENGTH          = "strength";
    public static final String COLUMN_HOBBIES           = "hobbies";
    public static final String COLUMN_GENDER            = "gender";
    public static final String COLUMN_UNDERGRAD_COLLEGE = "undergrad_college_name";
    public static final String COLUMN_UNDERGRAD_YEARS   = "undergrad_college_yrs_attend";
    public static final String COLUMN_GRAD_COLLEGE      = "grad_college_name";
    public static final String COLUMN_GRAD_YEARS        = "grad_college_yrs_attend";
    public static final String COLUMN_HIGH_SCHOOL       = "high_school_name";
    public static final String COLUMN_HIGH_SCHOOL_YRS   = "high_school_yrs_attend";
    public static final String COLUMN_JOB_HISTORY       = "job_history";
    public static final String COLUMN_SKILLS            = "skills";
    public static final String COLUMN_CERTIFICATE       = "certificate";
    public static final String COLUMN_GROUPS           = "groups";

    private static final String DATABASE_NAME = "resume.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_RESUME
            + "("
            + COLUMN_ID                     + " integer primary key autoincrement, "
            + COLUMN_NAME                   + " text not null, "
            + COLUMN_ADDRESS                + " text not null, "
            + COLUMN_EMAIL                  + " text not null, "
            + COLUMN_LANGUAGES              + " text not null, "
            + COLUMN_OBJECTIVES             + " text not null, "
            + COLUMN_STRENGTH               + " text not null, "
            + COLUMN_HOBBIES                + " text not null, "
            + COLUMN_GENDER                 + " text not null, "
            + COLUMN_UNDERGRAD_COLLEGE      + " text not null, "
            + COLUMN_UNDERGRAD_YEARS        + " text not null, "
            + COLUMN_GRAD_COLLEGE           + " text not null, "
            + COLUMN_GRAD_YEARS             + " text not null, "
            + COLUMN_HIGH_SCHOOL            + " text not null, "
            + COLUMN_HIGH_SCHOOL_YRS        + " text not null, "
            + COLUMN_JOB_HISTORY            + " text not null, "
            + COLUMN_SKILLS                 + " text not null, "
            + COLUMN_CERTIFICATE            + " text not null, "
            + COLUMN_GROUPS                 + " text not null);";

    public SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
