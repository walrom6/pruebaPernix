package logica;

import java.util.ArrayList;
import log_Conexion.Cliente;
import log_Conexion.Servidor;

	

public class FiveCards implements GeneralJuego{

        public int CartasVisibles;
	public int CartasPrivadas;
        public Mazo MazoGeneral;
        ArrayList <Jugador> Jugadores ;
        public  int cantidadJugadores;
        public ArrayList<Carta> Mesa;
          ArrayList <Jugador> JugadoresAcomodados ;

    public FiveCards(ArrayList<Jugador> pJugadores) {
        this.CartasVisibles = 0;
        this.CartasPrivadas = 5;
        this.Jugadores= pJugadores;
        this.Mesa= new ArrayList<Carta>();
        this.MazoGeneral = new Mazo();
        this.cantidadJugadores = pJugadores.size();
        this.JugadoresAcomodados=new ArrayList<Jugador>();
    }
 public ArrayList<Jugador> getJugadoresAcomodados() {
        return JugadoresAcomodados;
    }

    public void setJugadoresAcomodados(ArrayList<Jugador> JugadoresAcomodados) {
        this.JugadoresAcomodados = JugadoresAcomodados;
    }
    
      /**
     *
     * @return
     */
    @Override
    public int getCartasVisibles() {
        return CartasVisibles;
        }

    /**
     *
     * @param CartasVisibles
     */
    @Override
        public void setCartasVisibles(int CartasVisibles) {
            this.CartasVisibles = CartasVisibles;
        }

    /**
     *
     * @return
     */
    @Override
        public Mazo getMazoGeneral() {
            return MazoGeneral;
        }

    /**
     *
     * @param listaCartas
     */
    @Override
      public void calcularManos(){
            // calcula las posibles manos de cada jugador
            //Agrega la mejor mano posible a cada jugador
            
            for(int j=0; j< Jugadores.size(); j++)
                {
                    ArrayList<Carta> listaCartas= new ArrayList<Carta>();
                    listaCartas.addAll(Jugadores.get(j).getCartasJugador());
                    System.out.println(Jugadores.get(j).getCartasJugador().get(0).imprimirla());
                    System.out.println(Jugadores.get(j).getCartasJugador().get(1).imprimirla());
                    //listaCartas.addAll(Mesa);
                    
                    
                   
                    Hand mano=new Hand();
                    mano.setTodasLasCartas(listaCartas);
                    Jugadores.get(j).setMejorMano(mano.getMejorMano(mano.combinatoria(listaCartas)));  
                    EvalMano eval=new EvalMano();
                    eval.setHand(Jugadores.get(j).mejorMano);
                    System.out.println(eval.valorMano());
                    Jugadores.get(j).setValorMejorMano(eval.valorNumericoMano());

                }
        
        }
    /*public void calcularManosA(ArrayList<Carta> listaCartas){
            // calcula las posibles manos de cada jugador
            //Agrega la mejor mano posible a cada jugador
            
            for(int j=0; j<= cantidadJugadores-1; j++)
                {
                    
                    Hand mano=new Hand();
                    mano.setTodasLasCartas(listaCartas);
                    Jugadores.get(j).setMejorMano(mano.getMejorMano(mano.combinatoria(listaCartas)));  
                   
                    EvalMano eval=new EvalMano();
                    eval.setHand( Jugadores.get(j).mejorMano);
                    System.out.println(eval.valorNumericoMano());
                    Jugadores.get(j).setValorMejorMano(eval.valorNumericoMano());

                }
        
        }*/
  
        
    /**
     *
     * @param MazoGeneral
     */
    @Override
        public void setMazoGeneral(Mazo MazoGeneral) {
            this.MazoGeneral = MazoGeneral;
        }

    /**
     *
     * @return
     */
    @Override
        public int getCartasPrivadas() {
            return CartasPrivadas;
        }

    /**
     *
     * @param CartasPrivadas
     */
    @Override
        public void setCartasPrivadas(int CartasPrivadas) {
            this.CartasPrivadas = CartasPrivadas;
        }

    /**
     *
     * @return
     */
    @Override
        public int getCantidadJugadores() {
            return cantidadJugadores;
        }

    /**
     *
     * @param cantidadJugadores
     */
    @Override
        public void setCantidadJugadores(int cantidadJugadores) {
            this.cantidadJugadores = cantidadJugadores;
        }

    /**
     *
     */
    @Override
        public void repartirCartas()
        {
           for(int i=0; i < Jugadores.size(); i++)
           {
                for(int j=1; j<= CartasPrivadas; j++)
                {
                    Jugadores.get(i).CartasJugador.add(MazoGeneral.next());
                }
            }
        }

