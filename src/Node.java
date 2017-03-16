import java.util.ArrayList;

/**
 * Created by DJ on 3/15/2017.
 */
public class Node {
    public Token.Type token;
    public String string;
    public double value;
    ArrayList<Node> children;
    public Node(Token.Type token){
        this.children = new ArrayList<Node>();
        this.token = token;
    }
    public Node(Token token) {
        this.children = new ArrayList<Node>();
        this.token = token.type;
        this.value = token.value;
        this.string = token.string;
    }
    @Override
    public String toString() {
        return "[" + token + " : string: " + string + ", value: " + value + ", children: {" + children.toString() + "}]";
    }
}
