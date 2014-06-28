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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pozo {

        public int IdPozo;
        public  ArrayList<Apuesta> listaApuestas;
                
        public Pozo(ArrayList<Apuesta> Apuestas) {
                this.setListaApuestas(Apuestas);
        }
        public Pozo() {
                this.listaApuestas=new ArrayList<Apuesta>();
        }
        public int TotalPozo()
        {
            int total = 0;
            for (int i =0; i < listaApuestas.size(); i++)
            {
                total = total + listaApuestas.get(i).getValor();
            }
            return total;
        }
        
        public int getPozoTotal(){
                Iterator it = this.getListaApuestas().iterator();
                int total=0;
                while(it.hasNext()){
                        total = total + ((Apuesta)it.next()).getValor();
                }
                return total;
        }
        
        public void agregarApuesta(Apuesta unaApuesta){ 
                this.getListaApuestas().add(unaApuesta);
        }
        
        public void quitarApuesta(Apuesta unaApuesta){
                this.getListaApuestas().remove(unaApuesta);
        }
        
        public List getListaApuestas() {
                return listaApuestas;
        }
        private void setListaApuestas(ArrayList<Apuesta> listaApuestas) {
                this.listaApuestas = listaApuestas;
        }

}
