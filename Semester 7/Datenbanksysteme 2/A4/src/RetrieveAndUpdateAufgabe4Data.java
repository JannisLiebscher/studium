/**
 * Licensee: emanz(HTWG Konstanz)
 * License Type: Academic
 */
import org.orm.*;
public class RetrieveAndUpdateAufgabe4Data {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = Aufgabe4PersistentManager.instance().getSession().beginTransaction();
		try {
			Vorlesung vorlesung = Vorlesung.loadVorlesungByQuery(null, null);
			// Update the properties of the persistent object
			vorlesung.save();
			Studiengang studiengang = Studiengang.loadStudiengangByQuery(null, null);
			// Update the properties of the persistent object
			studiengang.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateAufgabe4Data retrieveAndUpdateAufgabe4Data = new RetrieveAndUpdateAufgabe4Data();
			try {
				retrieveAndUpdateAufgabe4Data.retrieveAndUpdateTestData();
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
