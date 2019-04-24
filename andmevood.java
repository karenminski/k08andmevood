import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class andmevood {
    private static int kogus(String s) {
        return Integer.parseInt(s.split(" ")[1]);
    }

    public static void main(String[] arg) throws Exception {

        String sisend = "sisend.txt";
        String valjund = "valjund.txt";
        ArrayList < String > words = new ArrayList < > ();
        Map < String, Long > occurrence;
        ArrayList < String > wordsWithOccurrence = new ArrayList < > ();

        File file = new File(sisend);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            words.add(scan.next()
                .replaceAll("[^a-zA-Z-]", "")
                .toLowerCase());
        }
        scan.close();

        occurrence = words.stream()
            .collect(groupingBy(Function.identity(), counting()));

        for (Map.Entry < String, Long > entry: occurrence.entrySet()) {
            wordsWithOccurrence.add(entry.getKey() + " " + entry.getValue());
        }

        PrintWriter kirjutaja = new PrintWriter(new FileWriter(valjund));
        wordsWithOccurrence
            .stream()
            .sorted((s1, s2) -> kogus(s2) - kogus(s1))
            .collect(Collectors.toList())
            .forEach(kirjutaja::println);
        kirjutaja.close();
    }
}