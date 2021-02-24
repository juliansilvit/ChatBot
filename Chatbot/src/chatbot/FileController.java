package chatbot;

import javax.swing.JOptionPane;
import java.io.*;

public class FileController {

    public File file;

    public FileController(String fileName) {
        file = new File(fileName);
    }

    public FileController(File file) {
        this.file = file;
    }

    public String getFileContent() {
        StringBuilder fileContent = new StringBuilder();
        try {
            RandomAccessFile fileReader = new RandomAccessFile(file, "r");
            String line = fileReader.readLine();

            while (line != null) {
                fileContent.append("\n").append(line);
                line = fileReader.readLine();
            }

        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(
                    null,
                    "El archivo no existe",
                    "ERROR EN ARCHIVO",
                    JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        } catch (IOException example) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al leer el archivo",
                    "ERROR EN ARCHIVO",
                    JOptionPane.ERROR_MESSAGE);
            example.printStackTrace();
        }

        return fileContent.toString();
    }

    public void save(String fileContent) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.println(fileContent);
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(
                    null,
                    "El archivo no existe",
                    "ERROR EN ARCHIVO",
                    JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        }
    }
}
