import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Exp7 {

    public static int prec(char c) {
        if (c == '/' || c == '*') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return -1;
        }
    }

    public static List<String> infixToPostfix(String s) {
        List<String> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                result.add(String.valueOf(c));
            } else {
                while (!stack.isEmpty() && prec(s.charAt(i)) <= prec(stack.peek())) {
                    result.add(String.valueOf(stack.pop()));
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.add(String.valueOf(stack.pop()));
        }

        return result;
    }

    public static void main(String[] args) {
        // Input expression
        String inputexp = "ans=a*b/c-d";
        String exp = "";
        String resvar = "";
        for (int i = 0; i < inputexp.length(); i++) {
            if (inputexp.charAt(i) == '=') {
                resvar = inputexp.substring(0, i);
                exp = inputexp.substring(i + 1);
            }
        }
        System.out.println(exp);
        System.out.println(resvar);

        // Function call
        List<String> expression = infixToPostfix(exp);
        System.out.println(expression);

        Stack<String> st = new Stack<>();
        int ind = 1;
        for (String i : expression) {
            if (Character.isLetter(i.charAt(0))) {
                st.push(i);
            } else {
                String temp1 = st.pop();
                String temp2 = st.pop();
                System.out.println("t" + ind + " = " + temp2 + " " + i + " " + temp1);
                st.push("t" + ind);
                ind++;
            }
        }

        System.out.println(resvar + " = t" + (ind - 1));
    }
}
