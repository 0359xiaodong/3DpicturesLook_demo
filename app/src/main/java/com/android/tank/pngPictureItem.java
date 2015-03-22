package com.android.tank;

/**
 * Created by huangchuanliang on 2015/3/20.
 * 存储图片的相关信息
 */
public class pngPictureItem {
    public String pathOfPicture;//图片地址
    public String takeTime;//拍照的时间


    public pngPictureItem(String path,String takeTime){
        this.pathOfPicture = path;
        this.takeTime = takeTime;
    }


    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getPathOfPicture() {
        return pathOfPicture;
    }

    public void setPathOfPicture(String pathOfPicture) {
        this.pathOfPicture = pathOfPicture;
    }

}
