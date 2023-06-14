package com.example.newsong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        String message = intent.getStringExtra("p_EXTRA");
        ImageView imageView = findViewById(R.id.imageView);
        if (Objects.equals(message, "台北小巨蛋")) {
            imageView.setImageResource(R.mipmap.laod13);
        } else if (Objects.equals(message, "台北田徑場")) {
            imageView.setImageResource(R.mipmap.laod20);
        }  else if(Objects.equals(message, "民生公園")){
            imageView.setImageResource(R.mipmap.laod22);
    }else if(Objects.equals(message, "台北暖身場")){
            imageView.setImageResource(R.mipmap.laod21);
    }else if(Objects.equals(message, "民族國小")){
            imageView.setImageResource(R.mipmap.laod15);
    }else if(Objects.equals(message, "民權國小")){
            imageView.setImageResource(R.mipmap.laod16);
    }else if(Objects.equals(message, "精忠社區")){
            imageView.setImageResource(R.mipmap.laod19);
    }else if(Objects.equals(message, "京華捐地")){
            imageView.setImageResource(R.mipmap.laod23);
    }else if(Objects.equals(message, "三民公園")){
            imageView.setImageResource(R.mipmap.laod9);
    }else if(Objects.equals(message, "復盛公園")){
            imageView.setImageResource(R.mipmap.laod11);
    }else if(Objects.equals(message, "吉祥公園")){
            imageView.setImageResource(R.mipmap.laod10);
    }else if(Objects.equals(message, "西松公園")){
            imageView.setImageResource(R.mipmap.laod12);
    }else if(Objects.equals(message, "松基公園")){
            imageView.setImageResource(R.mipmap.laod3);
    }else if(Objects.equals(message, "觀山河濱公園")){
            imageView.setImageResource(R.mipmap.laod6);
    }else if(Objects.equals(message, "撫遠公園")){
            imageView.setImageResource(R.mipmap.laod14);
     }else if(Objects.equals(message, "復源公園")){
            imageView.setImageResource(R.mipmap.laod18);
    }else if(Objects.equals(message, "慶城公園")){
            imageView.setImageResource(R.mipmap.laod8);
    }else if(Objects.equals(message, "民權運動公園")){
            imageView.setImageResource(R.mipmap.laod17);
    }else if(Objects.equals(message, "富民生態公園")){
            imageView.setImageResource(R.mipmap.laod7);
    }else if(Objects.equals(message, "松榮公園")){
            imageView.setImageResource(R.mipmap.laod2);
    }else if(Objects.equals(message, "新中公園")){
            imageView.setImageResource(R.mipmap.laod1);
    }else if(Objects.equals(message, "復建綠地")){
            imageView.setImageResource(R.mipmap.laod5);
    }
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
}
