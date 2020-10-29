package com.example.testapp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private View view;
    public static final List<Persona> listaPersone = new ArrayList<Persona>();
    public static List<TestFragment> fragmentList = new ArrayList<TestFragment>();
    public static final Map<TestFragment, String> fragmentMapInput = new HashMap<TestFragment, String>();

    public int counter;
    public static final Map<Integer, Integer> ITEM_MAP = new HashMap<Integer, Integer>();


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onRestoreInstanceState(Bundle savedInstanceState){

    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void manageDays(Boolean state){
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);

        if (state){
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);
            textView6.setVisibility(View.VISIBLE);

        }
        else{
            textView.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            textView4.setVisibility(View.INVISIBLE);
            textView5.setVisibility(View.INVISIBLE);
            textView6.setVisibility(View.INVISIBLE);
        }
    }

    public void azzeraCheckBox(View v){
        for (TestFragment fragment : fragmentList){
            fragment.azzera();
        }
    }


    public void addFragment(View v){
        view = v;
        TestFragment fragment = TestFragment.newInstance();
        //Inserisci il frammento alla lista dei frammenti
        fragmentList.add(fragment);
        //Bundle
        Bundle bundle = new Bundle();
        String input = ((EditText) findViewById(R.id.text_input)).getText().toString();
        bundle.putString("nome", input);
        fragment.setArguments(bundle);

        //Inserisci il mapping tra fragmento e nome della persona
        fragmentMapInput.put(fragment, input);

        //Begin transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(ITEM_MAP.get(counter), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        //End transaction

        //Cancella il testo inserito nell'EditText
        ((EditText)findViewById(R.id.text_input)).setText("");

        //Rendi visibili i tag dei giorni (Trova un altro modo per fare sta cosa dei tag dei giorni)
        manageDays(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void addContainer(View v){
        FrameLayout container = new FrameLayout(this);
        int id = View.generateViewId();
        container.setId(id);
        ITEM_MAP.put(counter, id);
        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        linearLayout.addView(container);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void click(View v){
        this.counter += 1;
        addContainer(v);
        addFragment(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void commit(View v){

        //Genera gli oggetti Giorno e li inserisce nella lista dei giorni
        for (GiorniEnum giorno : GiorniEnum.values()){
            Giorno nuovoGiorno = new Giorno(giorno);
            Giorno.listaGiorni.add(nuovoGiorno);
        }

        //Genera gli oggetti Persona e li inserisce nella lista delle persone
        for (int i = 0; i < fragmentList.size(); i++) {
            TestFragment actualFragment = fragmentList.get(i);
            List<GiorniEnum> giorniDisponibili = TestFragment.mostraGiorni(actualFragment);
            String nome = fragmentMapInput.get(fragmentList.get(i));
            Persona persona = new Persona(nome, giorniDisponibili);
            listaPersone.add(persona);
        }
        //Genera il mapping tra i giorni e le persone disponibili quei giorni e assegna i turni
        Persona.generaMappingGiorniPersona(listaPersone);
        Persona.assegnaTurni(Giorno.listaGiorni);

        //Logga il risultato
        for (Giorno giorno : Giorno.listaGiorni){
            Log.i("Shifts", giorno.nome.toString().concat(" "+giorno.personeAssegnateGiorno.toString()));
        }

        //Mostra turni su schermo
        String output = "";
        for (Giorno giorno : Giorno.listaGiorni){
            output = output.concat(giorno.nome.toString().concat(" "+giorno.personeAssegnateGiorno.toString()) + "\n");
        }

        TextView mostraTurni = findViewById(R.id.mostraTurni);
        mostraTurni.setText(output);
        Giorno.listaGiorni.clear();

    }








}