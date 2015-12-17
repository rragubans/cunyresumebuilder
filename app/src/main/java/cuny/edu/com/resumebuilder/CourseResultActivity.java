package cuny.edu.com.resumebuilder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CourseResultActivity extends AppCompatActivity {

    private CourseResultAdapter dataAdapter = null;
    private ProgressDialog m_ProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_search_results);
        setTitle("========= Course Result ==========");
        m_ProgressDialog = ProgressDialog.show(CourseResultActivity.this,
                "Please wait...", "Searching for Classes ...", true);
        m_ProgressDialog.setCanceledOnTouchOutside(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                m_ProgressDialog.dismiss();
            }
        }, 1000);


        displayListView();
    }

    public void displayListView() {

        dataAdapter = new CourseResultAdapter(this, R.layout.course_search_result_list, getResults());

        ListView listView = (ListView) findViewById(R.id.listView1);

        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ClassData courseResult = (ClassData) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + courseResult.getName() + " " + courseResult.getTime(),
                        Toast.LENGTH_LONG).show();
                startScheduleActivity();
            }
        });
    }

    public CourseResultAdapter getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(CourseResultAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    private void startScheduleActivity() {
        Intent intent = new Intent().setClass(getApplicationContext(), CourseScheduleActivity.class);
        startActivity(intent);
    }

    public List<ClassData> getResults() {

        return SQLLiteHelper.getInstance(getBaseContext()).findAllClassData();
    }

}

