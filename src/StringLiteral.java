/**
 * Created by DJ on 3/15/2017.
 */
public class StringLiteral extends Node {
    public StringLiteral() {
        super(Token.Type.STRING);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case STRING:
                this.string = token.string;
                break;
            default:
                throw new IllegalStateException("Expected STRING, encountered " + token);
        }
    }
}
