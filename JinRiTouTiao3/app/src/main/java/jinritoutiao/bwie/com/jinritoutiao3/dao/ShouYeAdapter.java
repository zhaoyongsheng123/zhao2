package jinritoutiao.bwie.com.jinritoutiao3.dao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.ShouActivity;
import jinritoutiao.bwie.com.jinritoutiao3.bean.ShouYeBean;
import jinritoutiao.bwie.com.jinritoutiao3.utils.BitmapUtils;

/**
 * Created by zhao on 2017/3/15.
 */
public class ShouYeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShouYeBean.DataBean> list;
    private final int v_1 = 0;
    private final int v_2 = 1;
    private final int v_3 = 2;

    public ShouYeAdapter(Context context, ArrayList<ShouYeBean.DataBean> list) {
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
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImage_list()!= null) {
            if (list.get(position).getImage_list().size() == 0) {
                if (list.get(position).getLarge_image_list().size() == 0) {
                    return v_3;
                } else if (list.get(position).getLarge_image_list().size() != 0) {
                    return v_2;
                }
            } else if (list.get(position).getImage_list().size() == 3) {
                return v_1;
            }
        }
        return v_3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 v1 = new ViewHolder1();
        ViewHolder2 v2 = new ViewHolder2();
        ViewHolder3 v3 = new ViewHolder3();
        //获取游标
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case v_1:
                    convertView = convertView.inflate(context,R.layout.shouitem1 ,null);
                    v1.biaotis = (TextView) convertView.findViewById(R.id.biaotis);
                    v1.wangzhis= (TextView) convertView.findViewById(R.id.wangzhis);
                    v1.pingluns= (TextView) convertView.findViewById(R.id.pingluns);
                    v1.zhanshiImg1s = (ImageView) convertView.findViewById(R.id.zhanshiImg1s);
                    v1.zhanshiImg2s = (ImageView) convertView.findViewById(R.id.zhanshiImg2s);
                    v1.zhanshiImg3s = (ImageView) convertView.findViewById(R.id.zhanshiImg3s);
                    convertView.setTag(v1);
                    break;
                case v_2:
                    convertView = convertView.inflate(context,R.layout.shouitem2 ,null);
                    v2.biaoti2= (TextView) convertView.findViewById(R.id.biaoti2);
                    v2.wangzhi2= (TextView) convertView.findViewById(R.id.wangzhi2);
                    v2.pinglun2 = (TextView) convertView.findViewById(R.id.pinglun2);
                    v2.zhanshiImg12 = (ImageView) convertView.findViewById(R.id.zhanshiImg12);
                    v2.zhanshiImg22 = (ImageView) convertView.findViewById(R.id.zhanshiImg22);
                    convertView.setTag(v2);
                    break;
                case v_3:
                    convertView = convertView.inflate(context,R.layout.shouitem3 ,null);
                    v3.biaoti3= (TextView) convertView.findViewById(R.id.biaoti3);
                    v3.zhanshiImg3= (ImageView) convertView.findViewById(R.id.zhanshiImg3);
                    v3.pinglun3= (TextView) convertView.findViewById(R.id.pinglun3);
                    v3.wangzhi3 = (TextView) convertView.findViewById(R.id.wangzhi3);
                    convertView.setTag(v3);
                    break;

            }

        } else {
            switch (type) {
                case v_1:
                    v1 = (ViewHolder1) convertView.getTag();
                    break;
                case v_2:
                    v2 = (ViewHolder2) convertView.getTag();
                    break;
                case v_3:
                    v3 = (ViewHolder3) convertView.getTag();
                    break;

            }
        }

        switch (type) {
            case v_1:
                v1.biaotis.setText(list.get(position).getTitle());
                v1.wangzhis.setText(list.get(position).getSource());
                v1.pingluns.setText(list.get(position).getLike_count() + "");
//                x.image().bind(v1.zhanshiImg1s, list.get(position).getImage_list().get(0).getUrl());
//                x.image().bind(v1.zhanshiImg2s, list.get(position).getImage_list().get(1).getUrl());
//                x.image().bind(v1.zhanshiImg3s, list.get(position).getImage_list().get(2).getUrl());
                BitmapUtils utils=new BitmapUtils(context);
                utils.display(v1.zhanshiImg1s, list.get(position).getImage_list().get(0).getUrl());
                utils.display(v1.zhanshiImg2s, list.get(position).getImage_list().get(1).getUrl());
                utils.display(v1.zhanshiImg3s, list.get(position).getImage_list().get(2).getUrl());

                break;
            case v_2:
                v2.biaoti2.setText(list.get(position).getTitle());
                v2.wangzhi2.setText(list.get(position).getSource());

                v2.pinglun2.setText(list.get(position).getLike_count() + "");
//                x.image().bind(v2.zhanshiImg12, list.get(position).getImage_list().get(0).getUrl());
//                x.image().bind(v2.zhanshiImg22, list.get(position).getImage_list().get(1).getUrl());
                break;
            case v_3:
                v3.biaoti3.setText(list.get(position).getTitle());
                v3.wangzhi3.setText(list.get(position).getSource());
                v3.pinglun3.setText(getData());
                if (list.get(position).getMiddle_image()!=null) {
                    if (list.get(position).getMiddle_image().getUrl()!=null)
                       // x.image().bind(v3.zhanshiImg3, list.get(position).getMiddle_image().getUrl());
                    {
                        BitmapUtils utilss=new BitmapUtils(context);
                        utilss.display(v3.zhanshiImg3,list.get(position).getMiddle_image().getUrl());
                    }

                }else {
                    v3.zhanshiImg3.setVisibility(View.VISIBLE);
                }

                break;

        }


        return convertView;
    }

    private String getData() {
        long timeMillis = System.currentTimeMillis();
        //获取当前系统时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date(timeMillis);
        String format = simpleDateFormat.format(date);
        return format;
    }

    class ViewHolder1{
         TextView biaotis;
         TextView wangzhis;
         TextView pingluns;
         ImageView zhanshiImg1s;
         ImageView zhanshiImg2s;
         ImageView zhanshiImg3s;
    }
    class ViewHolder2{
         TextView biaoti2;
         TextView wangzhi2;
         TextView pinglun2;
         ImageView zhanshiImg12;
         ImageView zhanshiImg22;

    }
    class ViewHolder3{
         TextView biaoti3;
         TextView wangzhi3;
         TextView pinglun3;
         ImageView zhanshiImg3;


    }
}
