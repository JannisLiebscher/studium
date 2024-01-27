import org.orm.PersistentException;
import org.orm.PersistentTransaction;

public class TeilC {
    public static void main(String[] args) throws PersistentException {
        try {
            PersistentTransaction t = Aufgabe4PersistentManager.instance().getSession().beginTransaction();


            Studiengang ain = new Studiengang();
            ain.setKuerzel("AIN");
            ain.setAbschluss("Bachelor of Science");
            ain.setName("Angewandte Informatik");
            ain.save();

            Studiengang win = new Studiengang();
            win.setKuerzel("WIN");
            win.setAbschluss("Bachelor of Science");
            win.setName("Wirtschaftsinformatik");
            win.save();

            Vorlesung dbsys = new Vorlesung();
            dbsys.setName("Datenbanksysteme");
            dbsys.setEcts(6);
            dbsys.setStudiengang(ain);
            dbsys.setSws(4);
            dbsys.save();

            Vorlesung dbsys2 = new Vorlesung();
            dbsys2.setName("Datenbanksysteme 2");
            dbsys2.setEcts(6);
            dbsys2.setStudiengang(ain);
            dbsys2.setSws(4);
            dbsys2.save();

            Vorlesung bsys = new Vorlesung();
            bsys.setName("Betriebssysteme");
            bsys.setEcts(6);
            bsys.setStudiengang(ain);
            bsys.setSws(4);
            bsys.save();

            Vorlesung bwl1 = new Vorlesung();
            bwl1.setName("BWL 1");
            bwl1.setEcts(6);
            bwl1.setStudiengang(win);
            bwl1.setSws(4);
            bwl1.save();

            Vorlesung bwl2 = new Vorlesung();
            bwl2.setName("BWL 2");
            bwl2.setEcts(6);
            bwl2.setStudiengang(win);
            bwl2.setSws(4);
            bwl2.save();

            Vorlesung prog1 = new Vorlesung();
            prog1.setName("Prog");
            prog1.setEcts(6);
            prog1.setStudiengang(ain);
            prog1.setSws(4);
            prog1.save();

            Vorlesung prog1win = new Vorlesung();
            prog1win.setName("Prog");
            prog1win.setEcts(6);
            prog1win.setStudiengang(win);
            prog1win.setSws(4);
            prog1win.save();

            t.commit();
        } finally {
            Aufgabe4PersistentManager.instance().disposePersistentManager();
        }


    }
}
