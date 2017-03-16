/**
 * Created by DJ on 3/15/2017.
 */
public class LParen extends Node {
    public LParen() {
        super(Token.Type.LPAREN);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case LPAREN:
                break;
            default:
                throw new IllegalStateException("Expected LPAREN, encountered " + token);
        }
    }
}
