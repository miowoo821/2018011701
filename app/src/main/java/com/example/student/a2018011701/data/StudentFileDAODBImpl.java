package com.example.student.a2018011701.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/22.
 */

public class StudentFileDAODBImpl implements StudentFileDAO2 {
    Context context;
    SQLiteDatabase db;
    public StudentFileDAODBImpl(Context context)
    {
        this.context = context;
        MyDbHelper helper = new MyDbHelper(context);
        db = helper.getWritableDatabase();
    }
    @Override
    public boolean add(student s) {
        ContentValues cv=new ContentValues();
        cv.put("_id",s.id);
        cv.put("name",s.name);
        cv.put("score",s.score);
        db.insert("students",null,cv);
        return true;
    }

    @Override
    public ArrayList<student> getList() {
        ArrayList<student> mylist=new ArrayList<>();
        Cursor c=db.query("students", new String[] {"_id", "name", "score"}, null, null, null, null, null);

        if(c.moveToFirst()){
            student s1=new student(c.getInt(0),c.getString(1),c.getInt(2));
            mylist.add(s1);
            while (c.moveToNext()){
                student s=new student(c.getInt(0),c.getString(1),c.getInt(2));
                mylist.add(s);
            }
        }

        return mylist;
    }

    @Override
    public student getStudent(int id) {
        Cursor c=db.query("students", new String[] {"_id", "name", "score"}, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()) {
            student s1 = new student(c.getInt(0), c.getString(1), c.getInt(2));
            return s1;
        }
        return null;
    }

    @Override
    public boolean update(student s) {
        ContentValues cv = new ContentValues();
        cv.put("name", s.name);
        cv.put("score", s.score);
        db.update("students", cv, "_id=?", new String[] {String.valueOf(s.id)});
        return true;
    }

    @Override
    public boolean delete(int id) {
        db.delete("students", "_id=?", new String[] {String.valueOf(id)});
        return true;
    }
}
