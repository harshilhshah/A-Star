package visual;

public class Cell{

	private char state; 
	/*– Use ’0’ to indicate a blocked cell 
– Use ’1’ to indicate a regular unblocked cell 
– Use ’2’ to indicate a hard to traverse cell 
– Use ’a’ to indicate a regular unblocked cell with a highway 
– Use ’b’ to indicate a hard to traverse cell with a highway */

	public  Cell(){
		state = '0';
	}

	public String toString(){
		return String.valueOf(this.state);
	}
}
