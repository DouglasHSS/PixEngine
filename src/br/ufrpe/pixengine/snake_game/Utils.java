package br.ufrpe.pixengine.snake_game;

import java.util.Random;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.components.GameObject;


public class Utils {
	
	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	/**
	 * Função para retornar uma coordenada randomica dentro do game 
	 * container.
	 * 
	 * @param gc
	 * @return int[]
	 */
	public static int[] randomicCoordenates(GameContainer gc, GameObject go){
		int[] coordenates = new int[2];
		
		int x_max = (int) (gc.getWidth() - go.getW());
		int randomic_x = randInt(0, x_max);
		
		int y_max = (int) (gc.getHeight() - go.getH());
		int randomic_y = randInt(0, y_max);
		
		coordenates[0] = randomic_x;
		coordenates[1] = randomic_y;
				
		return coordenates;
	}

}
