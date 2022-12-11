package com.utp.petfriendly.view.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utp.petfriendly.R;
import com.utp.petfriendly.model.AdopcionModel;
import com.utp.petfriendly.model.VeterinariaModel;

public class DetalleVeterinariaFragment extends Fragment {

    private TextView txtNombre, txtDescipcion, txtHorario, txtTelefono, txtDireccion;
    private VeterinariaModel veterinariaModel;

    public static DetalleVeterinariaFragment newInstance(VeterinariaModel veterinariaModel) {
        DetalleVeterinariaFragment fragment = new DetalleVeterinariaFragment();
        Bundle args = new Bundle();
        args.putSerializable("veterinariaModel", veterinariaModel);
        //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            veterinariaModel = (VeterinariaModel) getArguments().getSerializable("veterinariaModel");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_veterinaria, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setToolbar(view);
    }

    private void initView(View view) {
        txtNombre = view.findViewById(R.id.txtNombre);
        txtDescipcion = view.findViewById(R.id.txtDescipcion);
        txtHorario = view.findViewById(R.id.txtHorario);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        txtDireccion = view.findViewById(R.id.txtDireccion);

        txtNombre.setText(veterinariaModel.getNombreVeterinaria());
        txtDescipcion.setText(veterinariaModel.getDescripcion());
        txtHorario.setText(veterinariaModel.getAtencion());
        txtTelefono.setText(veterinariaModel.getTelefono());
        txtDireccion.setText(veterinariaModel.getDireccion());

    }

    private void setToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_back_24, null);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
            ab.setHomeAsUpIndicator(drawable);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}