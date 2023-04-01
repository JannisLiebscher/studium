// Histogramm.java
package aufgabe2bonus1;

import java.util.Scanner;

/**
 * Histogramm liest ganze Zahlen zwischen 1 und 6 ein und
 * gibt deren H&auml;ufigkeitsverteilung als Histogramm aus.
 * @author Jannsi Liebscher
 * @version 07.11.2020
 */
public final class Skalierung {
    private Skalierung() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        final int interval = 6;
        final int dollar = 5;
        final int maxZeichen = 50;
        int[]zaehler = new int[interval];
        //---------------------------------------------------- Zahlen einlesen
        System.out.println("Ganze Zahlen zwischen 1 und 6 eingeben "
             + "(Ende mit Strg-Z):");
        while (EINGABE.hasNext()) {
            int number = EINGABE.nextInt();
        //-------------------------Pruefen und Zaehlen der Eingabe schreiben
            if (number >= 1 && number <= interval) {
                ++zaehler[number - 1];
            } else {
                System.out.printf("Falsche Eingabe %d wird ignoriert", number);
            }
        }
        //----------------------------------------------- groessten Zaehler finden
        int max = 0;
        for (int i = 0; i < zaehler.length; i++) {
            if (zaehler[i] > max) {
                max = zaehler[i];
            }
        }
        //----------------------------------------------- n berechnen
        System.out.printf("max:%d%n", max);
        int n = max / maxZeichen + 1;
        System.out.printf("Skalierung:%d%n", n);
        //------------------------------------------------ Histogramm ausgeben

        int ges = 1;
        for (int i = 0; i < zaehler.length; ++i) {
            for (int j = zaehler[i]; j > 0; j = j - n) {
                if (ges != dollar) {
                    System.out.printf("%d", i + 1);
                    ++ges;
                } else {
                    System.out.print("$");
                    ges = 1;
                }

            }
            System.out.printf("(%d)%n", zaehler[i]);
            ges = 1;
        }
    }
}
