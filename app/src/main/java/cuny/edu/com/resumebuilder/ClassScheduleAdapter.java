package cuny.edu.com.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleAdapter extends ArrayAdapter<ClassSchedule> {

    private List<ClassSchedule> items;

    public ClassScheduleAdapter(Context context, int textViewResourceId, ArrayList<ClassSchedule> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.my_schedule_list, null);
        }
        ClassSchedule o = items.get(position);
        if (o != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            TextView t2 = (TextView) v.findViewById(R.id.toptext2);
            TextView t3 = (TextView) v.findViewById(R.id.toptext3);
            TextView t4 = (TextView) v.findViewById(R.id.toptext4);

            if (tt != null) {
                tt.setText("Subject: " + o.getSubject());                            }
            if (bt != null){
                bt.setText("Address: " + o.getAddress());
            }

            if (t2 != null) {
                t2.setText("Time: " + o.getStartAt() + "-" + o.getEndAt());
            }
            if (t3 != null) {
                t3.setText("Credits: " + o.getNumberOfCredits());
            }
        }

        return v;
    }
}
