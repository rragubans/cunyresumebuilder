package cuny.edu.com.resumebuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public abstract class DBAwareFragment extends Fragment {

    private SQLLiteHelper sqlLiteHelper = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
//        View view = findView(inflater, container);
        sqlLiteHelper = getDataBaseConnection();
        View submitButton = view.findViewById(R.id.buttonSendFeedback);
        ((Button) submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DBAwareFragment.this.getActivity().getBaseContext(),
                        "Saving ...", Toast.LENGTH_LONG).show();
                SavePersonalInfoListener savePersonalInfoListener = new SavePersonalInfoListener(v);
                savePersonalInfoListener.onButtonClicked(sqlLiteHelper);

                //Intent intent = new Intent();
                //intent.setClass(getActivity(), SendPersonalInfoActivity.class);
                //startActivity(intent);
            }
        });
        return view;
    }

    public abstract View getView(ViewGroup container);

    public SQLLiteHelper getDataBaseConnection() {
        return null;
        //return new SQLLiteHelper(getContext());
    }

    public abstract View findView(LayoutInflater inflater, ViewGroup container);

}
