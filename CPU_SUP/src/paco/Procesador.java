package paco;

import java.util.ArrayList;
import java.util.LinkedList;

public class Procesador {

	int s = 5;
	ArrayList<Instruccion> bancoInstrucciones;
	LinkedList<ID_ALU> ventanaInstrucciones;	
	int[] bancoRegistros;
	public int[] memoria;
	int pc;
	ALU_WB alu_wb;
	
	ID_ALU id_alu0;
	ID_ALU id_alu1;
	
	IF_ID if_id0;
	IF_ID if_id1;
	
	SUM sum0;
	SUM sum1;
	
	public int[] getBancoRegistros(){
		return this.bancoRegistros;
	}
	public int[] getMemoria(){
		return this.memoria;
	}
	public void inicializa(ArrayList<Instruccion> instrucciones){
		
		this.bancoRegistros =  new int[15];
		for(int i=0;i<bancoRegistros.length;i++)
			bancoRegistros[i]=i*10;
		this.memoria = new int[30];
		for(int i=0;i<memoria.length;i++)
			memoria[i]=0;
		this.bancoInstrucciones = instrucciones;
		this.ventanaInstrucciones=new LinkedList<ID_ALU>();
		pc = 0;
		alu_wb = new ALU_WB();
		
		if_id0 = new IF_ID();
		if_id1 = new IF_ID();
		
		id_alu0 = new ID_ALU();
		id_alu1 = new ID_ALU();
		
		sum0= new SUM();
		sum1= new SUM();
		
		System.out.println("Inicializado correctamente");
	}
	/**private boolean RAWDetector(){
		if(id_alu.codOp != 0 && id_alu.codOp != 88 ){
			if(if_id.rA == id_alu.rC){
				System.out.println("RAW: if_id.rA == id_alu.rC");
				return true;
			}if(if_id.rB == id_alu.rC){
				System.out.println("RAW: if_id.rB == id_alu.rC");
				return true;
			}
		}
		return false;
		
	}
	*/
	public void ejecuta(){
		boolean status = true;
		while(status){
			System.out.println("PC: "+ pc);
			
			status = UnidadesFuncionales.WB(alu_wb, bancoRegistros);
			
			
			
		
			//WB(ALU_WB, bancoRegistros) Cuando esten libres, finaliza
			if(sum0.isFree()&&sum1.isFree() && pc>4){
				status=false;
			}
			if(sum0.isFree()){
				bancoRegistros[sum0.getRC()]=sum0.getC();
			}
			if(sum1.isFree()){
				bancoRegistros[sum1.getRC()]=sum1.getC();
			}
			
			//ALU
			for(ID_ALU reg : ventanaInstrucciones){
				if(reg.codOp==1 && sum0.isFree()){
					sum0.set(reg, s);
				}else if(reg.codOp==1 && sum0.isFree()){
					sum0.set(reg, s);
				}
			}
			//ID(IF_ID, ID_ALU, bancoRegistros
			UnidadesFuncionales.ID(if_id0, id_alu0, bancoRegistros);
			UnidadesFuncionales.ID(if_id1, id_alu1, bancoRegistros);
			ventanaInstrucciones.addLast(new ID_ALU(id_alu0));
			ventanaInstrucciones.addLast(new ID_ALU(id_alu1));
			
			//IF(PC, bancoInstrucciones, IF_ID)
			UnidadesFuncionales.IF(pc, if_id0, bancoInstrucciones);
			UnidadesFuncionales.IF(pc, if_id1, bancoInstrucciones);
			
			pc++;
		}
	}
}
