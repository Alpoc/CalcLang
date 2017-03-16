/**
 * Created by DJ on 3/15/2017.
 */
public class NewLine extends Node {
    public NewLine() {
        super(Token.Type.NEWLINE);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case NEWLINE:
                break;
            default:
                throw new IllegalStateException("Expected NEWLINE, encountered " + token);
        }
    }
}
