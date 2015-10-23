package cuny.edu.com.resumebuilder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SavePersonalInfoListener extends Activity {

    private static final String TAG = "SendPersonalInfoActivit";
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_fragment_1);
        submitButton = (Button) findViewById(R.id.buttonSendFeedback);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fullName = (EditText) view.findViewById(R.id.EditTextName);
                EditText address  = (EditText) view.findViewById(R.id.EditTextAddress);
                System.out.println("Name " + fullName);
                Log.v(TAG, "FullName " + fullName);
                finish();
            }
        });
    }
}

