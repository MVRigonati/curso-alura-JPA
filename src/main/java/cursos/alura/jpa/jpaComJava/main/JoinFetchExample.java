package cursos.alura.jpa.jpaComJava.main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class JoinFetchExample {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();

		// Consulta todas as contas da tabela
		// "JOIN FETCH" fará todas as consultas necessárias em apenas um select,
		// ao invés de ir buscando no for each quando for solicitado.
		TypedQuery<Conta> selectMovimentacao =
				em.createQuery("SELECT c FROM Conta AS c JOIN FETCH c.movimentacoes", Conta.class);
		// TypedQuery to remove the unchecked compile warning
		
		List<Conta> result = selectMovimentacao.getResultList();
		
		for (Conta cont : result) {
			System.out.println(cont);
			System.out.println("Movimentações:");
			System.out.println( Arrays.toString(cont.getMovimentacoes().toArray()) );
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
