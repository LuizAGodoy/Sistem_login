package br.com.up.sistem_login;

import static android.content.ContentValues.TAG;
import static br.com.up.sistem_login.R.id.etSenha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.up.sistem_login.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private View btLogar;
    // Conector do API FireBase
    private FirebaseAuth mAuth;
    private Usuario u;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Autenticação
        mAuth = FirebaseAuth.getInstance();

        //receber na minha activity_cadastro os campos abaixo
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btLogar = findViewById(R.id.btLogar);

        //Quando clicar no logar fazer a instrução abaixo

        btLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //REceber os dados do form
                receberDados();

                // Metodo para logar no filebase
                logar();
            }

        });

    }
///API FIrebaseUSER
    private void logar() {

        mAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Redirecionar para a pagina desejada Criar Intent
                            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void receberDados() {
    u.setEmail(etEmail.getText().toString());
    u.setSenha(etSenha.getText().toString());




    }
}