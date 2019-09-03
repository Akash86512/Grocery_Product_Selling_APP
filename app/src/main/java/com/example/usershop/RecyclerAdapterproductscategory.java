package com.example.usershop;

/**
 * Created by Amisha on 22-02-2019.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class RecyclerAdapterproductscategory extends RecyclerView.Adapter<RecyclerAdapterproductscategory.MyHolder> {

    List<ImageTypedata> list;
    Context context;
    Intent intent;
    private  ImageTypedata mylist1;


    public RecyclerAdapterproductscategory(ArrayList<ImageTypedata> imageTypedata, FragmentActivity activity) {
        this.list = imageTypedata;
        this.context =  activity;
    }







    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context).inflate(R.layout.productscategory,parent,false);
        MyHolder myHolder = new MyHolder(view);



        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        mylist1 = list.get(position);



        Picasso.with(context).load(mylist1.getImageurl()).into(holder.imageView);
        holder.categoruy.setText(mylist1.getName());
        holder.product.setText("40 Products");












    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    ImageView imageView;
    TextView categoruy,product;




        public MyHolder(View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.img);
            categoruy=itemView.findViewById(R.id.name);
            product=itemView.findViewById(R.id.noofproduct);




        }

        @Override
        public void onClick(View v) {

        }
    }

}
