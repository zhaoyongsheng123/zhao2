package jinritoutiao.bwie.com.jinritoutiao3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import jinritoutiao.bwie.com.jinritoutiao3.R;


/**
 * Created by zhao on 2017/3/10.
 */
public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3ye,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
//        Button jiabutton= (Button) view.findViewById(R.id.jiabutton);
//        ImageView imageView= (ImageView) view.findViewById(R.id.guanzhusousuo);
//        ListView listView= (ListView) view.findViewById(R.id.listviewguanzhu);

    }
}
