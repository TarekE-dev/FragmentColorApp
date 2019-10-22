package edu.temple.fragmentcolorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PalleteFragment.ColorSelectedListener{

    DisplayFragment displayFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment = new DisplayFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container1, PalleteFragment.newInstance(getResources().getStringArray(R.array.colors)))
                .add(R.id.container2, displayFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String color) {
        displayFragment.getView().setBackgroundColor(Color.parseColor(color));
    }
}
