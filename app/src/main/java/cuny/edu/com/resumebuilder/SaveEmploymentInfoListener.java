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

        Employment employment = new Employment();
        employment.setDescription(findByName(R.id.EditTextDescription));
        employment.setResumeId(1);
        employment.setWhen(findByName(R.id.EditTextFrom));
        employment.setWhere(findByName(R.id.EditTextCompany));

        information.getEmployments().add(employment);

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

