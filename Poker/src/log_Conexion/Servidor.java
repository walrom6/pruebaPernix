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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import logica.Apuesta;
import logica.Jugador;



public class Servidor {

        final int PUERTO=5000;
        ServerSocket sc;
        Socket so;
        DataOutputStream salida;
        String mensajeRecibido;
        public ArrayList<Jugador> ListaJ;
        public int CantidadJugadores;
        public int ApuestaGeneral;
        public boolean Check;

        
     public Servidor()
     {
         
     }
        
        
public Servidor(mensaje pMensaje){
BufferedReader entrada;
int i =1;
int a = 0;
CantidadJugadores = pMensaje.CantidadInicial;
Calendar fecha = new GregorianCalendar();
int minuto = fecha.get(Calendar.MINUTE);
//System.out.println("minuto:"+minuto);
ListaJ = new ArrayList<Jugador>();
while (i >= 0){
        try{
            //System.out.println("sistema"+i);
            sc = new ServerSocket(PUERTO);
            so=new Socket();
            so = sc.accept();
            entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
            salida = new DataOutputStream(so.getOutputStream());
            mensajeRecibido = entrada.readLine();
            pMensaje = new mensaje(mensajeRecibido);
            //System.out.println("mensaje: "+ mensajeRecibido);
            pMensaje.evaluador();
            
            //System.out.println("Largo: "+ListaJ.size());
            //System.out.println(mensajeRecibido);
            if (a == 0)
            {
                i = pMensaje.CantidadInicial;
                
                System.out.println("i:"+i);
                a++;
            }
            else
            {
                ListaJ.add(pMensaje.ListaJugadores.get(0));
            }
            sc.close();
            System.out.println("Cierra conexion");
            i--;
        }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
        }
}

}

    public Servidor(ArrayList<Jugador> pJugadores, ArrayList<Apuesta> Apuestas, int apuestaGeneral){
    BufferedReader entrada;
    mensaje pMensaje = new mensaje();
    ListaJ = pJugadores;
  
            try{
                //System.out.println("sistema"+i);
                sc = new ServerSocket(PUERTO);
                so=new Socket();
                so = sc.accept();
                entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
                salida = new DataOutputStream(so.getOutputStream());
                mensajeRecibido = entrada.readLine();
                pMensaje = new mensaje(mensajeRecibido);
                pMensaje.ListaJugadores=ListaJ;
                pMensaje.ApuestaGeneral=apuestaGeneral;
                pMensaje.evaluador();
                
                
                ListaJ = pMensaje.ListaJugadores;
                Apuestas = pMensaje.ListaApuestas;
                ApuestaGeneral = pMensaje.ApuestaGeneral;
                Check = pMensaje.PuedePasar;
                sc.close();
                System.out.println("Cierra conexion");
               
            }catch(Exception e ){
            System.out.println("Error: "+e.getMessage());
            }
    }

}
