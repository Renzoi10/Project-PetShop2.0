package com.utp.petfriendly.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.TiendaModel;
import com.utp.petfriendly.model.UsuarioModel;
import com.utp.petfriendly.viewModel.AdopcionViewModel;
import com.utp.petfriendly.viewModel.UsurioViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView txtRestPassword, txtRegister;
    private EditText edtPassword, edtUser;
    private FirebaseDatabase database;
    private UsurioViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(UsurioViewModel.class);

        setup();
    }

    private void setup() {
        database = FirebaseDatabase.getInstance();

        edtPassword = findViewById(R.id.edtPassword);
        edtUser = findViewById(R.id.edtUser);

        btnLogin = findViewById(R.id.btnLogin);
        txtRestPassword = findViewById(R.id.txtRestPassword);
        txtRegister = findViewById(R.id.txtRegister);

        btnLogin.setOnClickListener(this);
        txtRestPassword.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    private Boolean validarCampos(){
        String user = edtUser.getText().toString();
        String clave = edtPassword.getText().toString();

        if(user.isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo usuario es requerido.", Toast.LENGTH_LONG).show();
        }

        if(clave.isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo clave es requerido.", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    private void loginUser() {
        String user = edtUser.getText().toString();
        String clave = edtPassword.getText().toString();

        Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(myIntent);
       /* viewModel.obtenerUsuario(user, clave).observe(this, new Observer<UsuarioModel>() {
            @Override
            public void onChanged(UsuarioModel usuarioModel) {
                if (usuarioModel != null) {
                    Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(myIntent);
                   // Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if(validarCampos()){
                    loginUser();
                }
                break;
            case R.id.txtRestPassword:
                Log.e("REST", "reset password");
                //casa
                break;
            case R.id.txtRegister:
                Intent itentResgister = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(itentResgister);
                break;
        }
    }
}