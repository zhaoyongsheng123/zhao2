package jinritoutiao.bwie.com.jinritoutiao3.dao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.ShouActivity;
import jinritoutiao.bwie.com.jinritoutiao3.application.ImageLoaderApp;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.bean.YuangGuangBean;
import jinritoutiao.bwie.com.jinritoutiao3.utils.BitmapUtils;

/**
 * Created by zhao on 2017/3/20.
 */
public class YuangGuangAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<YuangGuangBean.视频Bean> list;
    private String mp4_url;
    private String mp4_urls;
    private String topicImg;
    private String topicDesc;
    private ImageView imageView;
    private String topicName;
    private String title;
    private String ptime;
    private AlertDialog builder;

    public YuangGuangAdapter(Context context, ArrayList<YuangGuangBean.视频Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=View.inflate(context, R.layout.shipinitem,null);

        //VideoView video1= (VideoView) view.findViewById(R.id.video1);
        JCVideoPlayer jcVideoPlayer= (JCVideoPlayer) view.findViewById(R.id.video1);
        ImageView shipinTuBiao=  (ImageView) view.findViewById(R.id.shipinTuBiao);
        TextView shipinBoFangCount= (TextView) view.findViewById(R.id.shipinBoFangCount);
        TextView shiTitle= (TextView) view.findViewById(R.id.shiTitle);
        Button gengduo= (Button) view.findViewById(R.id.gengduo);
        shiTitle.setText(list.get(position).getTopicName());
        shipinBoFangCount.setText(list.get(position).getPlayCount()+"");
        ImageLoader.getInstance().displayImage(list.get(position).getTopicImg(),shipinTuBiao);
//        BitmapUtils utils=new BitmapUtils(context);
//        utils.display(shipinTuBiao,list.get(position).getTopicImg());
        mp4_url = list.get(position).getMp4_url();
        jcVideoPlayer.setUp(mp4_url,list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getCover()).into(jcVideoPlayer.ivThumb);
        //convertView.ivThumb.setThumbInCustomProject("视频/MP3缩略图地址");
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(context).create();
                //builder.setIcon(R.mipmap.why1);
                //builder.setView(R.layout.alertdialogitem);
                builder.show();
                Window window= builder.getWindow();
                window.setContentView(R.layout.alertdialogitem);
                ImageView qqkong= (ImageView) window.findViewById(R.id.qqkong);
                ImageView guanZhuImage= (ImageView) window.findViewById(R.id.guanZhuImage);
                ImageView shouCangImage= (ImageView) window.findViewById(R.id.shouCangImage);
                TextView quXiao= (TextView) window.findViewById(R.id.quXiao);
                mp4_urls = list.get(position).getMp4_url();
                topicImg = list.get(position).getTopicImg();
                topicDesc = list.get(position).getTopicDesc();
                topicName = list.get(position).getTopicName();
                title = list.get(position).getTitle();
                ptime = list.get(position).getPtime();

                qqkong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Qhare(v);
                    }
                });
                guanZhuImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DbManager db =  ImageLoaderApp.getDb();
                        try {
                            ArrayList<XutilsDb> childInfos = new ArrayList<>();
                            childInfos.add(new XutilsDb(title,ptime,topicName,topicDesc,topicImg,mp4_url));

                            List<XutilsDb> all = db.selector(XutilsDb.class).findAll();
                            //Log.e("ss","aaaaaaaaaaaa"+all.toString());
                            if (all == null) {
                                db.save(childInfos);
                                builder.dismiss();
                            }else{
                                for (XutilsDb xutilsDb:all
                                        ) {
                                    String titles = xutilsDb.getTitle();
                                    Log.e("ss","aaaaaaaaaaaa"+titles);
                                    if (title.equals(titles)){
                                        builder.dismiss();
                                    }
                                }
                                db.save(childInfos);
                                builder.dismiss();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                shouCangImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DbManager db =  ImageLoaderApp.getDb();
                        try {
                            ArrayList<XutilsDb> childInfos = new ArrayList<>();
                            childInfos.add(new XutilsDb(title,ptime,topicName,topicDesc,topicImg,mp4_url));
                            List<XutilsDb> all = db.selector(XutilsDb.class).findAll();
                            if (all == null) {
                                db.save(childInfos);
                                builder.dismiss();
                            }else{
                                for (XutilsDb xutilsDb:all
                                        ) {
                                    String titles = xutilsDb.getTitle();
                                    if (title.equals(titles)){
                                        builder.dismiss();
                                    }
                                }
                                db.save(childInfos);
                                builder.dismiss();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }
                });
                quXiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        DbManager db =  ImageLoaderApp.getDb();
//                        db =  ImageLoaderApp.getDb();
//                        try {
//                            db.delete(XutilsDb.class);
//                        } catch (DbException e) {
//                            e.printStackTrace();
//                        }
                        builder.dismiss();
                    }
                });
            }
        });

        return view;
    }
    public void Qhare(View v) {
 /*  new ShareAction(MainActivity.this).withText("hello")
           .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
           .setCallback(umShareListener).open();*/
 /*  new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
           .withText("hello")
           .setCallback(umShareListener)
           .share();*/
 /*  UMImage image = new UMImage(MainActivity.this,
           BitmapFactory.decodeResource(getResources(),
                   R.mipmap.ic_launcher));*/
        UMImage umImage = new UMImage(context,topicImg);
        //UMImage umImage = new UMImage(context, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489994233&di=c5b3c72b34e883b2826c390bb7ad6c81&imgtype=jpg&er=1&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F63d9f2d3572c11df28e42e30602762d0f703c2e8.jpg");
        new ShareAction((Activity) context)
                //  .setPlatform(SHARE_MEDIA.QQ)
                .setPlatform(SHARE_MEDIA.QZONE)
                .withText("hello")
                .withMedia(umImage)
                .setCallback(umShareListener)
                .share();


/*   new ShareAction(this).setDisplayList(displaylist).withText("呵呵")
           .withTitle("title").withTargetUrl("http://www.baidu.com")
           .withMedia(image).setListenerList(umShareListener).open();*/
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
