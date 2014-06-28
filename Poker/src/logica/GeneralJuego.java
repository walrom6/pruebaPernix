package logica;

import java.util.ArrayList;
import log_Conexion.Servidor;

public interface GeneralJuego {

    /**
     *
     * @return
     */
    
        public int getCartasVisibles();
        
        public void setCartasVisibles(int CartasVisibles);

        public Mazo getMazoGeneral();

        public void setMazoGeneral(Mazo MazoGeneral);

        public int getCartasPrivadas();

        public void setCartasPrivadas(int CartasPrivadas);

        public int getCantidadJugadores();

        public void setCantidadJugadores(int cantidadJugadores);
        
        public void repartirCartas();
        
        public void repartirCartasMesa();
        
        public void crearJugador(String Ip, String nombre, String TipoJuego);
        
        public void mostrarCartas();
    
        public void calcularManos();
        //public void calcularManosA(ArrayList<Carta> listaCartas);
        public int desempate(Jugador jug1, Jugador jug2,int contador);
        
        public boolean puedeApostar(int ApuestaGeneral, Jugador jugador);
        
        public void ApuestasIgualesJugador(ArrayList<Jugador> ListaJugadores, int ApuestaGeneral);
        
        public boolean jugadoresIguales(ArrayList<Jugador> ListaJugadores);
        
        public int getGanador(ArrayList<Jugador> Lista,int contador);
        
        public void GeneraJuego(Servidor ServidorPrincipal);
        
       public ArrayList<Carta> acomodarDobles(ArrayList<Carta> mano);
       
       public int desempateDobleP(Jugador jug1, Jugador jug2);
       
       public int desempatePTP(Jugador jug1, Jugador jug2);
       
       public ArrayList<Carta> acomodarPareja(ArrayList<Carta> mano);
       
       public void ImprimirGanadores();
       
       public void SacarGanadores();
        //definir ganador
        
}
