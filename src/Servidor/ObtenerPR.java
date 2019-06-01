
package Servidor;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ObtenerPR {
    
    
    public ArrayList<String> obtenerPreguntas(){
        ArrayList<String> preguntas= new ArrayList<>();
        try{
        String rutamensaje = "mensaje.txt";
        BufferedReader brm = getBuffered(rutamensaje);
        for (String line = brm.readLine(); line != null; line = brm.readLine()) {
                preguntas.add(line);   
        }
        }
        catch(Exception e){
                
        }
        return preguntas;
    }
    public ArrayList<String> obtenerRespuestas(){
        ArrayList<String> respuestas= new ArrayList<>();
        try{
        String rutarespuesta = "respuesta.txt";
        BufferedReader brr = getBuffered(rutarespuesta);
        for (String line = brr.readLine(); line != null; line = brr.readLine()) {
                respuestas.add(line);   
        }
        }
        catch(Exception e){
                
        }
        return respuestas;
    }
    
    //verifica si el archivo existe
    public BufferedReader getBuffered(String link){
        
        FileReader lector  = null;
        BufferedReader br = null;
        try {
            File Arch=new File(link);
            if(!Arch.exists()){
                Arch.createNewFile();
                System.out.println("No existe el archivo");
            }else{
                lector = new FileReader(link);
                br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }
}
