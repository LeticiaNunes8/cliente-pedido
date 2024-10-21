package tde3.pedido_cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tde3.pedido_cliente.models.Pedido;
import tde3.pedido_cliente.service.PedidoService;

import java.util.List;

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
        List<Pedido> pedidos = pedidoService.findPedidosByClienteId(clienteId);
        return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obter um pedido específico de um cliente")
    @GetMapping(value = "/{pedidoId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Pedido> obterPedido(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.findPedidoById(clienteId, pedidoId);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar um novo pedido para um cliente")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Pedido> criarPedido(@PathVariable Long clienteId, @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.createPedido(clienteId, pedido);
        return novoPedido != null ? ResponseEntity.ok(novoPedido) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar um pedido de um cliente")
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        boolean deletado = pedidoService.deletePedido(clienteId, pedidoId);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
