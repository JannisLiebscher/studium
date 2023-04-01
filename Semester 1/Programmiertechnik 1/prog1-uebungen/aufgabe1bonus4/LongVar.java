// IntVar.java

package aufgabe1bonus4;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * @author  Jannis Liebscher
 * @version  31.10.2020
 */
public final class LongVar {
    private LongVar() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        final int min = -2147483648;
        final int max = 2147483647;
        int zahl1Int;
        int zahl2Int;
        long ergebnis;

        System.out.printf("Zwei ganze Zahlen zwischen %1$d und %2$d"
             + " eiengeben (~2Mrd.) %n", min, max);

        zahl1Int = EINGABE.nextInt();
        zahl2Int = EINGABE.nextInt();

        long zahl1 = (long) zahl1Int;
        long zahl2 = (long) zahl2Int;
        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n",
            zahl1);
        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n",
            zahl2);

        ergebnis = zahl1 + zahl2;
        if (min < ergebnis && ergebnis < max) {
            System.out.printf("%d + %d ist %d%n", zahl1, zahl2, zahl1 + zahl2);
        } else {
            System.out.println("Die Summe kann nicht als "
                 + "Int dargestellt werden");
        }

        ergebnis = zahl1 - zahl2;
        if (min < ergebnis && ergebnis < max) {
            System.out.printf("%d - %d ist %d%n", zahl1, zahl2, zahl1 - zahl2);
        } else {
            System.out.println("Die Differenz kann nicht als "
                 + "Int dargestellt werden");
        }

        ergebnis = zahl1 * zahl2;
        if (min < ergebnis && ergebnis < max) {
            System.out.printf("%d * %d ist %d%n", zahl1, zahl2, zahl1 * zahl2);
        } else {
            System.out.println("Das Produkt kann nicht als"
                 + " Int dargestellt werden");
        }
        ergebnis = zahl1 / zahl2;
        if (min < ergebnis && ergebnis < max) {
            System.out.printf("%d/%d ist %d%n", zahl1, zahl2, zahl1 / zahl2);
        } else {
            System.out.println("Der Quotient kann nicht als"
                 + "Int dargestellt werden");
        }
        ergebnis = zahl1 % zahl2;
        if (min < ergebnis && ergebnis < max) {
            System.out.printf("%d / %d hat einen Rest von %d%n",
                zahl1, zahl2, zahl1 % zahl2);
        } else {
            System.out.println("Der Rest kann nicht als"
                 + "Int dargestellt werden");
        }

        System.out.printf("%d == %d ist %b%n", zahl1, zahl2, zahl1 == zahl2);
        System.out.printf("%d != %d ist %b%n", zahl1, zahl2, zahl1 != zahl2);
        System.out.printf("%d < %d ist %b%n", zahl1, zahl2, zahl1 < zahl2);
        System.out.printf("%d <= %d ist %b%n", zahl1, zahl2, zahl1 <= zahl2);
        System.out.printf("%d > %d ist %b%n", zahl1, zahl2, zahl1 > zahl2);
        System.out.printf("%d >= %d ist %b%n", zahl1, zahl2, zahl1 >= zahl2);

    }
}
