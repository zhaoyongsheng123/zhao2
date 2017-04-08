package jinritoutiao.bwie.com.jinritoutiao3.dao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.utils.BitmapUtils;

/**
 * Created by zhao on 2017/3/23.
 */
public class GuanAdapter extends BaseAdapter {
    private Context context;
    private List<XutilsDb> list;

    public GuanAdapter(Context context, List<XutilsDb> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.guanitem,null);
        TextView shujvkuTitle= (TextView) view.findViewById(R.id.shujvkuTitle);
        TextView shujvkuTop= (TextView) view.findViewById(R.id.shujvkuTop);
        ImageView shujvkuImage= (ImageView) view.findViewById(R.id.shujvkuImage);
            String titles = list.get(position).getTitle();
            String topicDesc = list.get(position).getTopicDesc();
            String ptime = list.get(position).getPtime();
            String topicImg = list.get(position).getTopicImg();
        shujvkuTitle.setText(titles);
        shujvkuTop.setText(topicDesc);
        ImageLoader.getInstance().displayImage(topicImg,shujvkuImage);
//        BitmapUtils utils=new BitmapUtils(context);
//        utils.display(shujvkuImage,topicImg);
        return view;
    }
}
