package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CursorUtils
{

    public static boolean checkCursor(String cursor)
    {
        if(cursor == null || cursor.isEmpty())
        {
            return true;
        }
        try {
            LocalDateTime.parse(cursor);
            return false;
        }
        catch (Exception e)
        {
            return true;
        }
    }

    public static LocalDateTime parseCursor(String cursor)
    {
        if(checkCursor(cursor))
        {
            throw new IllegalArgumentException("Invalid Cursor");
        }

        return  LocalDateTime.parse(cursor);
    }
}

