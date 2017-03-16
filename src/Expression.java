/**
 * Created by DJ on 3/15/2017.
 */
public class Expression extends Node {
    public Expression() {
        super(Token.Type.EXPRESSION);
        this.children.add(new Term());

        Token token = CalcLang.peekToken();
        switch (token.type) {
            case PLUS:
                this.children.add(new Plus());
                this.children.add(new Expression());
                break;
            case MINUS:
                this.children.add(new Minus());
                this.children.add(new Expression());
                break;
            default:
        }
    }
}
