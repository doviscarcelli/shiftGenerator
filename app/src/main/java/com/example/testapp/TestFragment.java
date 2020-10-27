package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt("FRAG_ID", fragment.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Tiene traccia del nome inserito all'intero della casella testo in MainActivity
        String nome = getArguments().getString("nome");
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        TextView nomePersona = v.findViewById(R.id.nome_testo);
        nomePersona.setText(nome);

        return v;
    }

    /*
    Inserisce in una lista i valori di tipo GiorniEnum selezionali nella checkbox
     */
    public static List<GiorniEnum> mostraGiorni(TestFragment fragment){
        List<GiorniEnum> listaGiorniEnum = new ArrayList<GiorniEnum>();

        CheckBox mar = fragment.getView().findViewById(R.id.checkMar);
        CheckBox mer = fragment.getView().findViewById(R.id.checkMer);
        CheckBox gio = fragment.getView().findViewById(R.id.checkGio);
        CheckBox ven = fragment.getView().findViewById(R.id.checkVen);
        CheckBox sab = fragment.getView().findViewById(R.id.checkSab);
        CheckBox dom = fragment.getView().findViewById(R.id.checkDom);

        if (mar.isChecked()) {
            listaGiorniEnum.add(GiorniEnum.MAR);
        }
        if (mer.isChecked()){
            listaGiorniEnum.add(GiorniEnum.MER);
        }
        if (gio.isChecked()){
            listaGiorniEnum.add(GiorniEnum.GIO);
        }
        if (ven.isChecked()){
            listaGiorniEnum.add(GiorniEnum.VEN);
        }
        if (sab.isChecked()){
            listaGiorniEnum.add(GiorniEnum.SAB);
        }
        if (dom.isChecked()){
            listaGiorniEnum.add(GiorniEnum.DOM);
        }


        String listaGiorniEnumFormat = "";
        for (int i = 0; i < listaGiorniEnum.size(); i++){
            String giorno = listaGiorniEnum.get(i).toString();
            listaGiorniEnumFormat = listaGiorniEnumFormat.concat(giorno);
        }

        return listaGiorniEnum;
    }





}