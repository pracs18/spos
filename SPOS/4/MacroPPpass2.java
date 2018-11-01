import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class MNT {
	String macroName;
	int MDTP;

	MNT(String s, int k) {
		this.macroName = s;
		this.MDTP = k;
	}
}

class ALA {
	String formArg;
	String actArg;

	ALA(String f) {
		this.formArg = f;
	}
}

class MacroPPpass2 {

	String MDT[] = new String[50];
	ALA ala[] = new ALA[50];
	MNT mnt[] = new MNT[50];
	int mntp;
	int mdtp;
	int alap;

	MacroPPpass2() {
		mntp = 0;
		mdtp = 0;
		alap = 0;
	}

	int searchALA(String s) {
		for (int i = 0; i < alap; i++) {
			if (ala[i].formArg.equals(s))
				return i;
		}
		return -1;
	}
	
	void initTables() throws Exception {
		File fMNT = new File("MNT.txt");
		File fMDT = new File("MDT.txt");
		File fALA = new File("ALA.txt");

		BufferedReader br = new BufferedReader(new FileReader(fMNT));
		br.readLine();
		String st = "";

		while ((st = br.readLine()) != null) {
			String token[] = st.split("\t");
			mnt[mntp] = new MNT(token[1], Integer.parseInt(token[2]));
			mntp++;
		}

		br = new BufferedReader(new FileReader(fMDT));
		while ((st = br.readLine()) != null) {
			String token[] = st.split("\t");
			MDT[mdtp] = new String(token[1]);
			mdtp++;
		}

		br = new BufferedReader(new FileReader(fALA));
		br.readLine();
		while ((st = br.readLine()) != null) {
			ala[alap] = new ALA(st);
			alap++;
		}
	/*	
		// printing
//		System.out.println("------------------------------INPUT---------------------------");
//		System.out.println("\n*******\tMNT\t********");
//		System.out.println("Index\tMacro Name\tMDTindex");
//		for (int i = 0; i < mntp; i++) {
//			System.out.println(i + "\t" + mnt[i].macroName + "\t" + mnt[i].MDTP);
//		}
//
//
//		System.out.println("\n*****\tMDT\t******");
//		for (int i = 0; i < mdtp; i++) {
//			System.out.println(i + "\t" + MDT[i]);
//		}
//
//		System.out.println("\n****\tALA\t****");
//		System.out.println("FormalArgs\tActArgs");
//		for (int i = 0; i < alap; i++) {
//			System.out.println(ala[i].formArg + "\t");
//		}
		*/
	}
	
	int searchMNT(String key){
		for (int i=0; i<mntp; i++){
			String token[] = mnt[i].macroName.split(" ");
			if(token[0].equals(key)){
				return mnt[i].MDTP;
			}
		}
		return -1;
	}
	
	void passTwo(BufferedReader br) throws Exception {
	    String st = "";
	    int k;
	    String arr[];
	    
	    while((st = br.readLine())!= null){
	    	
	    	String token[] = st.trim().split(" ");
	    	int j = searchMNT(token[0]);
	    	if(j!=-1){
    			arr = MDT[j].trim().split(" ");
	    		for (int i=1; i<arr.length; i++){
	    			k = Integer.parseInt(arr[i].substring(1));
	    			ala[k].actArg = token[i];
	    		}
	    		while(true){
	    			j++;
	    			arr = MDT[j].trim().split(" ");
	    			String repString = "";
	    			if(!arr[0].equals("MEND")){
	    				for(int i=0; i<arr.length; i++){
		    				if(arr[i].charAt(0) == '#'){
		    					k = Integer.parseInt(arr[i].substring(1));
		    					repString+=ala[k].actArg;
		    				}
		    				else
		    				{
		    					repString+=arr[i];
		    					repString+=" ";
		    				}
		    			}
	    				System.out.println(repString);
	    			}
	    			else
	    				break;
	    			
	    		}
	    
	    	}
	    	else
	    	{
	    		System.out.println(st);
	    	}
	    	
	    }
	    
	    
	    
	}

	public static void main(String[] args) throws Exception {
		MacroPPpass2 obj = new MacroPPpass2();
		File f = new File("macp1Output.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		obj.initTables();
		obj.passTwo(br);
	
	}

}
