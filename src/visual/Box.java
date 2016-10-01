package visual;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Terrain;

public class Box extends Rectangle implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Terrain terrain = Terrain.UNBLOCKED_CELL;
	private int highway_index = 0;

	public Box(int c, int r, int cellWidth, int cellHeight){
		super(c * cellWidth,r * cellHeight, cellWidth, cellHeight);
	}
	
	@Override
	public String toString(){
		return (getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL || getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL) ? getTerrain().toString() + highway_index : getTerrain().toString();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public int getHighway_index() {
		return highway_index;
	}
	
	public void setHighway_index(int highway_index) {
		this.highway_index = highway_index;
	}
}