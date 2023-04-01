// Noten.java /
package aufgabe5;


/**
 * eine Noten <em>(value-based class)</em> ist mittels Fabrikmethoden
 * instanziierbar (sie ist Bauplan f&uuml;r Wertobjekte),
 * hat keine Unterklassen und alle ihre Instanzvariablen sind konstant
 * (Wertobjekte sind <em>immutable objects</em>).
 * <p>
 * Fabrikmethoden sind Klassenmethoden, die eine Referenz auf ein
 * Wertobjekt der Klasse liefern. Zur Instanziierung verwenden die
 * Fabrikmethoden die privaten Konstruktoren der Klasse.
 * Ein Noten sollte keine &ouml;ffentlichen Konstruktoren haben.</p>
 * <p>
 * Unterklassen werden durch <code>final</code>-Markierung der Klasse
 * verhindert.</p>
 * <p>
 * Eine Noten muss die <code>java.lang.Object</code>-Methoden
 * <code>equals</code>, <code>hashCode</code> und <code>toString</code>
 * &uuml;berschreiben.</p>
 * <p>
 * Eine Noten muss die Schnittstelle <code>java.lang.Comparable</code>
 * implementieren, wenn es eine nat&uuml;rliche Ordnung f&uuml;r die
 * Wertobjekte der Klasse gibt.</p>
 * <p>
 * Eine Noten sollte die Schnittstelle <code>java.util.Formattable</code>
 * implementieren, wenn die String-Darstellung der Wertobjekte nicht durch
 * Formatierung (z.B. in <code>printf</code>) k&uuml;rzbar sein soll.</p>
 */
public final class Note {

    private final int note;

    /**
     *ZULAESSIGENOTEN enthält alle zulaessigen Noten.
     */
    private static final int[] ZULAESSIGENOTEN = {
        10,
        13,
        17,
        20,
        23,
        27,
        30,
        33,
        37,
        40,
        50
    };
    /**
     *BESTE ist die beste zulässige HTWG note.
     */
    public static final Note BESTE = new Note(ZULAESSIGENOTEN[0]);

    /**
     *BESTE ist die beste zulässige HTWG note.
     */
    public static final Note SCHLECHTESTE =
        new Note(ZULAESSIGENOTEN[ZULAESSIGENOTEN.length - 1]);

    private Note(int note) {
        this.note = note;
    }

    /**
     *valueOF liefert ein Notenobjekt.
     *@param n ist der Wert der Note
     *@return gibt ein Notenobjekt zurück
     */
    public static Note valueOf(int n) {
        //-----------------------------------Prüfen ob Note zulaessig ist

        //-----------------------------------Note Objekt zurückgeben
        if (istZulaessig(n)) {
            Note note = new Note(n);
            return note;
        }
        throw new IllegalArgumentException("unzulässige Note:" + n);
    }

    /**
     *vlueOF liefert ein Notenobjekt.
     *@param s ist der Wert der Note
     *@return gibt ein Notenobjekt zurück
     */
    public static Note valueOf(String s) {

        if (s != null
            && istZulaessig(s)) {

            int i = Character.getNumericValue(s.charAt(0)) * 10
                 + Character.getNumericValue(s.charAt(2));
            return new Note(i);
        }
        throw new IllegalArgumentException("unzulässige Note:" + s);
    }

    /**
     *intValue liefert den int Wert einer Note.
     *@return gibt den int wert der Note zurück
     */
    public int intValue() {
        return this.note;
    }

    /**
     *istBestanden Prüft ob ein Notenwert als bestanden zählt.
     *@param n ist der Wert der Note
     *@return true wenn bestanden, ansonnsten false
     */
    public static boolean istBestanden(int n) {
        if (n <= ZULAESSIGENOTEN[9]) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(/* final Note this */) {
        double kommaVerschiebung = 0.1 * this.note;
        return String.format("%.1f", kommaVerschiebung);
    }

    @Override
    public boolean equals(/* final Note this, */ Object o) {
        if (o instanceof Note) {
            Note that = (Note) o;
            return this.note == that.note;
        }
        return false;
    }

    @Override
    public int hashCode(/* final Note this */) {
        return this.note;
    }

    /**
     *istZulaessig prüft, ob die eingegebene Note eine HTWG Note ist.
     *@param n ist die Note als String
     *@return true wenn zulaessig, ansonnsten false
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
     *istZulaessig prüft, ob die eingegebene Note eine HTWG Note ist.
     *@param n ist die Note als int
     *@return true wenn zulaessig, ansonnsten false
     */
    public static boolean istZulaessig(int n) {
        for (int x: ZULAESSIGENOTEN) {
            if (x == n) {
                return true;
            }
        }
        return false;
    }
}
