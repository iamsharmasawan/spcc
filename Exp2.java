import java.util.*;

public class Exp2 {
    static List<String> MNT = new ArrayList<>();
    static List<String> MDT = new ArrayList<>();
    static List<String> ALA = new ArrayList<>();
    static Map<String, String> alaDict = new HashMap<>();
    static int alaInd = 1;
    static boolean insideMacro = false;

    public static void main(String[] args) {
        String input = "PRGSTART0\n" +
                "MACRO\n" +
                "&LAB INCR &ARG1,&ARG2,&ARG3\n" +
                "&LAB A1,&ARG1\n" +
                "A 2,&ARG2\n" +
                "A 3,&ARG3\n" +
                "MEND\n" +
                "LOOP1 INCR DATA1,DATA2,DATA3\n" +
                "DATA1 DC F’5’\n" +
                "DATA2 DC F’10’\n" +
                "DATA3 DC F’15’";

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.equals("MACRO")) {
                    insideMacro = true;
                    continue;
                } else if (line.equals("MEND")) {
                    break;
                }
                if (insideMacro) {
                    MDT.add(line);
                    line = line.replace(",", " ");
                    String[] lineArray = line.split(" ");
                    for (String i : lineArray) {
                        if (i.startsWith("&") && !ALA.contains(i)) {
                            ALA.add(i);
                            alaDict.put(ALA.get(ALA.size() - 1), "#" + alaInd);
                            alaInd++;
                        } else {
                            if (MNT.isEmpty()) {
                                MNT.add(i);
                            }
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, String> entry : alaDict.entrySet()) {
            for (int i = 1; i < MDT.size(); i++) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (MDT.get(i).contains(key)) {
                    MDT.set(i, MDT.get(i).replace(key, value));
                }
            }
        }
        MDT.add("MEND");

        System.out.println("MNT: " + MNT);
        System.out.println("MDT: " + MDT);
        System.out.println("ALA: " + ALA);
    }
}
