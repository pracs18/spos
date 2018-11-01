%{

#include<stdio.h>

%}

%%
"private"|"public"|"protected"|"System"|"out"|"Class"|"java"|"lang"|"static"|"new"|"main"|"import"|"void"|"return"|"println" {printf("\n%s\t:Keyward",yytext);}

[@|$|#|*|&][a-zA-Z0-9]+  {printf("\n%s\t:Invalid identifier starting with sysmbol",yytext);}

"&&"|"||"|"!"    {printf("\n%s\t:Logical Operator",yytext);}

"&"|"|"|"~"|"^"|">>"|"<<"  {printf("\n%s\t:Bitwise Operator",yytext);}

"=="|">="|"<="|">"|"<"    {printf("\n%s\t:Relational  Operator",yytext);}

"=" {printf("\n%s\t:Asignment Operator",yytext);}

"//"[^\n]* {printf("\n%s\t:Comment",yytext);}

[0-9]+\.[0-9]   {printf("\n%s\t:Decimal Value",yytext);}

"int"|"char"|"float"|"String"|"boolean" {printf("\n%s\t:Datatype",yytext);}

\'[_a-zA-Z]+[a-zA-Z0_9]*\' {printf("\n%s\t:Character Constant",yytext);}

\"[_a-zA-Z]+[a-zA-Z0_9]*\" {printf("\n%s\t:String Constant",yytext);}

"+"|"-"|"*"|"/"  {printf("\n%s\t:Arithmatic Operator",yytext);}

"("|")"|"["|"]"|"{"|"}"  {printf("\n%s\t:Bracket",yytext);}

[a-zA-Z][_a-zA-Z0-9]*"()" {printf("\n%s\t:function",yytext);}


[a-zA-Z]+[_a-zA-Z0-9]* {printf("\n%s\t:Identifier",yytext);}

[0-9][a-zA-Z0-9]+  {printf("\n%s\t:Invalid identifier starting with number",yytext);}

";" {printf("\n%s\t:delemiter",yytext);}

"." {printf("\n%s\t:Seperator",yytext);}

[0-9]* {printf("\n%s\t:Integer",yytext);}

[/][*][^*]*[*]+([^*/][^*]*[*]+)*[/]   {printf("%s:\t multi line Comment\n",yytext);}

%%

int main()
{
  FILE *fp;
  fp=fopen("abc.java","r");
  yyin=fp;
  yylex();
 
 }
 int yywrap()
 {
   return 1;
 }
