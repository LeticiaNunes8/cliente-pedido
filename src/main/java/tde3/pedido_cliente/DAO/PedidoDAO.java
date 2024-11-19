package tde3.pedido_cliente.DAO;

import tde3.pedido_cliente.models.Cliente;

import javax.persistence.*;

@Entity
public class PedidoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
