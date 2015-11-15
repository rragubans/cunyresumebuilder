package cuny.edu.com.resumebuilder;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class EmploymentListViewActivity extends ListFragment {

    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private List<String> list2 = new ArrayList<String>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.employmentlist, container, false);

        final View view2 = rootView.findViewById(R.id.buttonlistView);

        Button button = (Button) view2.findViewById(R.id.buttonAdd2);

        list.add(" ");
        getEmploymentData(list);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                final View promptView = inflater.inflate(R.layout.employee_input_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Employment Information");
                alertDialogBuilder.setView(promptView);

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                final EditText editText  = (EditText) promptView.findViewById(R.id.EditTextWhen);
                                final EditText editText2 = (EditText) promptView.findViewById(R.id.EditTextWhere);
                                final EditText editText3 = (EditText) promptView.findViewById(R.id.EditTextWhat);

                                String when        = editText.getText().toString();
                                String where       = editText2.getText().toString();
                                String description = editText3.getText().toString();
                                String str = "Date: "     + when + " Company: " + where;
                                list.add(str);
                                saveEmploymentInformation(when, where, description);
                                updateUI();
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
        };

        button.setOnClickListener(listener);
        setListAdapter(adapter);
        adapter.setNotifyOnChange(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
         adapter.notifyDataSetChanged();
    }

    public void updateUI() {

        getView().post(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void saveEmploymentInformation(String when, String where, String description) {

        SQLLiteHelper sqlLiteHelper = SQLLiteHelper.getInstance();
        sqlLiteHelper.createNewEmployment(when, where, description);
    }

    public void getEmploymentData(List<String> stringList) {
        SQLLiteHelper sqlLiteHelper  = SQLLiteHelper.getInstance();
        List<Employment> employments = sqlLiteHelper.findEmployments();
        for (Employment employment : employments) {
            String str = "Date: "       + employment.getWhen() +
                        " Company: "    + employment.getWhere();
            stringList.add(str);
        }


    }
}

