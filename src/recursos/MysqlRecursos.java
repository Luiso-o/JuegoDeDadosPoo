package recursos;

import Clases.Jugador;
import Clases.Partida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlRecursos {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        }
    }

    public static void guardarJugadorEnBD(Jugador jugador) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/juego_de_dados_db", "root", "")) {
            // Insertar el jugador en la tabla jugadores
            String insertJugadorQuery = "INSERT INTO jugadores (id, nombre) VALUES (?, ?)";
            PreparedStatement preparedStatementJugador = connection.prepareStatement(insertJugadorQuery);
            preparedStatementJugador.setInt(1, jugador.getId()); // Utilizamos el id generado automáticamente
            preparedStatementJugador.setString(2, jugador.getNombre());
            preparedStatementJugador.executeUpdate();

            // Insertar las partidas asociadas al jugador en la tabla partidas
            String insertPartidaQuery = "INSERT INTO partidas (idjugador, fecha, victorias, derrotas) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatementPartida = connection.prepareStatement(insertPartidaQuery);

            for (Partida partida : jugador.getMisPartidas()) {
                preparedStatementPartida.setInt(1, jugador.getId()); // Utilizamos el id generado automáticamente
                preparedStatementPartida.setString(2, partida.getFecha().toString());
                preparedStatementPartida.setInt(3, partida.getVictorias());
                preparedStatementPartida.setInt(4, partida.getDerrotas());
                preparedStatementPartida.executeUpdate();
            }

            System.out.println("Jugador y sus partidas guardados en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar el jugador en la base de datos: " + e.getMessage());
        }
    }
}
