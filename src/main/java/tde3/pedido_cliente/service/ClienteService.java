package tde3.pedido_cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tde3.pedido_cliente.DAO.ClienteDAO;
import tde3.pedido_cliente.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //CRUD DO PROJETO

    public List<ClienteDAO> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteDAO> getClienteById(Long id){
        return clienteRepository.findById(id);
    }

    public ClienteDAO createCliente(ClienteDAO cliente){
        return clienteRepository.save(cliente);
    }

    public ClienteDAO updateCliente(ClienteDAO cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean deleteCliente(Long id){
        clienteRepository.deleteById(id);
        return false;
    }



























//    private List<Cliente> clientes = new ArrayList<>();
//    private Long nextClienteId = 1L;
//
//    public List<Cliente> findAll() {
//        return clientes;
//    }
//
//    public Optional<Cliente> findById(Long id) {
//        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
//    }
//
//    public Cliente create(Cliente cliente) {
//        cliente.setId(nextClienteId++);
//        clientes.add(cliente);
//        return cliente;
//    }
//
//    public Cliente update(Long id, Cliente clienteAtualizado) {
//        return findById(id).map(cliente -> {
//            cliente.setNome(clienteAtualizado.getNome());
//            return cliente;
//        }).orElse(null);
//    }
//
//    public boolean delete(Long id) {
//        return clientes.removeIf(cliente -> cliente.getId().equals(id));
//    }
}
