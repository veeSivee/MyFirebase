package com.tes.vi.myfirebase;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    TextView mTxtCondition;
    Button mBtnSunny, mBtnFoggy;
    ListView mLvData;

    Firebase mRef;
    ArrayList<String> mMessage = new ArrayList<>();

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

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mMessage);
        mLvData.setAdapter(adapter);

        messageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("CHILD_ADDED", message);
                mMessage.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("CHILD_CHANGED", message);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("CHILD_REMOVED", message);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