    /**
     *
     */
    @Override
        public void repartirCartasMesa()
        {
         for(int j=1; j<= CartasVisibles; j++)
                {
                    Mesa.add(MazoGeneral.next());
                }
            
        }

    /**
     *
     * @param Ip
     * @param nombre
     * @param TipoJuego
     */
    @Override
     public void crearJugador(String Ip, String nombre, String TipoJuego)
        {
                Jugador jugador1 = new Jugador(Ip, nombre, TipoJuego);
                Jugadores.add(jugador1);
                cantidadJugadores++;
            
        }

    /**
     *
     */
    @Override
        public void mostrarCartas(){
        for(int i=0;i< this.Mesa.size();i++){
            System.out.println(this.Mesa.get(i).imprimirla());
        }
    
    }

    /**
     *
     */
   
        
        @Override
        public boolean puedeApostar(int ApuestaGeneral, Jugador jugador)
        {
            return jugador.cantidadDinero >= ApuestaGeneral;
        }
        
    @Override
        public void  ApuestasIgualesJugador(ArrayList<Jugador> ListaJugadores, int ApuestaGeneral)
        {
            int i =0;
            while (i < ListaJugadores.size())
            {
                if(ListaJugadores.get(i).apuesta.getValor() < ApuestaGeneral && ListaJugadores.get(i).cantidadDinero > 0)
                {
                    System.out.println("Distintos: "+ ListaJugadores.get(i).nombre);
                    ListaJugadores.get(i).Igualado = false;
                }
                else{
                ListaJugadores.get(i).Igualado = true;
                }
                i++;
            }

        }
        
        @Override
        public boolean jugadoresIguales(ArrayList<Jugador> ListaJugadores)
        {
            int i =0;
            while (i < ListaJugadores.size())
            {
                if(ListaJugadores.get(i).Igualado == false)
                {
                    System.out.println("Distintos: "+ ListaJugadores.get(i).nombre);
                    return false;
                }
                i++;
            }
            return true;

        }
        
         public int desempate(Jugador jug1, Jugador jug2) {
            if((jug1.getValorMejorMano()==8)||(jug1.getValorMejorMano()==4)||(jug1.getValorMejorMano()==2))
            { 
                return desempatePTP(jug1,jug2); 
            } 
            if(jug1.getValorMejorMano()==7){ //desempate full 
                return desempateDobleP(jug1,jug2); } 
            if(jug1.getValorMejorMano()==3){ //desempate doble pareja 
                return desempateDobleP(jug1,jug2); }
            

            int cont=4;
            while (cont>=0){ 
                if (jug1.getMejorMano().get(cont).getPuntaje()>jug2.getMejorMano().get(cont).getPuntaje()){
                    return 1;} 
                if (jug1.getMejorMano().get(cont).getPuntaje()<jug2.getMejorMano().get(cont).getPuntaje()){
                    return 2;} 
                if (jug1.getMejorMano().get(cont).getPuntaje()== jug1.getMejorMano().get(cont).getPuntaje()){cont--;} cont--; } 
                   
            return 1;
        }
        
        
    @Override
     public void SacarGanadores(){
            int largo=this.cantidadJugadores;
             ArrayList<Jugador> temporal=new ArrayList<Jugador>(this.Jugadores);
            ArrayList<Jugador> temporal2=new ArrayList<Jugador>();
            int contador=1;
            for(int i=0;i <largo;i++){
               
                int ganador=this.getGanador(temporal,contador);
                if(temporal.get(ganador).isActivo()==true){
                temporal2.add(temporal.get(ganador));
                temporal.remove(ganador);}
                else{temporal.remove(ganador);}
                contador++;
                
   
                
              
            }
            JugadoresAcomodados=temporal2;
           
     }
    
     
     @Override
   public int getGanador(ArrayList<Jugador> Lista,int contador) {
             Jugador Ganador= Lista.get(0);
             int puntajeGanador=Lista.get(0).getValorMejorMano();
             int indice=0,indiceTemporal=0,puntajeTemp;
            
            for(int i=1; i < Lista.size();i++)
            {
               Jugador temp= Lista.get(i);
               puntajeTemp=Lista.get(i).getValorMejorMano();
               indiceTemporal=i;
               if(puntajeGanador<puntajeTemp){
                 Ganador.setPosicionFinal(0);
                 Ganador=Lista.get(i);
                 puntajeGanador=Lista.get(i).getValorMejorMano();
                 indice=indiceTemporal;
                 Lista.get(i).setPosicionFinal(contador);
                 
               }
               
               if(puntajeGanador==puntajeTemp){
                   System.out.println("empatamos");
                  int jugadorGanador=0;
                  jugadorGanador=desempate(Ganador,Lista.get(i));
                  
                  if(jugadorGanador==2){
                    Ganador= Lista.get(i);
                    puntajeGanador=Lista.get(i).getValorMejorMano();
                    indice=indiceTemporal; 
                  }
               }
               }
            
            
             Lista.get(indice).setPosicionFinal(contador);
            return indice;                   
        } 
     
     
     
     
     
     
     
