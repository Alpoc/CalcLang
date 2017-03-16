/**
 * Created by DJ on 3/15/2017.
 */
public class Times extends Node {
    public Times() {
        super(Token.Type.TIMES);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case TIMES:
                break;
            default:
                throw new IllegalStateException("Expected TIMES, encountered " + token);
        }
    }
}
