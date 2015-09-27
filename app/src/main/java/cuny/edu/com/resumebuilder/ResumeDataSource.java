package cuny.edu.com.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ResumeDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLLiteHelper dbHelper;
    private String[] allColumns = {
            SQLLiteHelper.COLUMN_ID,
            SQLLiteHelper.COLUMN_NAME,
            SQLLiteHelper.COLUMN_ADDRESS,
            SQLLiteHelper.COLUMN_EMAIL,
            SQLLiteHelper.COLUMN_LANGUAGES,
            SQLLiteHelper.COLUMN_OBJECTIVES,
            SQLLiteHelper.COLUMN_STRENGTH,
            SQLLiteHelper.COLUMN_HOBBIES,
            SQLLiteHelper.COLUMN_GENDER,
            SQLLiteHelper.COLUMN_UNDERGRAD_COLLEGE,
            SQLLiteHelper.COLUMN_UNDERGRAD_YEARS,
            SQLLiteHelper.COLUMN_GRAD_COLLEGE,
            SQLLiteHelper.COLUMN_GRAD_YEARS,
            SQLLiteHelper.COLUMN_HIGH_SCHOOL,
            SQLLiteHelper.COLUMN_HIGH_SCHOOL_YRS,
            SQLLiteHelper.COLUMN_JOB_HISTORY,
            SQLLiteHelper.COLUMN_SKILLS,
            SQLLiteHelper.COLUMN_CERTIFICATE,
            SQLLiteHelper.COLUMN_GROUPS
    };

    public ResumeDataSource(Context context) {
        dbHelper = new SQLLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ResumeInformation createResume(String name) {
        ContentValues values = new ContentValues();
        values.put(SQLLiteHelper.COLUMN_NAME, name);
        long insertId = database.insert(SQLLiteHelper.TABLE_RESUME, null, values);
        Cursor cursor = database.query(SQLLiteHelper.TABLE_RESUME,
                allColumns, SQLLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ResumeInformation newResume = cursorToResume(cursor);
        cursor.close();
        return newResume;
    }

    private ResumeInformation cursorToResume(Cursor cursor) {
        ResumeInformation resumeInformation = new ResumeInformation();
        resumeInformation.setId(cursor.getLong(0));
        resumeInformation.setName(cursor.getString(1));
        return resumeInformation;
    }
}
