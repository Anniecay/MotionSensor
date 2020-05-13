package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GPS extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationProvider;


    TextView latText, longText, distanceText;
    LocationCallback locationCallback;
    LocationRequest locationRequest;
    double newLong, newLat;

    double longitude, latitude;
    float distance;
    Location location;
    Location oldLocation;
    double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }


        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this);

        latText = findViewById(R.id.lat);
        longText = findViewById(R.id.longitude);
        distanceText = findViewById(R.id.dist);

        locationRequest = new LocationRequest().create();
        locationRequest.setInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        getlastLoc();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {

                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {

                    longitude = location.getLongitude();
                    latitude = location.getLatitude();

                    latText.setText("Latitude:\n " + Double.toString(latitude));
                    longText.setText("Longitude: " + Double.toString(longitude));

                        double distance = distanceCalculator(oldLocation, location);
                        distanceText.setText(String.format("%.2f", distance) + " meters");
                        if(distance>100){
                            distanceText.setText("YOU WENT TOO FAR");
                        }

                }
            }
        };


        mFusedLocationProvider.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    public void getlastLoc() {

        mFusedLocationProvider.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                oldLocation = location;
            }
        });
    }

    public double distanceCalculator(Location location1, Location location2){

        if(location1!=null&& location2!=null){

            double distance = location1.distanceTo(location2);
            return distance;
        }else return 0.0;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
