package com.example.testapp;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persona implements Comparable<Persona> {
    public String nome;
    public List<GiorniEnum> giorniDisponibili;
    public int giorniPrenotati;

    public static Map<Giorno, List<Persona>> giorniMapPersona = new HashMap<>();

    public Persona(String nome, List<GiorniEnum> giorniDisponibili) {
        this.nome = nome;
        this.giorniDisponibili = giorniDisponibili;
    }

    @Override
    public String toString(){
        return nome;
    }

    @Override
    public int compareTo(Persona o) {
        return Integer.compare(giorniPrenotati, o.giorniPrenotati);
    }

    public static void generaMappingGiorniPersona(List<Persona> listaPersone) {
        for (Persona persona : listaPersone) {
            for (Giorno giorno : Giorno.listaGiorni) {
                if (persona.giorniDisponibili.contains(giorno.nome)) {
                    giorno.personeDisponibiliGiorno.add(persona);
                }
            }
        }
        for (Giorno giorno : Giorno.listaGiorni) {
            giorniMapPersona.put(giorno, giorno.personeDisponibiliGiorno);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void assegnaTurni(List<Giorno> listaGiorni) {
        for (Giorno giorno : listaGiorni) {
            for (int i = 0; i < giorno.numeroPersone; i++) {
                if (!giorno.personeDisponibiliGiorno.isEmpty()){
                    Persona personaMinima = Collections.min(giorno.personeDisponibiliGiorno);
                    giorno.personeDisponibiliGiorno.remove(personaMinima);
                    personaMinima.giorniPrenotati += 1;
                    giorno.personeAssegnateGiorno.add(personaMinima);
                }

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {}
}

