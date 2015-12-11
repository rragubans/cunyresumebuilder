package cuny.edu.com.resumebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class CourseSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_search);

        Spinner semester = (Spinner)findViewById(R.id.dynamic_spinner_semester);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getSemesters());


        Spinner college = (Spinner)findViewById(R.id.dynamic_spinner_college);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColleges());

        Spinner major = (Spinner)findViewById(R.id.dynamic_spinner_major);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getMajors());

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        semester.setAdapter(dataAdapter);
        college.setAdapter(dataAdapter2);
        major.setAdapter(dataAdapter3);
        addListenerOnButtonSearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<String> getSemesters() {

        List<String> semesters = new ArrayList<>();
        semesters.add("Spring");
        semesters.add("Summer");
        semesters.add("Winter");
        semesters.add("Fall");
        return semesters;
    }


    private List<String> getColleges() {
        final List<String> colleges = new ArrayList<>();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String urlString = "http://safe-dusk-8588.herokuapp.com/getcollegelist";
                try {
                    SyncHttpClient client = new SyncHttpClient();
                    RequestParams params  = new RequestParams();
                    client.post(urlString, params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                            System.out.println("ON FAILURE " + s);
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {
                            System.out.println("ON SUCCESS " + s);
                            List<String> collegeData = splitCollegeData(s);
                            colleges.addAll(collegeData);

                        }
                    });

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(r);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {}


        return colleges;
    }


    private List<String> getMajors() {
        final List<String> terms = new ArrayList<>();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String urlString = "http://safe-dusk-8588.herokuapp.com/gettermanddeptlist";
                try {
                    SyncHttpClient client = new SyncHttpClient();
                    RequestParams params  = new RequestParams();
                    params.add("college_value", "BAR01");
                    client.post(urlString, params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                            System.out.println("ON FAILURE " + s);
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {
                            System.out.println("ON SUCCESS term data" + s);
                            List<String> termData = splitTermData(s);
                            terms.addAll(termData);

                        }
                    });

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(r);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {}

        return terms;
    }

    private List<String> getTerm() {

        List<String> majors = new ArrayList<>();
        majors.add("Computer Science");
        majors.add("Physics");
        majors.add("Chemistry");
        majors.add("Biology");
        majors.add("Math");
        majors.add("Statistics");
        majors.add("English");
        majors.add("Political Science");
        return majors;
    }


    private List<String> splitCollegeData(String data) {

        List<String> colleges = new ArrayList<>();
        String[] splitString  = data.split("</option>");
        for (String str : splitString) {
            int indexOf = str.indexOf(">");
            colleges.add(str.substring(indexOf+1));
        }

        return colleges;
    }

    private List<String> splitTermData(String data) {

        List<String> colleges = new ArrayList<>();
        String[] splitData    = data.split("SPLIT");

        String[] splitString  = splitData[0].split("</option>");
        for (String str : splitString) {
            int indexOf = str.indexOf(">");
            colleges.add(str.substring(indexOf+1));
        }
        return colleges;
    }


    private void addListenerOnButtonSearch() {
        Button button = (Button) findViewById(R.id.btnSearchCourses);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendServletRequest(null, null, null, null);
                Intent intent = new Intent().setClass(getApplicationContext(), CourseResultActivity.class);
                startActivity(intent);

            }
        });
    }


    private void sendServletRequest(String college, String semester, String major, String professorName) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    SyncHttpClient client = new SyncHttpClient();
                    RequestParams params  = new RequestParams();
                    params.put("college_value",    "HTR01");
                    params.put("term_value",       "2015 Fall Term");
                    params.put("dept_value",       "CIS");
                    params.put("course_num_value", "");
                    params.put("search_type",             "DEFAULT_SEARCH");
                    params.put("id_num",            1);

                    String url = "http://safe-dusk-8588.herokuapp.com/performclasssearch";

                    client.post(url, params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                            System.out.println("ON FAILURE " + s);
                            throwable.printStackTrace();
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {
                            System.out.println("ON SUCCESS class search " + s);
                        }

                    });

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(r);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch(Exception e) {}
    }

}

