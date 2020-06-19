package com.example.proyectointegradorgrupal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.view.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button botonLogIn;
    private Button botonRegister;
    private Button botonLogOut;
    private SignInButton botonGoogle;
    private LoginButton botonFacebook;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        findViewsByID();

        botonLogIn.setOnClickListener(this);
        botonRegister.setOnClickListener(this);
        botonGoogle.setOnClickListener(this);
        botonLogOut.setOnClickListener(this);


        /**
         * Log In de Google
         *
         */
        //Esto es la solicitud de la identificación del usuario, la dirección de correo electrónico
        // y el perfil básico. El ID y el perfil básico se incluyen en DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        /**
         * Log in de Facebook
         */

        callbackManager = CallbackManager.Factory.create();


        botonFacebook.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        botonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "¡Vuelve pronto!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
            }
        });

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
        botonGoogle = findViewById(R.id.loginActivityBotonGoogle);
        botonFacebook = findViewById(R.id.login_buttonFacebook);
        botonLogOut = findViewById(R.id.loginActivityButtonLogOut);
    }


    /**
     * OnStart es para chequear si la persona esta logueada o no y en donde
     */
/*    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        updateUI(currentuser);
        updateUIconGoogle(account);
    }*/


    /**
     * updateUI - Actualiza la pantalla, este decide que hacer si la persona esta logueada o no
     *
     * @param user
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "¡Hola " + user.getEmail() + " !", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    private void updateUIconGoogle(GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this, "¡Hola " + account.getDisplayName() + "!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
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
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginActivityButtonLogIn:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                loguearUsuario(email, password);
                break;
            case R.id.loginActivityButtonRegister:
                String emailNuevo = editTextEmail.getText().toString();
                String passwordNuevo = editTextPassword.getText().toString();
                registrarUsuario(emailNuevo, passwordNuevo);
                break;
            case R.id.loginActivityBotonGoogle:
                signIn();
                break;
            case R.id.loginActivityButtonLogOut:
                mGoogleSignInClient.signOut().addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(LoginActivity.this, "¡Que bueno que te quedas!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "Cerro sesión", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }


    /**
     * Metodo iniciar sesion con Google . iniciara la actividad creada por google
     */
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN); //requestCode GOOGLE
    }


    /**
     * Metodo que recibe el request code del metodo de logueo
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_SIGN_IN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                break;
        }
    }


    /**
     * Metodo que realmente realiza el inicio de sesion con los datos otorgados por google
     *
     * @param completedTask
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Inicio de sesion exitoso
            updateUIconGoogle(account);
        } catch (ApiException e) {
            System.out.println(e);
            Toast.makeText(this, "Te esperamos en otro momento", Toast.LENGTH_SHORT).show();
            //Aca se muestra la razon del error
            updateUIconGoogle(null);
        }
    }


    /**
     * Metodo para obtener el KeyHash
     */
    private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.proyectointegradorgrupal",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }
}