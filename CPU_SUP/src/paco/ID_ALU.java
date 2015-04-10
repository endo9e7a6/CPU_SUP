package paco;

public class ID_ALU implements RegistroDesacoplamiento{
	public ID_ALU(ID_ALU nuevo){
		this.codOp=nuevo.codOp;
		this.A=nuevo.A;
		this.B=nuevo.B;
		this.rC=nuevo.rC;
	}
	public ID_ALU(){
		
	}
	public int codOp;
	public int A;
	public int B;
	public int rC;
}
