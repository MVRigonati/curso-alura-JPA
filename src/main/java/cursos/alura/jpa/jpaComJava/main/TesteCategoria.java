package cursos.alura.jpa.jpaComJava.main;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import cursos.alura.jpa.jpaComJava.model.Categoria;
import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.model.Movimentacao;
import cursos.alura.jpa.jpaComJava.model.TipoMovimentacao;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class TesteCategoria {

	public static void main(String[] args) {
		
		Categoria catNegocio = new Categoria("Negocio");
		Categoria catViagem = new Categoria("Viagem");
		
		Conta conta = new Conta();
		conta.setId(1);
		
		Movimentacao mov1 = new Movimentacao();
		mov1.setData(Calendar.getInstance());
		mov1.setDescricao("Viajem para valhala");
		mov1.setTipo(TipoMovimentacao.SAIDA);
		mov1.setValor(new BigDecimal(100));
		mov1.setCategorias(catNegocio, catViagem);
		mov1.setConta(conta);
		
		Movimentacao mov2 = new Movimentacao();
		mov2.setData(Calendar.getInstance());
		mov2.setDescricao("Viajem para Helheim");
		mov2.setTipo(TipoMovimentacao.SAIDA);
		mov2.setValor(new BigDecimal(300));
		mov2.setCategorias(catNegocio, catViagem);
		mov2.setConta(conta);
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();
		
		// É necessário persistir as Categorias que ainda não estão no banco,
		// pois as Movimentações utilizam estas categorias.
		em.persist(catViagem);
		em.persist(catNegocio);
		
		em.persist(mov1);
		em.persist(mov2);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void removeEntity(int id) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		
		em.getTransaction().begin();
		
			Movimentacao result = em.find(Movimentacao.class, id);
			em.remove(result);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void updateEntity(int id) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		
		em.getTransaction().begin();
		
			Movimentacao result = em.find(Movimentacao.class, id);
			result.setId(3);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
