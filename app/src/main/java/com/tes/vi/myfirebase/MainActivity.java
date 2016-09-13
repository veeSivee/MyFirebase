package com.tes.vi.myfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView mTxtCondition;
    Button mBtnSunny, mBtnFoggy;
    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mTxtCondition = (TextView)findViewById(R.id.tvCondition);
        mBtnSunny = (Button)findViewById(R.id.btnSunny);
        mBtnFoggy = (Button)findViewById(R.id.btnFoggy);
        mRef = new Firebase("https://gate-4b0fb.firebaseio.com/condition");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                mTxtCondition.setText(data);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                mTxtCondition.setText("!!! " + firebaseError.toString());
            }
        });
    }
}
