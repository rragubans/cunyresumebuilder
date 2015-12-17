package cuny.edu.com.resumebuilder;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import cz.msebera.android.httpclient.Header;


public class CourseSearchActivity extends AppCompatActivity {

    private ProgressDialog m_ProgressDialog = null;
    private boolean dismissNow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_search);
        setTitle("========= Course Search ==========");
        Spinner semester = (Spinner)findViewById(R.id.dynamic_spinner_semester);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getSemesters());


        Spinner college = (Spinner)findViewById(R.id.dynamic_spinner_college);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColleges());

        Spinner major = (Spinner)findViewById(R.id.dynamic_spinner_major);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getMajors(true));

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

        List<String> semesters = getMajors(false);
        return semesters;
    }


    private List<String> getColleges() {
        final List<String> colleges = new ArrayList<>();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String urlString = "http://cunyprime.herokuapp.com/getcollegelist";
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


    private List<String> getMajors(final boolean islower) {
        final List<String> terms = new ArrayList<>();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String urlString = "http://cunyprime.herokuapp.com/gettermanddeptlist";
                try {
                    SyncHttpClient client = new SyncHttpClient();
                    RequestParams params  = new RequestParams();
                    params.add("college_value", "HTR01");
                    client.post(urlString, params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                            System.out.println("ON FAILURE " + s);
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {
                            System.out.println("ON SUCCESS term data" + s);
                            List<String> termData = splitTermData(s, islower);
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

    private List<String> splitCollegeData(String data) {

        List<String> colleges = new ArrayList<>();
        String[] splitString  = data.split("</option>");
        for (String str : splitString) {
            int indexOf = str.indexOf(">");
            colleges.add(str.substring(indexOf+1));
        }

        return colleges;
    }

    private List<String> splitTermData(String data, boolean islower) {

        List<String> colleges = new ArrayList<>();
        String[] splitData    = data.split("SPLIT");

        String splitStringData = null;
        if (islower) {
            splitStringData = splitData[0];
        } else {
            splitStringData = splitData[1];
        }

        String[] splitString  = splitStringData.split("</option>");
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

                Spinner semester=(Spinner) findViewById(R.id.dynamic_spinner_semester);
                final String  semesterText = semester.getSelectedItem().toString();
                Spinner college = (Spinner)findViewById(R.id.dynamic_spinner_college);
                final String  collegeText = college.getSelectedItem().toString();
                Spinner major = (Spinner)findViewById(R.id.dynamic_spinner_major);
                final String  majorText = major.getSelectedItem().toString();

                m_ProgressDialog = ProgressDialog.show(CourseSearchActivity.this,
                        "Please wait...", "Searching for Classes ...", true);
                m_ProgressDialog.setCanceledOnTouchOutside(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         if (dismissNow) {
                            m_ProgressDialog.dismiss();
                        }
                    }
                }, 1000);

                Toast.makeText(getApplicationContext(), "searching with " + collegeText + " " + majorText
                        , Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            sendServletRequest(collegeText, semesterText, majorText, null);
                            dismissNow = true;
                    }
                }).start();

                while(dismissNow == false) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e){}

                }
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
                    client.setConnectTimeout(6000000);
                    RequestParams params  = new RequestParams();
                    params.put("college_value",    "HTR01");
                    params.put("term_value",       "2015 Fall Term");
                    params.put("dept_value",       "CSCI");
                    params.put("search_type",             "DEFAULT_SEARCH");
                    params.put("id_num",            2);

                    String url = "http://cunyprime.herokuapp.com/performclasssearch";

                    client.post(url, params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                            System.out.println("ON FAILURE " + s);
                            throwable.printStackTrace();
                            createLocalResponseData();
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {
                            System.out.println("ON SUCCESS class search " + s);

                            if ((s != null) && (s.length() > 0) && (!s.contains("ERRORS_BEGIN"))) {
                                System.out.println("string len " + s.length());
                                parseClassSearchData(s);
                            } else {
                                createLocalResponseData();
                            }
                        }

                    });

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(r);
        thread.start();

        //wait until work done
        try {
            Thread.sleep(12000);
        } catch(Exception e) {}
    }

    private List<ClassData> parseClassSearchData(String str) {

        List<ClassData> dataList = new ArrayList<>();
        ClassData classData = null;

        String[] data = str.split("FIELD_END");
        for (String elem : data) {
            if (elem.contains("Dept~")) {
                classData = new ClassData();
                dataList.add(classData);
                classData.setDept(getData(elem));
            } else if (elem.contains("Name~")) {
                classData.setName(getData(elem));
            } else if (elem.contains("Comp~")) {
                classData.setComp(getData(elem));
            } else if (elem.contains("Req~")) {
                classData.setReq(getData(elem));
            } else if (elem.contains("Prerequisites~")) {
                classData.setPrerequisite(getData(elem));
            } else if (elem.contains("Prerequisites")) {
                classData.setPrerequisite(getData(elem, ":"));
            } else if (elem.contains("Desc~")) {
                classData.setDesc(getData(elem));
            } else if (elem.contains("STime~")) {
                classData.setTime(getData(elem));
            } else if (elem.contains("ETime~")) {
                classData.setEndTime(getData(elem));
            } else if (elem.contains("Days~")) {
                classData.setDays(getData(elem));
            } else if (elem.contains("Room~")) {
                classData.setRoom(getData(elem));
            } else if (elem.contains("Inst~")) {
                classData.setInstructor(getData(elem));
            } else if (elem.contains("Cr~")) {
                classData.setCredits(getData(elem));
            }
        }

        saveSearchResults(dataList);
        return dataList;
    }


    private String getData(String elem, String delimiter) {
        int idx = elem.indexOf(delimiter);
        System.out.println("Data class " + elem.substring(idx + 1));
        return elem.substring(idx+1);
    }

    private String getData(String elem) {
        int idx = elem.indexOf("~");
        System.out.println("Data class " + elem.substring(idx+1));
        return elem.substring(idx+1);
    }

    public List<ClassData> createLocalResponseData() {

        List<ClassData> classDataList = new ArrayList<>();
        String str =
                "Dept~CSCIFIELD_ENDCNum~15000FIELD_ENDNa\tme~Discrete StructuresFIELD_ENDComp~LectureFIELD_ENDReq~\tPrerequisites: (MATH 15000 or MATH 15500 or MATH 12500) with a C or better. Math Proficient.FIELD_ENDDesc~Mathematical background required for computer science. Sets, relations, cardinality, propositional calculus, discrete functions, truth tables, induction, combinatorics.FIELD_ENDSNum~05-LECFIELD_ENDSTime~1110FIELD_ENDETime~1225FIELD_ENDDays~TuFrFIELD_ENDRoom~North Bldg 1516FIELD_ENDInst~Felisa Vazquez-AbadFIELD_ENDFlag~fFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~15000FIELD_ENDName~Discrete StructuresFIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: (MATH 15000 or MATH 15500 or MATH 12500) with a C or better. Math Proficient.FIELD_ENDDesc~Mathematical background required for computer science. Sets, relations, cardinality, propositional calculus, discrete functions, truth tables, induction, combinatorics.FIELD_ENDSNum~04-LECFIELD_ENDSTime~1900FIELD_ENDETime~2015FIELD_ENDDays~MoWeFIELD_ENDRoom~North Bldg C107FIELD_ENDInst~Alexey NikolaevFIELD_ENDFlag~fFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~15000FIELD_ENDName~Discrete StructuresFIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: (MATH 15000 or MATH 15500 or MATH 12500) with a C or better. Math Proficient.FIELD_ENDDesc~Mathematical background required for computer science. Sets, relations, cardinality, propositional calculus, discrete functions, truth tables, induction, combinatorics.FIELD_ENDSNum~03-LECFIELD_ENDSTime~1735FIELD_ENDETime~1850FIELD_ENDDays~MoWeFIELD_ENDRoom~North Bldg 1516FIELD_ENDInst~Julio KuplinskyFIELD_ENDFlag~fFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~15000FIELD_ENDName~Discrete StructuresFIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: (MATH 15000 or MATH 15500 or MATH 12500) with a C or better. Math Proficient.FIELD_ENDDesc~Mathematical background required for computer science. Sets, relations, cardinality, propositional calculus, discrete functions, truth tables, induction, combinatorics.FIELD_ENDSNum~02-LECFIELD_ENDSTime~945FIELD_ENDETime~1100FIELD_ENDDays~TuFrFIELD_ENDRoom~West Bldg W413FIELD_ENDInst~Felisa Vazquez-AbadFIELD_ENDFlag~fFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~15000FIELD_ENDName~Discrete StructuresFIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: (MATH 15000 or MATH 15500 or MATH 12500) with a C or better. Math Proficient.FIELD_ENDDesc~Mathematical background required for computer science. Sets, relations, cardinality, propositional calculus, discrete functions, truth tables, induction, combinatorics.FIELD_ENDSNum~01-LECFIELD_ENDSTime~1110FIELD_ENDETime~1225FIELD_ENDDays~MoThFIELD_ENDRoom~North Bldg 1516FIELD_ENDInst~Saman FarhatFIELD_ENDFlag~tFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~16000FIELD_ENDName~Comptr Archit 1FIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: CSCI 12700 and CSCI 15000.FIELD_ENDDesc~Comptr Archit 1FIELD_ENDSNum~04-LECFIELD_ENDSTime~1730FIELD_ENDETime~2030FIELD_ENDDays~FrFIELD_ENDRoom~North Bldg C111FIELD_ENDInst~Edmond LleshiFIELD_ENDFlag~tFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~16000FIELD_ENDName~Comptr Archit 1FIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: CSCI 12700 and CSCI 15000.FIELD_ENDDesc~Comptr Archit 1FIELD_ENDSNum~03-LECFIELD_ENDSTime~1735FIELD_ENDETime~1850FIELD_ENDDays~TuThFIELD_ENDRoom~North Bldg 1516FIELD_ENDInst~Christina ZamfirescuFIELD_ENDFlag~fFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~16000FIELD_ENDName~Comptr Archit 1FIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: CSCI 12700 and CSCI 15000.FIELD_ENDDesc~Comptr Archit 1FIELD_ENDSNum~02-LECFIELD_ENDSTime~1410FIELD_ENDETime~1525FIELD_ENDDays~TuFrFIELD_ENDRoom~North Bldg C112FIELD_ENDInst~Christina ZamfirescuFIELD_ENDFlag~tFIELD_ENDCr~3FIELD_ENDENTRY_ENDDept~CSCIFIELD_ENDCNum~16000FIELD_ENDName~Comptr Archit 1FIELD_ENDComp~LectureFIELD_ENDReq~Prerequisites: CSCI 12700 and CSCI 15000.FIELD_ENDDesc~Comptr Archit 1FIELD_ENDSNum~01-LECFIELD_ENDSTime~1245FIELD_ENDETime~1400FIELD_ENDDays~TuFrFIELD_ENDRoom~North Bldg";

        classDataList = parseClassSearchData(str);

        return classDataList;

    }


    private void saveSearchResults(List<ClassData> classDataList) {

        SQLLiteHelper.getInstance(getBaseContext()).deleteAllCourseData();
        System.out.println("Class Data List " + classDataList.size());
        for (ClassData classData : classDataList) {
            SQLLiteHelper.getInstance(getBaseContext()).saveCourseData(classData);
        }
    }
}

