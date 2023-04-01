// Noten.java
package aufgabe4;

/**
 * Hilfklasse mit den gueltigen HTWG Noten.
 * @author Jannis Liebscher
 * @version 10.12.2020
 */
public final class Noten {
    private Noten() { }

    /**
     *BESTE ist die beste zulässige HTWG note.
     */
    public static final double BESTE = 1.0;
    /**
    *SCHLECHTESTE ist die schlechteste zulässige HTWG note.
    */
    public static final double SCHLECHTESTE = 5.0;

    /**
     * istZulaessig prüft ob eine eingegebene Note nach Wert und Format
     * eine gueltige HTWG Note ist.
     * @param n ist ein String.
     * @return ob die Note gueltig ist oder nicht.
     */
    public static boolean istZulaessig(String n) {
        if (n.length() == 3
             && (n.charAt(1) == ',' || n.charAt(1) == '.')
             && Character.isDigit(n.charAt(0))
             && Character.isDigit(n.charAt(2))) {

            switch (Character.getNumericValue(n.charAt(0))) {
            case 1:
            case 2:
            case 3:
                if (n.charAt(2) == '0' || n.charAt(2) == '3'
                     || n.charAt(2) == '7') {
                    return true;
                }
                return false;

            case 4:
            case 5:
                if (n.charAt(2) == '0') {
                    return true;
                }
                return false;

            default:
                return false;
            }
        }
        return false;
    }
    /**
     * toDouble wandelt eine gueltige HTWG note in ein double um.
     * @param n ist ein String.
     * @return Eine HTWG Note als double.
     */
    public static double toDouble(String n) {
        double noteToD;
        if (istZulaessig(n)) {
            noteToD = Character.getNumericValue(n.charAt(0))
                + Character.getNumericValue(n.charAt(2)) * 0.1;
            return noteToD;
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * toString wandelt eine Note in ein String um.
     * @param note ist ein String.
     * @return Eine Note als String.
     */
    public static String toString(double note) {
        if (note <= SCHLECHTESTE && note >= BESTE) {
            return String.format("%.1f", note);
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * istBEstanden prüft ob eine Note als Bestanden gewertet wird.
     * @param note ist eine Gleitkommazahl.
     * @return ob eine Note Bestanden ist oder nicht.
     */
    public static boolean istBestanden(double note) {
        if (note <= 4.0) {
            return true;
        }
        return false;
    }
    /**
     * bessere liefert die bessere zweier Noten.
     * @param ersteNote ist eine Gleitkommazahl.
     * @param zweiteNote ist eine andere Gleitkommazahl.
     * @return das Minimum beider Argumente.
     */
    public static double bessere(double ersteNote, double zweiteNote) {
        if (ersteNote <= zweiteNote) {
            return ersteNote;
        } else {
            return zweiteNote;
        }
    }
    /**
     * schlechtere liefert die schlechtere zweier Noten.
     * @param ersteNote ist eine Gleitkommazahl.
     * @param zweiteNote ist eine andere Gleitkommazahl.
     * @return das Maximum beider Argumente.
     */
    public static double schlechtere(double ersteNote, double zweiteNote) {
        if (ersteNote >= zweiteNote) {
            return ersteNote;
        } else {
            return zweiteNote;
        }
    }

}
