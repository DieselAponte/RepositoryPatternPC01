package Utilidades;
import java.io.*;

public class ManejadorArchivos {
    public static void guardar(String ruta, String contenido) throws IOException {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            fw.write(contenido + "\n");
        }
    }

    public static String leer(String ruta) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        }
        return sb.toString();
    }
    }

