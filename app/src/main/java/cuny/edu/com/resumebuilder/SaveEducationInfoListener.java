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

        information.setEducationLine1(findByName(R.id.EditTextEducationLine1));
        information.setEducationLine2(findByName(R.id.EditTextEducationLine2));
        information.setEducationLine3(findByName(R.id.EditTextEducationLine3));
        information.setEducationLine4(findByName(R.id.EditTextEducationLine4));
        information.setEducationLine5(findByName(R.id.EditTextEducationLine5));
        information.setEducationLine6(findByName(R.id.EditTextEducationLine6));
        information.setEducationLine7(findByName(R.id.EditTextEducationLine7));
        sqlLiteHelper.saveEducationInformation(information);
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

