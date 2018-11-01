/*
    Aditya Kumbhar
*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;


class Symbol{
	String sname;
	int addr;
	int length;

	Symbol(String name, int addr, int len){
		this.sname = name;
		this.addr = addr;
		this.length = len;
	}

}

class Literal {
	String literal;
	int addr;

	Literal(String lit, int addr){
		this.literal = lit;
		this.addr = addr;
	}

}

class PassTwo{

  HashMap<Integer, String> POT = new HashMap <Integer, String>();
  HashMap<Integer, String> BCCODE = new HashMap <Integer, String>();

  Symbol ST[] = new Symbol[20];
  Literal LT[] = new Literal[20];
  int sIndex,lIndex,loc;


  PassTwo(){
    sIndex = 0;
    lIndex = 0;
    loc = 0;


    //assembler dir
    POT.put(1, "START");
    POT.put(2, "END");
    POT.put(3, "EQU");
    POT.put(4, "LTORG");
    POT.put(5, "ORIGIN");
    POT.put(6, "DS");
    POT.put(7, "DC");
   
  }
  
  void initTables() throws Exception{
	  File fSymTab = new File("symbolTable.txt");
	  File fLitTab = new File("literalTable.txt");
	  BufferedReader br = new BufferedReader(new FileReader(fSymTab));
	  br.readLine();
	  
	  String st ="";
	  
	  
	  while((st = br.readLine())!= null){
		  String token[] = st.split("\t");
		  ST[sIndex] = new Symbol(token[1],Integer.parseInt(token[2]),Integer.parseInt(token[3]));
		  sIndex++;
	  }
	  
	  br = new BufferedReader(new FileReader(fLitTab));
	  br.readLine();
	  while((st = br.readLine())!= null){
		  String token[] = st.split("\t");
		  LT[lIndex] = new Literal(token[1],Integer.parseInt(token[2]));
		  lIndex++;
	  }
	  System.out.println("******Symbol Table******");
	  System.out.println("sIndex\tsName\tsAddr\tsLen");
	  for (int i=0;	i<sIndex; i++) {
		System.out.println(i+"\t"+ST[i].sname+"\t"+ST[i].addr+"\t"+ST[i].length);
	  }
	  
	  System.out.println("\n***Literal Table***");
	  System.out.println("lIndex\tlName\tlAddr");
	  for (int i=0;	i<lIndex; i++) {
			System.out.println(i+"\t"+LT[i].literal+"\t"+LT[i].addr);
	  }
	  
  }

  void passTwo(BufferedReader br) throws Exception {
    String st = "";
    int k;
    // FileWriter fw = new FileWriter("G://TE//SPOS//Pracs//SPOS_CODES//2 Pass Assembler//Pass Two//mcCode.txt");
    
    System.out.println("\n\n***Machine Code***\n");
    
    while((st = br.readLine())!= null){
    	String token[] = st.split(" ");
    	
    	//check AD
    	if(token[0].equals("AD")){
    		String pot_content = POT.get(Integer.parseInt(token[1]));
    		if (pot_content.equals("START")){
    			if(token.length==4)
    				loc = Integer.parseInt(token[3]);
    			else
    				loc = 0;
    		}
    		else if (pot_content.equals("END")){
    			loc+=lIndex;
    		}
    		else if (pot_content.equals("DC")){
    			System.out.println(loc+") 00 00 "+token[4]);
    			loc+=1;
    		}
    		else if (pot_content.equals("DS")){
    			loc+=Integer.parseInt(token[4]);
    		}
    	}
    	else{	//IS 
    		
    		//symbol
    		if(token[3].equals("S")){
    			int symAddr = ST[Integer.parseInt(token[4])].addr;
    			System.out.println(loc+") 0"+token[1]+" "+token[2]+" "+symAddr);
    			loc+=1;
    		}
    		else{
    			int litAddr = LT[Integer.parseInt(token[4])].addr;
    			System.out.println(loc+") 0"+token[1]+" "+token[2]+" "+litAddr);
    			loc+=1;
    		}
    		
    		
    	}
    	
    }
    //fw.close();
   
  }




  public static void main(String[] args) throws Exception {

    File f = new File("output.txt");
    BufferedReader br = new BufferedReader(new FileReader(f));

    PassTwo obj = new PassTwo();
    obj.initTables();
    obj.passTwo(br);
  }
}
