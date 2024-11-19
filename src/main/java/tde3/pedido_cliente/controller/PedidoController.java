package tde3.pedido_cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tde3.pedido_cliente.DAO.PedidoDAO;
import tde3.pedido_cliente.models.Pedido;
import tde3.pedido_cliente.service.PedidoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes/{clienteId}/pedidos")
@Tag(name = "Pedido", description = "API para gerenciamento de pedidos de clientes.")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Listar todos os pedidos de um cliente")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PedidoDAO>> listarPedidos(@PathVariable Long clienteId) {
        List<PedidoDAO> pedidos = pedidoService.getAllPedidos();

        // Verifica se a lista está vazia ou nula
        if (pedidos == null || pedidos.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404
        }
        return ResponseEntity.ok(pedidos); // Retorna 200 com a lista de pedidos
    }

    @Operation(summary = "Obter um pedido específico de um cliente")
    @GetMapping(value = "/{pedidoId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PedidoDAO> obterPedido(@PathVariable Long clienteId,
                                                 @PathVariable Long pedidoId) {
        // Utiliza o metodo getPedidoById, que retorna um Optional
        Optional<PedidoDAO> pedido = pedidoService.getPedidoById(pedidoId);

        // Verifica se o pedido foi encontrado e retorna a resposta apropriada
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Criar um novo pedido para um cliente")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PedidoDAO> criarPedido(@PathVariable Long clienteId, @RequestBody PedidoDAO pedido) {
        PedidoDAO novoPedido = pedidoService.createPedido(pedido);

        // Verifica se o pedido foi criado com sucesso
        if (novoPedido != null) {
            // Retorna um ResponseEntity com o status CREATED (201) e o pedido no corpo
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } else {
            // Caso o pedido não tenha sido criado, retorna o status NOT FOUND (404)
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar um pedido existente")
    @PutMapping(value = "/pedidos/{pedidoId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PedidoDAO> atualizarPedido(
            @PathVariable Long pedidoId,
            @RequestBody Pedido pedidoAtualizado) {
        try {
            // Atualiza o pedido
            PedidoDAO pedidoAtualizadoResult = pedidoService.updatePedido(pedidoId, pedidoAtualizado);
            return ResponseEntity.ok(pedidoAtualizadoResult);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um pedido de um cliente")
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        boolean deletado = pedidoService.deletePedido(pedidoId);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
