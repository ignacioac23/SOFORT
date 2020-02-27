package com.example.proyectomenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnLogin,btnCrearCuenta;

    private FirebaseAuth mAuth;
    private String email = "";
    private String password = "";
    private String prueba="";
    private String prueba2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        mAuth = FirebaseAuth.getInstance();
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPassword=(EditText)findViewById(R.id.edtPass);
        btnLogin=(Button) findViewById(R.id.btnIni);
        btnCrearCuenta=(Button) findViewById(R.id.btnCrearCuenta);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    validarUsuario();
                } else {
                    Toast.makeText(MainActivity.this, "Debe rellenar los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });
    }

    private void validarUsuario() {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Menus.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Email y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,Menus.class));
            finish();
        }
    }
}

