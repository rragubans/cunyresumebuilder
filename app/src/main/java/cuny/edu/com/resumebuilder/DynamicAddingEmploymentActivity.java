package cuny.edu.com.resumebuilder;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
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


public class DynamicAddingEmploymentActivity extends ListFragment {

    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_fragment_3_input_box, container, false);
        Button button =(Button)rootView.findViewById(R.id.btnAdd);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) rootView.findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        button.setOnClickListener(listener);
        setListAdapter(adapter);
        return rootView;
    }
}

