package com.github.pierry.farmaplantaoconcordia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import com.github.pierry.farmaplantaoconcordia.common.FontfaceHelper;
import com.github.pierry.farmaplantaoconcordia.domain.Drugstore;
import com.github.pierry.farmaplantaoconcordia.domain.IDrugstoreRepository;
import com.github.pierry.farmaplantaoconcordia.repositories.DrugstoreRepository;
import com.github.pierry.farmaplantaoconcordia.ui.ToolbarBase;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity {

  @ViewById TextView today;
  @ViewById TextView city;
  @ViewById TextView street;
  @ViewById TextView local;
  @ViewById Button navigate;
  @ViewById Toolbar toolbar;

  @Bean(DrugstoreRepository.class) IDrugstoreRepository drugstoreRepository;
  @Bean(ToolbarBase.class) ToolbarBase toolbarBase;

  private Drugstore drugstore;

  @AfterViews void init() {
    toolbar.setTitle(R.string.app_name_title);
    setSupportActionBar(toolbar);
    toolbarBase.injectToolbar(toolbar, this);
    faces();
    drugstore = drugstoreRepository.get();
    load();
  }

  @UiThread void faces() {
    FontfaceHelper.setFontFace(this, city);
    FontfaceHelper.setFontFace(this, street);
    FontfaceHelper.setFontFace(this, local);
    FontfaceHelper.setFontFace(this, today);
    FontfaceHelper.setFontFace(this, navigate);
  }

  @UiThread void load() {
    if (drugstore != null) {
      city.setText("CONCÓRDIA");
      local.setText(drugstore.getName());
      street.setText(drugstore.getAddress());
    }
  }

  @Click void navigate() {
    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=loc:"
        + drugstore.getLatitude()
        + ","
        + drugstore.getLongitude()));
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    String mapsPackageName = "com.google.android.apps.maps";
    i.setClassName(mapsPackageName, "com.google.android.maps.MapsActivity");
    i.setPackage(mapsPackageName);
    startActivity(i);
  }
}
