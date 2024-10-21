package tde3.pedido_cliente.service;

import org.springframework.stereotype.Service;
import tde3.pedido_cliente.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();
    private Long nextClienteId = 1L;

    public List<Cliente> findAll() {
        return clientes;
    }

    public Optional<Cliente> findById(Long id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Cliente create(Cliente cliente) {
        cliente.setId(nextClienteId++);
        clientes.add(cliente);
        return cliente;
    }

    public Cliente update(Long id, Cliente clienteAtualizado) {
        return findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            return cliente;
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return clientes.removeIf(cliente -> cliente.getId().equals(id));
    }
}
