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


class Symbol{
	String sname;
	int addr;
	int length;

	Symbol(String name){
		this.sname = name;

	}
	void setLen(int l){
		this.length = l;
	}

}

class Literal {
	String literal;
	int addr;

}


public class Assembler {
	HashMap<String, Integer> POT = new HashMap <String,Integer>();
	HashMap<String, Integer> MOT = new HashMap <String,Integer>();
	HashMap<String, Integer> BCCODE = new HashMap <String,Integer>();
	HashMap<String, Integer> RT = new HashMap <String,Integer>();

	Symbol ST[] = new Symbol[20];
	Literal LT[] = new Literal[20];
	int sIndex,lIndex,loc;


	Assembler(){
		sIndex = 0;
		lIndex = 0;
		loc = 0;

		//imperative
		MOT.put("STOP",  0);
		MOT.put("MOVER", 1);
		MOT.put("MOVEM", 2);
		MOT.put("ADD",   3);
		MOT.put("SUB",   4);
		MOT.put("MUL",   5);
		MOT.put("DIV",   6);
		MOT.put("BC",    7);

		//assembler dir
		POT.put("START", 1);
		POT.put("END",   2);
		POT.put("EQU",   3);
		POT.put("LTORG", 4);
		POT.put("ORIGIN",5);

		//bccode
		BCCODE.put("LT", 1);
		BCCODE.put("LE", 2);
		BCCODE.put("GT", 3);
		BCCODE.put("GE", 4);
		BCCODE.put("EQ", 5);
		BCCODE.put("ANY",6);

		//Registers
		RT.put("AREG", 1);
		RT.put("BREG", 2);
		RT.put("CREG", 3);
		RT.put("DREG", 4);
	}

	int search(String s){
		for(int i = 0; i<sIndex; i++){
			if(ST[i].sname.equals(s))
				return i;
		}
		return -1;
	}
	void printTables() throws Exception{

		FileWriter fw = new FileWriter("symbolTable.txt");
		fw.write("index\tsname\taddress\tlength\n");
		for (int i=0; i< sIndex; i++){
			fw.write(i+"\t"+ST[i].sname+"\t"+ST[i].addr+"\t"+ST[i].length+"\n");
		}
		fw.close();

		fw = new FileWriter("literalTable.txt");
		fw.write("lindex\tlname\taddress\n");
		for (int i=0; i<lIndex; i++){
			fw.write(i+"\t"+LT[i].literal+"\t"+LT[i].addr+"\n");
		}
		fw.close();


	}
	void passOne(BufferedReader br) throws Exception {
		String st = "";
		int k;
		FileWriter fw = new FileWriter("output.txt");

		while((st = br.readLine())!= null){

			String token[] = st.split(" ");


			if(token.length>4){
				System.out.println("invalid");
			}
			else{
				if (POT.containsKey(token[0]))
				{
					int val = POT.get(token[0]);

					if(token[0].equals("START")) {
						if(token.length == 2) {
							loc = Integer.parseInt(token[1]);
						}
						else if (token.length>2) {
							System.out.println("Error");
							fw.write("Error");
						}
						System.out.println("AD "+val+" C "+loc);
						fw.write("AD "+val+" C "+loc+"\n");
					}

					else if(token[0].equals("END")) {
						for (int i=0; i<lIndex; i++) {
							LT[i].addr = loc;
							loc++;
						}
						System.out.println("AD "+val);
						fw.write("AD "+val+"\n");
					}
				}
				else if (MOT.containsKey(token[0])) {
					int val = MOT.get(token[0]);
					int reg = 0;
					char ch;


					if(RT.containsKey(token[1]))
						reg = RT.get(token[1]);

					if(token[2].charAt(0) == '=') {
						ch = 'L';
						LT[lIndex] = new Literal();
						LT[lIndex].literal = token[2].substring(1);
						k = lIndex;
						lIndex++;
					}
					else {
						ch = 'S';
					 	k = search(token[2]);
						if (k == -1){
							ST[sIndex] = new Symbol(token[2]);
							k = sIndex;
							sIndex++;
						}
					}
						System.out.println("IS "+val+" "+reg+" "+ch+" "+k);
						fw.write("IS "+val+" "+reg+" "+ch+" "+k+"\n");
						loc++;
				}
				else {
					if (token[1].equals("DS") || token[1].equals("DC")) {

						int len = Integer.parseInt(token[2]);
						k = search(token[0]);

						if (k == -1){
							ST[sIndex] = new Symbol(token[2]);
							k = sIndex;
							sIndex++;
						}

						if(token[1].equals("DS"))	{
							System.out.println("AD 7 - C "+token[2]);
							fw.write("AD 7 - C "+token[2]+"\n");
							ST[k].setLen(len);
							ST[k].addr = loc;
							loc+=len;
						}
						else	{
							System.out.println("AD 6 - C "+token[2]);
							fw.write("AD 6 - C "+token[2]+"\n");
							ST[k].setLen(1);
							ST[k].addr = loc;
							loc+=1;
						}

					}

				}
			}
		}
		fw.close();
		printTables();
	}

	public static void main(String[] args) throws Exception {

		//System.out.print("Enter file name: ");
		//BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
		//String fname = inp.readLine();
		File f = new File("input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));

		Assembler obj = new Assembler();
		obj.passOne(br);

	}
}
