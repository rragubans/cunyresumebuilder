package cuny.edu.com.resumebuilder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_search_results);
        displayListView();
    }

    public void displayListView() {

        List<CourseResult> results = getResults();
        /*CourseResult header = new CourseResult();
        header.setInstructor("Instructor");
        header.setCourseNumber("Number");
        header.setCourseName("Name");
        header.setSchedule("Schedule");
        header.setSection("Section");
        results.add(header);
        for (int i=0; i < 20; i++) {
            CourseResult result = new CourseResult();
            result.setCourseNumber(String.valueOf(i));
            result.setCourseName("Name " + i);
            result.setInstructor("Wishes" + i);
            result.setSchedule("Mon and Wed") ;
            results.add(result);
        }
*/
        dataAdapter = new CourseResultAdapter(this, R.layout.course_search_result_list, results);

        ListView listView = (ListView) findViewById(R.id.listView1);

        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                CourseResult courseResult = (CourseResult) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + courseResult.getCourseName() + " " + courseResult.getSchedule(),
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

    public List<CourseResult> getResults() {

        List<CourseResult> results = new ArrayList<>();

        CourseResult courseResult = new CourseResult();
        courseResult.setCourseNumber("A100");
        courseResult.setSection("B100");
        courseResult.setCourseNumber("100");
        courseResult.setSchedule("Mon and Wed 10:00 AM - 11:15 AM");
        courseResult.setInstructor("Prof: Bill");
        courseResult.setCourseName("Introduction to Discrete Math");

        results.add(courseResult);

        courseResult = new CourseResult();
        courseResult.setCourseNumber("A101");
        courseResult.setSection("B101");
        courseResult.setCourseNumber("101");
        courseResult.setSchedule("Tues and Thurs 10:00 AM - 11:15 AM");
        courseResult.setInstructor("Prof: Kara");
        courseResult.setCourseName("Computer Architecture 1");
        results.add(courseResult);

        courseResult = new CourseResult();
        courseResult.setCourseNumber("A102");
        courseResult.setSection("B102");
        courseResult.setCourseNumber("102");
        courseResult.setSchedule("Fri 5:00 PM - 8:30 PM");
        courseResult.setInstructor("Prof: Kara");
        courseResult.setCourseName("Computer Architecture 2");
        results.add(courseResult);


        courseResult = new CourseResult();
        courseResult.setCourseNumber("A102");
        courseResult.setSection("B102");
        courseResult.setCourseNumber("102");
        courseResult.setSchedule("Fri 5:00 PM - 8:30 PM");
        courseResult.setInstructor("Prof: Kara");
        courseResult.setCourseName("Computer Architecture 3");
        results.add(courseResult);



        courseResult = new CourseResult();
        courseResult.setCourseNumber("A102");
        courseResult.setSection("B102");
        courseResult.setCourseNumber("102");
        courseResult.setSchedule("Mon and Wed 5:00 PM - 6:15 PM");
        courseResult.setInstructor("Prof: Kara");
        courseResult.setCourseName("Unix Application Programming");
        results.add(courseResult);

        return results;
    }

}

