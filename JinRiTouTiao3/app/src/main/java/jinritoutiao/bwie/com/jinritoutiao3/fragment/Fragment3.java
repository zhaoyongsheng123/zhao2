package jinritoutiao.bwie.com.jinritoutiao3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.application.ImageLoaderApp;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.dao.GuanAdapter;


/**
 * Created by zhao on 2017/3/10.
 */
public class Fragment3 extends Fragment {

    private List<XutilsDb> all;
    private DbManager db;
    private GuanAdapter guanAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3ye,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Button jiabutton= (Button) view.findViewById(R.id.jiabutton);

        final ListView listView= (ListView) view.findViewById(R.id.listviewguanzhu);
        View bottomView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment3fooder,null);
        //mBottomImage = ((ImageView) bottomView.findViewById(R.id.teach_classify_bottom));
        listView.addFooterView(bottomView);
        db = ImageLoaderApp.getDb();
        try {
            all = db.selector(XutilsDb.class).findAll();
            guanAdapter = new GuanAdapter(getActivity(), all);
            listView.setAdapter(guanAdapter);
            //setListViewHeightBasedOnChildren(listView);

        } catch (DbException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String mp4_url = all.get(position).getMp4_url();
                WhereBuilder b = WhereBuilder.b();
                b.and("id","=",position-1);
                try {
                    db.delete(XutilsDb.class, b);
                    all.remove(position);
                    guanAdapter.notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
//    public void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
//            // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            // 计算子项View 的宽高
//            listItem.measure(1, 5);
//            // 统计所有子项的总高度
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }
}
