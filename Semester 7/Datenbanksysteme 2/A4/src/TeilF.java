import org.orm.PersistentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeilF {
    private static final int ROW_COUNT = 100;
    public static void main(String[] args) throws PersistentException {
        try {
            Vorlesung[] vorlesungs = Vorlesung.listVorlesungByQuery(null, null);
            int length = Math.min(vorlesungs.length, ROW_COUNT);
            List<String> ainList = new ArrayList();
            List<String> winList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                Vorlesung v = vorlesungs[i];
                if(v.getStudiengang().getKuerzel().equals("AIN")){
                    ainList.add(v.getName());
                } else {
                    winList.add(v.getName());
                }
            }
            for (String element : ainList) {
                if (winList.contains(element)) {
                    System.out.println(element);
                }
            }

        } finally {
            Aufgabe4PersistentManager.instance().disposePersistentManager();
        }
    }


}
