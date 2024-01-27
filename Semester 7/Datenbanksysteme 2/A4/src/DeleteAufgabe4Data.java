/**
 * Licensee: emanz(HTWG Konstanz)
 * License Type: Academic
 */
import org.orm.*;
public class DeleteAufgabe4Data {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = Aufgabe4PersistentManager.instance().getSession().beginTransaction();
		try {
			Vorlesung vorlesung = Vorlesung.loadVorlesungByQuery(null, null);
			vorlesung.delete();
			Studiengang studiengang = Studiengang.loadStudiengangByQuery(null, null);
			studiengang.delete();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteAufgabe4Data deleteAufgabe4Data = new DeleteAufgabe4Data();
			try {
				deleteAufgabe4Data.deleteTestData();
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
