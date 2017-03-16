/**
 * Created by DJ on 3/15/2017.
 */
public class Minus extends Node {
    public Minus() {
        super(Token.Type.MINUS);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case MINUS:
                break;
            default:
                throw new IllegalStateException("Expected MINUS, encountered " + token);
        }
    }
}
