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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.utp.petfriendly.R;
import com.utp.petfriendly.model.CampaniasModel;
import com.utp.petfriendly.model.VeterinariaModel;
import com.utp.petfriendly.view.adapter.CampaniaAdapter;
import com.utp.petfriendly.view.adapter.VeterinariaAdapter;
import com.utp.petfriendly.viewModel.VeterinariaViewModel;

import java.util.ArrayList;
import java.util.List;

public class CampaniaFragment extends Fragment implements CampaniaAdapter.OnItemClickListener {

    private CampaniaAdapter adapter;
    private List<CampaniasModel> list;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private VeterinariaViewModel viewModel;
    private FragmentManager fragmentManager;

    public CampaniaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_campania, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VeterinariaViewModel.class);
        setToolbar(view);

        recyclerView = view.findViewById(R.id.rv_campania);
        linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        list = new ArrayList<>();
        adapter = new CampaniaAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);

        obtenerCampania();
    }

    private void obtenerCampania() {
        viewModel.obtenerCampanias().observe(getActivity(), new Observer<List<CampaniasModel>>() {
            @Override
            public void onChanged(List<CampaniasModel> obj) {
                if (!obj.isEmpty()) {
                    adapter.setCampania(obj);
                }
            }
        });
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

    @Override
    public void onItemClick(CampaniasModel item) {

    }
}