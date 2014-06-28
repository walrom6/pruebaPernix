/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;
import java.util.List;
import log_Conexion.Cliente;
import log_Conexion.Servidor;
import log_Conexion.mensaje;

/**
 *
 * @author DianaA
 */
public class Poker {

    /**
     * @param args the command line arguments
     */
  @SuppressWarnings("empty-statement")
  public static void main(String[] args) throws InterruptedException  {
    
       mensaje MensajeActual = new mensaje();
       Servidor ServidorPrincipal = new Servidor(MensajeActual);
       HoldEm HoldEm = new HoldEm(ServidorPrincipal.ListaJ);
       Omaha Omaha = new Omaha(ServidorPrincipal.ListaJ);
       FiveCards FiveCards = new FiveCards(ServidorPrincipal.ListaJ);
       if(ServidorPrincipal.ListaJ.get(0).TipoJuego.equals("Texas Holdem"))
       {
           HoldEm.repartirCartas();
           HoldEm.repartirCartasMesa();
           ServidorPrincipal.ListaJ.get(0).CartasJugador.get(0).imprimirla();
           HoldEm.GeneraJuego(ServidorPrincipal);
       } 
       else if(ServidorPrincipal.ListaJ.get(0).TipoJuego.equals("Omaha"))
       {
           
           Omaha.repartirCartas();
           Omaha.repartirCartasMesa();
           ServidorPrincipal.ListaJ.get(0).CartasJugador.get(0).imprimirla();
          Omaha.GeneraJuego(ServidorPrincipal);
       }
       // otros juegos
        else
       {
           
           FiveCards.repartirCartas();
           FiveCards.repartirCartasMesa();
           ServidorPrincipal.ListaJ.get(0).CartasJugador.get(0).imprimirla();
           FiveCards.GeneraJuego(ServidorPrincipal);
       }
       
      
       System.out.println("fin");
       


       }
        
}
  

