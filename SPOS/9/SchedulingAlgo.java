import java.util.Scanner;


public class SchedulingAlgo {

	
	//First In First Out Algorithm
	public static void FIFO(int[] burst,int[] wait,int[] arrival,int[] turnAround) 
	{
		int n=burst.length;
		int averageWaitTime=0,averageTurnAroundTime=0,previousTotalBurstTime=0;
		wait[0]=0;
		for (int i = 1; i <n; i++) 
		{
			previousTotalBurstTime=0;
			for (int j = 0; j<i; j++)
			{
				previousTotalBurstTime=previousTotalBurstTime+burst[j];
				System.out.println(previousTotalBurstTime);
			}
		    
			wait[i]=previousTotalBurstTime-arrival[i];
		    averageWaitTime=averageWaitTime+wait[i];
		}
		
		for (int i = 0; i <n; i++) 
		{
		   turnAround[i]=wait[i]+burst[i];
		   averageTurnAroundTime=averageTurnAroundTime+turnAround[i];
		}
		
		System.out.println("\nProcess\t\tArrivalTime\tWaitTime\tBurstTime\tTurnAroundTime");
		for (int i = 0; i < n; i++)
		{
		  System.out.println("\nP"+i+"\t\t"+"  "+arrival[i]+"\t\t"+"  "+wait[i]+"\t\t"+"  "+burst[i]+"\t\t"+"  "+turnAround[i]);
			
		}
		
		System.out.println("\n\nAverage Waiting Time:"+(averageWaitTime/n));
		System.out.println("\n\nAverage Turn Around Time"+(averageTurnAroundTime/n));
		
		
	}
	
	//Shortest Job First
	public static void SJF(int[] burst,int[] wait,int[] arrival,int[] turnAround,int[] p) 
	{
		int n=burst.length;
		int runTime[]=new int[n];
		int averageWaitTime=0,averageTurnAroundTime=0;
		
		for (int i = 0; i <n; i++) 
		{
		   runTime[i]=burst[i];	
		}
		
		int complete=0,t=0,minm=Integer.MAX_VALUE;
		int shortest=0,finish_time;
		boolean check=false;
		
		while (complete!=n)
		{
				for (int i = 0; i < n; i++)
				{
					if(arrival[i]<=t && runTime[i]<minm && runTime[i] > 0)
					{
						minm=runTime[i];
						shortest=i;
						check=true;
					}
					
				}
				if(check==false)
				{
					t++;
					continue;
				}
				
				runTime[shortest]--;
				
				minm=runTime[shortest];
				
				if(minm==0)
				{
					minm=Integer.MAX_VALUE;
				}
				
				if(runTime[shortest]==0)
				{
					complete++;
					
					
					finish_time=t+1;
					
					
					wait[shortest]=finish_time-burst[shortest]-arrival[shortest];
					
					if(wait[shortest]<0)
					{
						wait[shortest]=0;
					}
				}
				t++;
			
			
		}
		
		for (int i = 0; i < n; i++) 
		{
		
			averageWaitTime=averageWaitTime+wait[i];
			turnAround[i]=burst[i]+wait[i];
			averageTurnAroundTime=averageTurnAroundTime+turnAround[i];
		}
		
		
		
		System.out.println("\nProcess\t\tArrivalTime\tWaitTime\tBurstTime\tTurnAroundTime");
		for (int i = 0; i < n; i++)
		{
		  System.out.println("\nP"+p[i]+"\t\t"+"  "+arrival[i]+"\t\t"+"  "+wait[i]+"\t\t"+"  "+burst[i]+"\t\t"+"  "+turnAround[i]);
			
		}
		
		System.out.println("\n\nAverage Waiting Time:"+(averageWaitTime/n));
		System.out.println("\n\nAverage Turn Around Time"+(averageTurnAroundTime/n));

	   
		
	}
	
	
	//Priority Based Algorithm
	public static void PRIORITY(int[] burst,int[] wait,int[] arrival,int[] turnAround,int[] priority,int[] p)
	{
		int n=burst.length;
		

		   int temp,pos;
		   
		   //sort according to priority
		   for(int i=0;i<n;i++)
		   {
			   pos=i;
			   for(int j=i+1;j<n;j++)
			   {
				   if(priority[j]<priority[pos])
				   { 
					   pos=j;
					 
				   }
				   
			   }
			   temp=priority[i];
			   priority[i]=priority[pos];
			   priority[pos]=temp;
			   
			   temp=burst[i];
			   burst[i]=burst[pos];
			   burst[pos]=temp;
			   
			   
			   temp=p[i];
			   p[i]=p[pos];
			   p[pos]=temp;
		   }
		   
		   int averageWaitTime=0,averageTurnAroundTime=0;
			wait[0]=0;
			for (int i = 1; i <n; i++) 
			{
			  wait[i]=wait[i-1]+burst[i-1];
			  averageWaitTime=averageWaitTime+wait[i];
			}
			
			for (int i = 0; i <n; i++) 
			{
			   turnAround[i]=wait[i]+burst[i];
			   averageTurnAroundTime=averageTurnAroundTime+turnAround[i];
			}
			
			System.out.println("\nProcess\t\tPriority\t\tArrivalTime\tWaitTime\tBurstTime\tTurnAroundTime");
			for (int i = 0; i < n; i++)
			{
			  System.out.println("\nP"+p[i]+"\t\t"+"  "+priority[i]+"\t\t"+"  "+arrival[i]+"\t\t"+"  "+wait[i]+"\t\t"+"  "+burst[i]+"\t\t"+"  "+turnAround[i]);
				
			}
			
			System.out.println("\n\nAverage Waiting Time:"+(averageWaitTime/n));
			System.out.println("\n\nAverage Turn Around Time"+(averageTurnAroundTime/n));
	  
		   
		
		
	}
	
	
	//ROUND_ROBBIN
	public static void ROUND_ROBBIN(int[] burst,int[] wait,int[] run,int[] p,int quantum)
	{
		
		int n=burst.length;
		int rp=n,i=0,time=0;
		while(rp!=0)
		{
			if(run[i]>quantum)
			{
				run[i]=run[i]-quantum;
				time+=quantum;
				System.out.println("|P["+(i+1)+"]|");
				System.out.println(time);
			}
			else if(run[i]<=quantum && run[i]>0)
			{
				time+=run[i];
				run[i]=run[i]-run[i];
				System.out.println("|P["+(i+1)+"]|");
				rp--;
				System.out.println(time);
				
			}
			i++;
			if(i==n)
			{
				i=0;
			}
		}
	}
	
