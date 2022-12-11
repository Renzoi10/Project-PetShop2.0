package com.utp.petfriendly.view.fragment;

import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.AdopcionModel;
import com.utp.petfriendly.view.activity.SolicitudAdopcionActivity;
import com.utp.petfriendly.view.adapter.AdopcionAdapter;

import java.util.List;


public class DetalleAdopcionFragment extends Fragment {

    private AdopcionModel adopcionModel;
    private TextView txtRaza, txtDescipcion, txtTamanio, txtRangoEdad, txtEspecialidad;
    private ImageView imgDog;
    private Button btnAdoptar;

    public static DetalleAdopcionFragment newInstance(AdopcionModel adopcionModel) {
        DetalleAdopcionFragment fragment = new DetalleAdopcionFragment();
        Bundle args = new Bundle();
        args.putSerializable("adopcionModel", adopcionModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
            adopcionModel = (AdopcionModel) getArguments().getSerializable("adopcionModel");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_adopcion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        txtRaza = view.findViewById(R.id.txtRaza);
        txtDescipcion = view.findViewById(R.id.txtDescipcion);
        txtTamanio = view.findViewById(R.id.txtTamanio);
        txtRangoEdad = view.findViewById(R.id.txtRangoEdad);
        txtEspecialidad = view.findViewById(R.id.txtEspecialidad);
        imgDog = view.findViewById(R.id.imgDog);
        btnAdoptar = view.findViewById(R.id.btnAdoptar);
        btnAdoptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SolicitudAdopcionActivity.class);
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {
        if (adopcionModel != null) {
            txtRaza.setText(adopcionModel.getRaza());
            txtDescipcion.setText(adopcionModel.getDescripcion());
            txtTamanio.setText(adopcionModel.getTamanio());
            txtRangoEdad.setText(adopcionModel.getRangoEdad());
            txtEspecialidad.setText(adopcionModel.getEspecialidad());
            if (adopcionModel.getImagen() != null) {
                Picasso.with(getContext()).load(adopcionModel.getImagen()).into(imgDog);
            }
        }
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