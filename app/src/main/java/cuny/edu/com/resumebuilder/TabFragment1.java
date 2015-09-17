package cuny.edu.com.resumebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        EditText fullName = (EditText) view.findViewById(R.id.EditTextName);

        View submitButton = view.findViewById(R.id.buttonSendFeedback);
        ((Button) submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TabFragment1.this.getActivity().getBaseContext(),
                        "clicked on Submit Button", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent();
                //intent.setClass(getActivity(), SendPersonalInfoActivity.class);
                //startActivity(intent);
            }
        });
        return view;
    }
}
