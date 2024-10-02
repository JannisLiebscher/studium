import java.util.*;

public class Aufgabe1_WarmUp {
    public static void aufgabe() {
        System.out.println("\nAufgabe 1a (3P):");
        aufgabe_a();

        System.out.println("\nAufgabe 1b (3P):");
        aufgabe_b();

        System.out.println("\nAufgabe 1c (3P):");
        aufgabe_c();

        System.out.println("\nAufgabe 1d (3P):");
        aufgabe_d();
    }

    private static void aufgabe_a() {
        // Kopieren Sie alle Elemente aus der Liste l1
        // in die Liste l2 ohne Duplikate.
        // D.h. l2 enthält alle Elemente aus l1,
        // wobei kein Element mehrfach vorkommt.

        List<String> l1 = new LinkedList<>();
        l1.add("vier");
        l1.add("fuenf");
        l1.add("vier");
        l1.add("eins");
        l1.add("fuenf");
        l1.add("acht");
        l1.add("vier");
        System.out.println(l1);

        // Ihr Code: ...

    }

    private static void aufgabe_b() {
        // Kopieren Sie alle Elemente aus l1 und l2 in eine gemeinsame List l3.
        // Verzichten Sie auf eine for-Schleife!

        List<String> l1 = new LinkedList<>();
        l1.add("vier");
        l1.add("fuenf");
        l1.add("vier");
        l1.add("eins");
        l1.add("fuenf");
        l1.add("acht");
        l1.add("vier");

        List<Integer> l2 = new ArrayList<>();
        l2.add(12);
        l2.add(1);
        l2.add(12);
        l2.add(11);
        l2.add(1);
        l2.add(12);

        // Ihr Code: ...
    }

    private static void aufgabe_c() {
        // Speichern Sie alle gemeinsamen Elemente von lNB und colInt
        // in eine gemeinsame Menge (Set) s.
        // Verzichten Sie auf eine for-Schleife!

        List<Number> lNb = new LinkedList<>();
        lNb.add(4);
        lNb.add(1);
        lNb.add(500);
        lNb.add(1.2);
        lNb.add(500);
        lNb.add(1);
        lNb.add(8.5);

        Collection<Integer> colInt = new ArrayList<>();
        colInt.add(4);
        colInt.add(500);
        colInt.add(1);
        colInt.add(1);
        colInt.add(7);

        // Ihr Code: ...
    }

    private static void aufgabe_d() {
        // Eine Matrix m lässt sich als Liste von Liste implementieren.
        // Fügen Sie folgende 3 Listen in eine Liste m von Listen ein.
        // Geben Sie die gesamte Matrix m und
        // das Element in der 2. Zeile und 4. Spalte (2.3) aus.
        // Ändern Sie das Element in der 2. Zeile und 4. Spalte in 4.6.

        List<Double> l1 = new LinkedList(List.of(1.2, 3.2, 5.1, 7.1));
        List<Double> l2 = new LinkedList(List.of(8.3, 2.0, 1.1, 5.2));
        List<Double> l3 = new LinkedList(List.of(6.8, 3.5, 1.7, 2.3));


    }
}
