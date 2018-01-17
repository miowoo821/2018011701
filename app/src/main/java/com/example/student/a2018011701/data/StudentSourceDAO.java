package com.example.student.a2018011701.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class StudentSourceDAO {
    public ArrayList<student> mylist;
    //新增一個內含student(類別)變數的物件，因為不只一種型態的變數，
    // 所以無法把所有變數都用int String Object之類的物件去賦予型態，
    //因此用<student>,一次賦予int Strt的型態給其class裡面相對應的變數

    public StudentSourceDAO()    {        mylist = new ArrayList<>();    }

    public void add(student s)    {
        mylist.add(s);
    }
    public ArrayList<student> getList()    {
        return mylist;
    }
    public student getStudent(int id)  {
        for (student s : mylist)
        {
            if (s.id == id){
                return s;
            }
        }
        return null;
    }
    public boolean update(student s) {
        //要傳入完整的變數就用student s，
        // 意思就是新增一個student物件,命名為s，
        // 爾後在使用這個方法的時候，就會需要輸入student的完整建構式
        for (student t : mylist) {
            //新增一個student物件,命名為t，掛載mylist
            //
            if (t.id == s.id) {
                t.name = s.name;//把mylist的name,改為新的s物件裡面的name
                t.score = s.score;//把mylist的score,改為新的s物件裡面的score
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id) {//什麼時候用(student s)，什麼時候用(int id)???
        //參數預設只有一個int id，爾後只要輸入id就可以使用這個方法
        for (int i=0;i<mylist.size();i++) {
            if (mylist.get(i).id==id) {//以id查找mylist裡面相符的
                mylist.remove(i);//然後移除
                return true;
            }
        }
        return false;
    }

}
