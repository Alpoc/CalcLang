/**
 * Created by DJ on 3/15/2017.
 */
public class RParen extends Node {
    public RParen() {
        super(Token.Type.RPAREN);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case RPAREN:
                break;
            default:
                throw new IllegalStateException("Expected RPAREN, encountered " + token);
        }
    }

}
