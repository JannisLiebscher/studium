// FachNoten.java /
package aufgabe5;

public final class FachNote {
    /**
     *fach ist der Fachname.
     */
    public final String fach;

    /**note ist di zugehörige Note.
     */
    public final Note note;

    /**
     *Fachnote ist ein Objekt in dem Fach und zugehörige Note gespeicher werden.
     *@param fach ist das Fach
     *@param note ist die erreichte Note
     */
    public FachNote(String fach, Note note) {
        if (fach != null && note != null && fach.length() != 0) {
            this.fach = fach;
            this.note = note;
            return;
        }
        throw new IllegalArgumentException();
    }
}
