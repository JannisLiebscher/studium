/**
 * Licensee: emanz(HTWG Konstanz)
 * License Type: Academic
 */
import org.orm.*;
public class CreateAufgabe4Data {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = Aufgabe4PersistentManager.instance().getSession().beginTransaction();
		try {
			Vorlesung vorlesung = Vorlesung.createVorlesung();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : sws, ects, studiengang
			vorlesung.save();
			Studiengang studiengang = Studiengang.createStudiengang();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : vorlesung, kuerzel
			studiengang.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateAufgabe4Data createAufgabe4Data = new CreateAufgabe4Data();
			try {
				createAufgabe4Data.createTestData();
			}
			finally {
				Aufgabe4PersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
