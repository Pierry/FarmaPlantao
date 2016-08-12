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

  public String currentGreetings() {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    String datetime = dateformat.format(c.getTime());
    String abr = abr(datetime.substring(3, 5));
    String greetings =
        "Hoje, dia " + datetime.substring(0, 2) + " de " + abr + " de " + datetime.substring(6);
    return greetings;
  }

  private String abr(String month) {
    String abr = "";
    switch (month) {
      case "01":
        abr = "Janeiro";
        break;
      case "02":
        abr = "Fevereiro";
        break;
      case "03":
        abr = "Março";
        break;
      case "04":
        abr = "Abril";
        break;
      case "05":
        abr = "Maio";
        break;
      case "06":
        abr = "Junho";
        break;
      case "07":
        abr = "Julho";
        break;
      case "08":
        abr = "Agosto";
        break;
      case "09":
        abr = "Setembro";
        break;
      case "10":
        abr = "Outubro";
        break;
      case "11":
        abr = "Novembro";
        break;
      case "12":
        abr = "Dezembro";
        break;
    }
    return abr;
  }
}