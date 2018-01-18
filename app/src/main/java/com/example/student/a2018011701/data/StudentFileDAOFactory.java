package com.example.student.a2018011701.data;

import android.content.Context;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentFileDAOFactory {
    public static StudentFileDAO2 getDAOInstance(Context context, DBType dbType)
    {
        switch (dbType)
        {
            case MEMORY:
                return new StudentSourceDAO();
            case FILE:
                return new StudentFileDAO(context);
        }
        return null;
    }
}
