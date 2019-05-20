package com.htp.domain.date;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CurrentDate {

  private static String DATE_REGEX = "yyyy-MM-dd";

  public CurrentDate() {}

  public Timestamp date() {
    DateFormat dateFormat = new SimpleDateFormat(DATE_REGEX);
    Date date = new Date(System.currentTimeMillis());
    return Timestamp.valueOf(dateFormat.format(date));
  }
}
