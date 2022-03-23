package com.gigawattstechnology.writeout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

import adapter.PageRecyclerAdapter;

public class writebutton extends AppCompatActivity {
EditText articlename,authorname;
TextView date;
CheckBox fiction,opinions,scientificfacts,drama;
Button create;
RadioGroup radioGroup;
RadioButton radioButton;
MediaPlayer player;
DatePickerDialog.OnDateSetListener setListener;
String scategory="",sarticlename=" ",sdate=" ",sauthorname=" ",discission=" ";
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
        radioGroup=findViewById(R.id.radiogroup);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(writebutton.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String pdate=day+"/"+month+"/"+year;
                        date.setText(pdate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sarticlename=articlename.getText().toString();
                sdate=date.getText().toString();
                sauthorname=authorname.getText().toString();
                if(fiction.isChecked()){
                    scategory=scategory+"Fiction ";
                }
                if(opinions.isChecked()){
                    scategory=scategory+"Opinions ";
                }
                if(scientificfacts.isChecked()){
                    scategory=scategory+"Scientific Facts ";
                }
                if(drama.isChecked()){
                    scategory=scategory+"Drama ";
                }
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