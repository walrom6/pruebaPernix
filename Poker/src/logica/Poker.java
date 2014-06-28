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
       
       
       
       
       // evalua primera lista de apuestas
       //System.out.println(ListaApuestas.get(0).valor);
       //System.out.println(ListaApuestas.get(1).valor);
       
       /*StatusDTO StatusHoldEm = new StatusDTO(ListaApuestas);
       StatusHoldEm.mainApuesta();
       StatusHoldEm.imprimirListaPozo();*/
       
       // mostrar las tres cartas de la mesa...
      /* 
       mensaje MuestraCartasMesa = new mensaje();
       
       
       // Segunda ronda de apuestas******************************************
       
       for (int i = 0; i < ServidorPrincipal.ListaJ.size(); i ++)
       {
           ServidorApuestas = new Servidor(ServidorPrincipal.ListaJ,ListaApuestas);
           ListaApuestas.add(ServidorApuestas.ListaJ.get(i).getApuesta());
       }
       System.out.println(PozoGeneral.TotalPozo());
       
       JugadoresNoIgualados = HoldEm.ApuestasIgualesJugador(ServidorApuestas.ListaJ, ServidorApuestas.ApuestaGeneral);

       while (!JugadoresNoIgualados.isEmpty())
       {
            for (int i=0; i<JugadoresNoIgualados.size(); i++)
            {
                ServidorApuestas = new Servidor(JugadoresNoIgualados,ListaApuestas);
                ListaApuestas.add(JugadoresNoIgualados.get(i).getApuesta());
            }
            JugadoresNoIgualados = HoldEm.ApuestasIgualesJugador(ServidorApuestas.ListaJ, ServidorApuestas.ApuestaGeneral);
       }
       */
       //System.out.println("valor mano"+ServidorPrincipal.ListaJ.get(0).getValorMejorMano());
       //System.out.println("valor mano"+ServidorPrincipal.ListaJ.get(1).getValorMejorMano());
       System.out.println("fin");
       


      
      
      
      
      //************************************************************************
       /*Jugador Jugador1 = new Jugador("192.168.1.0", "María", "HoldEm");
       Jugador Jugador2 = new Jugador("192.168.1.0", "Diana", "HoldEm");
       Jugador Jugador3 = new Jugador("192.168.1.0", "Isaias", "HoldEm");
       Jugador Jugador4 = new Jugador("192.168.1.0", "Walter", "HoldEm");
       Jugador Jugador5 = new Jugador("192.168.1.0", "Jose", "HoldEm");
       
       ArrayList <Jugador> JugadoresGeneral = new ArrayList<Jugador>();
       
       /*Jugador1.apuesta.setValor(500);
       Jugador2.apuesta.setValor(500);
       Jugador3.apuesta.setValor(400);
       Jugador4.apuesta.setValor(500);
       Jugador5.apuesta.setValor(500);
       
       //Agrega los jugadores a una lista general
       
       JugadoresGeneral.add(Jugador1);
       JugadoresGeneral.add(Jugador2);
       JugadoresGeneral.add(Jugador3);
       JugadoresGeneral.add(Jugador4);
       JugadoresGeneral.add(Jugador5);
              
       //HoldEm NuevoHoldEm = new HoldEm(JugadoresGeneral);
       //System.out.println(NuevoHoldEm.ApuestasIgualesJugador(JugadoresGeneral));
       
       String TipoJuego = "lo que reciba del socket";  // lo que le entra del socket
       
       // inicializa cada juego siempre y cuando las listas no esten vacias y al menos tengan dos jugadores
       // NOTA : no se sabe si entran a los juegos simultaneamente o si entran cuando el primero termina.
      
       
       if (JugadoresGeneral.size() >= 2 && TipoJuego.equals("HoldEm"))
       {
           HoldEm NuevoHoldEm = new HoldEm(JugadoresGeneral);
           
           int ApuestaGeneral = 100;
           NuevoHoldEm.repartirCartas(); // reparte cartas a jugadores
           String IPTurno = "";
           String ApuestaJugador= "Tipo Apuesta del jugador recibida del socket";
           ArrayList<Apuesta> ApuestasHoldEm = new ArrayList<Apuesta>();
           ArrayList<Pozo> PozosGeneral = new ArrayList<Pozo>();
           Pozo PozoInicial = new Pozo(ApuestasHoldEm);
           ArrayList<Jugador> JugadoresInactivos = new ArrayList<Jugador>();
           int ValorActualPozo = 0;
           
           int i = 0;  // contador while
           // ciclo de apuestas 
           while (! NuevoHoldEm.ApuestasIgualesJugador(JugadoresGeneral))   // inicia ronda de apuestas
           {
               if (JugadoresGeneral.get(i).isActivo())
               {    
                    JugadoresGeneral.get(i).enTurno = true;  // jugador en turno
                    IPTurno = JugadoresGeneral.get(i).IpJugador;  // Ip del jugador en turno
                    ApuestaJugador = "Lo que reciba del socket";  // recibe la apuesta del jugador del socket

                    if (ApuestaJugador.equals("call") && NuevoHoldEm.puedeApostar(ApuestaGeneral, JugadoresGeneral.get(i))) 
                    {
                        JugadoresGeneral.get(i).call(ApuestaGeneral);   // jugador hace call 
                        PozoInicial.listaApuestas.add(JugadoresGeneral.get(i).getApuesta());
                    } 
                    else if (ApuestaJugador.equals("raise") && NuevoHoldEm.puedeApostar(ApuestaGeneral, JugadoresGeneral.get(i))) 
                    {
                        JugadoresGeneral.get(i).raise(ApuestaGeneral);  // jugador hace raise 
                        ApuestaGeneral = ApuestaGeneral + JugadoresGeneral.get(i).apuesta.getValor();  // se aumenta la apuesta general ahora
                        PozoInicial.listaApuestas.add(JugadoresGeneral.get(i).getApuesta());
                    } 
                    else if (ApuestaJugador.equals("fold") && NuevoHoldEm.puedeApostar(ApuestaGeneral, JugadoresGeneral.get(i))) 
                    {
                        JugadoresGeneral.get(i).fold();
                        JugadoresGeneral.get(i).JugadorActivo = false;
                        ArrayList<Jugador> TemporalJugadores = new ArrayList<Jugador>();
                        for(int j =0; j< JugadoresGeneral.size(); j++)
                        {
                            if (JugadoresGeneral.get(j).isActivo())
                            {
                                TemporalJugadores.add(JugadoresGeneral.get(j));
                            }
                        }
                        JugadoresGeneral = TemporalJugadores;
                        i--;

                    }
                    JugadoresGeneral.get(i).enTurno = false;
                    ValorActualPozo = PozoInicial.TotalPozo();
                    i++;    
                    
                    if (i == JugadoresGeneral.size())
                    {
                       i = 0;
                    }
                }
               else
                   i++;
              
           }
           // validar que las apuestas sean iguales, a menos de que alguno o varios jugadores hayan hecho all in.
           ArrayList<Apuesta> ListaApuestas = new ArrayList<Apuesta>();

       }*/

       }
        
}
  

