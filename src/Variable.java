/**
 * Created by DJ on 3/15/2017.
 */
public class Variable extends Node {
    public Variable(){
        super(Token.Type.VARIABLE);
        Token token = CalcLang.readToken();
        switch (token.type) {
            case VARIABLE:
                this.string = token.string;
                break;
            default:
                throw new IllegalStateException("Expected VARIABLE, encountered " + token);
        }
    }
}
