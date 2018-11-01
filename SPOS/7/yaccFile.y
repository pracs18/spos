%{

#include<stdio.h>
#include<string.h>


%}

%start line
%token KEYWARD VARIABLE COMMA SEMICOLON

%%

line:START|line START
;
START:KEY LIST SEMICOLON {printf("Valid Statement");}
;

KEY:KEYWARD
;
LIST:LIST COMMA VARIABLE|VARIABLE
;

%%

extern FILE *yyin;
void main()
{
    FILE *fp=fopen("dec.txt","r");
    yyin=fp;
    do
    {
    
        yyparse();
    
    }while(!feof(yyin));


}

int yyerror(char* msg)
{
   printf("\nDeclaration error");
}
int yywrap()
{
    return 1;
}
