package cuny.edu.com.resumebuilder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseResultAdapter extends ArrayAdapter<ClassData> {

    private class ViewHolder {
        TextView code;
        TextView courseName;
        TextView section;
        TextView instructor;
        TextView schedule;
        CheckBox name;
        TextView room;

        /*
        private String dept;
    private String name;
    private String instructor;
    private String time;
    private String endTime;
    private String days;
    private String room;
    private String req;
    private String desc;
    private String comp;
    private String prerequisite;
    private String credits;

         */
    }

    private List<ClassData> courseResultList = new ArrayList<>();

    public CourseResultAdapter(Context context,
                               int textViewResourceId,
                               List<ClassData> courseResultList) {

        super(context, textViewResourceId, courseResultList);
        this.courseResultList.addAll(courseResultList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.course_search_result_list, null);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.coursenumber);
            holder.name = (CheckBox) convertView.findViewById(R.id.findSelected);
            holder.courseName = (TextView) convertView.findViewById(R.id.coursename);
            holder.section = (TextView) convertView.findViewById(R.id.section);
            holder.instructor = (TextView) convertView.findViewById(R.id.instructor);
            holder.schedule = (TextView) convertView.findViewById(R.id.schedule);
            holder.room = (TextView) convertView.findViewById(R.id.room);

            convertView.setTag(holder);

            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    ClassData courseResult = (ClassData) cb.getTag();
                    Toast.makeText(getContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    courseResult.setSelected(cb.isChecked());
                    SQLLiteHelper.getInstance(getContext()).saveSelectedCourseData(courseResult);
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ClassData courseResult = courseResultList.get(position);
        holder.name.setChecked(courseResult.isSelected());
        holder.code.setText(" (" + courseResult.getName() + ")");
        holder.name.setText(courseResult.getDept() + " ");

        holder.courseName.setText(courseResult.getPrerequisite() + " ");
        holder.instructor.setText(courseResult.getInstructor() + " ");
        holder.schedule.setText(courseResult.getDays() + " " + courseResult.getTime() + "-" + courseResult.getEndTime());
        holder.room.setText(courseResult.getRoom());
        holder.name.setTag(courseResult);

        return convertView;
    }


    public List<ClassData> getCourseResultList() {
        return courseResultList;
    }

    public void setCourseResultList(List<ClassData> courseResultList) {
        this.courseResultList = courseResultList;
    }



    private void saveSelectedClass(ClassData classData) {

        SQLLiteHelper.getInstance(getContext()).saveCourseData(classData);
    }
}

