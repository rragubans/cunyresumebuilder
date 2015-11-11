package cuny.edu.com.resumebuilder;

import android.view.View;
import android.widget.EditText;

import cuny.edu.com.resumebuilder.pdf.ResumeGenerator;


public class SavePersonalInfoListener {

    private static final String TAG = "SendPersonalInfoActivit";
    private View view;

    public SavePersonalInfoListener(View view) {
        this.view = view;
    }

    public void onButtonClicked(SQLLiteHelper sqlLiteHelper) {
        ResumeInformation information = new ResumeInformation();

        information.setName(findByName(R.id.EditTextName));
        information.setAddress(findByName(R.id.EditTextAddress));
        information.setStreet(findByName(R.id.EditTextStreet));
        information.setCity(findByName(R.id.EditTextCity));
        information.setState(findByName(R.id.EditTextState));
        information.setZipCode(findByName(R.id.EditTextZip));
        information.setEmail(findByName(R.id.EditTextEmail));
        information.setLanguagesKnown(findByName(R.id.EditTextLanguage));
        information.setStrength(findByName(R.id.EditTextStrength));
        information.addCareerObjectives(findByName(R.id.EditTextCareerObjectives));
        information.setHobbies(findByName(R.id.EditTextHobbies));
        sqlLiteHelper.savePersonalInformation(information);
        ResumeGenerator resumeGenerator = new ResumeGenerator();
        resumeGenerator.generateResume(information, view.getContext());
    }

    private String findByName(int id) {
        EditText text = (EditText) view.findViewById(id);
        return ((text != null) ? text.getText().toString() : "");
    }
}

