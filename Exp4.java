import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exp4 {
    public static void main(String[] args) {
        List<String> productions = List.of(
                "A -> TA'",
                "A' -> +TA' | E",
                "T -> FT'",
                "T' -> *FT' | E",
                "F -> id | (A)");

        List<String> terminals = List.of("id", "(", ")", "*", "+", "E");
        List<String> nonTerminals = List.of("A", "T", "A'", "T'", "F");

        List<Map<String, Object>> grammar = new ArrayList<>();

        for (int i = productions.size() - 1; i >= 0; i--) {
            String production = productions.get(i);
            Map<String, Object> productionMap = new HashMap<>();
            List<String> firstSet = new ArrayList<>();

            String[] parts = production.split(" -> ");
            String lhs = parts[0];
            String[] alternatives = parts[1].split(" \\| ");

            productionMap.put("production", production);
            productionMap.put("value", lhs);

            for (String alt : alternatives) {
                if (terminals.contains(Character.toString(alt.charAt(0)))) {
                    firstSet.add(Character.toString(alt.charAt(0)));
                } else if (nonTerminals.contains(Character.toString(alt.charAt(0)))) {
                    for (Map<String, Object> attr : grammar) {
                        if (attr.get("value").equals(Character.toString(alt.charAt(0)))) {
                            firstSet.addAll((List<String>) attr.get("first"));
                        }
                    }
                }
            }

            productionMap.put("first", firstSet);
            grammar.add(productionMap);
        }

        // Output the results
        for (Map<String, Object> attr : grammar) {
            for (Map.Entry<String, Object> entry : attr.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }
}