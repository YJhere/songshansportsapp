package com.example.newsong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadActivity extends AppCompatActivity {
    Dialog dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        // 客製化ListView
        SimpleAdapter adapter = new SimpleAdapter(loadActivity.this, getData(), R.layout.row, new String[]{"Title", "Name"}, new int[]{R.id.ttitle, R.id.otitle});
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                Intent intent = new Intent(loadActivity.this, locationActivity.class);
                TextView txv = (TextView) arg1.findViewById(R.id.otitle);
                String message = txv.getText().toString();
                intent.putExtra("m_EXTRA", message);
                startActivity(intent);
            }
        });
        computeWindowSizeClasses();

    }
    private void computeWindowSizeClasses() {
        WindowMetrics metrics = WindowMetricsCalculator.getOrCreate()
                .computeCurrentWindowMetrics(this);

        float widthDp = metrics.getBounds().width() /
                getResources().getDisplayMetrics().density;
        WindowSizeClass widthWindowSizeClass;

        if (widthDp < 600f) {
            widthWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (widthDp < 840f) {
            widthWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            widthWindowSizeClass = WindowSizeClass.EXPANDED;
        }

        float heightDp = metrics.getBounds().height() /
                getResources().getDisplayMetrics().density;
        WindowSizeClass heightWindowSizeClass;

        if (heightDp < 480f) {
            heightWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (heightDp < 900f) {
            heightWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            heightWindowSizeClass = WindowSizeClass.EXPANDED;
        }

        // Use widthWindowSizeClass and heightWindowSizeClass.
    }
    // List Map

    private List getData() {
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("Title", "熱量消耗:380–400卡/1小時");
        map.put("Name", "台北小巨蛋");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:346卡/1小時");
        map.put("Name", "台北田徑場");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:230-245卡/1小時");
        map.put("Name", "民生公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:346卡/1小時");
        map.put("Name", "台北暖身場");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:346卡/1小時");
        map.put("Name", "民族國小");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:343卡/1小時");
        map.put("Name", "民權國小");
        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:346卡/1小時");
        map.put("Name", "精忠社區");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:240-250卡/1小時");
        map.put("Name", "京華捐地");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:380卡/1小時");
        map.put("Name", "三民公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:380-400卡/1小時");
        map.put("Name", "復盛公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:203卡/1小時");
        map.put("Name", "吉祥公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:203卡/1小時");
        map.put("Name", "西松公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:330卡/1小時");
        map.put("Name", "松基公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:207卡/1小時");
        map.put("Name", "觀山河濱公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:330卡/1小時");
        map.put("Name", "撫遠公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:90-98卡/1小時");
        map.put("Name", "復源公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:330卡/1小時");
        map.put("Name", "慶城公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:288卡/1小時");
        map.put("Name", "民權運動公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:180卡/1小時");
        map.put("Name", "富民生態公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:173卡/1小時");
        map.put("Name", "松榮公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:180卡/1小時");
        map.put("Name", "新中公園");

        list.add(map);
        map = new HashMap();
        map.put("Title", "熱量消耗:240卡/1小時");
        map.put("Name", "復建綠地");

        list.add(map);
        return list;
    }
}