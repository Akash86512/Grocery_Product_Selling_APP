package com.example.usershop;

public class ImageTypedata {

   private String imageurl,name;

    public ImageTypedata() {


    }

    public ImageTypedata(String imageurl, String name) {
        this.imageurl = imageurl;
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getName() {
        return name;
    }
}
