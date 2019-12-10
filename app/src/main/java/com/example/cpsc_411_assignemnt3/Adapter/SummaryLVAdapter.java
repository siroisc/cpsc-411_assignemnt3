package com.example.cpsc_411_assignemnt3.Adapter;

import android.widget.BaseAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cpsc_411_assignemnt3.R;
import com.example.cpsc_411_assignemnt3.Model.Courses;
import com.example.cpsc_411_assignemnt3.Model.Student;
import com.example.cpsc_411_assignemnt3.Model.StudentDB;

public class SummaryLVAdapter extends BaseAdapter {

    StudentDB mStudentDB;
    public  SummaryLVAdapter(StudentDB sDB){
        mStudentDB = sDB;
    }

    @Override
    public int getCount() {
        return mStudentDB.getStudents().size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentDB.getStudents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row_view;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.row, parent, false);
        } else row_view = convertView;

        Student s = mStudentDB.getStudents().get(position);

        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name_id);
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name_id);
        firstNameView.setText(s.getFirstName());
        lastNameView.setText(s.getLastName());
        row_view.setTag(new Integer(position));

        return row_view;
    }
}
