package com.rahul.covid19;

public class precaution_list {
    private String title;
    private String detail;
    private int img;
    public precaution_list(String title, String detail,int img){
        this.title = title;
        this.detail = detail;
        this.img = img;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
