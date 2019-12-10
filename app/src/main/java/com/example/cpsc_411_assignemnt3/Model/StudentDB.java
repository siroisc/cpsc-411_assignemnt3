package com.example.cpsc_411_assignemnt3.Model;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {
    protected ArrayList<Student> sStudent;

    protected SQLiteDatabase sSQLiteDatabase;

    public StudentDB(Context context) {
        File dbFile = context.getDatabasePath("Student.db");
        sSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        sSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        sSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");

    }

    public ArrayList<Student> getStudents() {
        return sStudent;
    }

    public void addStudent(Student Student){
        Student.insert(sSQLiteDatabase);
    }

    public ArrayList<Student> retrieveStudentObjects(){
        sStudent = new ArrayList<Student>();
        Cursor c = sSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if (c.getCount() > 0){
            while(c.moveToNext()){
                Student sObj = new Student();
                sObj.initFrom(sSQLiteDatabase, c);
                sStudent.add(sObj);
            }
        }
        return sStudent;
    }

}
