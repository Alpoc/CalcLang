/**
 * Created by DJ on 3/15/2017.
 */
public class Divide extends Node {
    public Divide() {
        super(Token.Type.DEVIDE);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case DEVIDE:
                break;
            default:
                throw new IllegalStateException("Expected DIVIDE, encountered: " + token);
        }
    }
}
