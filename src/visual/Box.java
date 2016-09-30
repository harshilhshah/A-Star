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
	int col, row = 0;
	boolean isSelected = false;
	Terrain terrain = Terrain.UNBLOCKED_CELL;
	
	Box(int c, int r, int cellWidth, int cellHeight){
		super(c,r,cellWidth,cellHeight);
		col = c;
		row = r;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		isSelected = true;
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
}