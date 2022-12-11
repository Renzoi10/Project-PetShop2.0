package com.utp.petfriendly.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.utp.petfriendly.R;

import java.util.List;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private LinearLayout lnlTienda, lnlDog, lnlRabbit, lnlBird, lnlCat, lnlVeterinaria, lnlCampania;
    private AppCompatImageView appCompatImageView;
    private FragmentManager fragmentManager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lnlCampania = view.findViewById(R.id.lnlCampania);
        lnlTienda = view.findViewById(R.id.lnlTienda);
        lnlVeterinaria = view.findViewById(R.id.lnlVeterinaria);
        appCompatImageView = view.findViewById(R.id.appCompatImageView);
        lnlDog = view.findViewById(R.id.lnlDog);
        lnlCat = view.findViewById(R.id.lnlCat);
        lnlBird = view.findViewById(R.id.lnlBird);
        lnlRabbit = view.findViewById(R.id.lnlRabbit);
        appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new AdopcionFragment());
            }
        });

        lnlTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new TiendaFragment());
            }
        });

        lnlVeterinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new VeterinariaFragment());
            }
        });

        lnlDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new TiendaFragment().newInstance(1));
            }
        });

        lnlCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new TiendaFragment().newInstance(2));
            }
        });

        lnlBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new TiendaFragment().newInstance(3));
            }
        });

        lnlRabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new TiendaFragment().newInstance(4));
            }
        });

        lnlCampania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new CampaniaFragment());
            }
        });


    }

    public void setFragment(Fragment fragment) {
        if (null != fragment) {
            fragmentManager = getActivity().getSupportFragmentManager();
            boolean existFragment = false;
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments != null) {
                for (Fragment mFragment : fragments) {
                    if (mFragment != null && mFragment.isVisible()) {
                        if (mFragment.getClass().getName().equals(fragment.getClass().getSimpleName())) {
                            existFragment = true;
                        }
                    }
                }
            }
            if (!existFragment) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.content_main, fragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
            } else {
                fragmentManager.beginTransaction().remove(fragment)
                        .replace(R.id.content_main, fragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
            }
        }
    }
}