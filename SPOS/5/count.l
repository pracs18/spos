%{

#include<stdio.h>
int ccount=0;
int wcount=0;
int lcount=0;

%}

%%

[^ \t\n]+ {wcount++;ccount=ccount+yyleng;} // yyleng is the length of the matched string
[\n] {lcount++;}

%%

int main()
{
	FILE *fp;
	fp=fopen("data.txt","r");
	if(fp==NULL)
		printf("File cannot be opened...");
	else
	{	
	        yyin=fp;    //the input stream pointer (i.e it points to an input file which is to be scanned or tokenised), however the default input of default main() is stdin .
                              yylex();    //It is called to invoke the lexer (or scanner) and each time yylex() is called, the scanner continues processing the input from where it last left off.
                              printf("\nNo. of characters=%d\n",ccount);
	        printf("\nNo. of words=%d\n",wcount);
	        printf("\nNo. of lines=%d\n",lcount);
	}
}

int yywrap()
{
	return 1;
}


/*

yyin :- the input stream pointer (i.e it points to an input file which is to be scanned or tokenised), however the default input of default main() is stdin .

yylex() :- implies the main entry point for lex, reads the input stream generates tokens, returns zero at the end of input stream . It is called to invoke the lexer (or scanner) and 
               each time yylex() is called, the scanner continues processing the input from where it last left off.
yytext :- a buffer that holds the input characters that actually match the pattern (i.e lexeme) or say a pointer to the matched string .

yyleng :- the length of the lexeme (matched string)

yylval :- contains the token value .

yyval :- a local variable .*

yyout :- the output stream pointer (i.e it points to a file where it has to keep the output), however the default output of default main() is stdout .
yywrap() :- it is called by lex when input is exhausted (or at EOF). default yywrap always return 1.
*/



