

package aufgabe1bonus3;

import java.util.Scanner;

/**
 * Nur die gewünschte operation wird ausgegeben.
 * @author  Jannis Liebscher
 * @version  27.10.2020
 */
public final class Aufgabe1bonus3 {
    private Aufgabe1bonus3() { }

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

        String operator = EINGABE.nextLine();
        System.out.printf("Welche Operation(+,-,*,/,=,!=,<,<=,>,<=)"
             + "soll ausgefuehrt werden? %n");
        operator = EINGABE.nextLine();

        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n%n",
            zahl1);
        System.out.printf("%d ist %1$x in Hexadezimal und %1$o in Octal %n",
            zahl2);
        if (operator.equals("+")) {
            System.out.printf("%d + %d ist %d%n", zahl1, zahl2, zahl1 + zahl2);
        } else if (operator.equals("-")) {
            System.out.printf("%d - %d ist %d%n", zahl1, zahl2, zahl1 - zahl2);
        } else if (operator.equals("*")) {
            System.out.printf("%d * %d ist %d%n", zahl1, zahl2, zahl1 * zahl2);
        } else if (operator.equals("/")) {
            System.out.printf("%d/%d ist %d%n", zahl1, zahl2, zahl1 / zahl2);
            System.out.printf("%d / %d hat einen Rest von %d%n",
                zahl1, zahl2, zahl1 % zahl2);
        } else if (operator.equals("=")) {
            System.out.printf("%d == %d ist %b%n",
                zahl1, zahl2, zahl1 == zahl2);
        } else if (operator.equals("!=")) {
            System.out.printf("%d != %d ist %b%n",
                zahl1, zahl2, zahl1 != zahl2);
        } else if (operator.equals("<")) {
            System.out.printf("%d < %d ist %b%n",
                zahl1, zahl2, zahl1 < zahl2);
        } else if (operator.equals("<=")) {
            System.out.printf("%d <= %d ist %b%n",
                zahl1, zahl2, zahl1 <= zahl2);
        } else if (operator.equals(">")) {
            System.out.printf("%d > %d ist %b%n",
                zahl1, zahl2, zahl1 > zahl2);
        } else if (operator.equals(">=")) {
            System.out.printf("%d >= %d ist %b%n",
                zahl1, zahl2, zahl1 >= zahl2);
        } else {
            System.out.println("Sie haben keine Operation ausgewaehlt");
        }
    }
}
