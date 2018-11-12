package cursos.alura.jpa.jpaComJava.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name="selectAll", query="SELECT d FROM Conta d")
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	/* 
	 * Como o relacionamento foi criado pela outra parte, é necessário adicionar
	 * um atributo para esta anotação.
	 * "mappedBy" irá dizer à JPA que a classe Movimentacao já criou o relacionamento,
	 * isso irá evitar que outra tabela seja criada, e também irá possibilitar
	 * que a consulta retorne este compo preenchido corretamente com todas as
	 * movimentações que pertencem a uma determinada conta.
	 * Para este atributo passamos o nome do atributo na outra classe que realizou
	 * o mapeamento da relação. 
	 * 
	 */
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public List<Movimentacao> getMovimentacoes() {
		return this.movimentacoes;
	}
	
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public int getQuantidadeMovimentacoes() {
		return this.movimentacoes.size();
	}
	
	@Override
	public String toString() {
		return "ID - " + this.id + "\t" +
				this.titular + ": " + this.agencia + "/" + this.numero;
	}

}
