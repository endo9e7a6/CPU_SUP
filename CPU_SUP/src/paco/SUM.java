package paco;

public class SUM{
	private int leftover;
	private boolean free;
	private ID_ALU reg;
	private int C;
	private int rC;
	public void set(ID_ALU reg, int leftover){
		this.leftover=leftover;
		this.reg=reg;
	}
	public boolean isFree() {
		return free;
	}

	public void newCicle() {
		leftover--;
		if(leftover<0)
			free=false;
		else{
			rC=reg.rC;
			C=reg.A+reg.B;
			free=true;
		}
			
	}
	public int getC(){
		return C;
	}
	public int getRC(){
		return rC;
	}

}
