/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DianaA
 */
public class EvalMano {
    /**
     * Array que contiene nuestra mano.
     */
    public ArrayList<Carta> Hand;
    public int PrimRep=0;
    public int SecRep=0;
    public int cont=0;

    public ArrayList<Carta> getHand() {
        return Hand;
    }
   

    public void setHand( ArrayList<Carta> Hand) {
        this.Hand = Hand;
    }
    /**
     * Constantes que representan los valores de los tipos de manos
     */
    public static final int  ESCALERA_REAL = 10,
                             ESCALERA_COLOR = 9,
                             POKER = 8,
                             FULL = 7,
                             COLOR = 6,
                             ESCALERA = 5,
                             TRIO = 4,
                             DOBLE_PAREJA =3,
                             PAREJA=2,
                             AK =1,
                             CARTA_ALTA =0;
    /**
     * Constructor.
     */
    public EvalMano(){
       
    }
   
    /**
     * Getter.
     * @return la mano
     */
    public  ArrayList<Carta> getMano(){
        return this.Hand;
    }
    
    
    /**
     * Comprueba si la combinación de la mano es un color.
     * @return true si la combinacion de la mano es un Color
     */
    public boolean esColor(){
        String palo = Hand.get(0).getPaloString();
        for (int i = 1; i<Hand.size(); i++){
            if(!(Hand.get(i).getPaloString().equals(palo))){
                return false;
            }
        }
        return true;
    }
    /**
     * Comprobacion si la mano es una pareja.
     * @return true si la combinacion de la mano es Pareja.
     */
    public boolean esPareja(){
        //siempre y cuando no sea trio ni full ni poker
        if (esTrio() || esFull() || esPoker()){
            return false;
        } 
        return auxCartasIguales(2); //verifica que tenga dos cartas iguales
    }
    /**
     * Comprobacion si la mano es un trio (tres cartas iguales).
     * @return true si la combinacion de la mano es Trio.
     */
    public boolean esTrio(){
        //siempre y cuando no sea full ni poker
        if(esFull() || esPoker()){
            return false;
        }
        return auxCartasIguales(3);
    }
    /**
     * Comprobacion si la mano es 4 cartas iguales, poker.
     * @return true si la combinacion de la mano, es Poker.
     */
    public boolean esPoker(){
        return auxCartasIguales(4);//verifica que tenga  4 cartas iguales
    }
    /**
     * Metodo auxiliar para ver si hay parejas, trio o poker.
     * @param cartasIgualValor entero(2, 3 o 4) para comprobar si hay parejas.
     * @return true si hay parejas, trios, o poker, segun lo que se buscaba.
     */
    private boolean auxCartasIguales(int cartasIgualValor){
        int[] values = new int[5];
        int contador = 0;

        for(int i=0; i<Hand.size(); i++){
            values[i] = Hand.get(i).getPuntaje();
        }

        for(int x = 0; x<values.length; x++){
            
            for(int y = 0; y< Hand.size(); y++){
                if(values[x]==Hand.get(y).getPuntaje()){
                    contador++;
                }
                if(contador==cartasIgualValor){
                    if(cont==0){
                        this.PrimRep=values[x];  //almacena la primera encontrada de menor valor
                        cont++;   
                        return true;}
                    else{
                         this.SecRep=values[x];//almacena la segunda repetida carta mayor                      
                         return true;}
                }
            }
            contador =0;
        }
        return false;
    }
  
