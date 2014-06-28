package logica;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    
        public int IdJugador;
        public String IpJugador;
        public String nombre;
	public  int cantidadDinero;
	public   Hand  hand;
        public Apuesta apuesta;
        public int TotalApuesta;
        public boolean enTurno;
        public String TipoJuego;
        public ArrayList<Carta> CartasJugador;
        public boolean JugadorActivo;
        public boolean SubioApuesta;
       	public ArrayList<Carta> mejorMano;
        public int valorMejorMano=0;
        public int posicionFinal=0;
        public boolean Igualado;

    public int getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public int getValorMejorMano() {
        return valorMejorMano;
    }

    public void setValorMejorMano(int pvalorMejorMano) {
        this.valorMejorMano = pvalorMejorMano;
    }

    public String getTipoJuego() {
        return TipoJuego;
    }

    public void setTipoJuego(String TipoJuego) {
        this.TipoJuego = TipoJuego;
    }

    public ArrayList<Carta> getMejorMano() {
        return mejorMano;
    }

    public void setMejorMano(ArrayList<Carta> mejorMano) {
        this.mejorMano = mejorMano;
    }

  

    public boolean isJugadorActivo() {
        return JugadorActivo;
    }

    public void setJugadorActivo(boolean JugadorActivo) {
        this.JugadorActivo = JugadorActivo;
    }

    public boolean isSubioApuesta() {
        return SubioApuesta;
    }

    public void setSubioApuesta(boolean SubioApuesta) {
        this.SubioApuesta = SubioApuesta;
    }
	

        
    public Jugador(String nombre,int id,int cantidadDinero, Hand  hand, Apuesta  apuesta, boolean JugadorActivo) {
        this.nombre = nombre;
        this.cantidadDinero = cantidadDinero;
        this.hand = hand;
        this.apuesta = apuesta;
        this.IdJugador = id; 
        this.JugadorActivo = JugadorActivo;

        
      
    }
    /*public Jugador(String nombre, int cantidadDinero, Apuesta  apuesta) {
        this.nombre = nombre;
        this.cantidadDinero = cantidadDinero;
        this.apuesta = apuesta;
        ArrayList<Carta> lista= new ArrayList<Carta>();
        this.CartasJugador=lista;
        ArrayList<Carta> listaC= new ArrayList<Carta>();
        this.mejorMano=lista;
      
    }*/
    
    public Jugador(String Ip, String nombre, String pTipoJuego) {
        this.IpJugador = Ip;
        this.nombre = nombre;
        this.apuesta = new Apuesta(this);
        this.apuesta.valor = 100;
        this.TotalApuesta = 0;
        this.cantidadDinero = 10000;
        ArrayList<Carta> lista= new ArrayList<Carta>();
        this.CartasJugador=lista;
        ArrayList<Carta> listaC= new ArrayList<Carta>();
        this.mejorMano=lista;
        this.TipoJuego = pTipoJuego;
        this.JugadorActivo = true;
        this.Igualado = true;
      
    }
    
    
    public void mostrarCartas(){
        for(int i=0;i< this.CartasJugador.size();i++){
            System.out.println(this.CartasJugador.get(i).imprimirla());
        }
    
    }
    
    public void mostrarMejorMano(){
        for(int i=0;i< this.mejorMano.size();i++){
            System.out.println(this.mejorMano.get(i).imprimirla());
        }
    
    }
    
    public int getCantidadDinero() {
        return cantidadDinero;
    }

    public ArrayList<Carta> getCartasJugador() {
        return CartasJugador;
    }

    public void setCartasJugador(ArrayList<Carta> CartasJugador) {
        this.CartasJugador = CartasJugador;
    }

    public void setCantidadDinero(int cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    
	

    
    public void setUnnamed_Mano_(Hand hand) {
        this.hand = hand;
    }
        
        
    
    public Object getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad_Dinero() {
        return cantidadDinero;
    }

    public void setCantidad_Dinero(int cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    public Hand getMano() {
        return hand;
    }

    public void setMano(Hand hand) {
        this.hand = hand;
    }

    public Apuesta getApuesta() {
        return apuesta;
    }

    public void setApuesta(Apuesta apuesta) {
        this.apuesta = apuesta;
    }
        public boolean isEnTurno() {
        return enTurno;
    }

    public void setEnTurno(boolean enTurno) {
        this.enTurno = enTurno;
    }
	

   public int allIn(){
                int apuestaTodo = this.getCantidadDinero();
                this.getApuesta().subirApuesta(apuestaTodo);
                this.setCantidadDinero(0);
                return apuestaTodo;
   }

    public void call(int apuestaActual){
            int apuestaTemp = apuestaActual - this.getApuesta().getValor();
            System.out.println("DIFERENCIA: "+apuestaTemp);
            this.getApuesta().subirApuesta(apuestaTemp);
            System.out.println("valor apuesta call: "+this.getApuesta().getValor());
            this.setCantidadDinero(this.getCantidadDinero() - apuestaActual);
            System.out.println("valor cantidad dinero call: "+this.getCantidadDinero());
            //return apuestaActual;
    }

    public int raise(int apuestaActual, int apuestaAumentada){
            //call(apuestaActual);
            //this.getApuesta().valor = 0;
            this.getApuesta().subirApuesta(apuestaAumentada);
            System.out.println("valor apuesta raise"+this.getApuesta().getValor());
            this.setCantidadDinero(this.getCantidadDinero() - apuestaAumentada);
            System.out.println("valor cantidad dinero raise"+this.getCantidadDinero());
            int AumentoTemp = apuestaAumentada + apuestaActual;
            return AumentoTemp;
    }
        
        public boolean check(int ApuestaGeneral){
              if (this.getApuesta().valor >= ApuestaGeneral)
              {
                  return true;
              }
              else
              {
                  return false;
              }
        }
        
        public ArrayList<Carta> fold(){
                ArrayList cartasJugador = new ArrayList<Carta>();
                cartasJugador = this.getCartasJugador();
                this.CartasJugador.removeAll(this.getCartasJugador());
                return cartasJugador;
        }
                
        public boolean isActivo() {
                return JugadorActivo;
        }

        public void setActivo(boolean activo) {
                this.JugadorActivo = activo;
        }

      
    public int getIdJugador() {
        return IdJugador;
    }

    public void setIdJugador(int IdJugador) {
        this.IdJugador = IdJugador;
    }
    
    public void imprimeMejorMano()
    {
        for (int i =0; i< mejorMano.size(); i++)
        {
            System.out.println(mejorMano.get(i).imprimirla());
        }
    }

    



	
}