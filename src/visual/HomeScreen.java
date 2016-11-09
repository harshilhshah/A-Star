package visual;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.HeuristicType;
import model.IntegratedAStar;
import model.RegularAStar;
import model.SequentialAStar;
import model.UniformCostSearch;
import model.WeightedAStar;
import controller.Utility;

public class HomeScreen extends Applet implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	Button newMapBtn, readMapBtn, writeMapBtn, writePathBtn, aStarBtn, waStarBtn, uaStarBtn, saStarBtn, iaStarBtn, exitBtn, genNewStrtGoal;
	static JLabel lbl = new JLabel("");
	Grid g;

	public void init() {
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.resize(400, 400);
		
		newMapBtn = new Button("Create new map");
		add(newMapBtn);		
		newMapBtn.addActionListener(this);

		readMapBtn = new Button("Read map from file");
		add(readMapBtn);
		readMapBtn.addActionListener(this);
		
		uaStarBtn = new Button("Execute Uniform Cost Search");
		add(uaStarBtn);
		uaStarBtn.addActionListener(this);
		
		aStarBtn = new Button("Execute A*");
		add(aStarBtn);
		aStarBtn.addActionListener(this);
		
		waStarBtn = new Button("Execute weighted A*");
		add(waStarBtn);
		waStarBtn.addActionListener(this);
		
		saStarBtn = new Button("Sequential A*");
		add(saStarBtn);
		saStarBtn.addActionListener(this);
		
		iaStarBtn = new Button("Integrated A*");
		add(iaStarBtn);
		iaStarBtn.addActionListener(this);
		
		genNewStrtGoal = new Button("Generate new Stand,Goal points");
		add(genNewStrtGoal);
		genNewStrtGoal.addActionListener(this);
		
		writePathBtn = new Button("Save result");
		add(writePathBtn);
		writePathBtn.addActionListener(this);
		
		writeMapBtn = new Button("Save map");
		add(writeMapBtn);
		writeMapBtn.addActionListener(this);
		
		exitBtn = new Button("Exit");
		add(exitBtn);
		exitBtn.addActionListener(this);
		
		add(lbl);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newMapBtn) {
			if (g != null){
				g.dispose();
			}
			g = new Grid();
		}
		else if(e.getSource() == readMapBtn){
			if (g != null){
				g.dispose();
			}
			String s = (String)JOptionPane.showInputDialog(
					(Component) getAppletContext(),"Enter file name:", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, null, ".txt");
			display(s);
			try {
				g = Utility.readFile(s);
			} catch (IOException err) {
				displayError("File not found");
			}
		}
		else if(e.getSource() == writeMapBtn){
			if(g == null){
				displayError("No map found.");
			}else{
				String s = (String)JOptionPane.showInputDialog(
						(Component) getAppletContext(),"Enter file name:", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, null, ".txt");
				try {
					Utility.writeFile(s, g.toString());
					display("Map saved.");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					displayError("Couldn't save map. Try again.");
				}
			}
		}
		else if(e.getSource() == aStarBtn){
			if(g == null){
				displayError("Can't find a map to traverse.");
			}
			else{
				display("Loading...");
				g.runAStar(new RegularAStar(g.getGrid(), HeuristicType.EUCLIDEAN));
			}
		}
		else if(e.getSource() == waStarBtn){
			if(g == null){
				displayError("Can't find a map to traverse.");
			}
			else{
				display("Loading...");
				g.runAStar(new WeightedAStar(g.getGrid(), 3, HeuristicType.EUCLIDEAN));
			}
		}
		else if(e.getSource() == uaStarBtn){
			if(g == null){
				displayError("Can't find a map to traverse.");
			}
			else{
				display("Loading...");
				g.runAStar(new UniformCostSearch(g.getGrid()));
			}
		}
		else if(e.getSource() == saStarBtn){
			if(g == null){
				displayError("Can't find a map to traverse.");
			}
			else{
				display("Loading...");
				g.runAStar(new SequentialAStar(g.getGrid(),1.25,2));
			}
		}
		else if(e.getSource() == iaStarBtn){
			if(g == null){
				displayError("Can't find a map to traverse.");
			}
			else{
				display("Loading...");
				g.runAStar(new IntegratedAStar(g.getGrid(),1.25,2));
			}
		}
		else if(e.getSource() == genNewStrtGoal){
			if(g == null){
				displayError("Can't generate new Start and Goal points");
			}
			else{
				g.generateStartAndGoal();
			}
		}
		else if(e.getSource() == writePathBtn){
			if(g == null){
				displayError("No map found.");
			}else{
				String s = (String)JOptionPane.showInputDialog(
						(Component) getAppletContext(),"Enter file name:", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, null, ".txt");
				try {
					Utility.writeFile(s, g.getPathTaken());
					display("Path saved.");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					displayError("Couldn't save the path. Try again.");
				}
			}
		}
		else if(e.getSource() == exitBtn){
			System.exit(ABORT);
		}
	}
	
	public static void display(String s){
		lbl.setText(s);
		lbl.setForeground(Color.MAGENTA);
	}
	
	public static void displayError(String s){
		lbl.setText("ERROR: " + s);
		lbl.setForeground(Color.RED);
	}
	
	public static void main(String[] args) {
		new HomeScreen();
	} 
}