	public static void main(String[] args)
	{
	
		Scanner s=new Scanner(System.in);
		
		String ans;
		int n,choice;
		
		System.out.println("\nEnter the number of procesess:");
		n=s.nextInt();
		
		int p[]=new int[n];
		int burst[]=new int[n];
		int wait[]=new int[n];
		int arrival[]=new int[n];
		int turnAround[]=new int[n];
		int priority[]=new int[n];
		int run[]=new int[n];
		
		System.out.println(n);
		System.out.println("Entre the burst time of "+n+" processes:");
		for (int i = 0; i < n; i++) 
		{
		   burst[i]=s.nextInt();
		   run[i]=burst[i];
		   p[i]=i+1;
		   
		}
		
		System.out.println("Enter the arrival time of "+n+" processes:");
		for (int i = 0; i < n; i++) 
		{
		   arrival[i]=s.nextInt();	
		}
		
		
		do {
			   System.out.println("\n======================================================");
			   System.out.println("\tSCHEDULING ALGORITHMS");
			   System.out.println("\n======================================================");
			   System.out.println("\n1.FIFO\n2.SJF\n3.PRIORITY\n4.ROUND-ROBBIN");
			   System.out.println("\n======================================================");
               System.out.println("\nEntre your choice:");
               choice=s.nextInt();
               
               switch (choice) 
               {
               case 1:	
            	   		FIFO(burst,wait,arrival,turnAround);
                      	break;
               case 2:	
            	   		SJF(burst,wait,arrival,turnAround,p);
               		  	break;
               case 3:
            	   		System.out.println("\nEntre the priorities of 5 processes:");
            	   		for (int i = 0; i < n; i++) 
            	   		{
            	   			priority[i]=s.nextInt();
            	   		}
            	   		PRIORITY(burst, wait, arrival, turnAround, priority,p);
            	   		break;
               
               case 4:
            	   		System.out.println("\nEnter the time quantum:");
            	   		int quantum=s.nextInt();
            	   		ROUND_ROBBIN(burst,wait,run,p,quantum);
               			break;
                      
                
               
			   }
               
			
			
			
			
			
			
			
			System.out.println("\nDo you wanr to continue?");
			ans=s.next();
		} while (ans.equals("yes"));

	}

}
