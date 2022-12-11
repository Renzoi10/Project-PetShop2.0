package com.utp.petfriendly.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.UsuarioModel;
import com.utp.petfriendly.util.Constante;

import java.util.UUID;

public class RegisterUserActivity extends AppCompatActivity {

    private TextInputLayout inputName, inputEmail, inputPassword, inputConfirmPassword;
    private TextInputEditText txtName, txtEmail, txtPassword, txtConfirmPassword;
    private AppCompatButton btnRegister;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setup();
    }

    private void setup(){
        database = FirebaseDatabase.getInstance();
        inputName =  findViewById(R.id.inputName);
        inputEmail =  findViewById(R.id.inputEmail);
        inputPassword =  findViewById(R.id.inputPassword);
        inputConfirmPassword =  findViewById(R.id.inputConfirmPassword);
        txtName =  findViewById(R.id.txtName);
        txtEmail =  findViewById(R.id.txtEmail);
        txtPassword =  findViewById(R.id.txtPassword);
        txtConfirmPassword =  findViewById(R.id.txtConfirmPassword);

        btnRegister =  findViewById(R.id.btnRegister);

        setToolbar();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    registrarUsuario();
                }
            }
        });
    }

    private void registrarUsuario(){
        DatabaseReference reference = database.getReference(Constante.TABLA_USUARIO);
        UUID uuid1 = UUID.randomUUID();
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNombre(txtName.getText().toString());
        usuarioModel.setCorreo(txtEmail.getText().toString());
        usuarioModel.setPassword(txtPassword.getText().toString());
        reference.child(uuid1.toString()).setValue(usuarioModel);

        Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();

        inputName.setError(null);
        inputEmail.setError(null);
        inputPassword.setError(null);
        inputConfirmPassword.setError(null);
    }

    private Boolean validarCampos(){
        inputName.setError(null);
        inputEmail.setError(null);
        inputPassword.setError(null);
        inputConfirmPassword.setError(null);

        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if(name.isEmpty()){
            inputName.setError("Campo querido.");
            return false;
        }

        if(email.isEmpty()){
            inputEmail.setError("Campo querido.");
            return false;
        }

        if(password.isEmpty()){
            inputPassword.setError("Campo querido.");
            return false;
        }

        if(confirmPassword.isEmpty()){
            inputConfirmPassword.setError("Campo querido.");
            return false;
        }
        if(!confirmPassword.equals(password)){
            inputConfirmPassword.setError("Los correos no coinciden.");
            return false;
        }

        return true;
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Proceso Firma");
        //   toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow, null);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
            ab.setHomeAsUpIndicator(drawable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // MenuInflater inflater = getMenuInflater();
        //  inflater.inflate(R.menu.menu_anuncio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}