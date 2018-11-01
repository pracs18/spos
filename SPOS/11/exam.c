//example2.c
#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/types.h>
void main()
{
	int c,pid,pid1, id, status;
	char *argv[] = {"ls", "-l", "-R", "-a", NULL};
	printf("1.ps\n2.fork\n3.wait\n4.execv\n5.join\n");
	scanf("%dEnter your choice: ",&c);

	switch(c)
	{
		case 1:
			printf("\n#ps#\n");
			system("ps");
			break;
		case 2:
			printf("\n**********fork**********\n");
			pid=fork();
			printf("Hii\n");
			if(pid>0)
			{
				printf("Parent id = %d Child id=%d\n",getpid(),pid);
			}
			else if(pid==0)
			{
				printf("Parent = %d Child = %d\n",getppid(),getpid());
			}
			break;
	   case 3:
	
			//wait
			printf("\n**********Wait**********\n");
			pid=fork();
			printf("Hii\n");
			if(pid>0)
			{
				printf("Parent id = %d Child id=%d\n",getpid(),pid);
				//id = wait(&status);
				printf("waiting process terminated%d",id);
			}
			else if(pid==0)
			{
				printf("Parent = %d Child = %d\n",getppid(),getpid());
				
			}
			break;
	case 4:		
		printf("\n**********Execv**********\n");
				char* prog[3];
	prog[0] = "firefox";
	prog[1] = "http://www.yahoo.com";
	prog[2] = '\0';
	execvp(prog[0], prog);
			break;	

	case 5: pid = fork();
				pid = fork();
				if(pid<0)
				{
					printf("Error occured in fork()");
				}
				else 		//child process will get pid = 0 
				{
					execl("/usr/bin/join","join","a.txt","b.txt",NULL);		//execute join command
				}
		break;

	}
}







