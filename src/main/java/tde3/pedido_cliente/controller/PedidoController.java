package tde3.pedido_cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obter um pedido específico de um cliente")
    @GetMapping(value = "/{pedidoId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Pedido> obterPedido(@PathVariable Long clienteId,
                                              @PathVariable Long pedidoId) {
        // Utiliza o metodo getPedidoById, que retorna um Optional
        Optional<Pedido> pedido = pedidoService.getPedidoById(pedidoId);

        // Verifica se o pedido foi encontrado e retorna a resposta apropriada
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Criar um novo pedido para um cliente")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Pedido> criarPedido(@PathVariable Long clienteId, @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.createPedido(pedido);
        return novoPedido != null ? ResponseEntity.ok(novoPedido) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar um pedido existente")
    @PutMapping(value = "/pedidos/{pedidoId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable Long pedidoId,
            @RequestBody Pedido pedidoAtualizado) {
        try {
            // Atualiza o pedido
            Pedido pedidoAtualizadoResult = pedidoService.updatePedido(pedidoId, pedidoAtualizado);
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
