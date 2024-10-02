import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Aufgabe4_Movies_Musterloesung {

    public static void aufgabe() throws IOException {
        // Die record-Klasse Movie stellt zwei Konstruktoren zur Verfügung.
        // Versuchen Sie die Funktionsweise zu verstehen.
        System.out.println("\nAufgabe 4a (1P):");
        Movie movie1 = new Movie("101 Dalmatians", List.of("Benfield, John", "Braid, Hilda", "Capron, Brian"),1996);
        Movie movie2 = new Movie("101 Dalmatians (1996)/Benfield, John/Braid, Hilda/Capron, Brian");
        // Ausgabe von m1 und m2 sollte identisch sein:
        System.out.println(movie1);
        System.out.println(movie2);


        // a) (4P)
        // Die Datei data/movies-top-grossing.txt enthält einige erfolgreiche Kinofilme (jeweils eine Zeile) des 20. Jahrhunderts.
        // Jeder Film besteht aus einem Titel, einer Liste von Schauspielern und das Erscheinungsjahr.
        // Ergänzen Sie die gegebene statische Methode einlesen so, dass alle Filme in eine Liste eingelesen und zurückgegeben werden.
        // Geben Sie alle Filme nach Jahreszahlen sortiert aus,
        // indem Sie zuerst die Jahreszahl und dann den Filmtitel in einer Zeile ausgeben (Schauspieler werden weggelassen).
        System.out.println("\nAufgabe 4a (4P):");
        List<Movie> movieList = einlesen("data/movies-top-grossing.txt");
        movieList.sort((m1,m2)-> m1.year()- m2.year());
        for (Movie m : movieList)
            System.out.println(m.year() + ": " + m.title());


        // b) (5P)
        // Erstellen aus der Liste movieList eine Map, die jeder Jahreszahl die entsprechende Menge der Filmtitel zurordnet.
        // Geben Sie die Map aus, indem Sie für jeden Eintrag zuerst die Jahreszahl und dann die Filmtitel eingerückt
        // in jeweils eine Zeile ausgeben.
        System.out.println("\nAufgabe 4b (5P):");
        Map<Integer, Set<String>> jahrToTitel = new TreeMap<>();
        for (Movie m : movieList) {
            if (jahrToTitel.get(m.year()) == null)
                jahrToTitel.put(m.year(), new TreeSet<>());
            jahrToTitel.get(m.year()).add(m.title());
        }
        for (var e : jahrToTitel.entrySet()) {
            System.out.println(e.getKey());
            for (var m : e.getValue())
                System.out.println("   - " + m);
        }

        // c) (5P)
        // Erstellen aus der Liste movieList eine Map, die jedem/r Schauspieler/in die Menge der Filmtitel zurordnet,
        // in der er/sie mitgewirkt hat.
        // Geben Sie die Map für alle mit 'B' beginnende Schauspieler/innen aus,
        // indem Sie für jeden Eintrag zuerst der/die Schauspieler/in und dann die Filmtitel eingerückt in jeweils eine Zeile ausgeben.
        // Wieviel unterschiedliche Schaupieler gibt es?
        System.out.println("\nAufgabe 4c (5P):");
        SortedMap<String, Set<String>> actorToTitel = new TreeMap<>();
        for (Movie m : movieList) {
            for (String a : m.actors()) {
                if (actorToTitel.get(a) == null)
                    actorToTitel.put(a, new TreeSet<>());
                actorToTitel.get(a).add(m.title());
            }
        }
        for (var e : actorToTitel.subMap("B","C").entrySet()) {
            System.out.println(e.getKey());
            for (var m : e.getValue())
                System.out.println("   - " + m);
        }
        System.out.println("Anzahl Schauspieler: " + actorToTitel.size());


        // d) (4P)
        // Ermitteln Sie aus der Map actorToTitel von c) die fünf Schauspieler/innen,
        // die in den meisten Filmen mitgewirkt haben.
        System.out.println("\nAufgabe 4d (4P):");
        var actorList = new LinkedList<>(actorToTitel.entrySet());
        actorList.sort((e1,e2) -> e2.getValue().size() - e1.getValue().size());
        for (var a : actorList.subList(0,5))
            System.out.println(a.getKey() + ": " + a.getValue().size());
    }

    private static List<Movie> einlesen(String fn) throws IOException {
        List<Movie> movieList = new LinkedList<>();
        LineNumberReader in = new LineNumberReader(new FileReader(fn, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            movieList.add(new Movie(line));
        }
        return movieList;
    }
}
