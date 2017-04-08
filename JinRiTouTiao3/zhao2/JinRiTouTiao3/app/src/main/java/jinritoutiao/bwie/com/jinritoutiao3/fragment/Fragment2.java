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
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.ReDianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.SheHuiFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.TuiJianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.WenDaFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.YangGuangKuanPianFrag;
import jinritoutiao.bwie.com.jinritoutiao3.shouyefragment.YuLeFrag;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.DaiMengFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.GaoXiaoFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.SheHuiFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.ShengHuoFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.Tuijian2;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.XiaoPinFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.YinYueFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.YingShiFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.YuLeFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.YuanChuangFragment;
import jinritoutiao.bwie.com.jinritoutiao3.yuangguangfragment.YunXiFragment;


/**
 * Created by zhao on 2017/3/10.
 */
public class Fragment2 extends Fragment {

    private List<String> title=new ArrayList<>();
    private List<Fragment> fragm=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2ye,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TabLayout tab= (TabLayout) view.findViewById(R.id.tablayoutShipin);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.viewpagerShipin);
        title.add("推荐");
        title.add("音乐");
        title.add("搞笑");
        title.add("社会");
        title.add("小品");
        title.add("生活");
        title.add("影视");
        title.add("娱乐");
        title.add("呆萌");
        title.add("原创");
        fragm.add(new Tuijian2());
        fragm.add(new YinYueFragment());
        fragm.add(new GaoXiaoFragment());
        fragm.add(new SheHuiFragment());
        fragm.add(new XiaoPinFragment());
        fragm.add(new ShengHuoFragment());
        fragm.add(new YingShiFragment());
        fragm.add(new YuLeFragment());
        fragm.add(new DaiMengFragment());
        fragm.add(new YunXiFragment());
        fragm.add(new YuanChuangFragment());
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
