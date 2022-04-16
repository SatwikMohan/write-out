package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.ProgressDialog;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class texttyping extends AppCompatActivity {
    TextView articlename, date, authorname, category;
    String an, da, aun, ca, auth;
    EditText articletext;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    DatabaseReference userarticles;
    FirebaseAuth fAuth;
    double r = Math.random() * 100;
    double s = r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texttyping);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        articlename = findViewById(R.id.namefinal);
        fAuth = FirebaseAuth.getInstance();
        date = findViewById(R.id.datefinal);
        authorname = findViewById(R.id.authorfinal);
        category = findViewById(R.id.categoryfinal);
        an = getIntent().getStringExtra("articlename");
        da = getIntent().getStringExtra("date");
        aun = getIntent().getStringExtra("authorname");
        ca = getIntent().getStringExtra("category");
        articlename.setText(an);
        date.setText(da);
        authorname.setText(aun);
        category.setText(ca);
        articletext = findViewById(R.id.articletext);
        storageReference = FirebaseStorage.getInstance().getReference();
        Toast.makeText(texttyping.this,"-SCROLL UP TO PUBLISH-",Toast.LENGTH_LONG).show();

    }
    public void publish(View view)
    {

        /*EditText registeredemail=new EditText(view.getContext());
        EditText registeredpassword=new EditText(view.getContext());
        final AlertDialog.Builder Dialog=new AlertDialog.Builder(view.getContext());
        Dialog.setTitle("Enter Registered Email and Password respectively");
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(registeredemail);
        registeredemail.setHint("Registered Email");
        ll.addView(registeredpassword);
        registeredpassword.setHint("Registered Password");
        Dialog.setView(ll);
        Dialog.setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //ACTION
                auth=registeredemail.getText().toString();
                fAuth.signInWithEmailAndPassword(registeredemail.getText().toString(),registeredpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {*/
                            auth=authtransfer.givename();
                            PdfDocument mypdf = new PdfDocument();
                            String tex = articletext.getText().toString();
                            Paint mypaint = new Paint();
                            PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(598, 1000, 1).create();
                            PdfDocument.Page mypage = mypdf.startPage(mypageInfo);
                            Canvas canvas = mypage.getCanvas();
                            TextPaint paint = new TextPaint();
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
                            File file = new File(root, "text" + s + ".pdf");
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                mypdf.writeTo(fileOutputStream);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mypdf.close();
                            uploadPDF();

                        /*}else{
                            Toast.makeText(texttyping.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }*/

                    //}
                //});

                   // }
               // });
        /*Dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog.create().show();*/
    }
    private void uploadPDF() {
        File pdf=new File(Environment.getExternalStorageDirectory()+"/Documents/"+"text" +s+ ".pdf");
        Uri path=Uri.fromFile(pdf);
        sendtofirebase(path);
    }
    private void sendtofirebase(Uri path) {
        Toast.makeText(texttyping.this,"..Don't Click Anywhere..",Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Write OUT").child(auth.replace(".",""));
        userarticles=FirebaseDatabase.getInstance().getReference("Articles");
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
                              putPDF putPDF=new putPDF(an+" "+da+" "+aun+" "+ca,uri.toString());
                              authtransfer.storeurl(uri.toString());
                             // user.setValue(uri.toString());
                              databaseReference.child(an+""+da.replace("/","")+""+aun+""+ca.replace(" ","")).setValue(putPDF);
                              userarticles.child(an+""+da.replace("/","")+""+aun+""+ca.replace(" ","")).setValue(putPDF);
                              userarticles=FirebaseDatabase.getInstance().getReference().child("Articles").child(an+""+da.replace("/","")+""+aun+""+ca).child("favorite").child(authtransfer.givename());
                              userarticles.setValue("#");
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
        i.putExtra("auth",auth.replace(".",""));
        startActivity(i);
        Toast.makeText(texttyping.this,"...Publishing...",Toast.LENGTH_LONG).show();
    }
}