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


import com.example.proyectointegradorgrupal.controller.DatosUsuariosController;
import com.example.proyectointegradorgrupal.model.DatosUsuario;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATOS_USUARIO = "DatosUsuario";
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button botonLogIn;
    private Button botonRegister;

    private SignInButton botonGoogle;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;

    private DatosUsuariosController datosUsuariosController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        findViewsByID();

        botonLogIn.setOnClickListener(this);
        botonRegister.setOnClickListener(this);
        botonGoogle.setOnClickListener(this);
        //Esto hace al boton mas grande
        botonGoogle.setSize(SignInButton.SIZE_WIDE);


        /**
         * Log In de Google
         *
         */
        //Esto es la solicitud de la identificación del usuario, la dirección de correo electrónico
        // y el perfil básico. El ID y el perfil básico se incluyen en DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Obtenemos el cliente
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
    }


    /**
     * OnStart es para chequear si la persona esta logueada o no y en donde
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(currentuser);
        updateUIconGoogle(account);
    }


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
                            crearBaseDeDatosUsuario();
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
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_SIGN_IN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResultGoogle(task);
                break;
        }
    }


    /**
     * Metodo que realmente realiza el inicio de sesion con los datos otorgados por google
     *
     * @param completedTask
     */
    private void handleSignInResultGoogle(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Inicio de sesion exitoso
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Toast.makeText(this, "Te esperamos en otro momento", Toast.LENGTH_SHORT).show();

            firebaseAuthWithGoogle(null);
        }
    }

    /**
     * Autenticacion de Usuario de Google en Firebase
     *
     * @param acct
     */
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        // Log.d("GOOGLE", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            crearBaseDeDatosUsuario();
                            updateUI(user);
                        } else {
                            Snackbar.make(findViewById(R.id.loginActivity), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
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


    private void crearBaseDeDatosUsuario() {



    datosUsuariosController = new DatosUsuariosController(this);
    datosUsuariosController.setDatosUsuario(new ResultListener<DatosUsuario>() {
        @Override
        public void onFinish(DatosUsuario result) {
            Toast.makeText(LoginActivity.this, "Funcionoooo", Toast.LENGTH_SHORT).show();


        }
    });


        /*db.collection(DATOS_USUARIO)
                .document(currentUser.getUid())
                .set(datosUsuario)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LoginActivity.this, "Se creo base de datos", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error al crear base de datos", Toast.LENGTH_SHORT).show();
            }
        });
*/

    }
}