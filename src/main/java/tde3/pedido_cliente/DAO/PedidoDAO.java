package tde3.pedido_cliente.DAO;

import jakarta.persistence.*;

@Entity
public class PedidoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Essa coluna vai armazenar o ID do cliente
    private ClienteDAO cliente;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ClienteDAO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDAO cliente) {
        this.cliente = cliente;
    }
}
