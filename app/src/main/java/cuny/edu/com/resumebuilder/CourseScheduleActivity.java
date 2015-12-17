package cuny.edu.com.resumebuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

public class CourseScheduleActivity extends ListActivity{

    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<ClassSchedule> classSchedules = null;
    private ClassScheduleAdapter m_adapter;
    private Runnable viewOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);
        setTitle("========= Course Schedule ==========");

        classSchedules = new ArrayList<ClassSchedule>();
        this.m_adapter = new ClassScheduleAdapter(this, R.layout.my_schedule_list, classSchedules);
        setListAdapter(this.m_adapter);

        viewOrders = new Runnable(){
            @Override
            public void run() {
                getSchedules();
            }
        };
        //Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(CourseScheduleActivity.this,
                "Please wait...", "Building Class Schedule ...", true);
    }
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(classSchedules != null && classSchedules.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i< classSchedules.size();i++)
                    m_adapter.add(classSchedules.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };

    private void getSchedules(){
        try {

            classSchedules = new ArrayList<ClassSchedule>();

            List<ClassData> dataList = SQLLiteHelper.getInstance().findAllSelectedClassData();

            for (ClassData classData : dataList) {
                ClassSchedule c1 = new ClassSchedule();
                c1.setSubject(classData.getName());
                c1.setAddress(classData.getRoom());
                c1.setNumberOfCredits(classData.getCredits());
                c1.setEndAt(classData.getEndTime());
                c1.setStartAt(classData.getTime());
                c1.setStart(new Date());
                c1.setEnd(new Date());
                classSchedules.add(c1);
            }

            Thread.sleep(5000);
            Log.i("ARRAY", "" + classSchedules.size());
        } catch (Exception e) {
            Log.e("BACKGROUND_PROC", e.getMessage());
        }
        runOnUiThread(returnRes);
    }
}



