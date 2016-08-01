package com.github.pierry.farmaplantaoconcordia.common;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontfaceHelper {

  public static void setFontFace(Context context, TextView textView) {
    Typeface type = Typeface.createFromAsset(context.getAssets(), "roboto.ttf");
    textView.setTypeface(type);
  }
}
