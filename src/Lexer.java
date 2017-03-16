import java.io.*;
import java.util.*;

import static sun.management.Agent.error;

/**
 * Created by DJ on 3/15/2017.
 */
public class Lexer {
    public static String margin = "";

    private static String[] keywords = {
            "show", "msg", "newline", "string"};

    private static String[] builtInFunctions = {
            "sqrt", "sin", "cos",};

    // holds any number of tokens that have been put back
    private Stack<Token> stack;
    // the source of physical symbols
    private Scanner input;
    // one lookahead physical symbol
    private int lookahead;
    private String line = "";
    private static int lineIndex = 0;
    private static State state = State.START;
    private static String data = "";
    public Lexer(String fileName) {
        try {
            input = new Scanner(new FileReader(fileName));
        } catch (Exception e) {
            error("Problem opening file named [" + fileName + "]");
        }

        stack = new Stack<Token>();
        lookahead = 0;  // indicates no lookahead symbol present
    }// constructor
//-------------------------------------------------------------------------
    public static Token tokenize(char sym) {
        Token token;
        switch (state) {
            case START:
                switch (getType(sym)) {
                    case WHITESPACE:
                        break;

                    case LETTER:
                        data += sym;
                        state = State.VARIABLE;
                        break;
                    case OPERATOR:
                        switch (sym) {
                            case '(':
                                token = new Token(Token.Type.LPAREN);
                                data = "";
                                return token;
                            case ')':
                                token = new Token(Token.Type.RPAREN);
                                data = "";
                                return token;
                            case '=':
                                token = new Token(Token.Type.EQUALS);
                                data = "";
                                return token;
                            case '/':
                                token = new Token(Token.Type.DEVIDE);
                                data = "";
                                return token;
                            case '*':
                                token = new Token(Token.Type.TIMES);
                                data = "";
                                return token;
                            case '-':
                                token = new Token(Token.Type.MINUS);
                                data = "";
                                return token;
                            case '+':
                                token = new Token(Token.Type.PLUS);
                                data = "";
                                return token;
                            case '"':
                                state = State.STRING;
                                break;
                            default:
                                throw new IllegalStateException("Encountered illegal OPERATOR symbol: " + sym);
                        }
                        break;
                    case DIGIT:
                        data += sym;
                        state = State.DIGIT;
                        break;


                }
                break;
            case VARIABLE:
                switch(getType(sym)) {
                    case LETTER:
                    case DIGIT:
                        data += sym;
                        break;
                    default:
                        lineIndex--;
                    case WHITESPACE:
                        switch (data) {
                            case "SHOW":
                                token = new Token(Token.Type.SHOW);
                                data = "";
                                state = State.START;
                                return token;
                            case "MESSAGE":
                                token = new Token(Token.Type.MESSAGE);
                                data = "";
                                state = State.START;
                                return token;
                            case "INPUT":
                                token = new Token(Token.Type.INPUT);
                                data = "";
                                state = State.START;
                                return token;
                            case "NEWLINE":
                                token = new Token(Token.Type.NEWLINE);
                                data = "";
                                state = State.START;
                                return token;
                            default:
                        }
                        token = new Token(Token.Type.VARIABLE);
                        token.string = data;
                        data = "";
                        state = State.START;
                        return token;
                }
                break;
            case DIGIT:
                switch (getType(sym)) {
                    case DIGIT:
                        data += sym;
                        break;
                    case OPERATOR:
                        if (sym == '.') {
                            data += sym;
                            state = State.DECIMAL;
                            break;
                        }
                    default:
                        lineIndex--;
                    case WHITESPACE:
                        token = new Token(Token.Type.NUMBER);
                        token.value = (double)Integer.parseInt(data);
                        data = "";
                        state = State.START;
                        return token;
                }
                break;
            case DECIMAL:
                switch (getType(sym)) {
                    case DIGIT:
                        data += sym;
                        break;
                    default:
                        lineIndex--;
                    case WHITESPACE:
                        token = new Token(Token.Type.NUMBER);
                        token.value = Double.parseDouble(data);
                        data = "";
                        state = State.START;
                        return token;
                }
                break;
            case STRING:
                switch(sym) {
                    case '"':
                        token = new Token(Token.Type.STRING);
                        token.string = data;
                        data = "";
                        state = State.START;
                        return token;
                    default:
                        data += sym;
                        break;
                }
                break;
            default:
                throw new IllegalStateException("Invalid State: " + state);
        }
        return null;
    }
    public Token getNext() {
        char sym;
        Token token;
        while(input.hasNext() || lineIndex < line.length()) {
            token = tokenize((char)getNextSymbol());
            if (token != null) return token;
        }
        token = tokenize((char)getNextSymbol());
        if (token != null) return token;
        return new Token(Token.Type.EOF);
    } // End of getNext


    private int getNextSymbol() {
        if (lineIndex >= line.length()) {
            if (!input.hasNext()) return '\n';
            line = input.nextLine();
            lineIndex = 0;
            return '\n';
        }
        else {
            return line.charAt(lineIndex++);
        }
    }//End of getNextSymbol


    public void putBack(Token token) {
        System.out.println(margin + "put back token " + token.toString());
        stack.push(token);
    }

    public static CharType getType(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '.')
            return CharType.LETTER;
        else if (c >= '0' && c <= '9')
            return CharType.DIGIT;
        else if (c == '+' || c == '-' || c == '/' || c == '(' || c == ')' || c == '*' || c == '=' || c == '\"' || c == ';')
            return CharType.OPERATOR;
        else if (c == '\n' || c == '\t' || c == '\r' || c == ' ') return CharType.WHITESPACE;
        return CharType.ERROR;
    }//-----------------------------------------------------------------------
}
