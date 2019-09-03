package com.example.usershop;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener {
    NotificationBadge badge;
    ImageView imageView;
    TextView pincode;
    private DatabaseReference mDatabase;
    RelativeLayout relativeLayout;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    protected GoogleApiClient mGoogleApiClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(28.6388999, 77.2223797), new LatLng(28.6548425, 77.1572671));
    int Place1=1;

    ArrayList<Listpincode> allitms;
    ArrayList<Listpincode> list=new ArrayList<>();





     @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.location);
        imageView=findViewById(R.id.img);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SunSky");
        setSupportActionBar(toolbar);
         BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);







        updateMyLocation();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();



        pincode=findViewById(R.id.pincode);




        location();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();


                try {
                    Intent intent=builder.build(MainActivity.this);
                    startActivityForResult(intent,Place1);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }







            }
        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {


                case R.id.navigation_home:
                    loadFragment(new HomePagefrag(allitms));
                    return true;
                case R.id.navigation_dashboard:
                    Intent i=new Intent(MainActivity.this,SearchItem.class);
                    i.putExtra("pincode",zipcode);
                    startActivity(i);
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(new MyOrderfrag());
                    return true;

                case R.id.myorder:



                    return true;
            }
            return false;
        }
    };


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()

                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }




    void data(String pin)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("uploadedItemDetails").child(pin).child("allproducts");

        final SpotsDialog spotsDialog = new SpotsDialog(this);
        spotsDialog.show();
        spotsDialog.setMessage("Loading");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                list.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Allitm allitm=dataSnapshot1.getValue(Allitm.class);
                    Listpincode listpincode=new Listpincode(allitm,"249410");
                    list.add(listpincode);
                }
                spotsDialog.dismiss();
                allitms=list;
                loadFragment(new HomePagefrag(allitms));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {

        if(requestCode==Place1)
        {
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                zip1(place.getLatLng());

               // String address=String.format("%s",place.getLatLng());
               // pincode.setText(address);

            }
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        View view=menu.findItem(R.id.myCart).getActionView();
        badge=(NotificationBadge)view.findViewById(R.id.badge);
        ImageView cart=view.findViewById(R.id.cart_icon);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MyCart.class);
                startActivity(i);
            }
        });


        updateCartCount();
        return true;
    }

    private void updateCartCount() {
        if (badge==null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Addtocartitem> list=new ArrayList<>();
                AddtocartDB addtocartDB=new AddtocartDB(MainActivity.this);
                int a;
                addtocartDB.getAlldata();
                list=addtocartDB.getItem();
                a=list.size();


                if ( a==0)
                    badge.setVisibility(View.INVISIBLE);
                else{
                    badge.setVisibility(View.VISIBLE);
                    badge.setText(""+a);
                }
            }




        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartCount();
    }



    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i=new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(i);


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(MainActivity.this,MyOrders.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    double lat,lon;

    void location() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {


                if (!likelyPlaces.getStatus().isSuccess()) {
                    // Request did not complete successfully
                    //  Log.e(TAG, "Place query did not complete. Error: " + likelyPlaces.getStatus().toString());
                    likelyPlaces.release();
                    return;
                }


                lat = Double.parseDouble(String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().latitude));
                lon =Double.parseDouble( String.format("%s", likelyPlaces.get(0).getPlace().getLatLng().longitude));

                zip();

                likelyPlaces.release();
            }
        });


    }



    Address address1=null;
    String addr="";
    String zipcode="";
    String city="";
    String state="";
    void  zip()
    {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat,lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && addresses.size() > 0){

            addr=addresses.get(0).getAddressLine(0);
            city=addresses.get(0).getLocality();
            state=addresses.get(0).getAdminArea();

            for(int i=0 ;i<addresses.size();i++){
                address1 = addresses.get(i);
                if(address1.getPostalCode()!=null){
                    zipcode=address1.getPostalCode();

                    Toast.makeText(getApplicationContext(),zipcode,Toast.LENGTH_LONG).show();


                    break;
                }

            }

            data(zipcode);
            pincode.setText(zipcode);
           // pin.setText(zipcode);

        }
    }

    Address address11=null;
    String addr1="";
    String zipcode1="";
    String city1="";
    String state1="";

    void  zip1(LatLng latLng)
    {

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && addresses.size() > 0){

            addr1=addresses.get(0).getAddressLine(0);
            city1=addresses.get(0).getLocality();
            state1=addresses.get(0).getAdminArea();

            for(int i=0 ;i<addresses.size();i++){
                address11 = addresses.get(i);
                if(address11.getPostalCode()!=null){
                    zipcode1=address11.getPostalCode();


                    break;
                }

            }

            pincode.setText(zipcode1);
            data(zipcode1);
            Toast.makeText(getApplicationContext(),zipcode1,Toast.LENGTH_LONG).show();
            // pin.setText(zipcode);

        }
    }



    private void updateMyLocation() {



        // Enable the location layer. Request the location permission if needed.
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //mMap.setMyLocationEnabled(true);
        } else {
            // Uncheck the box until the layer has been enabled and request missing permission.

            PermissionUtils.requestPermission(MainActivity.this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //mMap.setMyLocationEnabled(true);

        }
    }




}
