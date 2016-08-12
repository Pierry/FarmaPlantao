package com.github.pierry.farmaplantaoconcordia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import com.github.pierry.farmaplantaoconcordia.common.DateHelper;
import com.github.pierry.farmaplantaoconcordia.common.FontfaceHelper;
import com.github.pierry.farmaplantaoconcordia.domain.Drugstore;
import com.github.pierry.farmaplantaoconcordia.domain.IDrugstoreRepository;
import com.github.pierry.farmaplantaoconcordia.repositories.DrugstoreRepository;
import com.github.pierry.farmaplantaoconcordia.ui.ToolbarBase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity
    implements OnMapReadyCallback {

  @ViewById TextView today;
  @ViewById TextView city;
  @ViewById TextView street;
  @ViewById TextView local;
  @ViewById Button navigate;
  @ViewById Toolbar toolbar;

  @Bean(DrugstoreRepository.class) IDrugstoreRepository drugstoreRepository;
  @Bean(ToolbarBase.class) ToolbarBase toolbarBase;
  @Bean DateHelper dateHelper;

  private GoogleMap mMap;
  private Drugstore drugstore;
  private SupportMapFragment mapFragment;

  @AfterViews void init() {
    toolbar.setTitle(R.string.app_name_title);
    setSupportActionBar(toolbar);
    toolbarBase.injectToolbar(toolbar, this);
    mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
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
      today.setText(dateHelper.currentGreetings());
      city.setText("CONCÓRDIA");
      local.setText(drugstore.getName());
      street.setText(drugstore.getAddress());
      double latitude = Double.valueOf(drugstore.getLatitude());
      double longitude = Double.valueOf(drugstore.getLongitude());
      goToLocation(latitude, longitude);
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

  @Override public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
  }

  @UiThread void goToLocation(double latitude, double longitude) {
    LatLng position = new LatLng(latitude, longitude);
    mMap.addMarker(new MarkerOptions().position(position).title(drugstore.getName()));
    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
  }
}
