package jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.WebViewsActivity;
import jinritoutiao.bwie.com.jinritoutiao3.bean.ShouYeBean;
import jinritoutiao.bwie.com.jinritoutiao3.bean.YuangGuangBean;
import jinritoutiao.bwie.com.jinritoutiao3.dao.ShouYeAdapter;
import jinritoutiao.bwie.com.jinritoutiao3.dao.Utils;
import jinritoutiao.bwie.com.jinritoutiao3.dao.YuangGuangAdapter;

/**
 * Created by zhao on 2017/3/10.
 */
public class Tuijian2 extends Fragment {

    private int numder=1;
    private PullToRefreshListView pull;
    private ArrayList<ShouYeBean.DataBean> data;
    private ArrayList<YuangGuangBean.视频Bean> list=new ArrayList<>();
    private YuangGuangAdapter adapter1;
    private ArrayList<YuangGuangBean.视频Bean> shipin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tuijian2fragment,null);
        initView(view);
        return view;
    }
    private void initView(View view) {
        pull = (PullToRefreshListView) view.findViewById(R.id.pull2);
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
                        adapter1.notifyDataSetChanged();
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
                        getServerData();
                        adapter1.notifyDataSetChanged();
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
    public class MyAsyncTask extends AsyncTask<String,Integer,String> {
        String url="http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&size="+numder+"&offset=0&fn=1&passport=&devId=YOyNSBj2dTTsHKDNc0qkRhloimHtUG2wOBGWA6AinORgbKJWB0zbo1D3lxXarwr%2BIIGNeE0nI41SFrBIaL1THA%3D%3D&lat=qE8y1hif8wXc%2FvASJkTSBA%3D%3D&lon=1qI78PEl4vwuhGR0fNazmg%3D%3D&version=21.0&net=wifi&ts=1489828936&sign=%2Fgat2%2FkW7Ds0o6jmM1eGj%2Byo3tY3l6mVP8IBBtsKCw948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=QQ_news&mac=racUMC0A9havm%2BHe6jH3YAvVdjgSXYDtwEDZ03eH1l8%3D&open=&openpath=";
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson=new Gson();
            YuangGuangBean yuangGuangBean = gson.fromJson(s, YuangGuangBean.class);
            shipin = (ArrayList<YuangGuangBean.视频Bean>) yuangGuangBean.get视频();
            //data =   (ArrayList<ShouYeBean.DataBean>) shouYeBean.getData();
            list.addAll(shipin);
            adapter1 = new YuangGuangAdapter(getActivity(), list);
            int i=0;
            if (i==0){
                i++;
                pull.setAdapter(adapter1);
            }
            pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) {
                            String url = list.get(i).getMp4_url();
                            Intent it=new Intent(getActivity(), WebViewsActivity.class);
                            it.putExtra("url",url);
                            startActivity(it);
                        }

                    }

                }
            });
        }
    }
}
