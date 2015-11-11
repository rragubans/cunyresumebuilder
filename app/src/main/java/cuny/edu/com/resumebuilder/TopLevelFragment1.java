package cuny.edu.com.resumebuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TopLevelFragment1 extends Fragment {

    private SQLLiteHelper sqlLiteHelper = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        sqlLiteHelper = SQLLiteHelper.getInstance(getContext());
        View submitButton = view.findViewById(R.id.buttonSendFeedback);
        ((Button) submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TopLevelFragment1.this.getActivity().getBaseContext(),
                        "Saving ...", Toast.LENGTH_LONG).show();
                System.out.println("VIEW " + v);
                System.out.println("NAME: " + view.findViewById((R.id.EditTextName)));

            }
        });
        return view;
    }
}
