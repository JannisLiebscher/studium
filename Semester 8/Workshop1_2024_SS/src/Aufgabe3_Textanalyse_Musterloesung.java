import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe3_Textanalyse_Musterloesung {
    public static void aufgabe() throws IOException {
        //
        // a) (6P)
        // Die Datei Kafka_Der_Prozess.txt soll eingelesen werden und verschiedene Auswertungen erfolgen.
        // Ergänzen Sie die Funktion einlesen so, dass die eingelesenen Wörter als Liste zurückgeliefert werden.
        System.out.println("\nAufgabe 3a (6P):");
        long start = System.nanoTime(); // aktuelle Zeit in nsec
        List<String> lst_Kafka = einlesen("data/Kafka_Der_Prozess.txt");
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);

        // Geben Sie die Anzahl der eingelesenen Wörter aus. Benutzen Sie dazu Ihre eingelesene Liste.
        System.out.println(lst_Kafka.size());

        // Sortieren Sie die Liste und geben Sie die ersten 5 Wörter und die letzten 5 Wörter aus.
        start = System.nanoTime();
        lst_Kafka.sort(null);
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        System.out.println(lst_Kafka.subList(0,10));
        System.out.println(lst_Kafka.subList(lst_Kafka.size()-10, lst_Kafka.size()-1));

        // Speichern Sie die Liste in eine TreeSet und geben Sie ersten die 100  Wörter aus.
        // Berücksichtigen Sie die Konstruktoren der Klasse TreeSet!
        SortedSet<String> set_Kafka = new TreeSet<>(lst_Kafka);
        int i = 0;
        for (String w : set_Kafka) {
            System.out.println(w);
            if (++i == 100)
                break;
        }

        //
        // b) (6P)
        // Verwenden Sie die bereits eingelesene Liste lst_Kafka und erstellen Sie eine Häufigkeitstabelle als SortedMap.
        // Wieviel unterschiedliche Wörter gibt es?
        // Geben Sie mit Hilfe von subMap alle Wörter (mit ihren Häufigkeiten) aus, die mit "Ver" beginnen.
        // Geben Sie die 20 häufigsten Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3b (6P):");
        start = System.nanoTime();
        SortedMap<String, Integer> fqTable_Kafka = ermittleHaeufigkeiten(lst_Kafka);
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        System.out.println(fqTable_Kafka.size());
        System.out.println(fqTable_Kafka.subMap("Ver","Ves"));
        printTop20(fqTable_Kafka);

        //
        // c) (6P)
        // Ermitteln Sie für den Kafka-Text eine Häufigkeitstabelle der falsch geschriebenen Wörter.
        // Wieviel falsch geschriebene Wörter gibt es?
        // Geben Sie die 20 häufigsten falsch geschriebenen Wörter (mit ihren Häufigkeiten) aus.
        // Ein Wort gilt als falsch geschrieben, wenn es nicht in der Wörterbuchdatei word_list_german_spell_checked.txt vorkommt.
        // Lesen Sie die alle Wörter der Datei ein und speichern Sie sie in eine effiziente Datenstruktur ab.
        // Achtung: Das Einlesen der Datei dauert etwas Zeit, da sie mehr als 2 Millionen Einträge enthält.
        System.out.println("\nAufgabe 3c (6P):");
        start = System.nanoTime();
        Set<String> wb = einlesenAlsSet("data/word_list_german_spell_checked.txt");
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        start = System.nanoTime();
        SortedMap<String, Integer> fqTableFalscheWoerter = ermittleHaeufigkeiten(lst_Kafka, wb);
        System.out.println(fqTableFalscheWoerter.size());
        printTop20(fqTableFalscheWoerter);
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
    }

    private static List<String> einlesen(String fileName) throws IOException {
        LineNumberReader in = new LineNumberReader(new FileReader(fileName, StandardCharsets.UTF_8));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w: wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                list.add(w);
            }
        }
        return list;
    }

    private static SortedMap<String, Integer> ermittleHaeufigkeiten(List<String> wListe)  {
        SortedMap<String, Integer> fqTable = new TreeMap<>();
        for(String w : wListe) {
            if (fqTable.containsKey(w))
                fqTable.put(w,fqTable.get(w)+1);
            else
                fqTable.put(w,1);
        }
        return fqTable;
    }

    public static void printTop20(Map<String,Integer> fqTable) {
        var l = new ArrayList<>(fqTable.entrySet());
        l.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        for (int i = 0; i < 20; i++)
            System.out.print(l.get(i).getKey() + " = " + l.get(i).getValue() + ", ");
        System.out.println();
    }

    private static Set<String> einlesenAlsSet(String fileName) throws IOException {
        LineNumberReader in = new LineNumberReader(new FileReader(fileName, StandardCharsets.UTF_8));
        Set<String> wb = new TreeSet<>();
        String line;

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w: wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                wb.add(w);
            }
        }
        return wb;
    }

    private static SortedMap<String, Integer> ermittleHaeufigkeiten(List<String> wListe, Set<String> wb) {
        SortedMap<String, Integer> fqTable = new TreeMap<>();
        for (String w : wListe) {
            if (!wb.contains(w))
                if (fqTable.containsKey(w))
                    fqTable.put(w, fqTable.get(w) + 1);
                else
                    fqTable.put(w, 1);
        }
        return fqTable;
    }
}
