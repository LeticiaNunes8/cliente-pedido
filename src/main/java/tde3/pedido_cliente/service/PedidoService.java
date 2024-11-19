package tde3.pedido_cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tde3.pedido_cliente.DAO.PedidoDAO;
import tde3.pedido_cliente.models.Pedido;
import tde3.pedido_cliente.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //CRUD DO PROJETO

    public List<PedidoDAO> getAllPedidos(){
        return pedidoRepository.findAll();
    }

    public Optional<PedidoDAO> getPedidoById(Long id){
        return pedidoRepository.findById(id);
    }

    public PedidoDAO createPedido(Long clienteId, PedidoDAO pedido){
        return pedidoRepository.save(clienteId, pedido);
    }

    public PedidoDAO updatePedido(Long pedidoId, Pedido pedidoAtualizado) {
        // Busca o pedido pelo ID
        PedidoDAO pedidoExistente = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza as informações do pedido
        pedidoExistente.setDescricao(pedidoAtualizado.getDescricao());
        pedidoExistente.setValor(pedidoAtualizado.getValor());
        // Outras propriedades podem ser atualizadas conforme necessário

        // Salva o pedido atualizado no banco de dados
        return pedidoRepository.save(pedidoExistente);
    }

    public boolean deletePedido(Long id){
        pedidoRepository.deleteById(id);
        return false;
    }


























//    private final ClienteService clienteService;
//    private Long nextPedidoId = 1L;  // Gera IDs para pedidos
//
//    public PedidoService(ClienteService clienteService) {
//        this.clienteService = clienteService;
//    }
//
//    public List<Pedido> findPedidosByClienteId(Long clienteId) {
//        Optional<Cliente> cliente = clienteService.findById(clienteId);
//        return cliente.map(Cliente::getPedidos).orElse(null);
//    }
//
//    public Pedido findPedidoById(Long clienteId, Long pedidoId) {
//        Optional<Cliente> cliente = clienteService.findById(clienteId);
//        if (cliente.isPresent()) {
//            return cliente.get().getPedidos().stream()
//                    .filter(p -> p.getId().equals(pedidoId))
//                    .findFirst()
//                    .orElse(null);
//        }
//        return null;
//    }
//
//    public Pedido createPedido(Long clienteId, Pedido pedido) {
//        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
//
//        if (clienteOpt.isPresent()) {
//            Cliente cliente = clienteOpt.get();
//            pedido.setId(nextPedidoId++);  // Gerar um novo ID para o pedido
//            cliente.getPedidos().add(pedido);  // Adicionar o pedido à lista do cliente
//            return pedido;  // Retornar o pedido criado
//        }
//        return null;
//    }
//
//    public Pedido updatePedido(Long clienteId, Long pedidoId, Pedido pedidoAtualizado) {
//        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
//
//        if (clienteOpt.isPresent()) {
//            Cliente cliente = clienteOpt.get();
//
//            // Localiza o pedido existente pelo pedidoId
//            Optional<Pedido> pedidoExistenteOpt = cliente.getPedidos().stream()
//                    .filter(p -> p.getId().equals(pedidoId))
//                    .findFirst();
//
//            if (pedidoExistenteOpt.isPresent()) {
//                Pedido pedidoExistente = pedidoExistenteOpt.get();
//
//                // Atualiza os campos do pedido existente
//                pedidoExistente.setDescricao(pedidoAtualizado.getDescricao());
//                pedidoExistente.setValor(pedidoAtualizado.getValor());
//
//                return pedidoExistente;
//            }
//        }
//        return null;
//    }
//
//    public boolean deletePedido(Long clienteId, Long pedidoId) {
//        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
//        if (clienteOpt.isPresent()) {
//            Cliente cliente = clienteOpt.get();
//            return cliente.getPedidos().removeIf(p -> p.getId().equals(pedidoId));
//        }
//        return false;
//    }
}
