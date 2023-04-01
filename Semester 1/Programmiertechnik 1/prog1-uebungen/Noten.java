//Noten.java
package aufgabe4;
/**
 * Hilfklasse mit den gueltigen HTWG Noten.
 * @author Oemer Sipahi
 * @version 10.12.2020
 */
public final class Noten {
    private Noten() { }
    /**
    * BESTE ist die beste zulässige Htwg Note.
    */
    public static final double BESTE = 1.0;
    /**
    * SCHLECHTESTE ist die schlechteste zulässige Htwg Note.
    */
    public static final double SCHLECHTESTE = 5.0;
    /**
     * istZulaessig prüft ob eine eingegebene Note nach Wert und Format
     * eine gueltige HTWG Note ist.
     * @param note ist ein String.
     * @return ob die Note gueltig ist oder nicht.
     */
    public static boolean istZulaessig(String note) {
        if (note.length() == 3
             && (note.charAt(1) == ',' || note.charAt(1) == '.')
             && Character.isDigit(note.charAt(0))
             && Character.isDigit(note.charAt(2))) {

            switch (Character.getNumericValue(note.charAt(0))) {
            case 1:
            case 2:
            case 3:

                if (note.charAt(2) == '0' || note.charAt(2) == '3'
                     || note.charAt(2) == '7') {
                    return true;
                }
                return false;
            case 4:
            case 5:
                if (note.charAt(2) == '0') {
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
     * toDouble setzt von String zu double.
     * @param note ist ein String.
     * @return den Parameter als double
     */
    public static double toDouble(String note) {
        if (istZulaessig(note)) {
            int a = Character.getNumericValue(note.charAt(0));
            int b = Character.getNumericValue(note.charAt(2));
            double currentnote = a + b * 0.1;

            return currentnote;
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * toString setzt von double zu String .
     * @param note ist ein double.
     * @return den Parameter als String
     */
    public static String toString(double note) {
        if (note <= SCHLECHTESTE && note >= BESTE) {
            return String.format("%.1f", note);
        }
        throw new IllegalArgumentException();
    }
    /**
     *istBestanden prüft eine Note als bestanden bewertet
     *wird oder nicht.
     * @param currentnote ist ein double.
     * @return true wenn sie bestanden ist
     * und false wenn sie nicht bestanden ist.
     */
    public static boolean istBestanden(double currentnote) {
        if (currentnote <= 4.0) {
            return true;
        }
        return false;
    }
    /**
     * bessere vergleicht zwei Noten welche besser ist.
     * @param note ist ein double.
     * @param noteZwei ist ein double.
     * @return gibt die bessere Note aus
     */
    public static double bessere(double note, double noteZwei) {
        if (note > noteZwei) {
            return noteZwei;
        } else {
            return note;
        }
    }
    /**
     * schlechtere vergleicht zwei Noten welche schlechter ist.
     * @param note ist ein double.
     * @param noteZwei ist ein double.
     * @return gibt die schlechtere Note aus
     */
    public static double schlechtere(double note, double noteZwei) {
        if (note < noteZwei) {
            return noteZwei;
        } else {
            return note;
        }
    }
}
