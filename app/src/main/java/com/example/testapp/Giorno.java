package com.example.testapp;

import java.util.ArrayList;
import java.util.List;

public class Giorno {
    public GiorniEnum nome;
    public List<Persona> personeDisponibiliGiorno = new ArrayList<>();
    public int numeroPersone = 3;
    public List<Persona> personeAssegnateGiorno = new ArrayList<>();
    public static List<Giorno> listaGiorni = new ArrayList<>();


    public Giorno(GiorniEnum giorno){
        this.nome = giorno;
        if ((this.nome == GiorniEnum.DOM) || (this.nome == GiorniEnum.DOM)){
            this.numeroPersone = 5;
        }
    }

    public static void main(String[] args){}


}
