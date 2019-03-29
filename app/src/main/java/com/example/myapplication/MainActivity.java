package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public String meg ="[" +
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"湿度\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"}," +
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"温度\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"},"+
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"水表\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"}," +
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"一氧化碳\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"}," +
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"氧气\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"}," +
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"电扇\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"},"+
            "{\"FromWhere\":\"%s\",\"RouterMac\"" +
            ":\"%s\",\"Name\":\"煤气\",\"Type\":%d,\"Value" +
            "\":\"%s\",\"Mac\":\"%s\", \"IsControl\":%d, \"TranType\":\"%s\",\"Action\":\"%s\",\"Time\":\"%s\"}"+
            "]";
    public List<HomeBean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        showdata();
    }
    private void showdata() {
        jsonJXDate(meg);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                MainActivity.this, DividerItemDecoration.VERTICAL));//分割线
        rec_item_Adapter adapter = new rec_item_Adapter(list, MainActivity.this);
                //设置布局显示格式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(adapter);
        // 设置item及item中控件的点击事件
        adapter.setOnItemClickListener(MyItemClickListener);
    }
    private void jsonJXDate(String meas){
        if(meas!=null) {
            try {
                //JSONObject object=new JSONObject(meas);
                JSONArray jsonArray = new JSONArray(meas);
                //for(int i=0; i<jsonData.length();i++){
                    //JSONObject jsonObject = jsonArray.getJSONObject(i);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    HomeBean homeBean=new HomeBean();
                    homeBean.setName(object.getString("Name"));
                    homeBean.setMac(object.getString("Mac"));
                    homeBean.setValue(object.getString("Value"));
                    list.add(homeBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private rec_item_Adapter.OnItemClickListener MyItemClickListener = new rec_item_Adapter.OnItemClickListener(){

        @Override
        public void onItemClick(View v, rec_item_Adapter.ViewName viewName, int position) {
            switch (v.getId()){
                case R.id.button:
                    FancyToast.makeText(MainActivity.this, "Hello World !",FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this,"你点击了item按钮"+(position+1),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
    //You can also create your custom Toasts with passing your image:

        //FancyToast.makeText(yourContext, "I'm a custom Toast", duration, type, yourimage).show();