package chatbot;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.applet.AudioClip;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends javax.swing.JFrame {
    
    String pregunta, respuesta, preguntagenerada;
    boolean reproducciendo = false;
    AudioClip sonido1, sonido2, alive;
    
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        texto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JTextArea();
        online = new javax.swing.JLabel();
        Namebot = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enviar.setBackground(new java.awt.Color(255, 255, 255));
        enviar.setText("enviar");
        enviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });
        getContentPane().add(enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, -1, -1));

        texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoActionPerformed(evt);
            }
        });
        getContentPane().add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 610, 23));

        pantalla.setEditable(false);
        pantalla.setColumns(20);
        pantalla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 12)); // NOI18N
        pantalla.setRows(5);
        jScrollPane1.setViewportView(pantalla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 683, 193));

        online.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        getContentPane().add(online, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 11, 95, 21));

        Namebot.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Namebot.setText("ChatBot");
        getContentPane().add(Namebot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jButton1.setText("Enseñar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pregunta = texto.getText();
                    respuesta = (new BuscaDatos().translate(texto.getText()));
                    preguntagenerada = (new BuscaDatos().translate(generarpregunta()));
                    pantalla.append("Usted: " + texto.getText() + "\n");
                    if (respuesta.equalsIgnoreCase("ok")) {
                        pantalla.append("Luna: Podrias enseñarme que debo responder si me dicen: '" + pregunta + "' por favor (si/no)\n");
                        String respuestUsuario = JOptionPane.showInputDialog("Deseas enseñarle?(si/no)");
                        if (respuestUsuario.equalsIgnoreCase("si")) {
                            String respuestUsuarioPregunta = JOptionPane.showInputDialog("Que responder a '" + pregunta + "'");
                            Leer aprender = new Leer();
                            String nuevapalabra = aprender.preguntanueva(pregunta, respuestUsuarioPregunta);
                            aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);
                        }
                    }
                    texto.setText("");
                    animacionEscribir(respuesta);
                    int probabilidad = mitadProbabilidad();
                    //System.out.println(probabilidad);
                    if (probabilidad > 5) {
                        //  System.out.println("entra");
                        animacionpregunta(preguntagenerada);
                        if (preguntagenerada.equalsIgnoreCase("pongamos musica") && reproducciendo != true) {
                            alive = java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
                            alive.play();
                        }
                        
                    }
                } catch (Exception e) {
                }
            }
        });
        hilo.start();

    }//GEN-LAST:event_enviarActionPerformed
    
    public String generarpregunta() {
        int numero;
        numero = (int) (Math.random() * 9) + 1;
        String preguntaAleatoria = Integer.toString(numero);
        String preguntacompletada = preguntaAleatoria + "p";
        return preguntacompletada;
    }
    
    public int mitadProbabilidad() {
        int numero;
        numero = (int) (Math.random() * 9) + 1;
        return numero;
    }
    
    public void animacionEscribir(String respuestaxd) throws InterruptedException, URISyntaxException, IOException {
        sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("./mensajellegada.wav"));
        sonido2 = java.applet.Applet.newAudioClip(getClass().getResource("./pop.wav"));
        Thread.sleep(generarRandom());
        online.setForeground(Color.blue);
        sonido2.play();
        online.setText("Visto");
        Thread.sleep(generarRandom());
        online.setText("Escribiendo");
        Thread.sleep(generarRandom());
        online.setText("");
        sonido1.play();
        pantalla.append("Luna: " + respuestaxd + "\n");
        if (pregunta.equalsIgnoreCase("reproducir musica")) {
            reproducciendo = true;
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=I_izvAbhExY"));
            //  alive =java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
            // alive.play();
        }
        if (pregunta.equalsIgnoreCase("reproducir musica de anuel")) {
            reproducciendo = true;
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/playlist?list=PLzAzZbNGlKlrBNFA_VCxWy2tcMvHfPbUw"));
            //  alive =java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
            // alive.play();
        }
        //  System.out.println(generarRandom());
    }
    
    public void animacionpregunta(String respuestaxd) throws InterruptedException {
        sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("./mensajellegada.wav"));
        Thread.sleep(1500);
        online.setText("Escribiendo");
        Thread.sleep(1000);
        online.setText("");
        sonido1.play();
        pantalla.append("Luna: " + respuestaxd + "\n");
    }
    
    public int generarRandom() {
        int numero;
        numero = (int) (Math.random() * 4000) + 1000;
        return numero;
        
    }
    
    public void fijarTexto() {
        pantalla.append("Maquina: " + respuesta + "\n");
        texto.setText("");
    }
    private void textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_textoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String UsuarioPregunta = JOptionPane.showInputDialog("Ingrese pregunta");
        String respuestUsuarioPregunta = JOptionPane.showInputDialog("Que responder a '" + UsuarioPregunta + "'");
        Leer aprender = new Leer();
        String nuevapalabra = aprender.preguntanueva(UsuarioPregunta, respuestUsuarioPregunta);
        aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public JButton enviarPresionado() {
        return enviar;
    }
    
    public void setPanatalla() {
        pantalla.append("Maquina: " + respuesta + "\n");
    }
    
    public JTextArea regresaPantalla() {
        return pantalla;
    }
    
    public JTextField regresaTexto() {
        return texto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Namebot;
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel online;
    private javax.swing.JTextArea pantalla;
    private javax.swing.JTextField texto;
    // End of variables declaration//GEN-END:variables
}
