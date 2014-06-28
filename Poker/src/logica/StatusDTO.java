/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package logica;

import java.util.ArrayList;

public class StatusDTO {
    
    public ArrayList<Apuesta> ListaApuestas;
    public ArrayList<Pozo> ListaPozos;
    
    Ronda RondaActual;

    public StatusDTO(ArrayList<Apuesta> pListaApuestas) 
    {
        ListaPozos = new ArrayList<Pozo>();         
        ListaApuestas = pListaApuestas;
    }
    

    public void mainApuesta()
    {
        System.out.println(verificarApuestasIguales());
        
        /*while(verificarRestoApuesta() == false)
        { */
            if (verificarApuestasIguales())
            {
                ListaPozos.add(new Pozo(ListaApuestas));
            }
            else
            {
                while(! verificarApuestasIguales())
                {
                    sidePot();
                }
            }
                
        
    }
    
    public void sidePot()
    {
        int menor = buscaMenorApuesta();
        insertaApuestaIgualada(menor);
        restarMenor(menor);
        eliminarValorEnCero();
    }
    
    public ArrayList<Jugador> repartirPots(ArrayList<Jugador> listaGanadores)
    {   System.out.println("Largo Lista pozos : "+ListaPozos.size());
        int i = 0;
        while (!ListaPozos.isEmpty())
        {
            if (estaEnPot(listaGanadores.get(i)))
            {
                System.out.println("Total del pozo");
                imprimirListaPozo();
                listaGanadores.get(i).cantidadDinero = listaGanadores.get(i).cantidadDinero + ListaPozos.get(0).TotalPozo();
                ListaPozos.remove(0);
            }
            else
            {
                i++;
            }

        }
        return listaGanadores;
    }
    
    public boolean estaEnPot(Jugador pjugador)
    {
        ArrayList<Apuesta> temp ;
        temp = ListaPozos.get(0).listaApuestas;
        for (int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).jugador.nombre == pjugador.nombre)
            {
                return true;
            }
            
        }
        return false;
    }
    
        //Verifica que las apuestas de la lista sean iguales.
    //Salida: 
    //      true, si las apuestas son iguales.
    //      false, si las apuestas son distitas.
    public boolean verificarApuestasIguales()
    {
        int i = 0;
        
        while( i < ListaApuestas.size())
        {
            if(i+1 == ListaApuestas.size())
            {
                //System.out.println("true");
                return true;
            }
            else if (ListaApuestas.get(i).valor != ListaApuestas.get(i+1).valor)
            {
                //System.out.println("false");
                return false;
            }
            i++;
        }
        
        return true;
    }
    
    
    public void restarMenor(int Menor)
    {
        for(int i = 0; i < ListaApuestas.size(); i++)
        {
            ListaApuestas.get(i).setValor(ListaApuestas.get(i).getValor()-Menor);
        }
    }
   
    
    public int buscaMenorApuesta()
    {
        int i = 0;
        int temp = ListaApuestas.get(i).getValor();
        
        
        while( i < ListaApuestas.size())
        {
            if (i+1 != ListaApuestas.size())
            {
                if (temp > ListaApuestas.get(i+1).getValor())
                {
                    temp = ListaApuestas.get(i+1).getValor();
                    
                }
            }
            i++;
            
        }

        return temp;
        
    }
    
    public void insertaApuestaIgualada(int menorApuesta)
    {
        ArrayList<Apuesta> ListaApuestasIgualadas = new ArrayList<Apuesta>();
        
        int i = 0;
        while (i < ListaApuestas.size())
        { 
            Apuesta ApuestaTemp = new Apuesta(ListaApuestas.get(i).jugador);
            ApuestaTemp.valor = menorApuesta;
            ListaApuestasIgualadas.add(ApuestaTemp);
            i++;
        }
        
        Pozo PozoIgualado = new Pozo(ListaApuestasIgualadas);
        ListaPozos.add(PozoIgualado);
        
    }
    
 
    //Verifica que el valor de la puesta este en cero, que la apuesta ya qued´repartida.
    //Retorna:
    //      true: Si todos estan en cero.
    //      false: Aun falta por repartir.
    public boolean verificarRestoApuesta()
    {
        for (int i = 0; i < ListaApuestas.size(); i++)
        {
            if ( ListaApuestas.get(i).getValor() != 0)
            {    
                
                return false;
            }
        }

        return true;
    }
    
    public void eliminarValorEnCero()
    {
        ArrayList<Apuesta> temListaApuestas = new ArrayList();
        
        for(int i = 0; i< ListaApuestas.size(); i++ )
        {
            if(ListaApuestas.get(i).valor != 0)
            {
                temListaApuestas.add(ListaApuestas.get(i));
            }
            
        }
        
        ListaApuestas = temListaApuestas;
    }
    
    public void imprimirListaPozo()
    {
       for(int m = 0; m < ListaPozos.size(); m++ )
       {
                System.out.println(ListaPozos.get(m).getPozoTotal());
       }
    }

    
}

