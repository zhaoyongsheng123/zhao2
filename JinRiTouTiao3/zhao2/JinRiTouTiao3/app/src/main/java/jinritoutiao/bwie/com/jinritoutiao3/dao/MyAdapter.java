package jinritoutiao.bwie.com.jinritoutiao3.dao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import jinritoutiao.bwie.com.jinritoutiao3.R;

/**
 * Created by zhao on 2017/3/11.
 */
public class MyAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> list;

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(context, R.layout.yindaoye,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.yindaoye);
        ImageLoader.getInstance().displayImage(list.get(position),imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
