%{

#include<stdio.h>

%}

%token NOUN VERB PRONOUN ADJECTIVE ADVERB PREPOSITION CONJUNCTION
%%
start:sentence start|
         sentence;

sentence:simple_sentence {printf("\nThis is a simple sentnece\n\n");} 
                |compound_sentence {printf("\nThis is a compound sentnece\n\n");} ;       

simple_sentence:Subject Verb Object     |    Subject Verb Object Pre_Phrase;
                              
Subject:NOUN | PRONOUN  | ADJECTIVE Subject;

Verb:  VERB| ADVERB VERB|Verb VERB;

compound_sentence:simple_sentence CONJUNCTION simple_sentence |  compound_sentence CONJUNCTION simple_sentence ;

Object:NOUN | ADJECTIVE Object;

Pre_Phrase:PREPOSITION NOUN;

%%

extern FILE *yyin;

void main()
{
                      FILE *fp=fopen("sentence.txt","r");
                      yyin=fp;
                      do
                      {
                          yyparse();
                       
                       }while(!feof(yyin));   

}                     

int yyerror(char* msg)
{
  printf("\n Syntax Error");
}

int yywrap()
{

   return 1;

}