    @Override
     public void ImprimirGanadores(){
        for(int i=0;i<= this.JugadoresAcomodados.size();i++){
            System.out.println(this.JugadoresAcomodados.get(i).nombre);
        }
    }
        
        //definir ganado
    @Override
    public ArrayList<Carta> acomodarPareja(ArrayList<Carta> mano){
               Hand hand=new Hand();
               hand.setMejorMano(mano);
              mano= hand.acomodarPareja(mano);
    
   return mano;
  
  }

    @Override
    public int desempatePTP(Jugador jug1, Jugador jug2) {
    
                jug1.setMejorMano(acomodarPareja(jug1.getMejorMano()));// acomoda las manos con la pareja primero
                jug2.setMejorMano(acomodarPareja(jug2.getMejorMano()));
                 if(jug1.getMejorMano().get(0).getPuntaje()==1){
                     jug1.getMejorMano().get(0).setPuntaje(14);
                 }
                 if(jug2.getMejorMano().get(0).getPuntaje()==1){
                     jug2.getMejorMano().get(0).setPuntaje(14);
                 }
                
                    if (jug1.getMejorMano().get(0).getPuntaje()>jug2.getMejorMano().get(0).getPuntaje()){
                        
                        return 1;}
                    
                    if (jug1.getMejorMano().get(0).getPuntaje() < jug2.getMejorMano().get(0).getPuntaje()){
                        return 2;}
                    
                    if (jug1.getMejorMano().get(0).getPuntaje()== jug2.getMejorMano().get(0).getPuntaje()){
                        int cont=4;
                        while (cont>=0){
                            if (jug1.getMejorMano().get(cont).getPuntaje()>jug2.getMejorMano().get(cont).getPuntaje()){return 1;}
                           if (jug1.getMejorMano().get(cont).getPuntaje()<jug2.getMejorMano().get(cont).getPuntaje()){return 2;}
                           if (jug1.getMejorMano().get(cont).getPuntaje()== jug2.getMejorMano().get(cont).getPuntaje()){ cont--;}

                 }
                 
                    }
           
              //System.out.println("EMPATAMOS");  
            return 1;
    }
    @Override
     public int desempateDobleP(Jugador jug1, Jugador jug2) {
  
                    jug1.setMejorMano(acomodarDobles( jug1.getMejorMano()));// acomoda las manos con la pareja primero
                    jug2.setMejorMano(acomodarDobles( jug2.getMejorMano()));
                    int jug1valorPareja=jug1.getMejorMano().get(0).getPuntaje();
                    int jug1valorPareja2=jug1.getMejorMano().get(2).getPuntaje();
                    int jug2valorPareja=jug2.getMejorMano().get(0).getPuntaje();
                    int jug2valorPareja2=jug2.getMejorMano().get(2).getPuntaje();
                    if(jug1.getMejorMano().get(0).getPuntaje()==1){
                        jug1valorPareja=14;
                    }
                     if(jug1.getMejorMano().get(2).getPuntaje()==1){
                        jug1valorPareja2=14;
                    }
                      if(jug2.getMejorMano().get(0).getPuntaje()==1){
                        jug2valorPareja=14;
                    }
                      if(jug2.getMejorMano().get(2).getPuntaje()==1){
                        jug2valorPareja2=14;
                    }
                    
                    int mayor1=jug1valorPareja;
                    int mayor2=jug2valorPareja;
                    int menor1=jug1valorPareja2;
                    int menor2=jug2valorPareja2;
                    
                    
                    if(jug1valorPareja>jug1valorPareja2){mayor1=jug1valorPareja2;menor1=jug1valorPareja;}
                    if(jug2valorPareja>jug1valorPareja2){mayor2=jug2valorPareja2;menor2=jug2valorPareja;}

                    if (mayor1>mayor2){return 1;}
                    if (mayor1<mayor2){return 2;}
                    else{
                        if((menor1>menor2)){
                         return 1;}
                         if(menor1<menor2){
                             
                          return 2;}
                         else{
                           if(jug1.getMejorMano().get(3).getPuntaje()>jug2.getMejorMano().get(3).getPuntaje()){return 1;}
                           return 2;
                         
                         }
                        }
                    
                    
                    } 
 
