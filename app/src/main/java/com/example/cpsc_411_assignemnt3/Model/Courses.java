package com.example.cpsc_411_assignemnt3.Model;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

public class Courses extends PersistentObject {
    protected String sCourseID;
    protected String sGrade;
    protected String sStudent;

    public Courses(String courseID, String grade, String student) {
        sCourseID = courseID;
        sGrade = grade;
        sStudent = student;
    }

    public Courses() {}

    public String getCourseID() {
        return sCourseID;
    }

    public void setCourseID(String courseID) {
        sCourseID = courseID;
    }

    public String getGrade() {
        return sGrade;
    }

    public void setGrade(String grade) {
        sGrade = grade;
    }

    public String getStudent() {
        return sStudent;
    }

    public void setStudent(String student) {
        sStudent = student;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("CourseID", sCourseID);
        value.put("Grade", sGrade);
        value.put("Student", sStudent);
        db.insert("CourseEnrollment", null, value);
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        sCourseID = cursor.getString(cursor.getColumnIndex("CourseID"));
        sGrade = cursor.getString(cursor.getColumnIndex("Grade"));
        sStudent = cursor.getString(cursor.getColumnIndex("Student"));
    }
}
