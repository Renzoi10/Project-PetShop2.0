package com.utp.petfriendly.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.utp.petfriendly.model.UsuarioModel;
import com.utp.petfriendly.respository.UsuarioRepository;

public class UsurioViewModel extends ViewModel {
    private UsuarioRepository usuarioRepository;

    public MutableLiveData<UsuarioModel> obtenerUsuario(String usuario, String clave) {
        usuarioRepository = new UsuarioRepository();
        return usuarioRepository.obtenerUsuario(usuario,clave);
    }
}
