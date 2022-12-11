package com.utp.petfriendly.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.utp.petfriendly.model.TiendaModel;
import com.utp.petfriendly.respository.TiendaRepository;

import java.util.List;

public class TiendaViewModel extends ViewModel {

    private TiendaRepository tiendaRepository;

    public MutableLiveData<List<TiendaModel>> obtenerTiendas() {
        tiendaRepository = new TiendaRepository();
        return tiendaRepository.obtenerTiendas();
    }
}
