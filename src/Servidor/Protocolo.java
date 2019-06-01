package Servidor;

import java.io.*;
import java.util.ArrayList;

public class Protocolo {

    ObtenerPR pr = new ObtenerPR();

    String preguntaNueva;
    String respuestaNueva;

    //ESTADOS DEL MENSAJE
    private final int ESPERANDOMENSAJE = 0;
    private final int MENSAJERECIBIDO = 1;
    private final int ESPERANDORESPUESTA = 2;
    int ESTADO = ESPERANDOMENSAJE;

    //BANDERA QUE VERIFICA SI SE CONTESTÓ EL MENSAJE
    int respuestaContestada = 0;

    //CONTADOR DEL TOTAL DE RESPUESTAS
    ArrayList<String> mensaje = pr.obtenerPreguntas();
    ArrayList<String> respuesta = pr.obtenerRespuestas();

    String mensajeMaquina[] = {"Hoy aprendí algo nuevo",
        "agregando respuesta",
        "información nueva",
        "gracias por la información",
        "algo nuevo que deba saber?",
        "listo!"};

    int totalRespuestas = mensaje.size();

    public String processInput(String theInput) {
        String theOutput = null;
        switch (ESTADO) {
            case ESPERANDOMENSAJE:
                theOutput = "Dime algo...";
                ESTADO = MENSAJERECIBIDO;
                break;
            case MENSAJERECIBIDO:
                if (theInput.equalsIgnoreCase("adios")) {
                    theOutput="Adiós.";
                } else {
                    for (int i = 0; i < totalRespuestas; i++) {
                        if (theInput.equalsIgnoreCase(mensaje.get(i))) {
                            theOutput = respuesta.get(i);
                            this.respuestaContestada = 1;
                        }
                    }
                    if (this.respuestaContestada == 0) {
                        preguntaNueva = theInput;
                        theOutput = "que debo responder? ";
                        ESTADO = ESPERANDORESPUESTA;
                    } else {
                        ESTADO = MENSAJERECIBIDO;
                        respuestaContestada = 0;
                    }
                }
                break;
            case ESPERANDORESPUESTA:
                int x = (int) (Math.random() * 6);
                theOutput = mensajeMaquina[x];
                mensaje.add(this.preguntaNueva);
                respuesta.add(theInput);
                try (
                        FileWriter fw = new FileWriter("mensaje.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                    out.println(preguntaNueva);
                } catch (IOException e) {
                }
                try (
                        FileWriter fw = new FileWriter("respuesta.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                    out.println(theInput);
                } catch (IOException e) {
                }
                totalRespuestas++;
                ESTADO = MENSAJERECIBIDO;
                break;
        }
        return theOutput;
    }

}
