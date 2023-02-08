package com.example.ejercicio4examenpepe;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    public static String KEY_COUNTRY_NAME="KEY_COUNTRY_NAME";
    public static String KEY_DESCRIPTION_NAME="KEY_DESCRIPTION_NAME";
    public static String KEY_IMAGE_ID="KEY_IMAGE_ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    // este metodo sobrescrito es novedoso (porque recive informacion desde el mainactivity)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_COUNTRY_NAME)) {
            showSelectedCountry(bundle.getString(KEY_COUNTRY_NAME), bundle.getString(KEY_DESCRIPTION_NAME), bundle.getInt(KEY_IMAGE_ID));
        }
    }

    public void showSelectedCountry(String countryName, String descripcion, int imagen) {
        TextView textView = getView().findViewById(R.id.textViewCountryName);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(countryName);

        TextView textView1 = getView().findViewById(R.id.descripcion);
        textView1.setMovementMethod(new ScrollingMovementMethod());
        textView1.setText(descripcion);

        ImageView imageView = getView().findViewById(R.id.imageView);
        imageView.setImageResource(imagen);
    }

}
