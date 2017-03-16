/**
 * Created by DJ on 3/15/2017.
 */
public class Factor extends Node {
    public Factor(){
        super(Token.Type.FACTOR);
        Token token = CalcLang.peekToken();
        switch (token.type) {
            case NUMBER:
                this.children.add(new Number());
                break;
            case VARIABLE:
                Token read = CalcLang.readToken();
                Token peek = CalcLang.peekToken();
                CalcLang.putBack(read);
                switch(peek.type) {
                    case LPAREN:
                        this.children.add(new BIF());
                        this.children.add(new LParen());
                        this.children.add(new Expression());
                        this.children.add(new RParen());
                        break;
                    default:
                        this.children.add(new Variable());
                        break;
                }
                break;
            case LPAREN:
                this.children.add(new LParen());
                this.children.add(new Expression());
                this.children.add(new RParen());
                break;
            case MINUS:
                this.children.add(new Negate());
                this.children.add(new Factor());
                break;
            default:
                throw new IllegalStateException("Expected NUMBER, encountered " + token);
        }
    }
}
