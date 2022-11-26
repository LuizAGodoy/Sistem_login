package br.com.up.sistem_login;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class CadastroActivity<button> extends AppCompatActivity {

    //Importar os botões / campos texto
    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Switch sMedico;
    private View btCadastrar;
    // Conector do API FireBase
    private FirebaseAuth mAuth;
    private Usuario u;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //receber na minha activity_cadastro os campos abaixo
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        sMedico = findViewById(R.id.sMedico);

    //Get API FireBase
        mAuth = FirebaseAuth.getInstance();

        btCadastrar = findViewById(R.id.btCadastrar);

        //Quando clicar no botão cadastrar, irei executar o comando abaixo
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no clicar no botão chamar a função abaixo, recebendo os dados dos formularios
                recuperarDados();

                //Função que cria o login
                criarLogin();
            }
        });


    }

    private void criarLogin() {

        mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        u.setId(user.getUid());
                        u.salvaDados();


                        if (task.isSuccessful()) {


                            // Caso cadastre ele entra na teal do APP PrincipalActivity
                            startActivity(new Intent(CadastroActivity.this, PrincipalActivity.class));

                        }else{
                            //Trata caso o cadastro de erro
                            Toast.makeText(CadastroActivity.this, "Erro ao criar login!", Toast.LENGTH_SHORT).show();

                        }
                        
                    }
                });



    }

    private void recuperarDados() {
        // Validação se todos os campos estão preenchidos no formulario
        if(etNome.getText().toString()==""||etEmail.getText().toString()==""||etSenha.getText().toString()==""){
            Toast.makeText(this, "Preeencha todos os campos", Toast.LENGTH_SHORT);
        }else{
            //Chamando/Instanciando o modelo, cada info do form será atribuida ao objeto
            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setSenha(etSenha.getText().toString());
            u.setProfessor(sMedico.getShowText());
        }
    }
}