/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log_Conexion;

import java.util.ArrayList;
import logica.Apuesta;
import logica.Jugador;

/**
 *
 * @author Isaías Tenorio
 */
public class mensaje {
    public String mensaje;
    public String comando;
    public ArrayList <Jugador> ListaJugadores;
    public int CantidadInicial;
    public int ApuestaGeneral;
    public ArrayList<Apuesta> ListaApuestas; 
    public boolean PuedePasar;
    
    
    public mensaje(){

    }
    
    public mensaje(String message){
        String[] tokens = message.split(";");
        this.comando=tokens[1];
        this.mensaje=message;
        this.ListaJugadores = new ArrayList<Jugador>();
        this.CantidadInicial = 0;
        this.ApuestaGeneral = 100;
        this.ListaApuestas = new ArrayList<Apuesta>();
        System.out.println(this.mensaje);
        
    }
    
    public mensaje(String message, ArrayList<Jugador> pListaJugadores){
        String[] tokens = message.split(";");
        this.comando=tokens[1];
        this.mensaje=message;
        this.ListaJugadores = new ArrayList<Jugador>();
        this.CantidadInicial = 0;
        System.out.println(this.mensaje);
        ListaJugadores = pListaJugadores;
        
    }
    
    
    public void agregarJugador(){
        
        String nombre, ip, tipo_juego;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
        nombre=tokens[3];
        tipo_juego=tokens[4];
        Jugador JugadorNuevo = new Jugador(ip, nombre, tipo_juego);
        ListaJugadores.add(JugadorNuevo);
        System.out.println("Jugador Creado");
        //lista listaj = new lista();
        //listaj.add_jugadores(nombre, ip, tipo_juego);
        
    }
    
    private void apuestaJugador() {
        String ip;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
        int ApuestaRaise = Integer.parseInt(tokens[3]);
        System.out.println("ApuestaRaise :"+ApuestaRaise);
        
        for (int i =0; i < ListaJugadores.size(); i++)
        {
            if (ListaJugadores.get(i).IpJugador.equals(ip))
            {  
                if (ListaJugadores.get(i).Igualado == false)
                {
                ListaJugadores.get(i).call(ApuestaGeneral-ListaJugadores.get(i).TotalApuesta);
                }
                //System.out.println("Diferencia apuesta raise: "+Diferencia);
                ApuestaGeneral = ListaJugadores.get(i).raise(ApuestaGeneral, ApuestaRaise);
                ListaApuestas.add(ListaJugadores.get(i).getApuesta());
                ListaJugadores.get(i).Igualado=true;
                System.out.println("Apuesta general: "+ApuestaGeneral);
            }
        }
        
    }
    
    private void retirarJugador() {
        String nombre, ip;
        String[] tokens = mensaje.split(";");
        nombre=tokens[1];
        ip=tokens[2];
        ArrayList<Jugador> JugadoresActivos = new ArrayList<Jugador>();
        for (int i =0; i < ListaJugadores.size(); i++)
        {
            if (ListaJugadores.get(i).IpJugador.equals(ip))
            {
                ListaJugadores.remove(i);
            }
            else 
            {
                JugadoresActivos.add(ListaJugadores.get(i));
            }
        }
 
    }

    private boolean pasarJugador() {
        String ip;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
            
        for (int i =0; i < ListaJugadores.size(); i++)
        {
            if (ListaJugadores.get(i).IpJugador.equals(ip))
            {
                if (ListaJugadores.get(i).check(ApuestaGeneral))
                {
                    PuedePasar = true;
                    return PuedePasar;
                }
                    
            }
        }
        PuedePasar = false;
        return PuedePasar;

        
    }

    private void igualarJugador() {
        String ip;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
        for (int i =0; i < ListaJugadores.size(); i++)
        {
            if (ListaJugadores.get(i).IpJugador.equals(ip))
            {
                ListaJugadores.get(i).call(ApuestaGeneral);
                ListaApuestas.add(ListaJugadores.get(i).getApuesta());
                ListaJugadores.get(i).Igualado = true;
            }
        }
        System.out.println("Apuestas general: "+ApuestaGeneral);
    }

    private void all_inJugador() {
        String ip;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
        for (int i =0; i < ListaJugadores.size(); i++)
        {
            if (ListaJugadores.get(i).IpJugador.equals(ip))
            {
                ApuestaGeneral = ApuestaGeneral + ListaJugadores.get(i).allIn();
                System.out.println(ListaJugadores.get(i).cantidadDinero); 
                ListaApuestas.add(ListaJugadores.get(i).getApuesta());
            }
        }
    }
    
    public void setCantidadJugadores()
    {
        String Cantidad;
        String[] tokens = mensaje.split(";");
        Cantidad=tokens[2]; 
        System.out.println("Cantidad:"+Cantidad);
        CantidadInicial = Integer.parseInt(Cantidad);
    }
    
    public void evaluador(){
         if ((this.comando).equals("n")){//nuevo jugador
               this.agregarJugador();}
         if ((this.comando).equals("a")){//apuesta valor
               this.apuestaJugador();}
         if ((this.comando).equals("r")){//retirarse
               this.retirarJugador();}
         if ((this.comando).equals("p")){//pasar
               this.pasarJugador();}
         if ((this.comando).equals("i")){//igualar
               this.igualarJugador();}
         if ((this.comando).equals("al")){//all in
               this.all_inJugador();}
         if ((this.comando).equals("q")){//cantidad jugadores 
             System.out.println("Llega a q");
               this.setCantidadJugadores();}
    }

    
    public void m_Cartas(){//recibe un arreglo de cartas
        
    }
    
    public void m_Pot(){//recibe un valor y lo envía a
        
    }
    public void m_jugador(){//recibe un jugador y los agrega a la computadora, con nombre e IP
        
    }
    public void retirarse(){//recibe la ip y elimina
        
    }
    public void pasar(){//recibe un arreglo de cartas
        
    }
    public void igualar(){//recibe un arreglo de cartas
        String ip;
        String[] tokens = mensaje.split(";");
        ip=tokens[2];
        
        
    }
    public void all_in(){//recibe un arreglo de cartas
        
    }
     public void turno(){//recibe un arreglo de cartas
        
    }
    
    
    
    
}
