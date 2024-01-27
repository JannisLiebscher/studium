/**
 * Licensee: emanz(HTWG Konstanz)
 * License Type: Academic
 */
import org.orm.*;
public class CreateAufgabe4DatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(Aufgabe4PersistentManager.instance());
			Aufgabe4PersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
