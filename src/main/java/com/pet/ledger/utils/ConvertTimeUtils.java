package com.pet.ledger.utils;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ConvertTimeUtils {

    private ConvertTimeUtils() {}

    public static String convertSecToDate(long milliseconds) {
        DateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
        Date result = new Date(milliseconds);
        return simple.format(result);
    }

    public static long convertDateToSec(String date) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date result = simple.parse(date);
           return result.getTime();
        } catch (ParseException e) {
            Logger.getLogger("Fail convert date to second");
            return -1;
        }
    }


}
