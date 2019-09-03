package com.example.usershop;

/**
 * Created by Amisha on 22-02-2019.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class RecyclerAdapterMyOrder extends RecyclerView.Adapter<RecyclerAdapterMyOrder.MyHolder> {

    List<Placeaddress> list;
    Context context;
    Intent intent;
    Placeaddress mylist;
    int position1;
    ArrayList<Addtocartitem> addtocart=new ArrayList<>();

    public RecyclerAdapterMyOrder() {
    }




    public RecyclerAdapterMyOrder(List<Placeaddress> list, Context context) {
        this.list = list;
        this.context =  context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context).inflate(R.layout.order_layout,parent,false);
        MyHolder myHolder = new MyHolder(view);



        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        mylist = list.get(position);


        holder.noofitem.setText(mylist.getNoofitem()
        );
        holder.totalamount.setText(mylist.getTotalamoint());

        holder.status.setText(mylist.getStatus());
        holder.paymentmode.setText(mylist.getPaymentmode());
        holder.date.setText(mylist.getCurrentTime());




        holder.showdeatils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position1=position;

                ArrayList<Addtocartitem> addtocartitems=new ArrayList<>();

                for(int i=0;i<list.get(position1).getAddtocartitem().size();i++)
                {
                    addtocartitems.add(list.get(position1).getAddtocartitem().get(i));
                }
                Toast.makeText(context,"position"+" "+list.get(position1).getAddtocartitem().size(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Myorderitemlist.class);
                intent.putExtra("mylist",addtocartitems);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });


       // mylist.getAddtocartitem();







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


        TextView totalamount,noofitem,status,paymentmode,date,showdeatils;
        LinearLayout linearLayout;




        public MyHolder(View itemView) {
            super(itemView);


            noofitem=itemView.findViewById(R.id.noOfItem);
            totalamount=itemView.findViewById(R.id.totalamount);
            status=itemView.findViewById(R.id.status);
            paymentmode=itemView.findViewById(R.id.paymentmode);
            date=itemView.findViewById(R.id.date);
            showdeatils=itemView.findViewById(R.id.showdetails);



        }

        @Override
        public void onClick(View v) {

        }
    }

}
