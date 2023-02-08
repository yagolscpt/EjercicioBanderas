package com.example.ejercicio4examenpepe;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.example.ejercicio4examenpepe.MasterFragment masterFragment = null;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);



        if (frameLayout != null) {
            mDualPane = false;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (com.example.ejercicio4examenpepe.MasterFragment) getSupportFragmentManager().findFragmentByTag("MASTER");

            if (masterFragment == null) {
                masterFragment = new com.example.ejercicio4examenpepe.MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }

            com.example.ejercicio4examenpepe.DetailFragment detailFragment = (com.example.ejercicio4examenpepe.DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);

            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }
            fragmentTransaction.commit();
        } else {
            mDualPane = true;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (com.example.ejercicio4examenpepe.MasterFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutMaster);
            if (masterFragment == null) {
                masterFragment = new com.example.ejercicio4examenpepe.MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }
            com.example.ejercicio4examenpepe.DetailFragment detailFragment = (com.example.ejercicio4examenpepe.DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new com.example.ejercicio4examenpepe.DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }
            fragmentTransaction.commit();
        }
        //listener para el menu de la lista
        masterFragment.setOnMasterSelectedListener(new com.example.ejercicio4examenpepe.MasterFragment.OnMasterSelectedListener() {
            @Override
            public void onItemSelected(String countryName, String description, int image) {
                sendCountryName(countryName, description, image);
            }
        });

    }

    private void sendCountryName(String countryName, String description, int image) {
        com.example.ejercicio4examenpepe.DetailFragment detailFragment;
        if (mDualPane) {
            //Two pane layout
            detailFragment = (com.example.ejercicio4examenpepe.DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            detailFragment.showSelectedCountry(countryName, description, image);
        } else {
            // Single pane layout
            detailFragment = new com.example.ejercicio4examenpepe.DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(com.example.ejercicio4examenpepe.DetailFragment.KEY_COUNTRY_NAME, countryName);
            bundle.putString(com.example.ejercicio4examenpepe.DetailFragment.KEY_DESCRIPTION_NAME, description);
            bundle.putInt(com.example.ejercicio4examenpepe.DetailFragment.KEY_IMAGE_ID, image);
            detailFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


}