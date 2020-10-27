package com.example.jsoupparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doIt().execute();
            }
        });
    }

    public class doIt extends AsyncTask<Void, Void, Void> {
        String para;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Boston").get();
                Element contentDiv = doc.select("div[id=mw-content-text]").first();
                para=contentDiv.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView.setText(para);
        }
    }
}
