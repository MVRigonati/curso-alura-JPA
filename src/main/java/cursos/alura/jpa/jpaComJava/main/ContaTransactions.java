package cursos.alura.jpa.jpaComJava.main;

import java.util.List;

import javax.persistence.EntityManager;

import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class ContaTransactions {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setBanco("Caixa Econômica");
		conta.setAgencia("123");
		conta.setNumero("456");
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();
		
		em.persist(conta);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void removeEntity(int id) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		
		em.getTransaction().begin();
		
			Conta result = em.find(Conta.class, id);
			em.remove(result);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void changeEntity(int id) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		
		em.getTransaction().begin();
		
			Conta result = em.find(Conta.class, id);
			
			result.setAgencia("666");
			result.setTitular("Demo");
			
			/* Não é necessário 'merge', pois como foi utilizado o método
			 * 'find' este objeto já é managed, então ao realizar 'commit'
			 * a JPA já irá realizar as modificações ao dos sets a cima.
			 */
			//em.merge(result);
		
		em.getTransaction().commit();
		em.close();
		
		System.out.println(result);
		
	}
	
	private static void namedQuery() {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		
		List<Conta> resultList = em.createNamedQuery("selectAll", Conta.class).getResultList();
		
		for (Conta conta : resultList) {
			System.out.println(conta);
		}
		
		em.close();

	}
	
}
