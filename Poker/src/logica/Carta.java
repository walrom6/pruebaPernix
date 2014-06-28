/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.io.Serializable;

public class Carta implements Serializable{
        private static final  String[] valores = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "D",  "J", "Q", "K" };
        private static final  int[] puntajes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        private static  String[] palos = { "c", "p", "d", "t" };
        private int puntaje;
        private final int valor;
        private final int palo;
        
        /**
         * Crea un objeto Carta indicandole el palo y su valor. 
         * @param palo Palo de la Carta.
         * @param valor Valor de la Carta.
         */
        
        public Carta(int palo, int valor){
                this.puntaje = puntajes[valor];
                this.valor = valor;
                this.palo = palo;       
        }

    Carta() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

      
        /**
         * @return Devuelve el palo de la Carta.
         */
        
        public int getPalo(){
                return palo;
        }
        
        /**
         * @return Devuelve el valor de la Carta.
         */
        
        public int getValor(){
                return valor;
        }
        
        /**
         * @return Devuelve un String con el valor de la Carta.
         */
        
        public String imprimirValor(){
                return valores[valor];
        }

        /**
         * @return Devuelve el puntaje de la Carta.
         */
        
        public int getPuntaje() {
                return puntaje;
        }
        
        /**
         * Cambia el puntaje de la Carta.
         * @param puntaje Puntaje nuevo.
         */
        
        public void setPuntaje(int puntaje){
                this.puntaje=puntaje;
        }
        
        /**
         * @return Un String con el valor y el palo de la Carta.
         */
        
        public String imprimirla(){
                return valores[valor]+ palos[palo];
        }

        /**
         * @return Un String con el palo de la Carta.
         */
        public String imprimirPalo() {
                return palos[palo];
        }
        
        public String getPaloString() {
                return palos[palo];
        }
        public String getValorString() {
                return valores[valor];
        }

}