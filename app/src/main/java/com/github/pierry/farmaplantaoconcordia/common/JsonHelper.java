package com.github.pierry.farmaplantaoconcordia.common;

import android.content.Context;
import com.github.pierry.farmaplantaoconcordia.domain.Drugstore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean public class JsonHelper {

  private static final String JSON_NAME = "farmaconcordia.json";
  private static final String CHARSET = "UTF-8";

  private Context context;

  public JsonHelper(Context context) {
    this.context = context;
  }

  private JsonArray getJson() {
    String json;
    try {
      InputStream is = context.getAssets().open(JSON_NAME);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, CHARSET);
      JsonParser parser = new JsonParser();
      return parser.parse(json).getAsJsonArray();
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public List<Drugstore> stores() {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Drugstore>>() {
    }.getType();
    return (List<Drugstore>) gson.fromJson(getJson(), listType);
  }
}
