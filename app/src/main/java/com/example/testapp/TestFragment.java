package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

    private View view;
    public String nome;
    private TextView fragmentText;

    private CheckBox checkMar;
    private CheckBox checkMer;
    private CheckBox checkGio;
    private CheckBox checkVen;
    private CheckBox checkSab;
    private CheckBox checkDom;




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
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("nome", (String) fragmentText.getText());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Tiene traccia del nome inserito all'intero della casella testo in MainActivity
        nome = getArguments().getString("nome");
        view = inflater.inflate(R.layout.fragment_test, container, false);
        fragmentText = view.findViewById(R.id.nome_testo);
        fragmentText.setText(nome);

        //Salva lo stato del frammento
        if (savedInstanceState != null){
            String nome = savedInstanceState.getString("nome");
            fragmentText.setText(nome);
        }
        checkMar = view.findViewById(R.id.checkMar);
        checkMer = view.findViewById(R.id.checkMer);
        checkGio = view.findViewById(R.id.checkGio);
        checkVen = view.findViewById(R.id.checkVen);
        checkSab = view.findViewById(R.id.checkSab);
        checkDom = view.findViewById(R.id.checkDom);

        //

        //Rimuove il frammento selezionato
        Button button = (Button) view.findViewById(R.id.rimuovi);
        button.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) this.getActivity();
            LinearLayout linearLayout = mainActivity.findViewById(R.id.linear_layout);
            FrameLayout frameLayout = mainActivity.findViewById(R.id.frame_layout);
            linearLayout.removeView(frameLayout);
            linearLayout.removeView(mainActivity.findViewById(this.getId()));
            Log.i("Printa", "Printato");
        });




        return view;
    }

    public void azzera(){
        this.checkMar.setChecked(false);
        this.checkMer.setChecked(false);
        this.checkGio.setChecked(false);
        this.checkVen.setChecked(false);
        this.checkSab.setChecked(false);
        this.checkDom.setChecked(false);
    }

    /*
    Inserisce in una lista i valori di tipo GiorniEnum selezionali nella checkbox
     */
    public static List<GiorniEnum> mostraGiorni(TestFragment fragment){
        List<GiorniEnum> listaGiorniEnum = new ArrayList<GiorniEnum>();

        if (fragment.checkMar.isChecked()) {
            listaGiorniEnum.add(GiorniEnum.MAR);
        }
        if (fragment.checkMer.isChecked()){
            listaGiorniEnum.add(GiorniEnum.MER);
        }
        if (fragment.checkGio.isChecked()){
            listaGiorniEnum.add(GiorniEnum.GIO);
        }
        if (fragment.checkVen.isChecked()){
            listaGiorniEnum.add(GiorniEnum.VEN);
        }
        if (fragment.checkSab.isChecked()){
            listaGiorniEnum.add(GiorniEnum.SAB);
        }
        if (fragment.checkDom.isChecked()){
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