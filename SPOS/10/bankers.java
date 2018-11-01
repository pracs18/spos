import java.io.*;
import java.util.*;
public class bankers {
	int req[],allo[][],max[][],avail[],need[][],n,m;
	boolean fin[],safe;
	int seq[],ind=0;
	Scanner sc =new Scanner(System.in);
	public bankers()
	{
		
		System.out.println("Enter the number of process :");
		 n=sc.nextInt();
		System.out.println("Enter the type of resources :");
		 m=sc.nextInt();
		    allo=new int[n][m];
			max=new int[n][m];
			avail=new int[m];
			fin=new boolean[n];
			seq=new int[n];
			System.out.println("Enter the MAX matrics :");
			 for(int i=0;i<n;i++)
			 {
				 for(int j=0;j<m;j++)
				 {
					// System.out.println("Enter the max["+i+"]"+"["+j+"]");
					 max[i][j]=sc.nextInt();
				 }
			 }
			 System.out.println("Enter the AVAIL matrics :");
			 for(int j=0;j<m;j++)
			 {
				 avail[j]=sc.nextInt();
			 }
			 System.out.println("Enter the ALLO matrics :");
			 for(int i=0;i<n;i++)
			 {
				 for(int j=0;j<m;j++)
				 {
					// System.out.println("Enter the allo["+i+"]"+"["+j+"]");
					 allo[i][j]=sc.nextInt();
				 }
			 }
			 need= new int[n][m];
			 for(int i=0;i<n;i++)
			 {
				 for(int j=0;j<m;j++)
				 {
					 need[i][j]=max[i][j]-allo[i][j];
				 }
			 }
			 System.out.println(" ");
			 System.out.println("NEED matrics is :");
			 for(int i=0;i<n;i++)
			 {
				 for(int j=0;j<m;j++)
				 {
					 System.out.print(need[i][j]);
				 }
				 System.out.println("");
			 }
			 
			 req=new int[m];
	}
	public void bank()
	{
		System.out.println("AVAIL matrics is :");
		
		 for(int j=0;j<m;j++)
		 {
			 System.out.print(avail[j]);
		 }
		 
		System.out.println("Enter the process you want to request: ");
		int p = sc.nextInt();
		System.out.println("Enter the request: ");
		for(int i=0;i<m;i++)
		{
			req[i]=sc.nextInt();
		}
		if(LE(p,req,need) && PE(req,avail))
		{
			for(int i=0;i<m;i++)
			{
				avail[i]=avail[i]-req[i];
				need[p][i]=need[p][i]-req[i];
				allo[p][i]=allo[p][i]+req[i];
				
			}
			
		}
		System.out.println("AVAIL matrics is :");
		
		 for(int j=0;j<m;j++)
		 {
			 System.out.print(avail[j]);
		 }
		 
		 System.out.println("New NEED matrics is :");
		 for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				 System.out.print(need[i][j]+" ");
			 }
			 System.out.println("");
		 }
		 safe= safty();
		 
	     if(safe)
	     {
	    	 System.out.println("System is Safe :");
	    	 System.out.println("Safe sequence is :");
	    	 for(int i=0;i<ind;i++)
	    	 {
	    		 System.out.print(seq[i] + "  ");
	    	 }
	      
	     }
	     else
	     { System.out.println("System is Not Safe :");
	     for(int i=0;i<m;i++)
			{
				avail[i]=avail[i]+req[i];
				need[p][i]=need[p][i]+req[i];
				allo[p][i]=allo[p][i]-req[i];
				
			}
	     
	     }
			 
	}
	public boolean LE(int a,int c[],int b[][])
	{
		 for(int i=0;i<m;i++)
		 {
			 if(c[i]>b[a][i])
			 {
				 return false;
			 }
			 
		 }
		 return true;
	}
	public boolean LE_Work(int a,int c[],int b[][])
	{
		 for(int i=0;i<m;i++)
		 {
			 if(b[a][i]>c[i])
			 {
				 return false;
			 }
			 
		 }
		 return true;
	}
	public boolean PE(int available[],int req[])
	{
		 for(int i=0;i<m;i++)
		 {
			 if(available[i]>req[i])
			 {
				 return false;
			 }
			 
		 }
		 return true;
	}
	
	public boolean safty()
	{
		int work[]= new int[m];
		for(int i= 0;i<m;i++)
		{
			work[i]=avail[i];
		}
		
		int finished[],flag;
		finished = new int[n];
		
		for(int i= 0;i<n;i++)
		{
			finished[i]=0;
		}
		while(true)
		{
			flag=0;
			int x=0;
			for(int i=0;i<n;i++)
			{
				if(finished[i]==0 && LE_Work(i,work,need))
				{
					
					flag=1;
					finished[i]=1;
					seq[ind++]=i;
					for(int j=0;j<m;j++)
					{
						work[j]+=allo[i][j];
						
					}
					
				}
				
			}
			if(flag==0)
			{
				break;
			}
		}
		
		for(int i=0;i<n;i++)
		{
			if(finished[i]==0)
			{
				return false;
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
      bankers obj=new bankers();
      obj.bank();
     
	}

}
/*output:
pccoe@ubuntu:~/TECOB224$ javac bankers.java
pccoe@ubuntu:~/TECOB224$ java bankers
Enter the number of process :
5
Enter the type of resources :
4
Enter the MAX matrics :
0 0 1 2
1 7 5 0
2 3 5 6
0 6 5 2
0 6 5 6
Enter the AVAIL matrics :
1 5 2 0
Enter the ALLO matrics :
0 0 1 2
1 0 0 0
1 3 5 4
0 6 3 2
0 0 1 4
 
NEED matrics is :
0000
0750
1002
0020
0642
AVAIL matrics is :
1520Enter the process you want to request: 
1
Enter the request: 
0 4 2 0
AVAIL matrics is :
1100New NEED matrics is :
0 0 0 0 
0 3 3 0 
1 0 0 2 
0 0 2 0 
0 6 4 2 
System is Safe :
Safe sequence is :
0  2  3  4  1  pccoe@ubuntu:~/TECOB224$ ^C
pccoe@ubuntu:~/TECOB224$ 

