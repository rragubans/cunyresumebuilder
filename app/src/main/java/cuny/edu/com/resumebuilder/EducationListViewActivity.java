package cuny.edu.com.resumebuilder;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class EducationListViewActivity extends ListFragment {

    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private List<String> list2 = new ArrayList<String>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.educationlist, container, false);
        final View view2    = rootView.findViewById(R.id.buttonlistView);
        Button button       = (Button) view2.findViewById(R.id.buttonAdd2);
        list.add(" ");
        getEducationData(list);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                final View promptView = inflater.inflate(R.layout.education_input_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Education Information");
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
                                String str = "Date: "     + when + " Institution: " + where;
                                list.add(str);
                                saveEducationInformation(when, where, description);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeItemFromListPosition(position);
            }
        });
    }

    public void saveEducationInformation(String when, String where, String description) {

        SQLLiteHelper sqlLiteHelper = SQLLiteHelper.getInstance();
        sqlLiteHelper.createNewEducation(when, where, description);
    }

    public void getEducationData(List<String> stringList) {
        SQLLiteHelper sqlLiteHelper = SQLLiteHelper.getInstance();
        List<Education> educations = sqlLiteHelper.findEducations();
        for (Education education : educations) {
            String str = "Date: " + education.getWhen() + " Institution: " + education.getWhere();
            stringList.add(str);
        }
    }

    public void removeItemFromListPosition(int position) {

        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setNegativeButton("Cancel", null);

        alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog,int which){

                String item = list.get(deletePosition);
                list.remove(deletePosition);
                deleteFromDatabase(item);
                updateUI();
            }
        });
        alert.show();
    }

    public void deleteFromDatabase(String item) {
        int lastIndexOf = item.lastIndexOf(":");
        String name = item.substring(lastIndexOf + 1);
        SQLLiteHelper sqlLiteHelper = SQLLiteHelper.getInstance();
        sqlLiteHelper.deleteEducationFromDatabase(name);

    }
}

