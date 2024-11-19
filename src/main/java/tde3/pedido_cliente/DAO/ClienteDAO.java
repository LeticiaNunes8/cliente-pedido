package tde3.pedido_cliente.DAO;


import tde3.pedido_cliente.models.Pedido;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClienteDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
}
