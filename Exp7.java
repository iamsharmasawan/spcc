import java.util.*;

public class Exp7 {

    // Sabse pehle ek Set of Operator banane hai
    // Aur Map of precedence
    static Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '(', ')'));
    static Map<Character, Integer> precedence = new HashMap<>();

    static {
        // CValues daalni hai

        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
    }

    public static String generateTAC(String postfix) {
        Stack<String> Stack = new Stack<>();
        int t = 1;

        for (char c : postfix.toCharArray()) {
            if (!operators.contains(c)) {
                Stack.push(Character.toString(c));
            } else {
                String operand2 = Stack.pop();
                String operand1 = Stack.pop();
                System.out.println("t" + t + " := " + operand1 + " " + c + " " + operand2);
                Stack.push("t" + t);
                t++;
            }
        }

        return Stack.pop();
    }

    public static String infixToPostfix(String formula) {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (char ch : formula.toCharArray()) {
            if (!operators.contains(ch)) {
                output.append(ch);
            } else if (ch == '(') {
                stack.push('(');
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(ch) <= precedence.get(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expression = sc.nextLine();

        String postfix = infixToPostfix(expression);
        generateTAC(postfix);

        sc.close();
    }
}
