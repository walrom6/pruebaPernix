/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Mazo implements Iterator {
        private ArrayList<Carta> cartas;

         Mazo()
	{
		cartas = new ArrayList<Carta>();
		int index_1, index_2;
		Random generator = new Random();
		Carta temp;
		for (short a=0; a<=3; a++)
		{
			for (short b=0; b<=12; b++)
			 {
			   cartas.add( new Carta(a,b) );
			 }
		}

		int size = cartas.size() -1;
		for (short i=0; i<100; i++)
		{
			index_1 = generator.nextInt( size );
			index_2 = generator.nextInt( size );

			temp = (Carta) cartas.get( index_2 );
			cartas.set( index_2 , cartas.get( index_1 ) );
			cartas.set( index_1, temp );
		}
	}


    @Override
    public Carta next() {
       return cartas.remove( cartas.size()-1 );
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getTotalCards()
	{
		return cartas.size();  //we could use this method when making a complete poker game to see if we needed a new deck
	}

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
       
}