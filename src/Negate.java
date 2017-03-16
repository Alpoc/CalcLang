/**
 * Created by DJ on 3/15/2017.
 */
public class Negate extends Node {
    public Negate () {
        super(Token.Type.NEGATE);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case MINUS:
                break;
            default:
                throw new IllegalStateException("Expected NEGATE, encountered: " + token);
        }
    }
}
