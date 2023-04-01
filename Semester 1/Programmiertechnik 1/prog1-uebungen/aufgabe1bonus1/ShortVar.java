// shorVar.java

package aufgabe1bonus1;

import java.util.Scanner;

/**
 * @author  Jannis Liebscher
 * @version  27.10.2020
 */
public final class ShortVar {
    private ShortVar() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        final short min = -32768;
        final short max = 32767;
        short zahl1;
        short zahl2;

        System.out.printf("Zwei ganze Zahlen zwischen %1$d und %2$d"
             + " eingeben %n", min, max);

        zahl1 = EINGABE.nextShort();
        zahl2 = EINGABE.nextShort();

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
