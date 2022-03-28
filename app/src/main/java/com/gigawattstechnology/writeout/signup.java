package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class signup extends AppCompatActivity {
EditText Name,Email,SetPassword,ConfirmPassword;
Button Register;
FirebaseAuth fAuth;
    ProgressBar progress;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name=findViewById(R.id.nameup);
        Email=findViewById(R.id.emailup);
        SetPassword=findViewById(R.id.passwordup);
        ConfirmPassword=findViewById(R.id.confirmpasswordup);
        Register=findViewById(R.id.register);
        fAuth=FirebaseAuth.getInstance();
        progress=findViewById(R.id.progressBarup);
        storageReference= FirebaseStorage.getInstance().getReference();
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),writeout.class));
            finish();
        }
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String password=SetPassword.getText().toString().trim();
                String cpassword=ConfirmPassword.getText().toString().trim();
                String auth=email.substring(0,email.indexOf("@")).replace(".","")+"aut";
                databaseReference= FirebaseDatabase.getInstance().getReference(auth);
                databaseReference.setValue(name);
                //String authenticate=name+email;
                if(!password.equals(cpassword))
                {
                    SetPassword.setError("* Password and Confirm Password are not same");
                    ConfirmPassword.setError("* Password and Confirm Password are not same");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Email.setError("* Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    SetPassword.setError("* Password is required");
                    return;
                }
                if(password.length()<8)
                {
                 SetPassword.setError("* Password should contain at least 8 characters");
                 return;
                }
                progress.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(signup.this,name+" is Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),writeout.class));

                        }else{
                            Toast.makeText(signup.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}