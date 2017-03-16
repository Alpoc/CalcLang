/**
 * Created by DJ on 3/15/2017.
 */
public class BIF extends Node {
    public BIF() {
        super(Token.Type.BIF);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case VARIABLE:
                this.string = token.string;
                break;
            default:
                throw new IllegalStateException("Expected BIF, encountered " + token);
        }
    }
}
