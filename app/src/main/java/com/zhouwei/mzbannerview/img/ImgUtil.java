package com.zhouwei.mzbannerview.img;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.zhouwei.mzbannerview.GlideApp;
import com.zhouwei.mzbannerview.R;

/**
 * 加载图片工具类，使用glide
 * 官网：https://github.com/bumptech/glide
 * Created by caibing.zhang on 2017/6/20.
 *
 * 其它说明：
 * 1：清除缓存的方法
        Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
        Glide.get(this).clearMemory();//清理内存缓存  可以在UI主线程中进行
   2：清除掉所有的图片加载请求
        Glide.clear()
   3：动态的GIF图片加载
         Glide.with(context).load(gifUrl).asBitmap().into(imageview); //显示gif静态图片
         Glide.with(context).load(gifUrl).asGif().into(imageview); //显示gif动态图片
   4：滚动加载，不滚动时不加载，提高ListView的效率
     public void onScrollStateChanged(AbsListView view, int scrollState) {
         switch (scrollState){
         case SCROLL_STATE_FLING:
             Log.i("ListView","用户在手指离开屏幕之前，由于滑了一下，视图仍然依靠惯性继续滑动");
             Glide.with(getApplicationContext()).pauseRequests();
         //刷新
         break;
         case SCROLL_STATE_IDLE:
             Log.i("ListView", "视图已经停止滑动");
             Glide.with(getApplicationContext()).resumeRequests();
         break;
         case SCROLL_STATE_TOUCH_SCROLL:
             Log.i("ListView","手指没有离开屏幕，视图正在滑动");
             Glide.with(getApplicationContext()).resumeRequests();
         break;
         }
     }
 */

public class ImgUtil {

    //默认圆角大小
    public static final int DEFAULT_ROUNDSIZE = 45;
    public static final int DEFAULT_AVATAR_ROUNDSIZE = 4;

    /**
     * 加载网络(普通)图片
     * @param imageView
     * @param imageUrl
     */
    public static void displayImage(ImageView imageView, String imageUrl){
        log(imageUrl);
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.building)
                .error(R.mipmap.building)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .centerCrop()  //Glide有两个方法可以设置图片剪裁的策略 ①fitCenter(), ②centerCrop()
                .into(imageView);

        /*Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.mipmap.building).error(R.mipmap.building).centerCrop())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);*/
    }

    /**
     * 加载网络(普通)图片，
     * @param imageView
     * @param imageUrl
     * @param roundSize 圆角大小
     */
    public static void displayImage(ImageView imageView, String imageUrl, int roundSize){
        log(imageUrl);
        if(roundSize<=0){
            roundSize = DEFAULT_ROUNDSIZE;
        }
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.building)
                .error(R.mipmap.building)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .transform(new GlideRoundTransform(imageView.getContext(),roundSize))
                .centerCrop()  //Glide有两个方法可以设置图片剪裁的策略 ①fitCenter(), ②centerCrop()
                .into(imageView);
    }

    /**
     * 加载assets中图片，
     * @param imageView
     * @param assetsPath 路径：如：file:///android_asset/xxx.jpg
     */
    public static void displayImageToAsset(ImageView imageView, String assetsPath){
        log(assetsPath);
        GlideApp.with(imageView.getContext())
                .load(assetsPath)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .into(imageView);
    }

    /**
     * 加载assets中图片，
     * @param imageView
     * @param assetsPath 路径：如：file:///android_asset/xxx.jpg
     * @param roundSize 圆角大小
     */
    public static void displayImageToAsset(ImageView imageView, String assetsPath, int roundSize){
        log(assetsPath);
        if(roundSize<=0){
            roundSize = DEFAULT_ROUNDSIZE;
        }
        GlideApp.with(imageView.getContext())
                .load(assetsPath)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .transform(new GlideRoundTransform(imageView.getContext(),roundSize))
                .into(imageView);
    }

    /**
     * 加载File中图片，
     * @param imageView
     * @param filePath 路径：如："file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"
     */
    public static void displayImageToFile(ImageView imageView, String filePath){
        log(filePath);
        GlideApp.with(imageView.getContext())
                .load(filePath)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .into(imageView);
    }

    /**
     * 加载File中图片，
     * @param imageView
     * @param filePath 路径：如："file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"
     * @param roundSize 圆角大小
     */
    public static void displayImageToFile(ImageView imageView, String filePath, int roundSize){
        log(filePath);
        if(roundSize<=0){
            roundSize = DEFAULT_ROUNDSIZE;
        }
        GlideApp.with(imageView.getContext())
                .load(filePath)
                .transition(new DrawableTransitionOptions().crossFade())//设置淡入淡出效果，默认300ms，可以传参
                .transform(new GlideRoundTransform(imageView.getContext(),roundSize))
                .into(imageView);
    }

    public static void log(String url){
        Log.e("--> url: ", url);
    }

}
