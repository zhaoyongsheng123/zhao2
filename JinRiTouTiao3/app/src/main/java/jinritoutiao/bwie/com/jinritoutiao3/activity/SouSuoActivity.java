package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.application.ImageLoaderApp;
import jinritoutiao.bwie.com.jinritoutiao3.bean.XutilsDb;
import jinritoutiao.bwie.com.jinritoutiao3.dao.GuanAdapter;

public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener {

    private DbManager db;
    private ArrayList<XutilsDb> all;
    private MultiAutoCompleteTextView mtxt;
    private String url="http://toutiao.com/search/?keyword=%E9%A5%B5%E6%96%99";
    private EditText editText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();

    }

    private void initView() {
        editText = (EditText) findViewById(R.id.sousuoEdit);
        TextView textView= (TextView) findViewById(R.id.sousuoText);
        ImageView imageView= (ImageView) findViewById(R.id.sousuoFanHui);
        listView = (ListView) findViewById(R.id.soulistview);
        textView.setOnClickListener(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(SouSuoActivity.this,ShouActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String s = editText.getText().toString().trim();
        db = ImageLoaderApp.getDb();
        try {

            all = (ArrayList<XutilsDb>) db.selector(XutilsDb.class).where("title","like","%"+s+"%").findAll();
            Log.e("ss","ssssssssssss"+all.toString());
            GuanAdapter guanAdapter = new GuanAdapter(this, all);
            listView.setAdapter(guanAdapter);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
