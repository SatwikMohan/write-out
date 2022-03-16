package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class writebutton extends AppCompatActivity {
EditText articlename,date,authorname;
RadioButton fiction,opinions,scientificfacts,drama,texttyping,byimages;
Button create;
boolean f,o,sf,d,tt,bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writebutton);
        articlename=findViewById(R.id.articlename);
        date=findViewById(R.id.date);
        authorname=findViewById(R.id.authorname);
        fiction=findViewById(R.id.fiction);
        opinions=findViewById(R.id.opinions);
        scientificfacts=findViewById(R.id.scientificfacts);
        drama=findViewById(R.id.drama);
        texttyping=findViewById(R.id.texttyping);
        byimages=findViewById(R.id.byimages);
        create=findViewById(R.id.create);
        f=fiction.isChecked();
        o=opinions.isChecked();
        sf=scientificfacts.isChecked();
        d=drama.isChecked();
        tt=texttyping.isChecked();
        bi=byimages.isChecked();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}