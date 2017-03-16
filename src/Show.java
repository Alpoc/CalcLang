/**
 * Created by DJ on 3/15/2017.
 */
public class Show extends Node {
    public Show() {
        super(Token.Type.SHOW);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case SHOW:
                break;
            default:
                throw new IllegalStateException("Expected SHOW, encountered " + token);
        }
    }
}
