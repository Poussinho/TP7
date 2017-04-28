package work.pegase.android.demo.tp5;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.pereiraan.session6.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import work.pegase.android.demo.DemoApplication;
import work.pegase.android.demo.tp6.database.model.MarkerPoint;


public class MapsFrag extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private GoogleMap mMap;
    private Switch modeIti;
    private Button gpsPostion;


    private View layoutDB;
    private Button addMarker;
    private Button cancel;
    private View listFrag;
    private EditText title;
    private LatLng posTouch;


    private PolylineOptions iti;
    private ArrayList<Marker> markers = new ArrayList<>();
    private Polyline itineraire;
    private boolean statusIti;

    // Define a listener that responds to location updates
    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            double la = location.getLatitude();
            double lo = location.getLongitude();
            gpsPostion.setText("Localisation [" + location.getProvider() + "] [" + la + "," + lo + "]\nPrécision [" + location.getAccuracy() + " m]");
            mMap.addCircle(new CircleOptions().center(new LatLng(la, lo)).strokeColor(Color.RED).radius(location.getAccuracy()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(la, lo), 16));
            LocationManager locationManager = (LocationManager) DemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(DemoApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DemoApplication.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;
            locationManager.removeUpdates(locationListener);
        }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
    private Marker lastMarker;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_maps,container,false);


        gpsPostion = (Button) root.findViewById(R.id.gpsPostion);
        cancel = (Button) root.findViewById(R.id.cancel);
        addMarker = (Button) root.findViewById(R.id.addMarker);
        listFrag = root.findViewById(R.id.listFrag);

        gpsPostion.setOnClickListener(this);
        cancel.setOnClickListener(this);
        addMarker.setOnClickListener(this);

        title = (EditText) root.findViewById(R.id.title);
        layoutDB = root.findViewById(R.id.layoutDB);

        modeIti = (Switch) root.findViewById(R.id.modeIti);
        modeIti.setOnCheckedChangeListener(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentByTag("MAPS");
        if (mapFragment == null){
            mapFragment = new SupportMapFragment();
        }
        mapFragment.getMapAsync(this);
        getFragmentManager().beginTransaction().replace(R.id.map,mapFragment,"MAPS").commit();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        iti = new PolylineOptions().color(Color.BLUE);
        itineraire = mMap.addPolyline(iti);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);

        LatLng paris = new LatLng(48.8534100, 2.3488000);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, 6));
        //LatLng bruxelles = new LatLng(50.8504500, 4.3487800);
        //LatLng londre = new LatLng(51.5085300, -0.1257400);
        //mMap.addPolygon(new PolygonOptions().add(paris).add(londre).add(bruxelles).strokeColor(Color.GREEN));


        Cursor cursor = MarkerPoint.SQL.getInstance(getContext()).getMarkerPoint(" s%");
        MarkerPoint marker = new MarkerPoint();
        while (cursor.moveToNext()) {
            marker._id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id" )));
            marker.title = cursor.getString(cursor.getColumnIndex("title" ));
            marker.latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude" )));
            marker.longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude" )));
            cursor.getString(cursor.getColumnIndex("title" ));

            Log.e("Trace", "Nombre de ligne : " + cursor.getCount());
        }

        //while (cursor.moveToNext()) {
          //  MarkerPoint marker =
              //      MarkerPoint.SQL.getInstance(getContext()).fromCursor(cursor);
            //Log.e("Trace", "Marker._id : " + marker._id);
        }


    public void refreshMarker(){
        /* TODO : Refresh all marker saved into the Database */
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (statusIti) {
            /* Mode Itineraire */
            iti.add(latLng); // Ajouter un point à l'itinéraire
            mMap.addPolyline(iti);  // Afficher l'itinéraire sur la Map
        } else {
            /* Mode Marker */
            posTouch = latLng;
            if (layoutDB.getVisibility() == View.GONE) {
                layoutDB.setVisibility(View.VISIBLE);
            } else {
                layoutDB.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (lastMarker == marker)
            marker.remove();
        lastMarker = marker;
        return false;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.gpsPostion) {
            /* Demander la lolci*/
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
           // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } else if (v.getId() == R.id.cancel) {
            layoutDB.setVisibility(View.GONE);
            title.setText("");
        } else if (v.getId() == R.id.addMarker && posTouch != null) {
            mMap.addMarker(new MarkerOptions().position(posTouch).title(title.getEditableText().toString()).visible(true));
            /* TODO Save Marker into the database */
            layoutDB.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        statusIti = isChecked;
    }
}
