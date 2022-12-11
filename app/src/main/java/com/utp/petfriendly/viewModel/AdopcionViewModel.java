package com.utp.petfriendly.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.utp.petfriendly.model.AdopcionModel;
import com.utp.petfriendly.respository.AdopcionRepository;

import java.util.List;

public class AdopcionViewModel extends ViewModel {

    private AdopcionRepository adopcionRepository;

    public  MutableLiveData<List<AdopcionModel>> obtenerAdopcion() {
        adopcionRepository = new AdopcionRepository();
        return adopcionRepository.obtenerAdopcion();
    }
}
