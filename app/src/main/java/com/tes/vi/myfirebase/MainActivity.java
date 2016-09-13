package com.tes.vi.myfirebase;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    TextView mTxtCondition;
    Button mBtnSunny, mBtnFoggy;
    ListView mLvData;

    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        init();
    }

    private void init(){

        mTxtCondition = (TextView)findViewById(R.id.tvCondition);
        mBtnSunny = (Button)findViewById(R.id.btnSunny);
        mBtnFoggy = (Button)findViewById(R.id.btnFoggy);
        mLvData = (ListView)findViewById(R.id.lvData);
        mRef = new Firebase("https://gate-4b0fb.firebaseio.com");

        Firebase messageRef = mRef.child("messages");

        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map = dataSnapshot.getValue(Map.class);
                String one = map.get("one");
                String two = map.get("two");
                String three = map.get("three");
                mTxtCondition.setText(one + " ~ " + two + " ~ " + three);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
