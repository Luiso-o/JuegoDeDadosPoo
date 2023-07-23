package Clases;

import recursos.MongoRecursos;
import recursos.MysqlRecursos;
import javax.swing.*;

public class Juego {
    private static Jugador jugador;

    public static void crearpartida() {

        String nombre = JOptionPane.showInputDialog("Introduce tu nombre");
       jugador = new Jugador(nombre);

        String cantidadlanzadas = JOptionPane.showInputDialog("Cuantas veces quieres lanzar los dados?");
        int numerolanzadas = controlaentero(cantidadlanzadas);

        lanzardados(numerolanzadas);
    }

    private static void lanzardados(int cantidadlanzadas){

        Dado dado1 = new Dado("Dado #1");
        Dado dado2 = new Dado("Dado #2");
        int victorias = 0;

        System.out.println("Empezamos la partida :,D!! \n");

        for (int i = 0; i < cantidadlanzadas; i++) {
            dado1.setValor(Dado.generaValorDado());
            dado2.setValor(Dado.generaValorDado());

            System.out.println("Ronda # " + (i+1));
            System.out.println(dado1);
            System.out.println(dado2);
            victorias += generarespuestaronda(dado1,dado2);
            System.out.println();
        }

        calculaporcentaje(victorias, cantidadlanzadas);
    }

    private static int generarespuestaronda(Dado dado1, Dado dado2){

        int valordado1 = dado1.getValor();
        int valordado2 = dado2.getValor();

        if(valordado1 + valordado2 == 7){
            System.out.println("Has tirado un 7!! esta ronda la has ganado tu :D" );
            return 1;
        }else{
            System.out.println("Has tirado un " + (valordado1+valordado2) + " esta ronda la has perdido :/");
        }
       return 0;
    }

    public static void calculaporcentaje(int victorias, int lanzadas){

        int derrotas = lanzadas - victorias;
        double porcentajevictorias = (double) (victorias * 100) / lanzadas;

        System.out.println("Fin del Juego \n" +
                "En este juego lograste " + victorias + " victorias (" + porcentajevictorias + " %) de la partida \n" +
                "y " + derrotas + " derrotas (" + (100 - porcentajevictorias) + " %).");

        guardarDatosPartida(victorias,derrotas);
    }

    public static void guardarDatosPartida(int victorias, int derrotas){
        Partida partida = new Partida();
        partida.setVictorias(victorias);
        partida.setDerrotas(derrotas);

        jugador.getMisPartidas().add(partida);
        MysqlRecursos.guardarJugadorEnBD(jugador);
        MongoRecursos.guardarJugadorEnDB(jugador);
    }

    private static int controlaentero(String numero) {
        int entero = 0;
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        boolean entradaValida = false;
        do {
            try {
                entero = Integer.parseInt(numero);
                if(entero > 100){
                    numero = JOptionPane.showInputDialog("Solo se permite un numero máximo de 100 lanzadas por partida.");
                    intentos++;
                }else {
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                numero = JOptionPane.showInputDialog("Debes introducir un número entero :/");
                intentos ++;

                if (intentos >= MAX_INTENTOS) {
                    JOptionPane.showMessageDialog(null,"Has alcanzado el máximo de intentos permitidos. Saliendo del programa.");
                    System.exit(0);
                }
            }
        } while (!entradaValida);
        return entero;
    }
}








