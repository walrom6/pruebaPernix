

package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author DianaA
 */

public class Hand {
  
        public List todasLasCartas;
        public ArrayList<Carta> mejorMano;
        public int valorMejorMano=0;
        
        public List MejManoVal;
        public ArrayList<Carta> MejorMano;

    public Hand() {
                this.setMejorMano(null);
                this.setTodasLasCartas(new ArrayList());
                this.MejManoVal= null;
        } 
     
        /**
         * Setea la mejor mano del jugador.
         * @param mejorMano Nueva mejor mano.
         */
        public void setMejorMano(ArrayList<Carta> mejorMano) {
                this.mejorMano = mejorMano;
        }
        /**
         * @return Todas las cartas (mesa + jugador)
         */
        public List getTodasLasCartas() {
                return todasLasCartas;
        }
        
        public ArrayList<Carta>  getBestHand() {
                return MejorMano;
        }
        /**
         * Setea Todas las cartas (mesa + jugador)
         * @param todasLasCartas Todas las cartas nuevas.
         */
        public void setTodasLasCartas(List todasLasCartas) {
                this.todasLasCartas = todasLasCartas;
        }
        
        public void Imprimirla(ArrayList<Carta> mejor){
        for(int i=0;i< mejor.size();i++){
            System.out.println(mejor.get(i).imprimirla());
        }
        }
      
       public List combinatoria(ArrayList todas){
                List mano = new ArrayList();
                int largo=todas.size();
                List manosPosibles = new ArrayList();
                for (int i=0; i<3; i++){
                        for (int j=i+1; j<4; j++){
                                for (int k=j+1; k<5; k++){
                                        for (int l=k+1; l<6; l++){
                                                for (int m=l+1; m<largo; m++){
                                                        mano = new ArrayList();
                                                        mano.add(0, todas.get(i));
                                                        mano.add(1, todas.get(j));
                                                        mano.add(2, todas.get(k));
                                                        mano.add(3, todas.get(l));
                                                        mano.add(4, todas.get(m));                                                                                                              
                                                        ordenarCartas(mano);                                                    
                                                        manosPosibles.add(mano);
                                                }
                                        }
                                }
                        }
                }
                return manosPosibles;
        }     
       
       public void ordenarCartas(List cartas){
                Carta aux;
                for(int i=0; i<4; i++) {
                        for (int j = i+1; j<5; j++) {
                                if (((Carta) cartas.get(j)).getValor()<=((Carta) cartas.get(i)).getValor()){
                                        aux = (Carta) cartas.get(j);
                                        cartas.set(j, cartas.get(i));
                                        cartas.set(i, aux);
                        }
                        }
                }      
        }       

