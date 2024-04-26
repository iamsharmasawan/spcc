import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input production
        System.out.print("Enter the production: ");
        String grammar = scanner.nextLine();
        String[] parts = grammar.split("->");
        String left = parts[0].trim();
        String[] right = parts[1].trim().split("\\|");

        List<String> prod = new ArrayList<>();
        boolean flag = false;

        for (String i : right) {
            if (i.charAt(0) == left.charAt(0)) {
                flag = true;
                prod.add(left + "' -> " + i.substring(1) + left + "'");
            } else {
                prod.add(left + " -> " + i + left + "'");
            }
        }

        if (flag) {
            prod.add(left + "' -> ε");
            for (String i : prod) {
                System.out.println(i);
            }
        } else {
            System.out.println("No left recursion detected");
        }

        scanner.close();
    }
}