    /**
     * Comprobacion si la combinacion de la mano es escalera
     * @return true si la combinacion es una escalera.
     */
    public boolean esEscalera(){
        //verifica que sea escalera alta o baja      
        if(esEscaleraAlta()||esEscaleraBaja()){
            return true;
        }
        return false;
    }
    /**
     * Metodo auxiliar para la comprobacion de la combinacion escalera
     * @param as 13 si se toma As como carta alta, 1 si se toma como baja.
     * @return true si la combinacion la mano es una escalera.
     */
    private boolean auxEscalera(int as){
        int [] values = new int[5];
        int pos;
        int temp;
        for(int i = 0; i< Hand.size(); i++){
            values[i] = Hand.get(i).getValor();
            if(values[i] == 0){
                values[i] = as;
            }
        }
        for (int i =1; i< values.length; i++){
            pos = i;
            while(pos != 0){
                if(values[pos] < values[pos-1]){
                    temp = values[pos];
                    values[pos] = values[pos-1];
                    values[pos-1] = temp;
                }
                pos--;
            }
        }
        for(int i = 0; i< values.length -1; i++){
            if(values[i] != values[i+1] -1){
                return false;
            }
        }
        return true;
    }
    /**
     * Metodo auxiliar para la comprobacion de escalera.
     * @return true si es escalera en casos que el As se toma como carta alta
     */
    private boolean esEscaleraAlta(){
        return auxEscalera(13);
    }
    /**
     * Metodo auxiliar para la comprobacion de escalera.
     * @return true si es escalera en casos que el As se toma como carta baja
     */
    private boolean esEscaleraBaja(){
        return auxEscalera(1);
    }
    /**
     * Comprueba si la mano es una Escalera de color.
     * @return true si la combinacion de la mano es Escalera de color.
     */
    public boolean esEscaleraColor(){
        if(esEscalera() == true && esColor() == true){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la mano es una Esclera real.
     * @return true si la combinacion de la mano es Escalera Real.
     */
    public boolean esEscaleraReal(){
        if(esColor() == false || esEscalera() == false){
            return false;
        }
        int [] values = new int[5];
        int pos;
        int temp;

        for(int i = 0; i< Hand.size(); i++){
            values[i] = Hand.get(i).getValor();
            if(values[i] == 0){
                values[i] = 13;
            }
        }

        for (int i =1; i< values.length; i++){
            pos = i;
            while(pos != 0){
                if(values[pos] < values[pos-1]){
                    temp = values[pos];
                    values[pos] = values[pos-1];
                    values[pos-1] = temp;
                }
                pos--;
            }
        }
        
        if(values[0] == 9){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la mano es una doble pareja.
     * @return true si la combinacion de la mano es una doble pareja.
     */
    public boolean esDoblePareja(){
        int[] values = new int[5];
        int contador = 0;
        int sum = 0;

        if(esPoker() == true){
            return false;
        } 
        //paso los valores a una lista auxiliar
        for(int i = 0; i< Hand.size(); i++){
            values[i] = Hand.get(i).getPuntaje();
        }
        for(int x = 0; x < values.length; x++){
            for (int y=0; y< Hand.size(); y++){
                if (values[x]==Hand.get(y).getPuntaje()){
                    contador++;
                }
              
            }
            if(contador > 1){
             if(cont==0){
             this.PrimRep=values[x];
             cont++;
             }
             else{
             this.SecRep=values[x];
             }
                sum++;
            }
            contador = 0;
            
        }
        if (sum ==4){
            
            return true;
        }
        return false;
    }
    /**
     * Comprueba si nuestra manos es un Full House.
     * @return true si la combinacion de la mano es un full.
     */
    public boolean esFull(){
        int[] values = new int[5];
        int contador = 0;
        int sum = 0;

        if(esPoker() == true){
            return false;
        }
        for(int i = 0; i< Hand.size(); i++){
            values[i] = Hand.get(i).getPuntaje();
        }
        for(int x=0; x < values.length; x++){
            for(int y = 0; y< Hand.size(); y++){
                if(values[x]==Hand.get(y).getPuntaje()){
                    contador++;
                    
                }
            }
            if(contador > 1){
                sum++;
                
             if(cont==0){
             this.PrimRep=values[x];
             cont++;
             }
             else{
             this.SecRep=values[x];
             }
            }
            contador = 0;
        }
        if (sum == 5){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la mano es un AKxxx, carta alta.
     * @return true si la mano es AKxxx.
     */
    public boolean esAK(){
        if( esPareja() || esDoblePareja()  || esTrio() ||
            esColor()  || esEscalera()     || esFull() ||
            esPoker()  || esEscaleraColor()|| esEscaleraReal()){
            return false;
        }
        int contador = 0;
        for(int i = 0; i< Hand.size(); i++){
            if(Hand.get(i).getValorString().equals("A")){
                contador++;
            }
            if(Hand.get(i).getValorString().equals("K")){
                contador++;
            }
        }
        if(contador == 2){
            return true;
        }
        return false;

    }
    
    /**
     * Entero que representa el valor de la mano de mayor a menor.
     * @return entero con el valor de la mano.
     */
    public int valorNumericoMano(){
        if(esEscaleraReal())
            return 10;
        else if(esEscaleraColor())
            return 9;
        else if(esPoker()){
            
            return 8;}
        else if (esFull())
            return 7;
        else if (esColor())
            return 6;
        else if (esEscalera())
            return 5;
        else if (esTrio()){
            return 4;}
        else if (esDoblePareja()){
        
            return 3;}
        else if (esPareja())
            return 2;
        else if (esAK())
            return 1;
        else
            return 0;
    }
    /**
     * Muestra el valor de la mano en forma de string
     * @return string con el valor de la mano.
     */
    public String valorMano(){
        if(esEscaleraReal())
            return "Escalera Real";
        else if(esEscaleraColor())
            return "Escalera de color";
        else if(esPoker())
           // acomodarPoker();
            return "Poker";
        else if (esFull())
            return "Full House";
        else if (esColor())
            return "Color";
        else if (esEscalera())
            return "Escalera";
        else if (esTrio())
            return "Trio";
        else if (esDoblePareja())
            return "Doble pareja";
        else if (esPareja())
            
            return "Pareja";
        else if (esAK())
            return "AK";
        else
            return "Carta alta";
    }
    
  

  

}

