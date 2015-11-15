package cuny.edu.com.resumebuilder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TabFragment3 extends Fragment {

    private SQLLiteHelper sqlLiteHelper = null;
    private TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_fragment_3_updated, container, false);

        final View view2 = view.findViewById(R.id.buttonView);

        final LayoutInflater _inflater = inflater;

        sqlLiteHelper = SQLLiteHelper.getInstance(getContext());

        View submitButton = view.findViewById(R.id.buttonGenerate);
        View addButton    = view2.findViewById(R.id.buttonAdd);

        ((Button) addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View promptView = _inflater.inflate(R.layout.employee_input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Employment Information");
                alertDialogBuilder.setView(promptView);

                final EditText editText  = (EditText) promptView.findViewById(R.id.EditTextWhen);
                final EditText editText2 = (EditText) promptView.findViewById(R.id.EditTextWhere);
                final EditText editText3 = (EditText) promptView.findViewById(R.id.EditTextWhat);

                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                resultText.setText("Hello, " + editText.getText());
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        ((Button) submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TabFragment3.this.getActivity().getBaseContext(),
                        "Saving ...", Toast.LENGTH_LONG).show();

                SaveEmploymentInfoListener saveEmploymentInfoInfoListener = new SaveEmploymentInfoListener(view);
                saveEmploymentInfoInfoListener.onButtonClicked(sqlLiteHelper);
            }
        });

        return view;
    }

    public void showInputDialog() {

    }
}
