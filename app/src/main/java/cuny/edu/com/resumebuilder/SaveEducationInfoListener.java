package cuny.edu.com.resumebuilder;

import android.view.View;
import android.widget.EditText;

import cuny.edu.com.resumebuilder.pdf.ResumeGenerator;


public class SaveEducationInfoListener {

    private static final String TAG = "SaveEducationInfoActivit";
    private View view;

    public SaveEducationInfoListener(View view) {
        this.view = view;
    }

    public void onButtonClicked(SQLLiteHelper sqlLiteHelper) {
        ResumeInformation information = new ResumeInformation();
        ResumeInformation dbInfo = sqlLiteHelper.findResume();

        System.out.println("DBINFO " + dbInfo.getSkills());
        ResumeGenerator resumeGenerator =new ResumeGenerator();
        resumeGenerator.generateResume(dbInfo, view.getContext());

    }

    private String findByName(int id) {
        EditText text = (EditText) view.findViewById(id);
        return text.getText().toString();
    }
}

