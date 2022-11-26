package br.com.up.sistem_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Importar os botões
    private Button btLogar;
    private Button btCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //receber do ID do botão
        btLogar = findViewById(R.id.btLogar);
        btCadastrar = findViewById(R.id.btCadastrar);

        //Caso clique nesse botão redirecionar para função logar
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogar();
            }
        });
        //Caso clique nesse botão redirecionar para função Cadastrar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastrar();
                }
            });
        }

    //Função que o click chama, que faz o redirecionamento das activitys, "Muda de tela"
        private void telaCadastrar() {
            startActivity(new Intent(this,CadastroActivity.class));
        }

        private void telaLogar() {
            startActivity(new Intent(this,LoginActivity.class));

        }

    }

