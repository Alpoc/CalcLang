/**
 * Created by DJ on 3/15/2017.
 */
public class Input extends Node {
    public Input() {
        super(Token.Type.INPUT);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case INPUT:
                break;
            default:
                throw new IllegalStateException("Expected INPUT, encountered " + token);
        }
    }
}
