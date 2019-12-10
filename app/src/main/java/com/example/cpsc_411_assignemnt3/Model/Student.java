package com.example.cpsc_411_assignemnt3.Model;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Student extends PersistentObject {

    protected String sFirstName;
    protected String sLastName;
    protected String sCWID;

    protected ArrayList<Courses> mCourses;

    public Student(String firstName, String lastName, String CWID) {
        sFirstName = firstName;
        sLastName = lastName;
        sCWID = CWID;
    }

    public Student() {}

    public String getFirstName() {
        return sFirstName;
    }

    public String getLastName() {
        return sLastName;
    }

    public void setCourses(ArrayList<Courses> courses) {
        mCourses = courses;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", sFirstName);
        vals.put("LastName", sLastName);
        vals.put("CWID", sCWID);
        db.insert("Student", null, vals);
        for(int i=0; i < mCourses.size(); i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        sFirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        sLastName = cursor.getString(cursor.getColumnIndex("LastName"));
        sCWID = cursor.getString(cursor.getColumnIndex("CWID"));

        mCourses = new ArrayList<Courses>();
        Cursor c = db.query("CourseEnrollment", null, "Student=?", new String[]{sCWID}, null, null, null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                Courses cObj = new Courses();
                cObj.initFrom(db, c);
                mCourses.add(cObj);
            }
        }
    }
}
