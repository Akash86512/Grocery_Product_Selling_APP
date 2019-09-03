package com.example.usershop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;


public class HomePagefrag extends Fragment {

    public HomePagefrag() {
        // Required empty public constructor
    }
    GridView gridView;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10;
    TextView categoryname1,categoryname2,categoryname3,categoryname4,categoryname5,categoryname6,categoryname7,categoryname8,categoryname9,categoryname10;
    static final String[] numbers = new String[] {
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",};

    RecyclerAdapterMainPageItem recyclerAdapterMainPageItem;
    RecyclerAdapterproductscategory recyclerAdapterproductscategory;
    RecyclerView recyclerView,recyclerView1;

    ArrayList<Listpincode> allitms;
    ArrayList<Listpincode> list=new ArrayList<>();

    @SuppressLint("ValidFragment")
    public HomePagefrag(ArrayList<Listpincode> allitms) {
        this.allitms = allitms;
    }


    SliderLayout sliderLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pagefrag, container, false);
        {
            recyclerView=view.findViewById(R.id.recyclemainpage);
            recyclerView1=view.findViewById(R.id.recyclecategory);
            image1=view.findViewById(R.id.image1);
            image2=view.findViewById(R.id.image2);
            image3=view.findViewById(R.id.image3);
            image4=view.findViewById(R.id.image4);
            image5=view.findViewById(R.id.image5);
            image6=view.findViewById(R.id.image6);
            image7=view.findViewById(R.id.image7);
            image8=view.findViewById(R.id.image8);
            image9=view.findViewById(R.id.image9);
            image10=view.findViewById(R.id.image10);

            categoryname1=view.findViewById(R.id.categoryname1);
            categoryname2=view.findViewById(R.id.categoryname2);
            categoryname3=view.findViewById(R.id.categoryname3);
            categoryname4=view.findViewById(R.id.categoryname4);
            categoryname5=view.findViewById(R.id.categoryname5);
            categoryname6=view.findViewById(R.id.categoryname6);
            categoryname7=view.findViewById(R.id.categoryname7);
            categoryname8=view.findViewById(R.id.categoryname8);
            categoryname9=view.findViewById(R.id.categoryname9);
            categoryname10=view.findViewById(R.id.categoryname10);



            RecyclerView.LayoutManager recyce1 = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
            /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
            // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView1.setLayoutManager(recyce1);
            // recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            recyclerView1.setItemAnimator( new DefaultItemAnimator());

            RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),3,GridLayoutManager.HORIZONTAL,false);
            /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
            // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setLayoutManager(recyce);
            // recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setItemAnimator( new DefaultItemAnimator());

            sliderLayout=view.findViewById(R.id.imageslider);
            sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
            sliderLayout.setScrollTimeInSec(1);

            data1();
            data12();


            recyclerAdapterMainPageItem=new RecyclerAdapterMainPageItem(allitms,getActivity());
            recyclerView.setAdapter(recyclerAdapterMainPageItem);
            // Inflate the layout for this fragment









            return view;

        }


    }

    ArrayList<String> imageurl=new ArrayList<>();
    private DatabaseReference mDatabase;
    void data1()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("uploadedItemDetails").child("Homepageimagetype").child("SlideImage");

        final SpotsDialog spotsDialog = new SpotsDialog(getActivity());
        spotsDialog.show();
        spotsDialog.setMessage("Loading");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                imageurl.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    String imageurls= (String) dataSnapshot1.child("imageurl").getValue();
                    imageurl.add(imageurls);


                }
                setSliderViews();
                spotsDialog.dismiss();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    ArrayList<ImageTypedata> imageTypedata=new ArrayList<>();
    void data12()
    {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("uploadedItemDetails").child("Homepageimagetype").child("CategoryImage");



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                imageTypedata.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    ImageTypedata allitm=dataSnapshot1.getValue(ImageTypedata.class);
                    imageTypedata.add(allitm);


                }
                category();
