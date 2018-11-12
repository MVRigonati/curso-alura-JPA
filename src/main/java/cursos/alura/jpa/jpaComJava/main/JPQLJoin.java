package cursos.alura.jpa.jpaComJava.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cursos.alura.jpa.jpaComJava.model.Categoria;
import cursos.alura.jpa.jpaComJava.model.Movimentacao;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class JPQLJoin {

	public static void main(String[] args) {
		
		Categoria cat = new Categoria();
		cat.setId(1); // Buscará pelo ID 1
		
		String jpql = "SELECT m FROM Movimentacao AS m JOIN m.categorias AS c WHERE c = :pCategoria";
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();
		
		Query selectMovimentacao = em.createQuery(jpql);
		
		// Busca uma categoria com as informações iguais às deste objeto
		selectMovimentacao.setParameter("pCategoria", cat);
		
		List<Movimentacao> result = selectMovimentacao.getResultList();
		
		for (Movimentacao mov : result) {
			System.out.println(mov);
			System.out.println("Quantidade de movimentações para esta conta: " + mov.getConta().getQuantidadeMovimentacoes());
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
