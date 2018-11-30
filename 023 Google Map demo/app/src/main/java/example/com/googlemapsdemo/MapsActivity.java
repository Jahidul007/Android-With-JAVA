package example.com.googlemapsdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener  {

    private GoogleMap mMap;
    private static  final LatLng  PERTH = new LatLng(-31.9528,115.857342);
    private static  final LatLng  SYDNEY = new LatLng(-34.87365,151.20689);
    private static  final LatLng  BRISBANE = new LatLng(-27.9528,153.857342);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBribane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        List<Marker>  markerList = new ArrayList<>();


        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        mPerth = mMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth"));
        mPerth.setTag(0);
        markerList.add(mPerth);

        mSydney = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mSydney.setTag(0);
        markerList.add(mSydney);

        mBribane = mMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mBribane.setTag(0);
        markerList.add(mBribane);

        mMap.setOnMarkerClickListener(this); // register outer listener
        for(Marker marker : markerList){

            LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));

            //Log.d("Marker", marker.getTitle());

        }
       /* LatLng Sydney1 = new LatLng(-34,151);
        LatLng Mount = new LatLng(27.988119,86.9074655);

        mMap.addMarker(new MarkerOptions().position(Sydney1).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sydney1,13));*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        if(clickCount != null){
            clickCount++;

            marker.setTag(clickCount);
            Toast.makeText(this, marker.getTitle()+" has been clicked "
                   + clickCount +" times ", Toast.LENGTH_SHORT).show();
            System.out.println("Count: "+ clickCount);
        }
        return false;
    }
}
