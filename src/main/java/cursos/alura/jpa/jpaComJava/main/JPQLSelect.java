package cursos.alura.jpa.jpaComJava.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.model.Movimentacao;
import cursos.alura.jpa.jpaComJava.model.TipoMovimentacao;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class JPQLSelect {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(1); // Buscará pelo ID 1
		
		String jpql = "SELECT m FROM Movimentacao as m WHERE m.conta = :pConta "
				+ "and m.tipo = :pTipo "
				+ "order by m.valor desc";
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();
		
		Query selectMovimentacao = em.createQuery(jpql);
		
		// Busca uma conta com as informações iguais as deste objeto
		selectMovimentacao.setParameter("pConta", conta);
		selectMovimentacao.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> result = selectMovimentacao.getResultList();
		
		for (Movimentacao movimentacao : result) {
			System.out.println(movimentacao);
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
