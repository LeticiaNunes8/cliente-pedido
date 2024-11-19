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
    @JoinColumn(name = "cliente_id")
    private ClienteDAO cliente;

    public void setDescricao(String descricao) {
    }

    public void setValor(Double valor) {

    }
}
