package visual;

import java.io.IOException;

import controller.Utility;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Grid g;
		System.out.println("Reading");
		try {
			g = new Grid(Utility.readFile("map.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		//Utility.writeFile("map.txt",g.toString());
		System.out.println(g.getStartPoint());
		System.out.println(g.getGoalPoint());
		
	//	System.out.println("starting");
   // 	for(Point p: Utility.generateBorderPoints()){
  //  		System.out.println(p.toString());
 //   	}
//    	System.out.println("ending");
	/*	ArrayList<Integer> points = new ArrayList<Integer>();
		points.add(3);
		points.add(2);
		System.out.println(points.size() +"done");
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
			System.out.println(rand.nextInt(2+1)); */
	} 

}
