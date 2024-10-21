package tde3.pedido_cliente.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Long id;
    private String descricao;
    private Double valor;
}
