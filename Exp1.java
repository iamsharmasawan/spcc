import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class Exp1 {
    public static void main(String[] args) {
        String s = "int a = b + c - ( 5 + 4 ) * 2";
        String[] tokens = s.split("\\s+");
        System.out.println(Arrays.toString(tokens));

        List<String> keywords = new ArrayList<>();
        List<String> variable = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        List<String> separators = new ArrayList<>();

        int token = tokens.length;
        for (String value : tokens) {
            if (value.equals("int") || value.equals("float") || value.equals("char") || value.equals("double")) {
                keywords.add(value);
            } else if (value.matches("\\d+")) {
                numbers.add(value);
            } else if (value.matches("[=*+-/]")) {
                operators.add(value);
            } else if (value.matches("[;()\\[\\]]")) {
                separators.add(value);
            } else {
                variable.add(value);
            }
        }

        System.out.println("Keywords: " + new HashSet<>(keywords));
        System.out.println("Variables: " + new HashSet<>(variable));
        System.out.println("Operators: " + new HashSet<>(operators));
        System.out.println("Separators: " + new HashSet<>(separators));
        System.out.println("Numbers: " + new HashSet<>(numbers));
        System.out.println("Total tokens: " + token);
    }
}

// import java.util.*;

// public class Exp1 {

// // Token types
// enum TokenType {
// INTEGER, FLOAT, IDENTIFIER, OPERATOR, DELIMITER, KEYWORD, INVALID
// }

// // Token class to represent each token
// static class Token {
// TokenType type;
// String value;

// Token(TokenType type, String value) {
// this.type = type;
// this.value = value;
// }

// @Override
// public String toString() {
// return "Token<" + type + ", " + value + ">";
// }
// }

// // Method to tokenize the input string
// public static List<Token> tokenize(String input) {
// List<Token> tokens = new ArrayList<>();
// String[] parts = input.split("\\s+");

// for (String part : parts) {
// if (part.matches("\\d+")) {
// tokens.add(new Token(TokenType.INTEGER, part));
// } else if (part.matches("\\d+\\.\\d+")) {
// tokens.add(new Token(TokenType.FLOAT, part));
// } else if (part.matches("[a-zA-Z]\\w*")) {
// tokens.add(new Token(TokenType.IDENTIFIER, part));
// } else if (part.matches("[\\+\\-\\*/]")) {
// tokens.add(new Token(TokenType.OPERATOR, part));
// } else if (part.matches("[,;]")) {
// tokens.add(new Token(TokenType.DELIMITER, part));
// } else if (isKeyword(part)) {
// tokens.add(new Token(TokenType.KEYWORD, part));
// } else {
// tokens.add(new Token(TokenType.INVALID, part));
// }
// }

// return tokens;
// }

// // Method to check if a token is a keyword
// private static boolean isKeyword(String token) {
// // Sample keywords, extend this list as needed
// String[] keywords = { "if", "else", "while", "for", "return", "int", "float",
// "void" };
// return Arrays.asList(keywords).contains(token);
// }

// // Main method for testing
// public static void main(String[] args) {
// String input = "int x = 10; float y = 20.5; if (x > y) { x = x + 1; } else {
// y = y - 1; }";
// List<Token> tokens = tokenize(input);

// for (Token token : tokens) {
// System.out.println(token);
// }
// }
// }
