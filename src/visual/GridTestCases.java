package visual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Utility;
import model.HeuristicType;
import model.RegularAStar;
import model.UniformCostSearch;
import model.WeightedAStar;

public class GridTestCases {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Grid[] gList = new Grid[5];
		gList[0] = Utility.readFile("src/map1.txt");
		gList[1] = Utility.readFile("src/map2.txt");
		gList[2] = Utility.readFile("src/map3.txt");
		gList[3] = Utility.readFile("src/map4.txt");
		gList[4] = Utility.readFile("src/map5.txt");
		
		for(int i = 0; i < 5; i++){
			System.out.println("FOR MAP"+ i+1 +" : Regular A*\n");
			runTestsAvgForAllHeuristicsFor1GridRegularAStar(gList[i]);
			System.out.println();
			System.out.println("FOR MAP"+ i+1 +" : Weighted A*\n");
			runTestsAvgForAllHeuristicsFor1GridWeightedAStar(gList[i]);
			System.out.println();
			System.out.println("FOR MAP"+ i+1 +" : UniformCostSearch\n");
			runTestsAvgForUniformCostSeach(gList[i]);
			System.out.println();
		}
		

//			g.runAStar(new RegularAStar(g.getGrid(), HeuristicType.EUCLIDEAN));
		

	}

	public static void runTestsAvgForAllHeuristicsFor1GridRegularAStar(Grid g){
		double[] averages = new double[5];
		for(int i = 0; i < 6; i++){
			System.out.println(HeuristicType.values()[i]);
			double[] currAverages = g.runAStar(new RegularAStar(g.getGrid(), HeuristicType.values()[i]));
			for(int j = 0; j < 5; j++){
				averages[j] += currAverages[j];
			}
			System.out.println();
		}
		for(int k = 0; k < 5; k++){
			averages[k] /= 5;
			System.out.println(k+") "+averages[k]);
		}		
	}
	
	public static void runTestsAvgForAllHeuristicsFor1GridWeightedAStar(Grid g){
		double[] averages = new double[5];
		for(int i = 0; i < 6; i++){
			System.out.println(HeuristicType.values()[i]);
			double[] currAverages = g.runAStar(new WeightedAStar(g.getGrid(), 1.5, HeuristicType.values()[i]));
			for(int j = 0; j < 5; j++){
				averages[j] += currAverages[j];
			}
			System.out.println();
		}
		for(int k = 0; k < 5; k++){
			averages[k] /= 5;
			System.out.println(k+") "+averages[k]);
		}		
	}
	
	public static void runTestsAvgForUniformCostSeach(Grid g){
//		double[] averages = new double[5];
		g.runAStar(new UniformCostSearch(g.getGrid()));
	}
	
}
