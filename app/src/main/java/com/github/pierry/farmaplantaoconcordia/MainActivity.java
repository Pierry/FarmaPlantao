package com.github.pierry.farmaplantaoconcordia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
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
  @ViewById TextView title;
  @ViewById TextView content;
  @ViewById ImageView directions;
  @ViewById CardView cardView;
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
    FontfaceHelper.setFontFace(this, title);
    FontfaceHelper.setFontFace(this, content);
    FontfaceHelper.setFontFace(this, today);
  }

  @UiThread void load() {
    if (drugstore != null) {
      title.setText(drugstore.getName());
      content.setText(drugstore.getAddress());
    }
  }

  @Click void directions() {
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
