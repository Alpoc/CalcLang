/**
 * Created by DJ on 3/15/2017.
 */
public class Equals extends Node {
    public Equals() {
        super(Token.Type.EQUALS);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case EQUALS:
                break;
            default:
                throw new IllegalStateException("Expected EQUALS, encountered " + token);
        }
    }
}
