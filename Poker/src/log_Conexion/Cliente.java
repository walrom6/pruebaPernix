/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log_Conexion;

/**
 *
 * @author Isaías Tenorio
 */
import java.net.*;
import java.io.*;


public class Cliente {

String IP = null;
final int PUERTO=5000;
Socket sc;
DataOutputStream mensaje;
DataInputStream entrada;

public Cliente(){}

public Cliente(String message, String Ip){
   System.out.println(message);
   System.out.println(""+Ip);
    try{
            sc = new Socket(Ip , PUERTO ); 
            mensaje = new DataOutputStream(sc.getOutputStream());
            System.out.println(mensaje+""+Ip);
            mensaje.writeUTF(message);
            sc.close();
        }catch(Exception e ){System.out.println("Error: "+e.getMessage());}}



}
