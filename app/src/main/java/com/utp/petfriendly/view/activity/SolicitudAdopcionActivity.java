package com.utp.petfriendly.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.SolicitudAdopcionModel;
import com.utp.petfriendly.model.UsuarioModel;
import com.utp.petfriendly.util.Constante;
import com.utp.petfriendly.util.PetUtil;

import java.util.UUID;

public class SolicitudAdopcionActivity extends AppCompatActivity {

    TextInputEditText tietNombre, tietDocumento, tietTelefono, tietCorreo, tietDireccion;
    AppCompatButton btnEnviar;
    private FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_adopcion);

        database = FirebaseDatabase.getInstance();
        tietNombre = findViewById(R.id.tietNombre);
        tietDocumento = findViewById(R.id.tietDocumento);
        tietTelefono = findViewById(R.id.tietTelefono);
        tietCorreo = findViewById(R.id.tietCorreo);
        tietDireccion = findViewById(R.id.tietDireccion);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    showProgressDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            guardarSolicitud();
                        }
                    }, 3000);

                } else {
                    Toast.makeText(getApplicationContext(), "Todos los campos son requeridos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_atras, null);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
            ab.setHomeAsUpIndicator(drawable);
        }
    }

    private Boolean validarCampos() {
        if (tietNombre.getText().toString().trim().isEmpty() ||
                tietDocumento.getText().toString().trim().isEmpty() ||
                tietTelefono.getText().toString().trim().isEmpty() ||
                tietCorreo.getText().toString().trim().isEmpty() ||
                tietDireccion.getText().toString().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private void guardarSolicitud() {

        DatabaseReference reference = database.getReference(Constante.TABLA_SOLICITUD);
        UUID uuid1 = UUID.randomUUID();
        SolicitudAdopcionModel usuarioModel = new SolicitudAdopcionModel();
        usuarioModel.setNombre(tietNombre.getText().toString());
        usuarioModel.setDocumento(tietDocumento.getText().toString());
        usuarioModel.setTelefono(tietTelefono.getText().toString());
        usuarioModel.setCorreo(tietCorreo.getText().toString());
        usuarioModel.setDireccion(tietDireccion.getText().toString());
        reference.child(uuid1.toString()).setValue(usuarioModel);
        hideProgreesDialog();
    }

    private void showProgressDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_autenticando, null);
        //showMessage = (TextView) view.findViewById(R.id.tvAccion);
        //showMessage.setText("Autenticando..");

        progressDialog = PetUtil.getProgressDialog(SolicitudAdopcionActivity.this, "Autenticando...");
        progressDialog.show();
        progressDialog.setContentView(view);
        progressDialog.setCancelable(false);
    }

    private void hideProgreesDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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