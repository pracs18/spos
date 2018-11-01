%option noyywrap
%{
#include<stdio.h>
#include "y.tab.h"
%}

%%
"int"|"float"|"char"|"boolean"|"String"   {return KEYWARD;}

[a-zA-Z]+[_a-zA-Z0-9]* {return VARIABLE;}

";" {return SEMICOLON;}

"," {return COMMA;}

. {}

%%

