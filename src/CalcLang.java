import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by DJ on 3/15/2017.
 */
public class CalcLang {
    private static LinkedList<Token> tokens;
    private static Node head;
    private static ArrayList<String> variables;
    private static ArrayList<Double> variable;
    private static ArrayList<String> bifs;
    private static Scanner keys;
    private static int tokensize = 0;

    public static void main(String[] args) throws Exception {
        bifs = new ArrayList<String>();
        variables = new ArrayList<String>();
        variable = new ArrayList<Double>();
        bifs.add("cos");
        bifs.add("sin");
        bifs.add("abs");
        bifs.add("sqrt");
        bifs.add("radians");
        bifs.add("degrees");
        System.out.print("Enter file name: ");
        keys = new Scanner(System.in);
        String name = keys.nextLine();

        tokens = new LinkedList<Token>();
        Lexer lex = new Lexer(name);
        Token token;

        do {
            token = lex.getNext();
            tokens.add(token);
        } while (!token.type.equals(Token.Type.EOF));
        tokensize = tokens.size();
        head = new Statements();
        execute(head);
    }

    public static double execute(Node node) {
        switch (node.token) {
            case STATEMENTS:
                if (node.children.size() > 1) {
                    execute(node.children.get(0));
                    return execute(node.children.get(1));
                }
                return execute(node.children.get(0));
            case STATEMENT:
                switch (node.children.get(0).token) {
                    case VARIABLE:
                        if (variables.contains(node.children.get(0).string)) {
                            variable.set(variables.indexOf(node.children.get(0).string), execute(node.children.get(2)));
                        } else {
                            variables.add(node.children.get(0).string);
                            variable.add(execute(node.children.get(2)));
                        }
                        break;
                    case SHOW:
                        System.out.print(execute(node.children.get(1)));
                        break;
                    case MESSAGE:
                        System.out.print(node.children.get(1).string);
                        break;
                    case INPUT:
                        System.out.print(node.children.get(1).string);
                        if (variables.contains(node.children.get(2).string)) {
                            variable.set(variables.indexOf(node.children.get(2).string), keys.nextDouble());
                        } else {
                            variables.add(node.children.get(2).string);
                            variable.add(keys.nextDouble());
                        }
                        break;
                    case NEWLINE:
                        System.out.println();
                        break;
                    default:
                }
                break;
            case EXPRESSION:
                switch (node.children.size()) {
                    case 1:
                        return execute(node.children.get(0));
                    case 3:
                        switch (node.children.get(1).token) {
                            case PLUS:
                                return execute(node.children.get(0)) + execute(node.children.get(2));
                            case MINUS:
                                return execute(node.children.get(0)) - execute(node.children.get(2));
                            default:
                        }
                        break;
                    default:
                }
                break;
            case TERM:
                switch (node.children.size()) {
                    case 1:
                        return execute(node.children.get(0));
                    case 3:
                        switch (node.children.get(1).token) {
                            case TIMES:
                                return execute(node.children.get(0)) * execute(node.children.get(2));
                            case DEVIDE:
                                return execute(node.children.get(0)) / execute(node.children.get(2));
                            default:
                        }
                        break;
                    default:
                }
                break;
            case FACTOR:
                switch (node.children.get(0).token){
                    case NUMBER:
                        return node.children.get(0).value;
                    case VARIABLE:
                        if (variables.contains(node.children.get(0).string)) {
                            return variable.get(variables.indexOf(node.children.get(0).string));
                        } else {
                            variables.add(node.children.get(0).string);
                            variable.add(0.0);
                            return 0.0;
                        }
                    case LPAREN:
                        return execute(node.children.get(1));
                    case NEGATE:
                        return -1.0 * execute(node.children.get(1));
                    case BIF:
                        switch (node.children.get(0).string) {
                            case "cos":
                                return Math.cos(execute(node.children.get(2)));
                            case "sin":
                                return Math.sin(execute(node.children.get(2)));
                            case "abs":
                                return Math.abs(execute(node.children.get(2)));
                            case "degrees":
                                return Math.toDegrees(execute(node.children.get(2)));
                            case "radians":
                                return Math.toRadians(execute(node.children.get(2)));
                            case "sqrt":
                                return Math.sqrt(execute(node.children.get(2)));
                            default:
                                throw new IllegalStateException("Encountered invalid BIF: " + node.children.get(0).string);
                        }
                }
                break;
            default:
                throw new IllegalStateException("Encountered invalid token: " + node.token);
        }
        return 0.0;
    }

    public static Token readToken() {
        return tokens.pollFirst();
    }
    public static Token peekToken() {
        return tokens.peek();
    }
    public static void putBack(Token token) {
        tokens.offerFirst(token);
    }
}
