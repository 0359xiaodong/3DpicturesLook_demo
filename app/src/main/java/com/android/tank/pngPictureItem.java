package com.android.tank;

/**
 * Created by huangchuanliang on 2015/3/20.
 * �洢ͼƬ�������Ϣ
 */
public class pngPictureItem {
    public String pathOfPicture;//ͼƬ��ַ
    public String takeTime;//���յ�ʱ��


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
