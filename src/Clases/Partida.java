package Clases;

import java.time.LocalDate;

public class Partida {
    private static int contador = 1;
    private final int idPartida;
    private final LocalDate fecha;

    private int  victorias;
    private int derrotas;

    public Partida() {
       idPartida = contador++;
       fecha = LocalDate.now();
       victorias = 0;
       derrotas = 0;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    @Override
    public String toString() {
        return "Partida # " + idPartida + ", Fecha " + fecha + "\n" +
                "Total de victorias: "+ victorias +
                "\nTotal de Derrotas: " + derrotas;
    }


}
