package Clases;

public class Dado {

    private final String nombre;
    private int valor;

    public Dado(String nombre) {
        this.nombre = nombre;
        valor = 0;
    }

    static int generaValorDado(){
        return  (int) Math.floor(Math.random() * (6 - 1)) +1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nombre + ": " + valor;
    }

}
