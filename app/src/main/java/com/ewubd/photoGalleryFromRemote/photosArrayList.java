package com.ewubd.photoGalleryFromRemote;

public class photosArrayList {
    String imageid;
    String description;

    public photosArrayList(String imageid, String description){
        this.imageid = imageid;
        this.description = description;
    }

    public String getImageID() {
        return imageid;
    }

    public void setImageID(String imageid) {
        this.imageid = imageid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