    @Override
    public ArrayList<Carta> acomodarDobles(ArrayList<Carta> mano) {
               Hand hand=new Hand();
               hand.setMejorMano(mano);
               EvalMano evma1=new EvalMano();
               evma1.setHand(mano);
               mano= hand.acomodarDobles(mano);
              
  
   return mano;
    }
  
    @Override
  public void GeneraJuego(Servidor ServidorPrincipal){
  for (int i=0; i< ServidorPrincipal.ListaJ.size(); i++)
       {
           ArrayList<Carta> Cartas = ServidorPrincipal.ListaJ.get(i).CartasJugador;
           Cartas.addAll(this.Mesa);
           System.out.println(Cartas.size());
           String CartasString = Cartas.get(0).imprimirla();
           System.out.println(Cartas.get(0).imprimirla());
           for(int j = 1; j< Cartas.size(); j++)
           {
               CartasString += ";"+ Cartas.get(j).imprimirla();
           }
           Cliente MensajeCliente = new Cliente("algo;y;"+CartasString, ServidorPrincipal.ListaJ.get(i).IpJugador);

       }
       
       // metodos para ronda de apuestas...
     
       ArrayList<Apuesta> ListaApuestas = new ArrayList<Apuesta>();
       Pozo PozoGeneral = new Pozo(ListaApuestas);
       Servidor ServidorApuestas = new Servidor();
       
       int ApuestaGeneralMain = 100;
       // primera ronda de apuestas *************************************************
       for (int i = 0; i < ServidorPrincipal.ListaJ.size(); i ++)
       {   
           ServidorApuestas = new Servidor(ServidorPrincipal.ListaJ,ListaApuestas,ApuestaGeneralMain);
           ListaApuestas.add(ServidorApuestas.ListaJ.get(i).getApuesta());
           ApuestaGeneralMain = ServidorApuestas.ApuestaGeneral;
           System.out.println("VALOR ACTUALIZADO:"+ApuestaGeneralMain);
           ServidorApuestas.ApuestaGeneral = ApuestaGeneralMain;
           System.out.println("Pozo General: "+PozoGeneral.TotalPozo());
       }
       System.out.println("Pozo General antes del while: "+PozoGeneral.TotalPozo());       
       this.ApuestasIgualesJugador(ServidorApuestas.ListaJ, ServidorApuestas.ApuestaGeneral);  
       int i =0;
       while ( this.jugadoresIguales(ServidorApuestas.ListaJ) != true)
       {    System.out.println("Pozo General  del while: "+PozoGeneral.TotalPozo());
            System.out.println("Entra while si los jugadores igualados no es vacio");
            for (int j=0; j< ServidorApuestas.ListaJ.size(); j++)
            {
                if (ServidorApuestas.ListaJ.get(j).Igualado == false)
                {
                    ServidorApuestas = new Servidor(ServidorApuestas.ListaJ, ListaApuestas,ApuestaGeneralMain);
                    System.out.println("Apuesta por jugador: "+ ServidorApuestas.ListaJ.get(j).getApuesta().valor);
                    ListaApuestas.add(ServidorApuestas.ListaJ.get(j).getApuesta());
                    ApuestaGeneralMain = ServidorApuestas.ApuestaGeneral;
                    ServidorApuestas.ApuestaGeneral = ApuestaGeneralMain;
              
                    System.out.println(ListaApuestas.get(j).jugador.nombre);
                    System.out.println(ListaApuestas.get(j).valor);   
                   this.ApuestasIgualesJugador(ServidorApuestas.ListaJ, ServidorApuestas.ApuestaGeneral); 
                }
              System.out.println("Pozo General: "+PozoGeneral.TotalPozo());   
            }
            /*for (int x=0; x< ServidorApuestas.ListaJ.size(); x++)
            {
                ServidorApuestas.ListaJ.get(x).getApuesta().valor = 0;
            }*/
       }
       
    this.calcularManos();
    this.SacarGanadores();
    
    StatusDTO Apuestas = new StatusDTO(ListaApuestas);
    Apuestas.mainApuesta();
    this.JugadoresAcomodados=Apuestas.repartirPots(JugadoresAcomodados);

    EvalMano evaluador=new EvalMano();
    evaluador.setHand(this.JugadoresAcomodados.get(0).getMejorMano());
    System.out.println("Ganador :"+this.JugadoresAcomodados.get(0).nombre);
    System.out.println("Ganador :"+evaluador.valorMano());
    System.out.println("Ganador :"+this.JugadoresAcomodados.get(0).cantidadDinero);
    
  }

    @Override
    public int desempate(Jugador jug1, Jugador jug2, int contador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}