package com.example.usershop;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MyOrderfrag extends Fragment {

    public MyOrderfrag() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    RecyclerAdapterMyOrder recyclerAdapterMyOrder;

    List<Placeaddress> placeaddresses=new ArrayList<>();

    FirebaseAuth firebaseAuth;
    String number;

    FirebaseDatabase db;
    DatabaseReference requests;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_orderfrag, container, false);
        {
            recyclerView=view.findViewById(R.id.recyclerOrder);

            requests = FirebaseDatabase.getInstance().getReference().child("uploadedItemDetails").child("249410").child("request");

            RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),1);
            /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
            // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setLayoutManager(recyce);
            recyclerView.setItemAnimator( new DefaultItemAnimator());


            firebaseAuth= FirebaseAuth.getInstance();
            number=firebaseAuth.getCurrentUser().getPhoneNumber();

            data();

            // Inflate the layout for this fragment
            return view;

        }


    }

    void data()
    {

        requests.child(number).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeaddresses.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Placeaddress placeaddress1=dataSnapshot1.getValue(Placeaddress.class);

                    placeaddresses.add(placeaddress1);
                }

                recyclerAdapterMyOrder=new RecyclerAdapterMyOrder(placeaddresses,getActivity());
                recyclerView.setAdapter(recyclerAdapterMyOrder);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

