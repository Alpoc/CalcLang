/**
 * Created by DJ on 3/15/2017.
 */
public class Statement extends Node {
    public Statement() {
        super(Token.Type.STATEMENT);
        Token token = CalcLang.peekToken();
        switch (token.type) {
            case VARIABLE:
                this.children.add(new Variable());
                this.children.add(new Equals());
                this.children.add(new Expression());
                break;
            case SHOW:
                this.children.add(new Show());
                this.children.add(new Expression());
                break;
            case MESSAGE:
                this.children.add(new Message());
                this.children.add(new StringLiteral());
                break;
            case INPUT:
                this.children.add(new Input());
                this.children.add(new StringLiteral());
                this.children.add(new Variable());
                break;
            case NEWLINE:
                this.children.add(new NewLine());
                break;
            default:
                throw new IllegalStateException("Expected start of STATEMENT, encountered: " + token);
        }
    }
}
