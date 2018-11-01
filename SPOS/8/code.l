%option noyywrap
%{

#include<stdio.h>
#include"y.tab.h"

%}

%%



am|is|are|was|were|do|did|does|can|could|would|will|has|had|be|being|have|say|said|went|go|goes|eat|ate  {return VERB;}

slowly|quietly|angrily {return ADVERB;}

on|above|below|behind|in|between|from  {return PREPOSITION;}

and|but|if|then|else|or {return CONJUNCTION;}

his|her|their|my|your|its {return ADJECTIVE;}

he|she|we|you|they {return PRONOUN;}

[a-zA-Z]+ {return NOUN;}

%%


