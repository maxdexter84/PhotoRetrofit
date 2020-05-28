package com.maxdexter.newretrofit2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maxdexter.newretrofit2.R;
import com.maxdexter.newretrofit2.pogo.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

private List<Image> mImages;

    public ImageAdapter(List<Image> images) {
        mImages = images;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Image image = mImages.get(position);
        holder.bind(image);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_item);
        }
        void bind(Image image){
            Picasso.get().load(image.getUrl()).into(mImageView);
        }
    }
}
