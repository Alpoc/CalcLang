/**
 * Created by DJ on 3/15/2017.
 */
public class Number extends Node {
    public Number(){
        super(Token.Type.NUMBER);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case NUMBER:
                this.value = token.value;
                break;
            default:
                throw new IllegalStateException("Expected NUMBER, encountered " + token);
        }
    }
}
