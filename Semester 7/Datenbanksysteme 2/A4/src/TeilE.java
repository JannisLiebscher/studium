import org.orm.PersistentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeilE {
    private static final int ROW_COUNT = 100;
    public static void main(String[] args) throws PersistentException {
        try {
            Vorlesung[] vorlesungs = Vorlesung.listVorlesungByQuery(null, null);
            int length = Math.min(vorlesungs.length, ROW_COUNT);
            Map map = new HashMap();
            for (int i = 0; i < length; i++) {
                Vorlesung v = vorlesungs[i];
                if(!map.containsKey(v.getStudiengang())){
                    map.put(v.getStudiengang(),v.getEcts());
                } else {
                    int etcs = (int) map.get(v.getStudiengang());
                    map.replace(v.getStudiengang(),etcs + v.getEtcs());
                }
            }
            System.out.println(map.toString());
        } finally {
            Aufgabe4PersistentManager.instance().disposePersistentManager();
        }
    }
}
