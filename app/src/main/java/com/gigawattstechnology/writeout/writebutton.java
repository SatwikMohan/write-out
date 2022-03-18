package com.gigawattstechnology.writeout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import adapter.PageRecyclerAdapter;

public class writebutton extends AppCompatActivity {
EditText articlename,date,authorname;
CheckBox fiction,opinions,scientificfacts,drama;
Button create;
boolean f,o,sf,d;
RadioGroup radioGroup;
RadioButton radioButton;
MediaPlayer player;
    String scategory=" ",sarticlename=" ",sdate=" ",sauthorname=" ",sfiction=" ",sopinions=" ",sscientificfacts=" ",sdrama=" ",discission=" ";
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
        create=findViewById(R.id.create);
        f=fiction.isChecked();
        o=opinions.isChecked();
        sf=scientificfacts.isChecked();
        d=drama.isChecked();
        radioGroup=findViewById(R.id.radiogroup);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sarticlename=articlename.getText().toString();
                sdate=date.getText().toString();
                sauthorname=authorname.getText().toString();
                if(f){
                    sfiction="Fiction";
                }
                if(o){
                    sopinions="Opinions";
                }
                if(sf){
                    sscientificfacts="Scientific facts";
                }
                if(d){
                    sdrama="Drama";
                }
                scategory=sfiction+""+sopinions+""+sdrama+""+sscientificfacts+" ";
                if(discission.equals("Text typing"))
                {
                    Intent i=new Intent(writebutton.this,texttyping.class);
                    i.putExtra("articlename",sarticlename);
                    i.putExtra("date",sdate);
                    i.putExtra("authorname",sauthorname);
                    i.putExtra("category",scategory);
                    startActivity(i);
                }
                if(discission.equals("By images"))
                {

                }
            }
        });
    }
    public void check(View view)
    {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        discission=radioButton.getText().toString().trim();
    }
}