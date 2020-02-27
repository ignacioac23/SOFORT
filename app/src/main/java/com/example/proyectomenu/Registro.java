package com.example.proyectomenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends Activity {

    private EditText redtNombre;
    private EditText redtEmail;
    private EditText redtPass;
    private  EditText redtRepetir;
    private Button rbtnReg;

    private String name="";
    private String email="";
    private String pass="";
    private String repetir="";

    FirebaseAuth rAuth;
    DatabaseReference rDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regwindow);
        rAuth= FirebaseAuth.getInstance();
        rDatabase= FirebaseDatabase.getInstance().getReference();
        redtNombre= (EditText)findViewById(R.id.edtNombreReg);
        redtEmail= (EditText)findViewById(R.id.edtEmailReg);
        redtPass= (EditText)findViewById(R.id.edtPassReg);
        redtRepetir=(EditText)findViewById(R.id.edtRepetir);
        rbtnReg= (Button)findViewById(R.id.btnReg);
        rbtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= redtNombre.getText().toString();
                email= redtEmail.getText().toString();
                pass= redtPass.getText().toString();
                repetir=redtRepetir.getText().toString();
                if(!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !repetir.isEmpty()){
                    if(pass.equals(repetir)){
                        if(pass.length()>=6){
                            registrarUsuario();
                        }else{
                            Toast.makeText(Registro.this,"La contraseña debe tener almenos 6 caracteres",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Registro.this, "pass="+pass+" repetir="+repetir+ "", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Registro.this, "Todos los campos deben ser escritos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void registrarUsuario(){
        rAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String id= rAuth.getCurrentUser().getUid();
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("password",pass);

                    rDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Registro.this, MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(Registro.this, "No se pudieron crear los datos",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Registro.this, "Ya existe un usuario con este email",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}