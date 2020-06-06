package com.example.proyectointegradorgrupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button botonLogIn;
    private Button botonRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        findViewsByID();

        botonLogIn.setOnClickListener(this);
        botonRegister.setOnClickListener(this);



    }


    /**
     * Metodo de findViewsById
     */
    private void findViewsByID() {
        textInputLayoutEmail = findViewById(R.id.loginActivityTextInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.loginActivityTextInputLayoutPassword);
        editTextEmail = findViewById(R.id.loginActivityEditTextEmail);
        editTextPassword = findViewById(R.id.loginActivityEditTextPassword);
        botonLogIn = findViewById(R.id.loginActivityButtonLogIn);
        botonRegister = findViewById(R.id.loginActivityButtonRegister);
    }


    /**
     * updateUI - Actualiza la pantalla, este decide que hacer si la persona esta logueada o no
     *
     * @param user
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "¡Hola " + user.getEmail() + " !", Toast.LENGTH_SHORT).show();

            Intent unIntent = new Intent(this, MainActivity.class);
            startActivity(unIntent);
        }
    }


    /**
     * Metodo para Registrar Usuario
     *
     * @param email
     * @param password
     */
    public void registrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            task.getException().printStackTrace();
                            Toast.makeText(LoginActivity.this, "Usuario ya registrado",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    /**
     * Metodo para Loguear Usuario
     */
    public void loguearUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "La autenticacion no fue posible.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }


    /**
     * Metodo onClick generico de todos los botones de login
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginActivityButtonLogIn:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                loguearUsuario(email,password);
                break;
            case R.id.loginActivityButtonRegister:
                String emailNuevo = editTextEmail.getText().toString();
                String passwordNuevo = editTextPassword.getText().toString();
                registrarUsuario(emailNuevo,passwordNuevo);
                break;


        }

    }
}