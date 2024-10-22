package org.example;

import org.example.ficheros.FicheroAleatorio;
import org.example.modelo.Persona;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Juan Pérez", 30, "12345678A");
        Persona persona2 = new Persona("María García", 25, "87654321B");
        Persona persona3 = new Persona("Carlos López", 40, "11223344C");
        Persona persona4 = new Persona("Ana Fernández", 35, "44332211D");
        Persona persona5 = new Persona("Luis Martínez", 28, "55667788E");

        FicheroAleatorio.leerPersonas();

        ArrayList<Long> listadoPosicones = FicheroAleatorio.leerPosicionesPersona();


        System.out.println(FicheroAleatorio.buscarPersona(listadoPosicones.get(0)));
        //FicheroAleatorio.insertarObjeto(persona1);
       /* FicheroAleatorio.insertarObjeto(persona2);
        FicheroAleatorio.insertarObjeto(persona3);
        FicheroAleatorio.insertarObjeto(persona4);
        FicheroAleatorio.insertarObjeto(persona5);*/

       /* ArrayList<Persona> personas = FicheroAleatorio.leerPersonas();
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.get(i).toString());
        }*/

        // System.out.println(FicheroAleatorio.buscarPersona("11223344C"));

    }
}
