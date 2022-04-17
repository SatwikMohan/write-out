package com.gigawattstechnology.writeout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class writebutton extends AppCompatActivity {
EditText articlename,authorname;
TextView date;
//CheckBox fiction,opinions,scientificfacts,drama;
Button create;
RadioGroup radioGroup;
RadioButton radioButton;
MediaPlayer player;
DatePickerDialog.OnDateSetListener setListener;
String item,scategory="",sarticlename=" ",sdate=" ",sauthorname=" ",discission=" ";
    String[] items={"Fiction","ScientificFacts","Opinions","Drama"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writebutton);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        articlename=findViewById(R.id.articlename);
        date=findViewById(R.id.date);
        authorname=findViewById(R.id.authorname);
        create=findViewById(R.id.create);
        //radioGroup=findViewById(R.id.radiogroup);

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

        autoCompleteTextView=findViewById(R.id.dropmenu);
        adapterItems=new ArrayAdapter<>(this,R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Category: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sarticlename=articlename.getText().toString();
                sdate=date.getText().toString();
                sauthorname=authorname.getText().toString();
                scategory=item;
                //if(discission.equals("Text typing"))
               // {
                if(sarticlename.equals("")){
                    Toast.makeText(writebutton.this,"Enter Article name",Toast.LENGTH_SHORT).show();
                }
                if(sdate.equals("")){
                    Toast.makeText(writebutton.this,"SET DATE",Toast.LENGTH_SHORT).show();
                }
                if(sauthorname.equals("")){
                    Toast.makeText(writebutton.this,"Enter Author name",Toast.LENGTH_SHORT).show();
                }
                if(!sauthorname.equals("") && !sarticlename.equals("") && !sdate.equals("")) {
                    Intent i = new Intent(writebutton.this, texttyping.class);
                    i.putExtra("articlename", sarticlename);
                    i.putExtra("date", sdate);
                    i.putExtra("authorname", sauthorname);
                    i.putExtra("category", scategory);
                    startActivity(i);
                }
               // }
                /*if(discission.equals("By images"))
                {
                    Intent i=new Intent(writebutton.this,byimages.class);
                    i.putExtra("articlename",sarticlename);
                    i.putExtra("date",sdate);
                    i.putExtra("authorname",sauthorname);
                    i.putExtra("category",scategory);
                    startActivity(i);
                }*/
            }
        });
    }
    /*public void check(View view)
    {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        discission=radioButton.getText().toString().trim();
    }*/
}