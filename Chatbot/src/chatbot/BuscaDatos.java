package chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class BuscaDatos extends FileController {

    /**
     * Ruta del archivo que contiene la base de datos de palabras.
     */
    private static final String DATABASE = "./datos.txt";

    /**
     * Lector utilizado para consultar la base de datos de palabras.
     */
    private RandomAccessFile fileReader;

    public BuscaDatos() {
        super(DATABASE);
        try {
            fileReader = new RandomAccessFile(file, "r");

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "La base de datos de traduccion no fue encontrada",
                    "Error Base Datos Traduccion",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public String translate(String word) {
        String translation = "ok";
        String palabraGuardada = "";
        String palabra = word;
        int contador = 0, contador2 = 0;

        try {
            String line = fileReader.readLine();
            boolean encontro = false;
            while (line != null && encontro != true) {
                String[] tupla = line.split("#");
                String[] tuplados = palabra.split(" ");

                for (int i = 0; i <= tuplados.length - 1; i++) {
                    for (int j = 0; j <= tupla.length - 1; j++) {
                        if (tuplados[i].equalsIgnoreCase(tupla[j])) {
                            translation = tupla[tupla.length - 1];
                            contador++;
                        }
                    }
                }
                if (contador >= contador2) {
                    contador2 = contador;
                    palabraGuardada = translation;
                }
                contador = 0;
                line = fileReader.readLine();
            }
            //  System.out.println("palbra acumulada : "+ palabraGuardada);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al leer la base de datos de palabras",
                    "Error Base Datos Traduccion",
                    JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al cerrar la base de datos de palabras",
                        "Error Base Datos Traduccion",
                        JOptionPane.ERROR_MESSAGE);
                ioe.printStackTrace();
            }
        }

        return palabraGuardada;
    }

}
