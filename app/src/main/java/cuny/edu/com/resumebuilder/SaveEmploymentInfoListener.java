package cuny.edu.com.resumebuilder;

import android.view.View;
import android.widget.EditText;

import cuny.edu.com.resumebuilder.pdf.ResumeGenerator;


public class SaveEmploymentInfoListener {

    private static final String TAG = "SendPersonalInfoActivit";
    private View view;

    public SaveEmploymentInfoListener(View view) {
        this.view = view;
    }

    public void onButtonClicked(SQLLiteHelper sqlLiteHelper) {
        ResumeInformation information = new ResumeInformation();

        information.setEmploymentLine1(findByName(R.id.EditTextJobHistory1));
        information.setEmploymentLine2(findByName(R.id.EditTextJobHistory2));
        information.setEmploymentLine3(findByName(R.id.EditTextJobHistory3));
        information.setEmploymentLine4(findByName(R.id.EditTextJobHistory4));
        information.setEmploymentLine5(findByName(R.id.EditTextJobHistory5));
        information.setEmploymentLine6(findByName(R.id.EditTextJobHistory6));
        information.setEmploymentLine7(findByName(R.id.EditTextJobHistory7));
        information.setSkills(findByName(R.id.EditTextSkills));

        sqlLiteHelper.saveEmploymentInformation(information);

        ResumeInformation dbInfo = sqlLiteHelper.findResume();
        System.out.println("generator " + dbInfo);

        ResumeGenerator resumeGenerator  =new ResumeGenerator();
        resumeGenerator.generateResume(dbInfo, view.getContext());
    }

    private String findByName(int id) {
        EditText text = (EditText) view.findViewById(id);
        return text.getText().toString();
    }
}

