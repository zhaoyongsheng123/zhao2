package jinritoutiao.bwie.com.jinritoutiao3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.BeiJingFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.KeJiFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.QiCheFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.ReDianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.SheHuiFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.TuPianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.TuiJianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.WenDaFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.YangGuangKuanPianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.YuLeFrag;

/**
 * Created by zhao on 2017/3/10.
 */
public class Fragment1 extends Fragment {

    private List<String> title=new ArrayList<>();
    private List<Fragment> fragm=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        TabLayout tab= (TabLayout) view.findViewById(R.id.tablayout);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.shouViewPager);
        title.add("推荐");
        title.add("热点");
        title.add("宽频");
        title.add("北京");
        title.add("社会");
        title.add("娱乐");
        title.add("问答");
        title.add("图片");
        title.add("科技");
        title.add("汽车");
        fragm.add(new TuiJianFrag());
        fragm.add(new ReDianFrag());
        fragm.add(new YangGuangKuanPianFrag());
        fragm.add(new BeiJingFrag());
        fragm.add(new SheHuiFrag());
        fragm.add(new YuLeFrag());
        fragm.add(new WenDaFrag());
        fragm.add(new TuPianFrag());
        fragm.add(new KeJiFrag());
        fragm.add(new QiCheFrag());
        tab.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < title.size(); i++) {
            tab.addTab(tab.newTab().setText(title.get(i)));
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragm.get(position);
            }

            @Override
            public int getCount() {
                return title.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position%title.size());
            }
        });
        tab.setupWithViewPager(viewPager);
    }
}
