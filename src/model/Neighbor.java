package model;

public enum Neighbor{
	N(0,1), NE(1,1),
	E(1,0), SE(1,-1),
	S(0,-1), SW(-1,-1),
	W(-1,0), NW(-1,1);
	
	public static Neighbor[] neighnbors = {NW, N, NE, E, SE, S, SW, W };
	
	private int dx,dy;
	
	Neighbor(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getXChange(){ return this.dx; }
	public int getYChange(){ return this.dy; }
	public boolean isDiagonal(){ return this == NE || this == NW || this == SE || this == SW; }
}