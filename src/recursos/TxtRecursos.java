package recursos;

import Clases.Jugador;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TxtRecursos {

    private static final String ARCHIVO_NOMBRES = "src" + File.separator + "recursos" + File.separator + "nombres.txt";

    //Recorre la listatxt para generar un id no repetido a un jugador
    public static int obtenerIdUnico() {
        int nuevoId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_NOMBRES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(", ");
                if (datos.length >= 2) {
                    int id = Integer.parseInt(datos[0]);
                    if (id >= nuevoId) {
                        nuevoId = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo nombres.txt: " + e.getMessage());
            e.printStackTrace();
        }
        return nuevoId;
    }

    // Método para guardar un nuevo jugador en el archivo nombres.txt
    public static void guardarJugador(Jugador jugador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_NOMBRES, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaRegistro = sdf.format(new Date());

            writer.write(jugador.getId() + ", " + jugador.getNombre() + ", " + fechaRegistro);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el jugador en el archivo nombres.txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
