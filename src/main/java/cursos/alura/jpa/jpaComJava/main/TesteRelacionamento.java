package cursos.alura.jpa.jpaComJava.main;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.model.Movimentacao;
import cursos.alura.jpa.jpaComJava.model.TipoMovimentacao;
import cursos.alura.jpa.jpaComJava.util.JPAUtils;

public class TesteRelacionamento {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);
		
		Movimentacao mov = new Movimentacao();
		mov.setData( Calendar.getInstance() );
		mov.setDescricao( "Compra de Almas" );
		mov.setTipo( TipoMovimentacao.SAIDA );
		mov.setValor( new BigDecimal(200) );
		mov.setConta(conta);
		
		em.persist(mov);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void getMovimentacao(int id) {
		
		EntityManager em = JPAUtils.getEntityManager("financas");
		Movimentacao result = em.find(Movimentacao.class, id);
		em.close();
		
		System.out.println(result);

	}
	
}
