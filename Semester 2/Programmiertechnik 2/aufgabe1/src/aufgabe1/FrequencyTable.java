package aufgabe1;

/**
 * @author oliverbittel
 * @since 12.2.2020
 */
public interface FrequencyTable {
    /**
     * Liefert die Anzahl der W&ouml;rter in dieser Tabelle zur&uuml;ck.
     *
     * @return Anzahl der H&auml;ufigkeitseintr&auml;ge.
     */
    int size();

    /**
     * Pr&uuml;ft, ob die Tabelle leer ist.
     *
     * @return true, falls diese Tabelle leer ist, sonst false.
     */
    boolean isEmpty();

    /**
     * L&ouml;scht die Tabelle.
     */
    void clear();

    /**
     * F&uuml;gt das Wort w mit der H&auml;ufigkeit f zu dieser Tabelle dazu.
     * Falls w bereits in der Tabelle enthalten ist,
     * wird die H&auml;ufigkeit um f erhÃ¶ht.
     *
     * @param w Wort.
     * @param f H&auml;ufigkeit.
     */
    void add(String w, int f);

    /**
     * F&uuml;gt das Wort w mit der H&auml;ufigkeit 1 zu dieser Tabelle dazu.
     * Falls w bereits in der Tabelle enthalten ist,
     * wird die H&auml;ufigkeit um 1 erhÃ¶ht.
     *
     * @param w Wort.
     */
    void add(String w);

    /**
     * F&uuml;gt alle W&ouml;rter mit ihren H&auml;ufigkeiten aus fq zu dieser Tabelle dazu.
     * H&auml;ufigkeiten fÃ¼r gleiche W&ouml;rter werden addiert.
     * fq bleibt unver&auml;ndert.
     *
     * @param fq H&auml;ufigkeitstabelle.
     */
    void addAll(FrequencyTable fq);

    /**
     * Liefert das Wort mit seiner HÃ¤ufigkeit zur&uuml;ck, das mit seiner H&auml;ufigkeit an Position pos steht.
     * get(0) liefert das hÃ¤ufigste Wort zurÃ¼ck,
     * get(1) liefert das zweithÃ¤ufigste Wort zurÃ¼ck, usw.
     *
     * @param pos Position.
     * @return Wort mit H&auml;ufigkeit oder null,
     * falls die Tabelle weniger als pos-1 Elemente  enth&auml;lt.
     */
    Word get(int pos);

    /**
     * Liefert die H&auml;ufigkeit des Worts w zur&uuml;ck.
     * Falls das Wort nicht vorkommt, wird 0 zur&uuml;ckgeliefert.
     *
     * @param w Wort
     * @return H&auml;ufigkeit.
     */
    int get(String w);

    /**
     * Sammelt alle W&ouml;rter mit der gr&ouml;ssten H&auml;ufigkeit und speichert sie in fq.
     * Beispiel:
     * Falls tab1 = {"ein":3, "das":3, "ist":2, "der:1", "die":1}, dann
     * gilt nach Aufruf von tab1.collectMostFrequent(tab2):
     * tab2 = {"ein":3, "das":3}.
     *
     * @param fq H&auml;ufigkeitstabelle.
     */
    void collectMostFrequent(FrequencyTable fq);

    /**
     * Sammelt alle W&ouml;rter mit der H&auml;ufigkeit 1 und speichert sie in fq.
     *
     * @param fq H&auml;ufigkeitstabelle.
     */
    void collectLeastFrequent(FrequencyTable fq);
}