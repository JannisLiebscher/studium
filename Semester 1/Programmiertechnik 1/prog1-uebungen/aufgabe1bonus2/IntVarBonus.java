// IntVar.java

package aufgabe1bonus2;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * @author  Jannis Liebscher
 * @version  27.10.2020
 */
public final class IntVarBonus {
    private IntVarBonus() { }

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

        System.out.printf("Zwei ganze Zahlen zwischen %1$d und %2$d eingeben"
             + "(~2Mrd.) %n", min, max);
        while (!EINGABE.hasNextInt()) {
            if (EINGABE.hasNextLong()) {
                System.out.println("Die eingegeben Zahl Ueberschreitet"
                     + "den Wertebereich");
            } else if (EINGABE.hasNextDouble()) {
                System.out.println("Die eingegeben Zahl ist keine Ganze Zahl");
            } else {
                System.out.println("Sie haben keine Zahl eingegeben");
            }
            EINGABE.next();
        }
        if (EINGABE.hasNextInt()) {
            zahl1 = EINGABE.nextInt();
            while (!EINGABE.hasNextInt()) {
                if (EINGABE.hasNextLong()) {
                    System.out.println("Die eingegeben Zahl Ueberschreitet"
                         + "den Wertebereich");
                } else if (EINGABE.hasNextDouble()) {
                    System.out.println("Die eingegeben Zahl ist"
                         + " keine Ganze Zahl");
                } else if (EINGABE.hasNext()) {
                        System.out.println("Keine Eingabe");
                } else {
                    System.out.println("Sie haben keine Zahl eingegeben");
                }
                EINGABE.next();
            }
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
            System.out.printf("%d == %d ist %b%n", zahl1, zahl2,
                zahl1 == zahl2);
            System.out.printf("%d != %d ist %b%n", zahl1, zahl2,
                zahl1 != zahl2);
            System.out.printf("%d < %d ist %b%n", zahl1, zahl2, zahl1 < zahl2);
            System.out.printf("%d <= %d ist %b%n", zahl1, zahl2,
                zahl1 <= zahl2);
            System.out.printf("%d > %d ist %b%n", zahl1, zahl2, zahl1 > zahl2);
            System.out.printf("%d >= %d ist %b%n", zahl1, zahl2,
                zahl1 >= zahl2);
        }
    }
}
