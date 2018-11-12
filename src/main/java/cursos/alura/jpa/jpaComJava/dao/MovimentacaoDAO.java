package cursos.alura.jpa.jpaComJava.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cursos.alura.jpa.jpaComJava.model.Conta;
import cursos.alura.jpa.jpaComJava.model.TipoMovimentacao;

public class MovimentacaoDAO {

	private EntityManager em;

	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
	}
	
	public List<Double> getMediasPorDiaETipo(TipoMovimentacao tipo, Conta conta) {

		// TypedQuery to remove the unchecked compile warning
		TypedQuery<Double> selectMovimentacao = em.createNamedQuery("MediasPorDiaETipo", Double.class);
		selectMovimentacao.setParameter("pConta", conta);
		selectMovimentacao.setParameter("pTipo", tipo);
		
		return selectMovimentacao.getResultList();
		
	}
	
}
