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

    EditText edtEmail,edtPassword;  //// EditText de la pantalla principal
    Button btnLogin,btnCrearCuenta; ///// Botones de la pantalla principal

    //Asignando variables

    private FirebaseAuth mAuth;

    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        mAuth = FirebaseAuth.getInstance();   /// Instanciando la variable del Authentication
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPassword=(EditText)findViewById(R.id.edtPass);
        btnLogin=(Button) findViewById(R.id.btnIni);
        btnCrearCuenta=(Button) findViewById(R.id.btnCrearCuenta);

        ///// ESTO ES LO QUE VA A HACER EL BOTON CUANDO SE PRESIONE
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    validarUsuario();  /// SI AMBOS CAMPOS ESTAN ESCRITOS ENTONCES SE COMIENZA LA FUNCION PARA VALIDAR USUARIO
                } else {
                    Toast.makeText(MainActivity.this, "Debe rellenar los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
        ///// ESTO ES LO QUE VA A HACER EL BOTON CUANDO SE PRESIONE
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registro.class); //// AQUI SE INICIA LA ACTIVIDAD PARA CREAR LA CUENTA
                startActivity(intent);
            }
        });
    }

    private void validarUsuario() {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { //// ESTE METODO ES EXCLUSIVO DE FIREBASE
                if(task.isSuccessful()){ //// SI SE CUMPLE LA TAREA DE VALIDACION ENTONCES SE MUESTRA LA PANTALLA DE LOS MENUS
                    startActivity(new Intent(MainActivity.this, Menus.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Email y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /// SI EL USUARIO NO CERRO LA CUENTA EN CERRAR SESION, ENTONCES NO NECESITA LOGEARSE LA PROXIMA VEZ QUE ENTRE A LA APP
    /// CON ESTE METODO SE COMIENZA LA ACTIVIDAD DE LOS MENUS SIN NECESIDAD DE LOGEARSE NUEVA,EMTE
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,Menus.class));
            finish();
        }
    }
}

