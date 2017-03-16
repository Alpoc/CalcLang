/**
 * Created by DJ on 3/15/2017.
 */
public class Plus extends Node {
    public Plus() {
        super(Token.Type.PLUS);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case PLUS:
                break;
            default:
                throw new IllegalStateException("Expected PLUS, encountered " + token);
        }
    }
}
