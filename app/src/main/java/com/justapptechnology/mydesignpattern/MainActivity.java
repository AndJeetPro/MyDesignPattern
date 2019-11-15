package com.justapptechnology.mydesignpattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView tv;
    TextView tv_as;
    TextView tv_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.submit_d);
        tv = findViewById(R.id.tv);
        tv_as = findViewById(R.id.tv_as);
        tv_ds = findViewById(R.id.tv_ds);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetatlItemActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,2);



            }
        });


        Integer [] array = {45,12,85,32,89,39,69,44,42,1,6,8};
        int temp;
        Arrays.sort(array);
        Integer [] sr_array = {45,12,85,32,89,39,69,44,42,1,6,8};
//        Arrays.sort(sr_array, Collections.reverseOrder());

//        for (int i = 1;i<array.length;i++){
//            for (int j=i;j>0;j--){
//
//                if (array[j]<array[j-1]){
//                    temp = array[j];
//                    array[j] = array[j-1];
//                    array[j-1] = temp;
//                }
//            }
//        }


        for (int i = 0; i < sr_array.length; i++)
        {
            for (int j = i + 1; j < sr_array.length; j++)
            {
                if (sr_array[i] < sr_array[j])
                {
                    temp = sr_array[i];
                    sr_array[i] = sr_array[j];
                    sr_array[j] = temp;
                }
            }
        }


        String str = "";
        for (int i=0;i<array.length;i++){

            str = str+"\n"+array[i];
        }

        tv_as.setText(str);

        String strr = "";
        for (int i=0;i<sr_array.length;i++){

            strr = strr+"\n"+sr_array[i];
        }

        tv_ds.setText(strr);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if (data!=null && data.getExtras()!=null){
                String str = data.getExtras().getString("Manage");
                tv.setText(str);
            }

        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        Log.i("suppercall","supper");
    }


    @Override
    protected void onStop() {
        Log.i(TAG,"stop_MainActivity");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"onStart_MainActivity");
        super.onStart();
    }


    @Override
    protected void onPause() {
        Log.i(TAG,"onPause_MainActivity");
        super.onPause();
    }


    @Override
    protected void onResume() {
        Log.i(TAG,"onResume_MainActivity");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart_MainActivity");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy_MainActivity");
        super.onDestroy();
    }
}
