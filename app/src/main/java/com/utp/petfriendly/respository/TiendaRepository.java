package com.utp.petfriendly.respository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utp.petfriendly.model.TiendaModel;
import com.utp.petfriendly.util.Constante;

import java.util.ArrayList;
import java.util.List;

public class TiendaRepository {

    public MutableLiveData<List<TiendaModel>> obtenerTiendas() {
        MutableLiveData<List<TiendaModel>> adopcionModelMutableLiveData = new MutableLiveData<>();
        List<TiendaModel> listAdopcion = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance(Constante.URL_DATABASEFIRE).getReference().child(Constante.TABLA_TIENDA);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TiendaModel adopcionModel = postSnapshot.getValue(TiendaModel.class);
                    listAdopcion.add(adopcionModel);
                }
                adopcionModelMutableLiveData.setValue(listAdopcion);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return adopcionModelMutableLiveData;
    }

}

