package com.gigawattstechnology.writeout;

import static com.google.common.net.MediaType.PDF;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import adapter.PageRecyclerAdapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class texttyping extends AppCompatActivity {
TextView articlename,date,authorname,category;
String an,da,aun,ca,k;
EditText articletext;
StorageReference storageReference;
DatabaseReference databaseReference,user;
    double r=  Math.random()*100;
    double s=r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texttyping);
       articlename=findViewById(R.id.namefinal);
       date=findViewById(R.id.datefinal);
       authorname=findViewById(R.id.authorfinal);
       category=findViewById(R.id.categoryfinal);
       an=getIntent().getStringExtra("articlename");
       da=getIntent().getStringExtra("date");
       aun=getIntent().getStringExtra("authorname");
        ca=getIntent().getStringExtra("category");
       // k=getIntent().getStringExtra("done");
        articlename.setText(an);
        date.setText(da);
        authorname.setText(aun);
        category.setText(ca);
        articletext=findViewById(R.id.articletext);
        storageReference=FirebaseStorage.getInstance().getReference();
       databaseReference= FirebaseDatabase.getInstance().getReference("Write OUT");
      // user=FirebaseDatabase.getInstance().getReference(k);
    }
    public void publish(View view)
    {
        PdfDocument mypdf = new PdfDocument();
        String tex=articletext.getText().toString();
        Paint mypaint = new Paint();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(598,1000 , 1).create();
        PdfDocument.Page mypage = mypdf.startPage(mypageInfo);
        Canvas canvas = mypage.getCanvas();
        TextPaint paint=new TextPaint();
        StaticLayout mTextLayout = new StaticLayout(tex, paint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        int textX = 0;
        int textY = 10;
        canvas.translate(textX, textY);
        mTextLayout.draw(canvas);
        canvas.restore();
        mypdf.finishPage(mypage);
        File root = new File(Environment.getExternalStorageDirectory(), "Documents");
        if (!root.exists()) {
            root.mkdir();
        }
        File file = new File(root, "text" +s+ ".pdf");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            mypdf.writeTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mypdf.close();
        uploadPDF();
    }
    private void uploadPDF() {
        File pdf=new File(Environment.getExternalStorageDirectory()+"/Documents/"+"text" +s+ ".pdf");
        Uri path=Uri.fromFile(pdf);
        sendtofirebase(path);
    }
    private void sendtofirebase(Uri path) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle(".......File is getting ONLINE.......");
        progressDialog.show();
        StorageReference reference=storageReference.child( "text" +s+ ".pdf");

        reference.putFile(path)
                      .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                          @Override
                          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                              Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                              while(!uriTask.isComplete());
                              Uri uri=uriTask.getResult();
                              putPDF putPDF=new putPDF(an+" "+da+" "+aun+" "+ca+".pdf",uri.toString());
                              //user.setValue(uri.toString());
                              databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                              Toast.makeText(texttyping.this,"Article Published Successfully",Toast.LENGTH_LONG).show();
                              progressDialog.dismiss();
                          }
                      }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("..Article Published.."+(int)progress+"%");
            }
        });
        Intent i=new Intent(texttyping.this,workspace.class);
        startActivity(i);
        Toast.makeText(texttyping.this,"...Publishing...",Toast.LENGTH_LONG).show();
    }
}