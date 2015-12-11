package cuny.edu.com.resumebuilder;

import java.util.ArrayList;
import java.util.Date;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseScheduleActivity extends ListActivity{

    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<ClassSchedule> m_orders = null;
    private ClassScheduleAdapter m_adapter;
    private Runnable viewOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);
        m_orders = new ArrayList<ClassSchedule>();
        this.m_adapter = new ClassScheduleAdapter(this, R.layout.my_schedule_list, m_orders);
        setListAdapter(this.m_adapter);

        viewOrders = new Runnable(){
            @Override
            public void run() {
                getSchedules();
            }
        };
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(CourseScheduleActivity.this,
                "Please wait...", "Retrieving data ...", true);
    }
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                    m_adapter.add(m_orders.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };

    private void getSchedules(){
        try {
            m_orders = new ArrayList<ClassSchedule>();
            ClassSchedule c1 = new ClassSchedule();
            c1.setSubject("Discrete Math");
            c1.setAddress("Hunter North");
            c1.setNumberOfCredits("3 credits");
            c1.setEndAt("Tues 11:15AM");
            c1.setStartAt("Tues 10:00AM");
            c1.setStart(new Date());
            c1.setEnd(new Date());
            ClassSchedule c2 = new ClassSchedule();
            c2.setSubject("Operating System");
            c2.setAddress("Hunter North");
            c2.setNumberOfCredits("3 credits");
            c2.setEndAt("Wed 11:15AM");
            c2.setStartAt("Mon 10:00AM");
            c2.setStart(new Date());
            c2.setEnd(new Date());

            ClassSchedule c3 = new ClassSchedule();
            c3.setSubject("Computer Architecture 1");
            c3.setAddress("Hunter West");
            c3.setNumberOfCredits("3 credits");
            c3.setEndAt("Wed 6:30PM");
            c3.setStartAt("Mon 5:15PM");
            c3.setStart(new Date());
            c3.setEnd(new Date());

            m_orders.add(c1);
            m_orders.add(c2);
            m_orders.add(c3);

            Thread.sleep(5000);
            Log.i("ARRAY", ""+ m_orders.size());
        } catch (Exception e) {
            Log.e("BACKGROUND_PROC", e.getMessage());
        }
        runOnUiThread(returnRes);
    }
}



