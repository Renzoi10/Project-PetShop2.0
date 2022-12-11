package com.utp.petfriendly.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.utp.petfriendly.model.CampaniasModel;
import com.utp.petfriendly.model.VeterinariaModel;
import com.utp.petfriendly.respository.VeterinariaRepository;
import java.util.List;

public class VeterinariaViewModel extends ViewModel {

    private VeterinariaRepository vetrepositori;

    public MutableLiveData<List<VeterinariaModel>> obtenerVeterianrias() {
        vetrepositori = new VeterinariaRepository();
        return vetrepositori.obtenerVeterinarias();
    }

    public MutableLiveData<List<CampaniasModel>> obtenerCampanias() {
        vetrepositori = new VeterinariaRepository();
        return vetrepositori.obtenerCampanias();
    }



}
