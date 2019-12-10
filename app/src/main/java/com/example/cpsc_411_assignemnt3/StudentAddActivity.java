package com.example.cpsc_411_assignemnt3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cpsc_411_assignemnt3.Model.Courses;
import com.example.cpsc_411_assignemnt3.Model.Student;
import com.example.cpsc_411_assignemnt3.Model.StudentDB;

import java.util.ArrayList;

public class StudentAddActivity extends AppCompatActivity {
    StudentDB mStudentDB;

    ArrayList<EditText> courseViewList = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_summary_sv);
        mStudentDB = new StudentDB(this);
        courseViewList.add((EditText) findViewById(R.id.course_id));
        courseViewList.add((EditText) findViewById(R.id.grade_id));
        Button addCourseButton = findViewById(R.id.add_course);

        addCourseButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        LinearLayout course_id_ll = findViewById(R.id.courseid_ll_id);
                        LinearLayout grade_ll = findViewById(R.id.grade_ll_id);
                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        EditText course_id = (EditText) inflater.inflate(R.layout.edit_text_row,
                                course_id_ll, false);
                        EditText grade = (EditText) inflater.inflate(R.layout.edit_text_row,
                                grade_ll, false);
                        courseViewList.add(course_id);
                        courseViewList.add(grade);
                        course_id_ll.addView(course_id);
                        grade_ll.addView(grade);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions_positive, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_done)
        {
            ArrayList<Courses> courseList = new ArrayList<Courses>();

            for(int i = 0; i < courseViewList.size(); i += 2)
            {
                courseList.add(new Courses(courseViewList.get(i).getText().toString(),
                        courseViewList.get(i+1).getText().toString(),
                        (((EditText) findViewById(R.id.edit_cwid)).getText().toString())));
            }
            Student s = new Student
                    (
                            ((EditText) findViewById(R.id.edit_fname)).getText().toString(),
                            ((EditText) findViewById(R.id.edit_lname)).getText().toString(),
                            (((EditText) findViewById(R.id.edit_cwid)).getText().toString())
                    );
            s.setCourses(courseList);
            mStudentDB.addStudent(s);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

