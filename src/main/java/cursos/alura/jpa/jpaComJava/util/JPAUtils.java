package cursos.alura.jpa.jpaComJava.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtils {

	public static EntityManager getEntityManager(String persistenceUnit) {
		return Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}
	
}
