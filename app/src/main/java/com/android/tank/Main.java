package com.android.tank;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class Main extends Activity implements OnItemSelectedListener,
		ViewFactory, OnItemClickListener {
    public static String TAG = "Main";
	private Gallery gallery;
    public static String pictureFilesPath;
    List<pngPictureItem> pictureList = new ArrayList<>();
    List<String> pictureNameList = new ArrayList<>();
    Bitmap[]  bitmaps =null;

	//自定义图片的填充方式
	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		public ImageAdapter(Context context) {
			mContext = context;
			TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
			mGalleryItemBackground = typedArray.getResourceId(
					R.styleable.Gallery_android_galleryItemBackground, 0);
		}

		public int getCount() {
			return pictureNameList.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			 ImageView imageView = new ImageView(mContext);

             Bitmap bitmap = bitmaps[position];

			 imageView.setImageBitmap(MyImgView.createReflectedImage(bitmap));

			 imageView.setLayoutParams(new Gallery.LayoutParams(80, 60));
			// imageView.setBackgroundResource(mGalleryItemBackground);
			 return imageView;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// 选中Gallery中某个图像时
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	// ImageSwitcher组件需要这个方法来创建一个View对象（一般为ImageView对象）
	// 来显示图像
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return imageView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 获取文件中的相片
        dealWithPicture();

		final CoverFlow cf = new CoverFlow(this);
		cf.setAdapter(new ImageAdapter(this));
		ImageAdapter imageAdapter = new ImageAdapter(this);
		cf.setAdapter(imageAdapter);//自定义图片的填充方式
		cf.setAnimationDuration(1500);
		cf.setOnItemClickListener(this);
		cf.setOnItemLongClickListener(lonClick);
		setContentView(cf);

	}

    public void dealWithPicture(){
        pictureFilesPath = getSdPath()+File.separator+"learning"+File.separator+"testPicture/";
        File path = new File(pictureFilesPath);
        Log.i(TAG,"sd path----file path "+getSdPath()+"----"+path.toString());
        pictureNameList = Arrays.asList(path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith("jpg")) {
                    return true;
                }else return false;
            }
        }));

         bitmaps = new Bitmap[pictureNameList.size()];


         for (int i =0;i < pictureNameList.size();i++) {
             BitmapFactory.Options options=new BitmapFactory.Options();
             options.inJustDecodeBounds = true;
             BitmapFactory.decodeFile(pictureFilesPath + pictureNameList.get(i), options);
             options.inSampleSize = calculataInSample(options,100,68);
             options.inJustDecodeBounds =false;
             bitmaps[i] = BitmapFactory.decodeFile(pictureFilesPath + pictureNameList.get(i),options);
         }

        Log.i(TAG,"图片地址集合----》"+pictureNameList);



    }

    public int calculataInSample(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final int width=options.outWidth;
        final int height=options.outHeight;
        Log.i(TAG,"width:height---->"+width+":"+height);
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth){
            if (width < height){
                inSampleSize = Math.round( (float)width / (float)reqWidth);
            }else {
                inSampleSize = Math.round( (float)height / (float)reqHeight);
            }
        }
        return inSampleSize;
    }

    public String getSdPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist){
            Log.i(TAG,"存在SD卡");
            sdDir = Environment.getExternalStorageDirectory();
        }
         return sdDir.toString();
    }
	// 点击某项时
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub


	}

	// 长按事件，长按打开软件
	public OnItemLongClickListener lonClick = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {

			return true;
		}
	};



}