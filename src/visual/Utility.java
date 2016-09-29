package visual;

import java.util.ArrayList;
import java.util.Random;

public class Utility{
	
	public static Random rand = new Random();
	
	public static ArrayList<Point> generateRandomPoints(int numOfPoints){
		
		ArrayList<Point> points = new ArrayList<>(numOfPoints);
		
		for(int i = 0; i < numOfPoints; i++){
			points.add(generateRandomPoint(rand));
		}

		return points;
		
	}
	
	private static Point generateRandomPoint(final Random r){
		return new Point(r.nextInt(Grid.cols),r.nextInt(Grid.rows));
	}
}
