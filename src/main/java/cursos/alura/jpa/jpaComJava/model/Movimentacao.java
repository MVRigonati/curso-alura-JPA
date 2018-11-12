package cursos.alura.jpa.jpaComJava.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "MediasPorDiaETipo", query = "SELECT DISTINCT AVG(m.valor) FROM Movimentacao AS m WHERE "
		+ "m.conta = :pConta AND m.tipo = :pTipo GROUP BY m.data")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	private String descricao;

	@ManyToOne
	private Conta conta;
	
	@ManyToMany
	private List<Categoria> categorias;
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Categoria... categorias) {
		this.categorias = Arrays.asList(categorias);
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "ID - " + this.id + "\t" + this.data.getTime() + " / " + this.descricao;
	}
	
}
