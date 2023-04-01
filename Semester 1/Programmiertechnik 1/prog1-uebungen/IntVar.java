// IntVar.java

package aufgabe1;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * @author  Jannis Liebscher
 * @version  31.10.2020
 */
public final class IntVar {
    private IntVar() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        final int min = -2147483648;
        final int max = 2147483647;
        int zahl1;
        int zahl2;

        System.out.printf("Zwei ganze Zahlen zwischen %1$d und %2$d"
             + " eingeben (~2Mrd.) %n", min, max);

        zahl1 = EINGABE.nextInt();
        zahl2 = EINGABE.nextInt();

        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n",
            zahl1);
        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n",
            zahl2);

        System.out.printf("%d + %d ist %d%n", zahl1, zahl2, zahl1 + zahl2);
        System.out.printf("%d - %d ist %d%n", zahl1, zahl2, zahl1 - zahl2);
        System.out.printf("%d * %d ist %d%n", zahl1, zahl2, zahl1 * zahl2);
        System.out.printf("%d/%d ist %d%n", zahl1, zahl2, zahl1 / zahl2);
        System.out.printf("%d / %d hat einen Rest von %d%n",
            zahl1, zahl2, zahl1 % zahl2);

        System.out.printf("%d == %d ist %b%n", zahl1, zahl2, zahl1 == zahl2);
        System.out.printf("%d != %d ist %b%n", zahl1, zahl2, zahl1 != zahl2);
        System.out.printf("%d < %d ist %b%n", zahl1, zahl2, zahl1 < zahl2);
        System.out.printf("%d <= %d ist %b%n", zahl1, zahl2, zahl1 <= zahl2);
        System.out.printf("%d > %d ist %b%n", zahl1, zahl2, zahl1 > zahl2);
        System.out.printf("%d >= %d ist %b%n", zahl1, zahl2, zahl1 >= zahl2);

    }
}
