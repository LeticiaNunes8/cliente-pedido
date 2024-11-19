package tde3.pedido_cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tde3.pedido_cliente.DAO.ClienteDAO;
import tde3.pedido_cliente.service.ClienteService;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "API para gerenciamento de clientes.")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ClienteDAO>> listarClientes() {
        List<ClienteDAO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obter um cliente por ID")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteDAO> obterCliente(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar um novo cliente")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteDAO> criarCliente(@RequestBody ClienteDAO cliente) {
        ClienteDAO novoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @Operation(summary = "Atualizar um cliente")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteDAO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDAO clienteAtualizado) {
        ClienteDAO cliente = clienteService.updateCliente(clienteAtualizado);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Excluir um cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        boolean deletado = clienteService.deleteCliente(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
