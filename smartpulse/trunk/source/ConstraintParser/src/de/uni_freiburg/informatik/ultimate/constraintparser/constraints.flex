/* SMT-Lib lexer */
package de.uni_freiburg.informatik.ultimate.constraintparser;
import com.github.jhoenicke.javacup.runtime.Symbol;

/**
 * This is a autogenerated lexer for Boogie 2.
 * It is generated from Boogie.flex by JFlex.
 */
%%

%class Lexer
%public
%unicode
%implements com.github.jhoenicke.javacup.runtime.Scanner
%type com.github.jhoenicke.javacup.runtime.Symbol
%function next_token
%line
%column

%{
  private StringBuffer string = new StringBuffer();
  private MySymbolFactory symFactory;
  
  public void setSymbolFactory(MySymbolFactory factory) {
    symFactory = factory;
  }

  private Symbol symbol(int type) {
    return symFactory.newSymbol(yytext(), type, yyline+1, yycolumn, yyline+1, yycolumn+yylength());
  }
  private Symbol symbol(int type, String value) {
    return symFactory.newSymbol(value, type, yyline+1, yycolumn, yyline+1, yycolumn+yylength(), value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment   = "/*" ~"*/" 
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
SMTLetter = [:letter:]
SMTLetterDigit = {SMTLetter} | [.'_] | [:digit:]
Identifier = {SMTLetter} {SMTLetterDigit}*

Numeral = 0 | [1-9][0-9]*
ArithPred = <|<=|>|>=|=
%state STRING PATTERN

%%

<YYINITIAL>  {
  "and"           { return symbol(LexerSymbols.AND); }
  "or"            { return symbol(LexerSymbols.OR); }
  "not"           { return symbol(LexerSymbols.NOT); }
  "false"         { return symbol(LexerSymbols.FALSE); }
  "true"          { return symbol(LexerSymbols.TRUE); }
  
  "("             { return symbol(LexerSymbols.LPAR); }
  ")"             { return symbol(LexerSymbols.RPAR); }

  /* Numbers, Ids and Strings */
  {Identifier}                   { return symbol(LexerSymbols.ID, yytext()); }
  [\*\/]                         { return symbol(LexerSymbols.ARITH_MUL, yytext()); }
  [\+\-]                         { return symbol(LexerSymbols.ARITH_PLUS, yytext()); }
  {ArithPred}                    { return symbol(LexerSymbols.ARITH_PRED, yytext()); }
  
  {Numeral}                      { return symbol(LexerSymbols.NUMERAL, yytext()); }
  {Numeral}\.[0-9]*              { return symbol(LexerSymbols.DECIMAL, yytext()); }
 
  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

/* error fallback */
.|\n                             { return symbol(LexerSymbols.error, yytext()); }
<<EOF>>                          { return symbol(LexerSymbols.EOF); }
