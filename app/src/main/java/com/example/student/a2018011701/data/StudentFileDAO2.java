package com.example.student.a2018011701.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */
//實作介面出來，即強迫你一定要有介面裡的那些方法(這樣你的介面就跟他的一樣，所以才叫介面)
//硬性規定你一定要有哪些方法
public interface StudentFileDAO2 {
    public boolean add(student s);
    public ArrayList<student> getList();
    public student getStudent(int id);
    public boolean update(student s);
    public boolean delete(int id);

}
