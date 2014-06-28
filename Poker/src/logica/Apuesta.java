/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/**
 *
 * @author Walter
 */
public class Apuesta {
    
        public  int valor;
        public Jugador jugador; 
        public int restoApuesta = 0;
        
        public Apuesta(Jugador unJugador) {
                this.setJugador(unJugador);
                this.setValor(0);       
        }
        
        public void subirApuesta(int fichas){
                this.setValor(this.getValor() + fichas);
        }

        public int getValor() {
                return valor;
        }

        public void setValor(int valor) {
                this.valor = valor;
        }

        public Jugador getJugador() {
                return jugador;
        }

        public void setJugador(Jugador jugador) {
                this.jugador = jugador;
        }
    
}
