package recursos;

import Clases.Jugador;
import Clases.Partida;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MongoRecursos {

    private static MongoDatabase database;

    static {
        // Configuración de conexión a MongoDB
        System.out.println("Configuración de conexión a MongoDB...");
        String host = "localhost";
        int port = 27017;
        String databaseName = "juego_de_dados_db";

        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        CodecRegistry pojoCodecRegistryWithCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(codecRegistry, pojoCodecRegistry);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(host, port))))
                .codecRegistry(pojoCodecRegistryWithCodecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
    }

    public static void guardarJugadorEnDB(Jugador jugador) {
        // Convertir las partidas del jugador a documentos de MongoDB
        List<Document> partidasDocs = new ArrayList<>();
        for (Partida partida : jugador.getMisPartidas()) {
            Document partidaDoc = new Document("idPartida", partida.getIdPartida())
                    .append("fecha", partida.getFecha().toString())
                    .append("victorias", partida.getVictorias())
                    .append("derrotas", partida.getDerrotas());
            partidasDocs.add(partidaDoc);
        }

        // Construir el documento del jugador con sus partidas
        Document jugadorDoc = new Document("_id", jugador.getId())
                .append("nombre", jugador.getNombre())
                .append("Partidas", partidasDocs);

        // Guardar el jugador y sus partidas en la colección "jugadores"
        MongoCollection<Document> jugadoresCollection = database.getCollection("jugadores");
        jugadoresCollection.insertOne(jugadorDoc);

        System.out.println("Jugador y sus partidas guardados en la base de datos MongoDB.");
    }
}

