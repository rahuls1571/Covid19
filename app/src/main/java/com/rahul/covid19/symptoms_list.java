package com.rahul.covid19;

public class symptoms_list {
    private String title;
    private String detail;
    private int img;
   // private Context context;
    public symptoms_list(String title, String detail,int img) {
        this.title = title;
        this.detail = detail;
        this.img = img;
       // this.context = context;
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
