package org.example.ficheros;

import org.example.modelo.Persona;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FicheroAleatorio {


    public static void insertarObjeto(Persona persona) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "rw")) {
            file.seek(file.length());
            file.writeUTF(persona.getNombre());        // Escribir el ID (4 bytes)
            file.writeInt(persona.getEdad());    // Escribir la Edad (2 bytes)
            file.writeUTF(persona.getDni()); // Escribir el Salario (4 bytes)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertarObjeto(Persona persona, String fichero) {
        try (RandomAccessFile file = new RandomAccessFile(fichero, "rw")) {
            file.seek(file.length());
            file.writeUTF(persona.getNombre());        // Escribir el ID (4 bytes)
            file.writeInt(persona.getEdad());    // Escribir la Edad (2 bytes)
            file.writeUTF(persona.getDni()); // Escribir el Salario (4 bytes)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertarObjetos(ArrayList<Persona> personas, String fichero) {
        try (RandomAccessFile file = new RandomAccessFile(fichero, "rw")) {
            file.seek(file.length());
            for (int i = 0; i < personas.size(); i++) {
                Persona persona = personas.get(i);
                file.writeUTF(persona.getNombre());
                file.writeInt(persona.getEdad());
                file.writeUTF(persona.getDni());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     *
     * 1: Adrina
     *
     *
     * */
    public static ArrayList<Persona> leerPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();


        try (RandomAccessFile file = new RandomAccessFile("b.bin", "r")) {

            while (true) {
                String nombre = file.readUTF();
                int edad = file.readInt();
                String dni = file.readUTF();
                Persona persona = new Persona(nombre, edad, dni);

                personas.add(persona);
                System.out.println(persona);
            }

        } catch (IOException ioException) {

        }

        return personas;
    }


    public static ArrayList<Long> leerPosicionesPersona() {
        ArrayList<Long> personas = new ArrayList<>();

        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {

            long fileLength = file.length(); // Tamaño total del archivo.
            long numberOfRecords = fileLength / Persona.SIZE_PARAMETER; // Calcula el número de registros.

            // Agrega las posiciones (offsets) de cada registro al índice.
            for (long i = 0; i < numberOfRecords; i++) {
                personas.add(i * Persona.SIZE_PARAMETER);
            }

        } catch (IOException ioException) {

        }

        return personas;
    }

    public static Persona buscarPersona(String dni) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            while (true) {
                String nombre = file.readUTF();
                int edad = file.readInt();
                String dnis = file.readUTF();

                if (dnis.equals(dni)) {
                    return new Persona(nombre, edad, dni);
                }
            }
        } catch (IOException ioException) {

        }
        return null;
    }

    public static Persona buscarPersona(Long pos) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            file.seek(pos);
            String nombre = file.readUTF();
            int edad = file.readInt();
            String dnis = file.readUTF();

            return new Persona(nombre, edad, dnis);
        } catch (IOException ioException) {

        }
        return null;
    }


    public static void modificar(String dni, String nombre) {

        ArrayList<Persona> personas = FicheroAleatorio.leerPersonas();


        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);


            if (persona.getDni().contains(dni)) {
                persona.setNombre(nombre);
            }
        }


        FicheroAleatorio.insertarObjetos(personas, "b.bin");

        File fichero = new File("ejemplo_fichero.bin");
        fichero.delete();
        File fichero2 = new File("b.bin");
        fichero2.renameTo(fichero);

    }

}
