package com.maxdexter.newretrofit2.pogo;

import java.util.ArrayList;
import java.util.List;

public class ImageBox {
    public static ImageBox sImageBox;
    private List<Image> mImages;

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(Image images) {
        mImages.add(images);
    }

    public ImageBox() {
        mImages = new ArrayList<>();
    }

    public static ImageBox getImageBox() {
        if(sImageBox == null){
            sImageBox = new ImageBox();
        }
        return sImageBox;
    }
}
