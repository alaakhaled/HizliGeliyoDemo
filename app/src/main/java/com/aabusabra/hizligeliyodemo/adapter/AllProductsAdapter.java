package com.aabusabra.hizligeliyodemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.aabusabra.hizligeliyodemo.R;
import com.aabusabra.hizligeliyodemo.comm.ProductResult;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;



public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.MyViewHolder> {

    private Context context;
    private List<ProductResult> dataList;
    private Animation animationUp, animationDown;
    private Gson gson;


    public AllProductsAdapter(Context context, List<ProductResult> dataList, Animation animationUp, Animation animationDown) {
        this.context = context;
        this.dataList = dataList;
        this.animationUp = animationUp;
        this.animationDown = animationDown;
    }


    @Override
    public AllProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);

        return new AllProductsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AllProductsAdapter.MyViewHolder holder, final int position) {


        gson = new Gson();
        final ProductResult product = dataList.get(position);

        holder.titletxt.setText(product.getTitle());
        holder.pricestxt.setText(product.getPrice()+" TL");


        if (product.getImage()!= null && !product.getImage().equalsIgnoreCase(""))
        {
            Glide.with(context)
                    .load(product.getImage())
                    .into(holder.productImage);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void removeItem(int position) {

    }
    public void updateItem(ProductResult product, int position)
    {

    }

    public void restoreItem(ProductResult product,int position)
    {

    }

    public void addAll(List<ProductResult> addList) {
        dataList.addAll(addList);
        notifyDataSetChanged();
    }

    public void clearAll() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public List<ProductResult> getData() {
        return dataList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView titletxt, pricestxt;
        private ImageView productImage;


        public MyViewHolder(View itemView) {
            super(itemView);

            titletxt = itemView.findViewById(R.id.productTitletxt);
            pricestxt = itemView.findViewById(R.id.productPricetxt);
            productImage = itemView.findViewById(R.id.product_image);




        }
    }


}
