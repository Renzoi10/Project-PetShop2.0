package com.utp.petfriendly.view.activity;

import static com.utp.petfriendly.util.Constante.TABLA_ADOPCION;
import static com.utp.petfriendly.util.Constante.TABLA_CAMPANIA;
import static com.utp.petfriendly.util.Constante.TABLA_VETERINARIA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.AdopcionModel;
import com.utp.petfriendly.model.CampaniasModel;
import com.utp.petfriendly.model.TiendaModel;
import com.utp.petfriendly.model.VeterinariaModel;
import com.utp.petfriendly.util.Constante;
import com.utp.petfriendly.viewModel.AdopcionViewModel;

import java.util.List;
import java.util.UUID;

public class WelcomeActivity extends AppCompatActivity {

    private CardView cardStart;
    private FirebaseDatabase database;
    private AdopcionViewModel adopcionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        adopcionViewModel = new ViewModelProvider(this).get(AdopcionViewModel.class);

        cardStart = findViewById(R.id.cardStart);
        cardStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });

        database = FirebaseDatabase.getInstance();
     // crearAdopcion();
       // obtenerAdopcionFireBase();
        //crearAdopcion();
    }

    //TEST OBTENER DATA DE FIREBASE
    private void obtenerAdopcionFireBase() {

       /* DatabaseReference ref = FirebaseDatabase.getInstance(Constante.URL_DATABASEFIRE).getReference().child(Constante.TABLA_ADOPCION);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("onDataChange", "dataa");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AdopcionModel adopcionModel = postSnapshot.getValue(AdopcionModel.class);
                    Log.e("onDataChange", " : " + adopcionModel.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });*/
    }

    //TEST CREAR DATA EN FIREBASE


    private void crearAdopcion(){
        DatabaseReference reference = database.getReference(TABLA_ADOPCION);

        UUID uuid1 = UUID.randomUUID();
        CampaniasModel obj = new CampaniasModel();
        obj.setDescripcion("Miraflores cuenta con la Primera Brigada de Tenencia Responsable de Mascotas: son brigadistas capacitados que recorren " +
                "los parques orientando a los vecinos sobre las normas básicas de higiene, a fin de salvaguardar la salud y el bienestar de la comunidad. " +
                "La brigada se encuentra durante los turnos de mañana y tarde en parques como el Raimondi, John F. Kennedy, Domodossola," +
                " Francisco de Zela, Ramón Castilla, Solari, Tradiciones, Melitón Porras y el Faro.");
        obj.setTitulo("Brigada de Tenencia Responsable de Mascotas");
        obj.setImagen("https://www.miraflores.gob.pe/wp-content/uploads/2020/12/BRIGADISTAS-DE-TENENCIA-RESPONSABLE-DE-MASCOTAS-1024x461.jpg");
        reference.child(uuid1.toString()).setValue(obj);
    }


    private void crearVeterianaria(){
        DatabaseReference reference = database.getReference(TABLA_VETERINARIA);

        UUID uuid1 = UUID.randomUUID();
        VeterinariaModel obj = new VeterinariaModel();
        obj.setNombreVeterinaria("Veterinaria Rodriguez Eirl");
        obj.setDireccion("Sector 1 Grupo 3 Manzana M Lote 22 Frente a la iglesia los mormones, Villa EL Salvador 15842");
        obj.setAtencion("8:00 am - 22:00 pm");
        obj.setTelefono("925896325");

        obj.setImagen("https://www.miraflores.gob.pe/wp-content/uploads/2020/12/BRIGADISTAS-DE-TENENCIA-RESPONSABLE-DE-MASCOTAS-1024x461.jpg");
        reference.child(uuid1.toString()).setValue(obj);
    }

}