       public ArrayList<Carta> getMejorManoO(ArrayList<Carta> CartasTotales) {
            ArrayList<Carta> temporal1=new ArrayList<Carta>(CartasTotales);
            ArrayList<Carta> temporal2=new ArrayList<Carta>(CartasTotales);
            ArrayList<Carta> temporal3=new ArrayList<Carta>(CartasTotales);
            ArrayList<Carta> temporal4=new ArrayList<Carta>(CartasTotales);
            ArrayList<Carta> temporal5=new ArrayList<Carta>(CartasTotales);
            
            
            temporal1.remove(2); temporal1.remove(2);
            temporal2.remove(1); temporal2.remove(2);
            temporal3.remove(1); temporal3.remove(1);
            temporal4.remove(0); temporal4.remove(2);
            temporal5.remove(0); temporal5.remove(0);
            ArrayList TodasCartas=new ArrayList();
            TodasCartas.add(getMejorMano(this.combinatoria(temporal1)));
            TodasCartas.add(getMejorMano(this.combinatoria(temporal2)));
            TodasCartas.add(getMejorMano(this.combinatoria(temporal3)));
            TodasCartas.add(getMejorMano(this.combinatoria(temporal4)));
            TodasCartas.add(getMejorMano(this.combinatoria(temporal5)));
            MejorMano= (ArrayList) TodasCartas.get(0); 
           EvalMano evaluador= new EvalMano();
           evaluador.setHand(MejorMano);
           int valMejormano=evaluador.valorNumericoMano();
        
        //recorre toda la lista de posibles jugadas
        for(int i=1; i < TodasCartas.size();i++) { 
            ArrayList<Carta> temp= (ArrayList) TodasCartas.get(i);
            EvalMano evaltemp= new EvalMano(); 
            evaltemp.setHand(temp); 
            int valTemp= evaltemp.valorNumericoMano();
            if(valMejormano < valTemp) { // si el valor de la nueva jugada es mejor las intercambia
                    valMejormano=valTemp; MejorMano=temp; } 
            if(valMejormano == valTemp) // si empata manda a desempatar
                if(desempate(MejorMano,temp)==2){ //si devuelve que la ganadora es la segunda las intercambia
                    valMejormano=valTemp;MejorMano=temp; } 
        }
       
       
       return MejorMano;
       }
       
   
        /**
     * @param TodasManos
         * @return La mano de mayor valor a partir de esa combinacion
         */
        public ArrayList<Carta> getMejorMano(List TodasManos) {
            
        MejorMano= (ArrayList) TodasManos.get(0); 
        EvalMano evaluador= new EvalMano();
        evaluador.setHand(MejorMano);
        int valMejormano=evaluador.valorNumericoMano();
        
        //recorre toda la lista de posibles jugadas
        for(int i=1; i < TodasManos.size();i++) { 
            ArrayList<Carta> temp= (ArrayList) TodasManos.get(i);
            EvalMano evaltemp= new EvalMano(); 
            evaltemp.setHand(temp); 
            int valTemp= evaltemp.valorNumericoMano();
            if(valMejormano < valTemp) { // si el valor de la nueva jugada es mejor las intercambia
                    valMejormano=valTemp; MejorMano=temp; } 
            if(valMejormano == valTemp) // si empata manda a desempatar
                if(desempate(MejorMano,temp)==2){ //si devuelve que la ganadora es la segunda las intercambia
                    valMejormano=valTemp;MejorMano=temp; } 
        }
        
        return MejorMano; //returna la mejor mano 
        }
        
        //Desempata los manos devuelve cual de las dos es mejor mano
        public int desempate(ArrayList<Carta> mano1, ArrayList<Carta>  mano2) {
            EvalMano evaluador= new EvalMano();
            evaluador.setHand(mano1);
            int valMejormano=evaluador.valorNumericoMano();
            if((valMejormano ==8)||(valMejormano==4)||(valMejormano==2)) //desempate pareja,trio,poker
            { return desempatePTP(mano1,mano2); } 
            if((valMejormano==7)||(valMejormano==3)){ //desempate full 
                return desempateDobleP(mano1,mano2); } 
  
            int cont=4;  // si no son esas jugadas, busca desempatar con las cartas mas altas
            while (cont>=0){ 
                if (mano1.get(cont).getPuntaje()>mano2.get(cont).getPuntaje()){return 1;} 
                if (mano1.get(cont).getPuntaje()<mano2.get(cont).getPuntaje()){return 2;} 
                if (mano1.get(cont).getPuntaje()== mano2.get(cont).getPuntaje()){cont--;} cont--; } 
            return 1;
        }
        /**
     * @param mano
         * @return acomoda la jugada de mayor a menor ordenando la pareja
         */
       
