package com.filipkesteli.phone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TEL_PREFIX = "tel:";
    private String phoneNumber;

    private EditText etNumber;
    private Button btnPrepare;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        etNumber = (EditText) findViewById(R.id.etNumber);
        btnPrepare = (Button) findViewById(R.id.btnPrepare);
        btnCall = (Button) findViewById(R.id.btnCall);
    }

    private void setupListeners() {
        btnPrepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dok god nemamo tekst ukucan
                if (formIsOk()) {
                    phoneNumber = TEL_PREFIX + etNumber.getText().toString();
                    //uri -> uniform resource identifier
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                    startActivity(intent);
                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsOk()) {
                    phoneNumber = TEL_PREFIX + etNumber.getText().toString();
                    //uri -> uniform resource identifier
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
                    startActivity(intent);
                }
            }
        });
    }

    private boolean formIsOk() {
        if (etNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(MainActivity.this, R.string.please_enter_number, Toast.LENGTH_SHORT).show();
            etNumber.requestFocus();
            return false;
        }
        return true;
    }
}
