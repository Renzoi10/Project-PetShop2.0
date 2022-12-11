package com.utp.petfriendly.respository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utp.petfriendly.model.CampaniasModel;
import com.utp.petfriendly.model.VeterinariaModel;
import com.utp.petfriendly.util.Constante;
import java.util.ArrayList;
import java.util.List;

public class VeterinariaRepository {

    public MutableLiveData<List<VeterinariaModel>> obtenerVeterinarias() {
        MutableLiveData<List<VeterinariaModel>> vetModelMutableLiveData = new MutableLiveData<>();
        List<VeterinariaModel> listVeterinaria = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance(Constante.URL_DATABASEFIRE).getReference().child(Constante.TABLA_VETERINARIA);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VeterinariaModel vetModel = postSnapshot.getValue(VeterinariaModel.class);
                    listVeterinaria.add(vetModel);
                }
                vetModelMutableLiveData.setValue(listVeterinaria);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return vetModelMutableLiveData;
    }

    public MutableLiveData<List<CampaniasModel>> obtenerCampanias() {
        MutableLiveData<List<CampaniasModel>> vetModelMutableLiveData = new MutableLiveData<>();
        List<CampaniasModel> listVeterinaria = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance(Constante.URL_DATABASEFIRE).getReference().child(Constante.TABLA_CAMPANIA);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CampaniasModel vetModel = postSnapshot.getValue(CampaniasModel.class);
                    listVeterinaria.add(vetModel);
                }
                vetModelMutableLiveData.setValue(listVeterinaria);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return vetModelMutableLiveData;
    }

}
