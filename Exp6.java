import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exp6 {
    public static void main(String[] args) {
        // Define the grammar
        Map<Character, List<String>> grammar = new HashMap<>();
        grammar.put('E', List.of("TA"));
        grammar.put('A', List.of("+TA", "@"));
        grammar.put('T', List.of("FB"));
        grammar.put('B', List.of("*FB", "@"));
        grammar.put('F', List.of("(E)", "i"));

        // Define the first set
        Map<Character, Set<Character>> first = new HashMap<>();
        first.put('E', Set.of('(', 'i'));
        first.put('A', Set.of('+', '@'));
        first.put('T', Set.of('(', 'i'));
        first.put('B', Set.of('*', '@'));
        first.put('F', Set.of('(', 'i'));

        // Define the follow set
        Map<Character, Set<Character>> follow = new HashMap<>();
        follow.put('E', Set.of(')', '$'));
        follow.put('A', Set.of(')', '$'));
        follow.put('T', Set.of(')', '$', '+'));
        follow.put('B', Set.of(')', '$', '+'));
        follow.put('F', Set.of(')', '$', '+', '*'));

        // Define the terminals and non-terminals
        List<Character> terminals = List.of('i', '+', '*', '(', ')', '$');
        List<Character> nonTerminals = List.of('E', 'A', 'T', 'B', 'F');

        // Initialize the parsing table
        Map<Character, Map<Character, List<String>>> table = new HashMap<>();
        for (char nonTerminal : nonTerminals) {
            table.put(nonTerminal, new HashMap<>());
            for (char terminal : terminals) {
                table.get(nonTerminal).put(terminal, new ArrayList<>());
            }
        }

        // Populate the parsing table
        for (Map.Entry<Character, List<String>> entry : grammar.entrySet()) {
            char nonTerminal = entry.getKey();
            List<String> productions = entry.getValue();
            for (String production : productions) {
                char firstSymbol = production.charAt(0);
                if (firstSymbol == '@') {
                    for (char terminal : follow.get(nonTerminal)) {
                        table.get(nonTerminal).get(terminal).add(nonTerminal + "->" + production);
                    }
                } else {
                    for (char symbol : production.toCharArray()) {
                        if (terminals.contains(symbol)) {
                            table.get(nonTerminal).get(symbol).add(nonTerminal + "->" + production);
                            break;
                        } else {
                            for (char symbolFirst : first.get(symbol)) { // Renamed firstSymbol to symbolFirst
                                table.get(nonTerminal).get(symbolFirst).add(nonTerminal + "->" + production);
                            }
                            if (!first.get(symbol).contains('@')) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Handle epsilon productions
        for (char nonTerminal : nonTerminals) {
            for (char terminal : follow.get(nonTerminal)) {
                table.get(nonTerminal).get(terminal).add(nonTerminal + "->@");
            }
        }

        // Print the parsing table
        System.out.print("\t");
        for (char terminal : terminals) {
            System.out.printf("%-8c", terminal);
        }
        System.out.println();
        for (char nonTerminal : nonTerminals) {
            System.out.printf("%-10c", nonTerminal);
            for (char terminal : terminals) {
                List<String> productions = table.get(nonTerminal).get(terminal);
                if (!productions.isEmpty()) {
                    for (String production : productions) {
                        System.out.print(production);
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
