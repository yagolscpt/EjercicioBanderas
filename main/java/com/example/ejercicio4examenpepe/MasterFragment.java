package com.example.ejercicio4examenpepe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class MasterFragment extends ListFragment {

    //global para usar el listener
    private OnMasterSelectedListener mOnMasterSelectedListener = null;

    //seteamos el listener
    public void setOnMasterSelectedListener(OnMasterSelectedListener listener) {
        mOnMasterSelectedListener = listener;
    }


    // creamos una interfaz

    public interface OnMasterSelectedListener {
        public void onItemSelected(String countryName, String dialogue, int image);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] titles = com.example.ejercicio4examenpepe.Datos.TITLES;
        ListAdapter countryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);
        setListAdapter(countryAdapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnMasterSelectedListener != null) {
                    mOnMasterSelectedListener.onItemSelected(com.example.ejercicio4examenpepe.Datos.TITLES[position], com.example.ejercicio4examenpepe.Datos.DIALOGUE[position], com.example.ejercicio4examenpepe.Datos.IMAGENES[position]);
                }
            }
        });
    }

}
