package com.example.home.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import asyncTask.loginAsyncTask;

public class MainActivity extends Activity {

    EditText nomeTextView;
    EditText senhaTextView;
    Button enviarButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarButton.findViewById(R.id.enviarButton);
        enviarButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                nomeTextView.findViewById(R.id.nomeEditText);
                String nome = nomeTextView.getText().toString();
                senhaTextView.findViewById(R.id.senhaEditText);
                String senha = senhaTextView.getText().toString();

                loginAsyncTask loginAsyncTask = new loginAsyncTask(v.getContext());
                String[] valores = {nome,senha};

                loginAsyncTask.execute(valores);
            }
        });

    }
}
