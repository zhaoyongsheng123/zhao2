package com.bwie.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.test.activity.ShouActivity;
import com.bwie.test.bean.MeSqlBean;
import com.bwie.test.mesql.SqlDiao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextZHZC;
    private EditText editTextMIMAZC;
    private Button zhucebut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initView();
    }

    private void initView() {
        ImageView zhuceFanHuis= (ImageView) findViewById(R.id.zhuceFanHuis);
        editTextZHZC = (EditText) findViewById(R.id.editTextZHZC);
        editTextMIMAZC = (EditText) findViewById(R.id.editTextMIMAZC);
        Button zhucebut=  (Button) findViewById(R.id.zhucebut);
        zhuceFanHuis.setOnClickListener(this);
        zhucebut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuceFanHuis:
                Intent it=new Intent(this, ShouActivity.class);
                startActivity(it);
                break;
            case R.id.zhucebut:
                String zhanghao = editTextZHZC.getText().toString().trim();
                String mima = editTextMIMAZC.getText().toString().trim();
                SqlDiao diao=new SqlDiao(this);

                ArrayList<MeSqlBean> select = diao.select();
                if (select.size()==0){
                    diao.insent(zhanghao,mima);
                }else{
                    ArrayList<MeSqlBean> selects = diao.select();
                    for (int i = 0; i < selects.size(); i++) {
                        String zhanghao1 =  selects.get(i).getZhanghao();
                        String mima1 = selects.get(i).getMima();
                        if (zhanghao1.equals(zhanghao)){
                            Toast.makeText(this, "账号已存在，请从新输入", Toast.LENGTH_SHORT).show();
                            break;
                        }else{
                            diao.insent(zhanghao,mima);
                            finish();
                        }
                    }
                }
             break;
        }

    }
}
