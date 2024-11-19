package tde3.pedido_cliente.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tde3.pedido_cliente.DAO.ClienteDAO;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDAO, Long> {


}
