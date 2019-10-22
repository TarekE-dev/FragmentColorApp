package edu.temple.fragmentcolorapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ColorSelectedListener} interface
 * to handle interaction events.
 * Use the {@link PalleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PalleteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String COLOR_PARAM = "colorParam";

    // TODO: Rename and change types of parameters
    private String[] colors;

    private ColorSelectedListener parentFragment;
    private BaseAdapter colorAdapter;

    public PalleteFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PalleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PalleteFragment newInstance(String[] param1) {
        PalleteFragment fragment = new PalleteFragment();
        Bundle args = new Bundle();
        args.putStringArray(COLOR_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            colors = bundle.getStringArray(COLOR_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pallete, container, false);
        Spinner colorSpinner = (Spinner) view.findViewById(R.id.colorSpinner);
        colorAdapter = new ColorAdapter(this.getActivity(), colors);
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0){
                    view.setBackgroundColor(Color.WHITE);
                    String colorSplit[] = colors[i].split(",");
                    if(colorSplit.length == 2)
                        parentFragment.onFragmentInteraction(colorSplit[1]);
                    else
                        parentFragment.onFragmentInteraction(colorSplit[0]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ColorSelectedListener) {
            parentFragment = (ColorSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ColorSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentFragment = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ColorSelectedListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String color);
    }
}