        public ArrayList<Carta> acomodarPareja(ArrayList<Carta> mano){
               Hand hand=new Hand();
               hand.setMejorMano(mano);
               EvalMano evma1=new EvalMano();
               evma1.setHand(mano);
               ArrayList<Carta> temporal=new ArrayList<Carta>();
               ArrayList<Carta> temporalDif=new ArrayList<Carta>();
               ArrayList<Carta> totales=new ArrayList<Carta>();
               evma1.valorNumericoMano();
               
                for(int i=0;i< 5;i++){
                   if(mano.get(i).getPuntaje()==evma1.PrimRep){

                   temporal.add(mano.get(i));
                 }
                 else{temporalDif.add(mano.get(i)) ;}
             }
   totales.addAll(temporal);
   totales.addAll(temporalDif);
   mano=totales;
  
   return mano;
  
  }
        //desempate de full y doble pareja
    public int desempatePTP(ArrayList<Carta> mano1, ArrayList<Carta>  mano2) {     
                mano1=acomodarPareja(mano1);
                mano2=acomodarPareja(mano2);
                 if(mano1.get(0).getPuntaje()==1){mano1.get(0).setPuntaje(14);}
                 if(mano2.get(0).getPuntaje()==1){mano2.get(0).setPuntaje(14);}
                    if (mano2.get(0).getPuntaje()>mano2.get(0).getPuntaje()){return 1;}
                    if (mano1.get(0).getPuntaje() <  mano2.get(0).getPuntaje()){return 2;}
                    if (mano1.get(0).getPuntaje()== mano2.get(0).getPuntaje()){
                        int cont=4;
                        while (cont>=0){
                            if (mano1.get(cont).getPuntaje()>mano2.get(cont).getPuntaje()){return 1;}
                           if (mano1.get(cont).getPuntaje()<mano2.get(cont).getPuntaje()){return 2;}
                           if (mano1.get(cont).getPuntaje()== mano2.get(cont).getPuntaje()){ cont--;}

                 }
            }  
            return 1;
    }

    public int desempateDobleP(ArrayList<Carta> mano1, ArrayList<Carta>  mano2) {
                    mano1=acomodarDobles(mano1);
                    mano2=acomodarDobles(mano2);
                    if(mano1.get(0).getPuntaje()==1){mano1.get(0).setPuntaje(14); }
                    if(mano2.get(0).getPuntaje()==1){mano2.get(0).setPuntaje(14);}
                    int jug1valorPareja=mano1.get(0).getPuntaje();
                    int jug1valorPareja2=mano1.get(2).getPuntaje();
                    int jug2valorPareja=mano2.get(0).getPuntaje();
                    int jug2valorPareja2=mano2.get(2).getPuntaje();
                    int mayor1=0,mayor2=0,menor1=02, menor2=0;
                    if(mano1.get(0).getPuntaje()==1){jug1valorPareja=14;}
                    if(mano1.get(2).getPuntaje()==1){jug1valorPareja2=14;}
                    if(mano2.get(0).getPuntaje()==1){jug2valorPareja=14;}
                    if(mano2.get(2).getPuntaje()==1){jug2valorPareja2=14;}
                    
                    mayor1=jug1valorPareja;mayor2=jug2valorPareja;menor1=jug1valorPareja2;menor2=jug2valorPareja2;
             
                    if (mayor1>mayor2){return 1;}
                    if (mayor1<mayor2){return 2;}
                    else{
                        if((menor1>menor2)){return 1;}
                         if(menor1<menor2){return 2;}
                         else{
                           if(mano1.get(3).getPuntaje()> mano2.get(3).getPuntaje()){return 1;}
                           return 2;
                         
                         }
                        }
                    
                    
                    } 
                
 
    public ArrayList<Carta> acomodarDobles(ArrayList<Carta> mano) {
               Hand hand=new Hand();
               hand.setMejorMano(mano);
               EvalMano evma1=new EvalMano();
               evma1.setHand(mano);
               ArrayList<Carta> temporalMay=new ArrayList<Carta>();
               ArrayList<Carta> temporalMenor=new ArrayList<Carta>();
               ArrayList<Carta> temporalDif=new ArrayList<Carta>();
               ArrayList<Carta> totales=new ArrayList<Carta>();
               evma1.valorNumericoMano();
               
                for(int i=0;i< 5;i++){
                   if(mano.get(i).getPuntaje()==evma1.PrimRep){                                        
                   temporalMenor.add(mano.get(i));}
                   else if(mano.get(i).getPuntaje()==evma1.SecRep){
                   temporalMay.add(mano.get(i));
                 }
                 else{temporalDif.add(mano.get(i)) ;}
             }
  totales.addAll(temporalMay);
  totales.addAll(temporalDif);
  totales.addAll(temporalMenor);
  mano=totales;
   return mano;
    }
}
  
        
         
