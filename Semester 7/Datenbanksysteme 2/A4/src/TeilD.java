import org.orm.PersistentException;

public class TeilD {
    private static final int ROW_COUNT = 100;
    public static void main(String[] args) throws PersistentException {
        try {
            System.out.println("Listing Vorlesung...");
            Vorlesung[] vorlesungs = Vorlesung.listVorlesungByQuery(null, null);
            int length = Math.min(vorlesungs.length, ROW_COUNT);
            for (int i = 0; i < length; i++) {
                Vorlesung v = vorlesungs[i];
                System.out.println("name: " + v.getName() + " etcs: " + v.getEtcs() + " Studiengang: " + v.getStudiengang());
            }
            System.out.println(length + " record(s) retrieved.");
        } finally {
            Aufgabe4PersistentManager.instance().disposePersistentManager();
        }
    }
}
