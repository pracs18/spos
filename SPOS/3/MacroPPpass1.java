
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class MNT{
	String macroName;
	int MDTP;
	MNT(String s, int k){
		this.macroName = s;
		this.MDTP = k;
	}
}

class ALA{
	String formArg;
	String actArg;
	
	ALA(String f){
		this.formArg = f;
	}
}

class MacroPPpass1{
	
	String MDT[] = new String[50];
	ALA ala[] = new ALA[50];
	MNT mnt[] = new MNT[50];
	int mntp;
	int mdtp;
	int alap;
	
	MacroPPpass1(){
		mntp = 0;
		mdtp = 0;
		alap = 0;
	}
	
	int searchALA(String s, int start){
		for(int i=start; i< alap; i++){
			if(ala[i].formArg.equals(s))
				return i;
		}
		return -1;
	}
	
	void printTables() throws Exception
	{
		FileWriter fw = new FileWriter("MNT.txt");
		System.out.println("\n*******\tMNT\t********");
		System.out.println("Index\tMacro Name\tMDTindex");
		fw.write("Index\tMacro Name\tMDTindex\n");
		for (int i=0; i < mntp; i++){
			System.out.println(i+"\t"+mnt[i].macroName+"\t"+mnt[i].MDTP);
			fw.write(i+"\t"+mnt[i].macroName+"\t"+mnt[i].MDTP+"\n");
		}
		
		fw.close();
		
		fw = new FileWriter("MDT.txt");
		System.out.println("\n*****\tMDT\t******");
		for (int i=0; i < mdtp; i++){
			System.out.println(i+"\t"+MDT[i]);
			fw.write(i+"\t"+MDT[i]+"\n");
		}
		fw.close();
		
		fw = new FileWriter("ALA.txt");
		System.out.println("\n****\tALA\t****");
		System.out.println("FormalArgs\tActArgs");
		fw.write("FormalArgs\tActArgs\n");
		for (int i=0; i < alap; i++){
			System.out.println(ala[i].formArg+"\t");
			fw.write(ala[i].formArg+"\n");
		}
		fw.close();
	}
	
	void scanFile() throws Exception{
		
		String st = "";
		String macStr = "";
		File f = new File("macp1Input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		FileWriter fw = new FileWriter("macp1Output.txt");
		
		while((st = br.readLine())!= null){

			String token[] = st.split(" ");
			
			if (token[0].equals("MACRO")){
			
				String tok[] = br.readLine().split(" ");
				String instr = tok[0];
				int start = alap;
				for(int i=1; i<tok.length;i++){
					ala[alap] = new ALA(tok[i]);
					instr = instr.concat(" #"+alap);
					alap++;
				}
				mnt[mntp] = new MNT(instr, mdtp);
				MDT[mdtp] = new String(instr);
				mdtp++;
				mntp++;
				
				while(!(macStr = br.readLine()).equals("MEND")){
					tok = macStr.split(" ");
					instr = "";
					for(int i=0; i<tok.length; i++){
						
						if(tok[i].charAt(0) == '&'){
							int p = searchALA(tok[i],start);
							instr = instr.concat("#"+p+" ");
						}
						else
							instr += tok[i]+" ";
					}
					MDT[mdtp] = new String(instr);
					mdtp++;
				}
				MDT[mdtp] = new String("MEND");
				mdtp++;
				
			}
			else
				fw.write(st+"\n");
			
		}
		fw.close();
		
	}
	
	public static void main(String[] args) throws Exception{
		MacroPPpass1 obj = new MacroPPpass1();
		obj.scanFile();
		obj.printTables();
	}

    
}
