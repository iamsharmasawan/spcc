import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exp8 {
    public static List<String> gcse(List<String> code) {
        Map<String, String> expressions = new HashMap<>();
        List<String> optimizedCode = new ArrayList<>();

        for (String line : code) {
            String[] tokens = line.split("=");
            String tempVar = tokens[0].trim();
            System.out.println(tokens[0]);
            String expr = (tokens[1].trim());

            if (!expressions.containsValue(expr)) {
                expressions.put(tempVar, expr);
                optimizedCode.add(line);
            }
        }

        return optimizedCode;
    }

    public static void main(String[] args) {
        // Input code
        List<String> code = new ArrayList<>();
        code.add("t1 = -c");
        code.add("t2 = a+b");
        code.add("t3 = a+b");
        code.add("t4 = a+b");
        code.add("t5 = a+b");
        code.add("t6 = d+5");
        code.add("t7 = a+b");
        code.add("t8 = -c");
        code.add("t9 = 3-2");

        // Optimize the code
        List<String> optimizedCode = gcse(code);

        // Print the optimized code
        System.out.println("Optimized Code:");
        for (String line : optimizedCode) {
            System.out.println(line);
        }
    }
}
