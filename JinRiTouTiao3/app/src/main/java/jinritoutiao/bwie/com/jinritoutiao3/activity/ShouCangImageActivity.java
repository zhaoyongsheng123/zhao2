package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.application.ImageLoaderApp;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.dao.GuanAdapter;

public class ShouCangImageActivity extends AppCompatActivity {

    private DbManager db;
    private List<XutilsDb> all;
    private GuanAdapter guanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang_image);
        ListView listView= (ListView) findViewById(R.id.shouCanglistView);
        db = ImageLoaderApp.getDb();
        try {
            all = db.selector(XutilsDb.class).findAll();
            guanAdapter = new GuanAdapter(this, all);
            listView.setAdapter(guanAdapter);
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
}
