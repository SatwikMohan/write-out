package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class myarticle extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myarticle);
        /*recyclerView=findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String name[]={"1","2","3","1","2","3","1","2","3","1","2","3"};
        recyclerView.setAdapter(new PageRecyclerAdapter(name));*/
    }
    /*public void pdfView(View view)
    {
        Intent i=new Intent(myarticle.this,pdfview.class);
        startActivity(i);
    }*/

}