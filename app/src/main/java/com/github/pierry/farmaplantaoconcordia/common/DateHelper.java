package com.github.pierry.farmaplantaoconcordia.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.androidannotations.annotations.EBean;

@EBean public class DateHelper {

  public String current() {
      Calendar c = Calendar.getInstance();
      SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
      String datetime = dateformat.format(c.getTime());
      return datetime;
  }
}