//                recyclerAdapterproductscategory=new RecyclerAdapterproductscategory(imageTypedata,getActivity());
//                recyclerView1.setAdapter(recyclerAdapterproductscategory);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void  category()
    {
        for (int i=0;i<imageTypedata.size();i++)
        {

            if(i==0)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image1);
                categoryname1.setText(imageTypedata.get(i).getName());
            }
            if(i==1)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image2);
                categoryname2.setText(imageTypedata.get(i).getName());
            }
            if(i==2)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image3);
                categoryname3.setText(imageTypedata.get(i).getName());
            }
            if(i==3)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image4);
                categoryname4.setText(imageTypedata.get(i).getName());
            }
            if(i==4)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image5);
                categoryname5.setText(imageTypedata.get(i).getName());
            }
            if(i==5)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image6);
                categoryname6.setText(imageTypedata.get(i).getName());
            }
            if(i==6)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image7);
                categoryname7.setText(imageTypedata.get(i).getName());
            }
            if(i==7)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image8);
                categoryname8.setText(imageTypedata.get(i).getName());
            }
            if(i==8)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image9);
                categoryname1.setText(imageTypedata.get(i).getName());
            }
            if(i==9)
            {
                Picasso.with(getActivity()).load(imageTypedata.get(i).getImageurl()).into(image10);
                categoryname9.setText(imageTypedata.get(i).getName());
            }







        }
    }


    private void setSliderViews(){
        int i;
        for(i=0;i<imageurl.size();i++){

            DefaultSliderView sliderView=new DefaultSliderView(getActivity());

            sliderView.setImageUrl(imageurl.get(i));

//            switch(i){
//                case 0:
//                    sliderView.setImageDrawable(R.drawable.download);
//                    // sliderView.setImageUrl("https://www.google.com/search?q=nature+image&rlz=1C1CHZL_enIN844IN844&tbm=isch&source=iu&ictx=1&fir=nYJQfRu5GDnPfM%253A%252CJ8A-uvfJQp6tLM%252C_&vet=1&usg=AI4_-kQ0Lznb_n-8siaZxdbal-Twaa27ug&sa=X&ved=2ahUKEwjcv6XepM3jAhWPiXAKHep6B54Q9QEwAHoECAMQBA#imgrc=nYJQfRu5GDnPfM:");
//                    break;
//                case 1:
//                    sliderView.setImageDrawable(R.drawable.download1);
//
//                    //  sliderView.setImageUrl("https://www.google.com/search?q=nature+image&rlz=1C1CHZL_enIN844IN844&tbm=isch&source=iu&ictx=1&fir=J94m3SDMuRSZ2M%253A%252CIS1eYpTbz5glDM%252C_&vet=1&usg=AI4_-kQ0SnnX5NScQeR8bjdC6EA5JLs9SQ&sa=X&ved=2ahUKEwjcv6XepM3jAhWPiXAKHep6B54Q9QEwBXoECAMQDg#imgrc=J94m3SDMuRSZ2M:");
//                    break;
//                case 2:
//                    sliderView.setImageDrawable(R.drawable.download2);
//
//                    // sliderView.setImageUrl("https://www.google.com/search?q=nature+image&rlz=1C1CHZL_enIN844IN844&tbm=isch&source=iu&ictx=1&fir=z-m6k0JuLB0aCM%253A%252CBa_eiczVaD9-zM%252C_&vet=1&usg=AI4_-kSOMzBjYGztTXDCVQvMYhUEisW08Q&sa=X&ved=2ahUKEwjcv6XepM3jAhWPiXAKHep6B54Q9QEwCXoECAMQFg#imgrc=z-m6k0JuLB0aCM:");
//                    break;
//                case 3:
//                    sliderView.setImageDrawable(R.drawable.download1);
//
//                    //  sliderView.setImageUrl("https://www.google.com/search?q=nature+image&rlz=1C1CHZL_enIN844IN844&tbm=isch&source=iu&ictx=1&fir=RJ8ha90S195uOM%253A%252C97pMe5Bd8THbrM%252C_&vet=1&usg=AI4_-kSnynRrgiiJVDsA6tqogA1owiUvlQ&sa=X&ved=2ahUKEwjcv6XepM3jAhWPiXAKHep6B54Q9QEwCHoECAMQFA#imgrc=RJ8ha90S195uOM:");
//                    break;
//            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("Image"+i);
            final int finalI=i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(),"Image Clicked",Toast.LENGTH_SHORT).show();
                }
            });

            sliderLayout.addSliderView(sliderView);

        }




    }
}