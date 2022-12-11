package com.utp.petfriendly.respository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utp.petfriendly.model.TiendaModel;
import com.utp.petfriendly.model.UsuarioModel;
import com.utp.petfriendly.util.Constante;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public MutableLiveData<UsuarioModel> obtenerUsuario(String usuario, String clave) {
        Log.e("usuario",""+usuario);
        Log.e("clave",""+clave);
        MutableLiveData<UsuarioModel> adopcionModelMutableLiveData = new MutableLiveData<>();
        DatabaseReference ref = FirebaseDatabase.getInstance(Constante.URL_DATABASEFIRE).getReference().child(Constante.TABLA_USUARIO).child(usuario).child(clave);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    UsuarioModel  usuarioModel = (UsuarioModel)dataSnapshot.getValue();
                    adopcionModelMutableLiveData.setValue(usuarioModel);
                }else{
                    adopcionModelMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
        return adopcionModelMutableLiveData;
    }

}
