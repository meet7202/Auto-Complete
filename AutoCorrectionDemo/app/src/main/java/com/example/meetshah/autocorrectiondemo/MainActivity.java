package com.example.meetshah.autocorrectiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.meetshah.autocorrectiondemo.R.layout.support_simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity {

    private String [] arr = new String[100];
    InputStream is;
    ternaryTree Tst;
    TextView tvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final EditText edittext;
            tvDisplay=(TextView) findViewById(R.id.tvDisplay);

            //String strarr[] =new String[10];


            edittext = (EditText) findViewById(R.id.editText);
            try {
                is = getAssets().open("words.txt");
                Tst=new ternaryTree(is);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        TextWatcher Twatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvDisplay.setText("");
                String str=edittext.getText().toString().trim();
                if(str=="" || str==null){
                    Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<String> ar=Tst.tofind(str.toLowerCase());
                    tvDisplay.setText(edittext.getText().toString());
                    tvDisplay.append(TextUtils.join("\n"+str, ar));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edittext.addTextChangedListener(Twatcher);
           /* b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                       tvDisplay.setText("");

                        ArrayList<String> ar=Tst.tofind(edittext.getText().toString().toLowerCase());
                        tvDisplay.setText(edittext.getText().toString());
                        tvDisplay.append(TextUtils.join("\n"+edittext.getText().toString(), ar));

      //              Toast.makeText(MainActivity.this,Tst.prefix(edittext.getText().toString()), Toast.LENGTH_SHORT).show();
                }
            });*/

    }
}
