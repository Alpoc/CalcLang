/**
 * Created by DJ on 3/15/2017.
 */
public class Term extends Node {
    public Term() {
        super(Token.Type.TERM);
        this.children.add(new Factor());
        Token token = CalcLang.peekToken();
        switch (token.type) {
            case TIMES:
                this.children.add(new Times());
                this.children.add(new Term());
                break;
            case DEVIDE:
                this.children.add(new Divide());
                this.children.add(new Term());
                break;
            default:
        }
    }
}
