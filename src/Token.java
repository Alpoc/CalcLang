/**
 * Created by DJ on 3/15/2017.
 */
public class Token {
    public enum Type {
        STATEMENTS, STATEMENT, SHOW, MESSAGE, INPUT, STRING, NEWLINE,
        EXPRESSION, TERM, FACTOR,
        BIF, MINUS, PLUS, TIMES, DEVIDE, NEGATE, EQUALS, LPAREN, RPAREN,
        NUMBER, VARIABLE, SEMICOLEN, EOF;
    }
    public double value;
    public String string;
    public Type type;
    public Token(Type type) {
        this.type = type;
    }
}
