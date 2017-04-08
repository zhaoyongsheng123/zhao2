package jinritoutiao.bwie.com.jinritoutiao3.shouyefragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.WebViewsActivity;
import jinritoutiao.bwie.com.jinritoutiao3.application.ImageLoaderApp;
import jinritoutiao.bwie.com.jinritoutiao3.bean.ShouYeBean;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.dao.ShouYeAdapter;
import jinritoutiao.bwie.com.jinritoutiao3.dao.Utils;

/**
 * Created by zhao on 2017/3/10.
 */
public class TuiJianFrag extends Fragment {
    private int numder=0;
    private PullToRefreshListView pull;
    private ArrayList<ShouYeBean.DataBean> data;
    private ArrayList<ShouYeBean.DataBean> list=new ArrayList<>();
    private String mp4_url;
    private String mp4_urls;
    private String topicImg;
    private String topicDesc;
    private ImageView imageView;
    private String topicName;
    private String title;
    private int ptime;
    private AlertDialog builder;

    private ShouYeAdapter adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tuijianfragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        pull = (PullToRefreshListView) view.findViewById(R.id.pullToRefresh);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                numder=0;
                pull.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();
                        getServerData();
                        pull.onRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                numder++;
                pull.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "上拉加载", Toast.LENGTH_SHORT).show();
                        adapter1.notifyDataSetChanged();
                        getServerData();
                        pull.onRefreshComplete();
                    }
                },2000);
            }
        });
        getServerData();
    }

    public void getServerData() {
        MyAsyncTask task=new MyAsyncTask();
        task.execute();
    }
    public class MyAsyncTask extends AsyncTask<String,Integer,String>{
        String url="http://ic.snssdk.com/2/article/v25/stream/?count="+numder+"&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
        @Override
        protected String doInBackground(String... params) {
            HttpClient client=new DefaultHttpClient();
            HttpGet get=new HttpGet(url);
            try {
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    String json = Utils.pressare(inputStream);
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void
        onPostExecute(String s) {


            super.onPostExecute(s);
            Gson gson=new Gson();
            ShouYeBean shouYeBean = gson.fromJson(s, ShouYeBean.class);
            data =   (ArrayList<ShouYeBean.DataBean>) shouYeBean.getData();
            list.addAll(data);

            adapter1 = new ShouYeAdapter(getActivity(), list);
            pull.setAdapter(adapter1);

            pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) {
                            String url = list.get(i).getUrl();
                            Intent it=new Intent(getActivity(), WebViewsActivity.class);
                            it.putExtra("url",url);
                            startActivity(it);
                        }

                    }

                }
            });
            pull.getRefreshableView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setTitle("标题");
                    //图片
                    //builder.setIcon(R.mipmap.why1);
                    //builder.setView(R.layout.alertdialogitem);
                    //显示AlertDialog窗口
                    builder.show();
                    Window window= builder.getWindow();
                    //布局
                    window.setContentView(R.layout.alertdialogitem);
                    ImageView qqkong= (ImageView) window.findViewById(R.id.qqkong);
                    ImageView guanZhuImage= (ImageView) window.findViewById(R.id.guanZhuImage);
                    ImageView shouCangImage= (ImageView) window.findViewById(R.id.shouCangImage);
                    TextView quXiao= (TextView) window.findViewById(R.id.quXiao);
                    mp4_urls = list.get(position).getUrl();
                    topicImg = list.get(position).getMiddle_image().getUrl();
                    topicDesc = list.get(position).getKeywords();
                    topicName = list.get(position).getSource();
                    title = list.get(position).getTitle();
                    ptime = list.get(position).getPublish_time();

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
                                childInfos.add(new XutilsDb(title,ptime+"",topicName,topicDesc,topicImg,mp4_url));

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
                                childInfos.add(new XutilsDb(title,ptime+"",topicName,topicDesc,topicImg,mp4_url));
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
//                            DbManager db =  ImageLoaderApp.getDb();
//                            db =  ImageLoaderApp.getDb();
//                            try {
//                                db.delete(XutilsDb.class);
//                            } catch (DbException e) {
//                                e.printStackTrace();
//                            }
                            builder.dismiss();
                        }
                    });
                return true;
                }
            });

        }
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
        UMImage umImage = new UMImage(getActivity(),topicImg);
        //UMImage umImage = new UMImage(context, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489994233&di=c5b3c72b34e883b2826c390bb7ad6c81&imgtype=jpg&er=1&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F63d9f2d3572c11df28e42e30602762d0f703c2e8.jpg");
        new ShareAction((Activity) getActivity())
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

            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
