package tde3.pedido_cliente.service;

import org.springframework.stereotype.Service;
import tde3.pedido_cliente.models.Cliente;
import tde3.pedido_cliente.models.Pedido;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final ClienteService clienteService;
    private Long nextPedidoId = 1L;  // Gera IDs para pedidos

    public PedidoService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Pedido> findPedidosByClienteId(Long clienteId) {
        Optional<Cliente> cliente = clienteService.findById(clienteId);
        return cliente.map(Cliente::getPedidos).orElse(null);
    }

    public Pedido findPedidoById(Long clienteId, Long pedidoId) {
        Optional<Cliente> cliente = clienteService.findById(clienteId);
        if (cliente.isPresent()) {
            return cliente.get().getPedidos().stream()
                    .filter(p -> p.getId().equals(pedidoId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public Pedido createPedido(Long clienteId, Pedido pedido) {
        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            pedido.setId(nextPedidoId++);  // Gerar um novo ID para o pedido
            cliente.getPedidos().add(pedido);  // Adicionar o pedido à lista do cliente
            return pedido;  // Retornar o pedido criado
        }
        return null;
    }

    public Pedido updatePedido(Long clienteId, Long pedidoId, Pedido pedidoAtualizado) {
        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            // Localiza o pedido existente pelo pedidoId
            Optional<Pedido> pedidoExistenteOpt = cliente.getPedidos().stream()
                    .filter(p -> p.getId().equals(pedidoId))
                    .findFirst();

            if (pedidoExistenteOpt.isPresent()) {
                Pedido pedidoExistente = pedidoExistenteOpt.get();

                // Atualiza os campos do pedido existente
                pedidoExistente.setDescricao(pedidoAtualizado.getDescricao());
                pedidoExistente.setValor(pedidoAtualizado.getValor());

                return pedidoExistente;
            }
        }
        return null;
    }

    public boolean deletePedido(Long clienteId, Long pedidoId) {
        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return cliente.getPedidos().removeIf(p -> p.getId().equals(pedidoId));
        }
        return false;
    }
}
