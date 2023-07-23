package Clases;
import recursos.TxtRecursos;

import java.util.ArrayList;

public class Jugador {
    private final int id;
    private String nombre;
    private ArrayList<Partida>misPartidas;

    public Jugador(String nombre) {
        this.id = TxtRecursos.obtenerIdUnico();

        if (nombre.equals("")){
            this.nombre = "Anónimo";
        }else {
            this.nombre = nombre;
        }

        misPartidas = new ArrayList<>();

        TxtRecursos.guardarJugador(this);
    }
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ", " + nombre + "\n"+ misPartidas;
    }

    public ArrayList<Partida> getMisPartidas() {
        return misPartidas;
    }

}
