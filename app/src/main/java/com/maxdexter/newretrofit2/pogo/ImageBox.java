package com.maxdexter.newretrofit2.pogo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ImageBox {
private static ImageBox sImageBox;
    private List<Image> allImage;

    public List<Image> getAllImage() {
        return allImage;
    }

    public void setAllImage(Image image) {
        allImage.add(image);
    }



    private ImageBox() {
        allImage = new ArrayList<>();
    }

    public static ImageBox getImageBox() {
        if(sImageBox == null){
            sImageBox = new ImageBox();
        }
        return sImageBox;
    }

}
