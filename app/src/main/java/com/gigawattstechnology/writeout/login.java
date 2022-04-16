package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class login extends AppCompatActivity {
EditText Name,Email,Password;
Button Login;
ProgressBar progress;
TextView resetpassword;
    FirebaseAuth fAuth;
FirebaseDatabase fd=FirebaseDatabase.getInstance();
String value,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Name=findViewById(R.id.namein);
        Email=findViewById(R.id.emailin);
        Password=findViewById(R.id.passwordin);
        Login=findViewById(R.id.Login);
        resetpassword=findViewById(R.id.resetpassword);
        progress=findViewById(R.id.progressBarin);
        fAuth=FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                //Intent i=new Intent(login.this,texttyping.class);
               // i.putExtra("useremail",Email.getText().toString().trim().substring(0,Email.getText().toString().trim().indexOf("@")));
                String auth=email.substring(0,email.indexOf("@")).replace(".","")+"aut";
                DatabaseReference mref= fd.getInstance().getReference().child(auth);
                mref.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener(){
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                        value= snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if(TextUtils.isEmpty(email))
                {
                    Email.setError("* Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Password.setError("* Password is required");
                    return;
                }
                if(password.length()<8)
                {
                    Password.setError("* Password should contain at least 8 characters");
                    return;
                }
                //test=email.substring(0,email.indexOf("@")).replace(".","");

                if(!name.equals(value))
                {
                    Name.setText("");
                    Name.setError("* Re-enter Username for security");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(login.this,name+" is Successfully Logged IN",Toast.LENGTH_SHORT).show();
                            //Bundle bundle=new Bundle();
                            //bundle.putString("registeredauth",test);
                            //myarticletab fragobj = new myarticletab();
                            //fragobj.setArguments(bundle);
                            Intent i=new Intent(login.this,workspace.class);
                            i.putExtra("auth",auth.replace("aut",""));
                            startActivity(i);

                        }else{
                            Toast.makeText(login.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),writeout.class));
                        }

                    }
                });

            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetmail=new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Forgotten Password");
                passwordResetDialog.setMessage("Enter your registered email "+resetmail.getText().toString().trim());
                passwordResetDialog.setView(resetmail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail=resetmail.getText().toString().trim();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this,"Link sent successfully",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this,"ERRORR!!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });

    }
}