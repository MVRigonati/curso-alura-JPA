package cursos.alura.jpa.jpaComJava.main;

import javax.persistence.EntityManager;

import cursos.alura.jpa.jpaComJava.model.Cliente;
import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class ContaClienteRelation {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setEndereco("Rua Fulano, 123");
		cliente.setNome("Leonardo");
		cliente.setProfissao("Professor");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		cliente.setConta(conta);
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();
		
		em.persist(cliente);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
