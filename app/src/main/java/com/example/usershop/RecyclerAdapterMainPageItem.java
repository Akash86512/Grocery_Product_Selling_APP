package com.example.usershop;

/**
 * Created by Amisha on 22-02-2019.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * Created by csa on 3/7/2017.
 */

public class RecyclerAdapterMainPageItem extends RecyclerView.Adapter<RecyclerAdapterMainPageItem.MyHolder> {

    Context ctx;
    ArrayList<Listpincode> imageModelArrayList;


    public RecyclerAdapterMainPageItem(ArrayList<Listpincode> allitms, Context applicationContext) {
        ctx=applicationContext;
        imageModelArrayList=allitms;

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( ctx).inflate(R.layout.mainpageitemrecycler,parent,false);
        MyHolder myHolder = new MyHolder(view);



        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.name.setText(imageModelArrayList.get(position).allitm.getItemname());
        //   holder.price.setText("₹ "+imageModelArrayList.get(position).allitm.getItempriceperkg());
        //  holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getOneItemquantity());
        //  holder.brandname.setText(imageModelArrayList.get(position).getBrandname());


        if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("Only_Cartoon")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getOnecartoonprice());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getOnecartoonweight().toString() + " " + imageModelArrayList.get(position).allitm.getOnecartoonunit().toString());
        } else if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("Cartton-lari-pices")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getItempriceperpice());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getItemweight().toString() + " " + imageModelArrayList.get(position).allitm.getItemunit().toString());

        } else if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("BagKg")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getItempriceperkg());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getOnebagunit());

        } else if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("Cartton-Box-pices")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getItempriceperpice());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getItemweight().toString() + " " + imageModelArrayList.get(position).allitm.getItemunit().toString());

        } else if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("Cartoon_pices")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getItempriceperpice());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getItemweight().toString() + " " + imageModelArrayList.get(position).allitm.getItemunit().toString());

        } else if ((imageModelArrayList.get(position).allitm.getItemCategory().toString()).equals("Cartoon_pices")) {
            holder.price.setText("₹ " + imageModelArrayList.get(position).allitm.getOnebagprice());
            holder.OneItemQuantity.setText(imageModelArrayList.get(position).allitm.getOnebagquantity().toString() + " " + imageModelArrayList.get(position).allitm.getItemunit().toString());

        }
        Picasso.with(ctx).load(imageModelArrayList.get(position).allitm.getItemimageurl())
                .into(holder.img);


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Only_Cartoon")) {
                    Intent i = new Intent(ctx, OnlyCartoon.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonweight", imageModelArrayList.get(position).allitm.getOnecartoonweight().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit().toString());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartton-lari-pices")) {
                    Intent i = new Intent(ctx, CartoonLariPice.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());
                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("onelariprice", imageModelArrayList.get(position).allitm.getOnelariprice().toString());
                    i.putExtra("onelariquantity", imageModelArrayList.get(position).allitm.getOnelariquantity().toString());
                    i.putExtra("onelariunit", imageModelArrayList.get(position).allitm.getOnelariunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartton-Box-pices")) {
                    Intent i = new Intent(ctx, CartoonBoxPice.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("oneboxprice", imageModelArrayList.get(position).allitm.getOneboxprice().toString());
                    i.putExtra("oneboxquantity", imageModelArrayList.get(position).allitm.getOneboxquantity().toString());
                    i.putExtra("oneboxunit", imageModelArrayList.get(position).allitm.getOneboxunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartoon_pices")) {
                    Intent i = new Intent(ctx, Cartoonpices.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Only_Bag")) {
                    Intent i = new Intent(ctx, OnlyBag.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("onebagprice", imageModelArrayList.get(position).allitm.getOnebagprice().toString());
                    i.putExtra("onebagweight", imageModelArrayList.get(position).allitm.getOnebagquantity());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("BagKg")) {
                    Intent i = new Intent(ctx, Bagkg.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());
                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itemprice", imageModelArrayList.get(position).allitm.getItempriceperkg().toString());

                    i.putExtra("onebagprice", imageModelArrayList.get(position).allitm.getOnebagprice().toString());
                    i.putExtra("onebagweight", imageModelArrayList.get(position).allitm.getOnebagquantity());
                    i.putExtra("onebagunit", imageModelArrayList.get(position).allitm.getOnebagunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                }

            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Only_Cartoon")) {
                    Intent i = new Intent(ctx, OnlyCartoon.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonweight", imageModelArrayList.get(position).allitm.getOnecartoonweight().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit().toString());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartton-lari-pices")) {
                    Intent i = new Intent(ctx, CartoonLariPice.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());
                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("onelariprice", imageModelArrayList.get(position).allitm.getOnelariprice().toString());
                    i.putExtra("onelariquantity", imageModelArrayList.get(position).allitm.getOnelariquantity().toString());
                    i.putExtra("onelariunit", imageModelArrayList.get(position).allitm.getOnelariunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartton-Box-pices")) {
                    Intent i = new Intent(ctx, CartoonBoxPice.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());
                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("oneboxprice", imageModelArrayList.get(position).allitm.getOneboxprice().toString());
                    i.putExtra("oneboxquantity", imageModelArrayList.get(position).allitm.getOneboxquantity().toString());
                    i.putExtra("oneboxunit", imageModelArrayList.get(position).allitm.getOneboxunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Cartoon_pices")) {
                    Intent i = new Intent(ctx, Cartoonpices.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("itempriceperpices", imageModelArrayList.get(position).allitm.getItempriceperpice().toString());
                    i.putExtra("itemweight", imageModelArrayList.get(position).allitm.getItemweight().toString());
                    i.putExtra("itemunit", imageModelArrayList.get(position).allitm.getItemunit());

                    i.putExtra("onecartoonprice", imageModelArrayList.get(position).allitm.getOnecartoonprice().toString());
                    i.putExtra("onecartoonquantity", imageModelArrayList.get(position).allitm.getOnecartoonquantity().toString());
                    i.putExtra("onecartoonunit", imageModelArrayList.get(position).allitm.getOnecartoonunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("Only_Bag")) {
                    Intent i = new Intent(ctx, OnlyBag.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("onebagprice", imageModelArrayList.get(position).allitm.getOnebagprice().toString());
                    i.putExtra("onebagweight", imageModelArrayList.get(position).allitm.getOnebagquantity());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                } else if ((imageModelArrayList.get(position).allitm.getItemCategory().trim()).equals("BagKg")) {
                    Intent i = new Intent(ctx, Bagkg.class);

                    i.putExtra("image", imageModelArrayList.get(position).allitm.getItemimageurl().toString());
                    i.putExtra("brandname", imageModelArrayList.get(position).allitm.getBrandname().toString());
                    i.putExtra("itemname", imageModelArrayList.get(position).allitm.getItemname().toString());
                    i.putExtra("itemprice", imageModelArrayList.get(position).allitm.getItempriceperkg().toString());

                    i.putExtra("itemcategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.putExtra("itemid11", imageModelArrayList.get(position).allitm.getItemid().toString());
                    i.putExtra("onebagprice", imageModelArrayList.get(position).allitm.getOnebagprice().toString());
                    i.putExtra("onebagweight", imageModelArrayList.get(position).allitm.getOnebagquantity());
                    i.putExtra("onebagunit", imageModelArrayList.get(position).allitm.getOnebagunit());

                    i.putExtra("itemdescription", imageModelArrayList.get(position).allitm.getItemdescription().toString());
                    i.putExtra("itemCategory", imageModelArrayList.get(position).allitm.getItemCategory().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("pin", imageModelArrayList.get(position).getPincode());
                    ctx.startActivity(i);

                }

            }

        });

    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(imageModelArrayList.size()==0){

                arr = 0;

            }
            else{

                arr=imageModelArrayList.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        LinearLayout linearLayout;
        TextView button;
        private AutofitTextView name;;
            TextView price;;
        TextView OneItemQuantity;;

            TextView brandname;
        ImageView img;

        private ItemClickListner itemClickListner;




        public MyHolder(View itemView) {
            super(itemView);

            linearLayout =itemView.findViewById(R.id.lay1);
            button=itemView.findViewById(R.id.buy);
            name =  itemView.findViewById(R.id.itemname);
            price = (TextView) itemView.findViewById(R.id.ItemPrice);
            OneItemQuantity = (TextView) itemView.findViewById(R.id.weight);

            // brandname = (TextView) itemView.findViewById(R.id.brandnm);
            img=itemView.findViewById(R.id.img);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
