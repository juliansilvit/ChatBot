package chatbot;

import java.io.*;

public class Leer {

    File file = new File("datos.txt");

    public String leertxt(String direccion) {
        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temporal = "";
            String bfread;
            while ((bfread = bf.readLine()) != null) {
                temporal = temporal + bfread + "\r\n";
            }
            texto = temporal;
        } catch (Exception e) {
        }
        return texto;
    }

    public String preguntanueva(String pregunta, String respuesta) {

        //String myword="";
        // String respuesta=""; 
        String nuevaPalabra = "";
        String[] tupla3 = pregunta.split(" ");
        //System.out.println(tupla3[0]);
        for (int i = 0; i <= tupla3.length - 1; i++) {
            if (i == tupla3.length - 1) {
                nuevaPalabra = nuevaPalabra + tupla3[i] + " # " + tupla3[i] + " ? " + " # " + respuesta;
            } else if (tupla3[i].equalsIgnoreCase("que") || tupla3[i].equalsIgnoreCase("es") || tupla3[i].equalsIgnoreCase("un") || tupla3[i].equalsIgnoreCase("de") || tupla3[i].equalsIgnoreCase("hijo")) {
                continue;
            } else {
                nuevaPalabra = nuevaPalabra + tupla3[i] + "#";
            }
        }
        System.out.println(nuevaPalabra);
        // palabra.guardar(palabra.leertxt("fileName1.txt"), nuevaPalabra);
        return nuevaPalabra;
    }

    public void guardar(String contenidoAnteriorTxt, String nuevapalabra) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pw = new PrintWriter(file);
            // pw.write(leertxt("fileName1.txt"));
            //String palabra=leertxt("fileName1.txt");
            pw.append(contenidoAnteriorTxt + nuevapalabra);

            // pw.append(leertxt("fileName1.txt"))
            pw.println("Done");
            pw.close();
        } catch (IOException trys) {
            // TODO Auto-generated catch block
           trys.printStackTrace();
        }
    }

}
