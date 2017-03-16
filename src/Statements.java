/**
 * Created by DJ on 3/15/2017.
 */
public class Statements extends Node {
    public Statements() {
        super(Token.Type.STATEMENTS);
        this.children.add(new Statement());
        Token token = CalcLang.peekToken();
        switch (token.type) {
            case EOF:
                break;
            default:
                this.children.add(new Statements());
        }
    }
}
