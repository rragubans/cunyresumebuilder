package cuny.edu.com.resumebuilder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseResultAdapter extends ArrayAdapter<CourseResult> {

    private class ViewHolder {
        TextView code;
        TextView courseName;
        TextView section;
        TextView instructor;
        TextView schedule;
        CheckBox name;
    }

    private List<CourseResult> courseResultList = new ArrayList<>();

    public CourseResultAdapter(Context context,
                               int textViewResourceId,
                               List<CourseResult> courseResultList) {

        super(context, textViewResourceId, courseResultList);
        this.courseResultList.addAll(courseResultList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.course_search_result_list, null);

            holder = new ViewHolder();
            holder.code =        (TextView) convertView.findViewById(R.id.coursenumber);
            holder.name =        (CheckBox) convertView.findViewById(R.id.findSelected);
            holder.courseName =  (TextView) convertView.findViewById(R.id.coursename);
            holder.section =     (TextView) convertView.findViewById(R.id.section);
            holder.instructor =  (TextView) convertView.findViewById(R.id.instructor);
            holder.schedule =    (TextView) convertView.findViewById(R.id.schedule);

            convertView.setTag(holder);

            holder.name.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    CourseResult courseResult = (CourseResult) cb.getTag();
                    Toast.makeText(getContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    courseResult.setSelected(cb.isChecked());
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CourseResult courseResult = courseResultList.get(position);
        if (position != 0) {
            holder.code.setText(" (" + courseResult.getCourseNumber() + ")");
            holder.name.setChecked(courseResult.isSelected() );

        } else {
            holder.code.setText("Number ");
        }
        holder.name.setText(courseResult.getSection() + " ");
        holder.courseName.setText(courseResult.getCourseName() + " ");
        holder.instructor.setText(courseResult.getInstructor()  + " ");
        holder.name.setTag(courseResult);
        return convertView;
    }


    public List<CourseResult> getCourseResultList() {
        return courseResultList;
    }

    public void setCourseResultList(List<CourseResult> courseResultList) {
        this.courseResultList = courseResultList;
    }

}
