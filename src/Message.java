/**
 * Created by DJ on 3/15/2017.
 */
public class Message extends Node {
    public Message() {
        super(Token.Type.MESSAGE);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case MESSAGE:
                break;
            default:
                throw new IllegalStateException("Expected MESSAGE, encountered " + token);
        }
    }
}
