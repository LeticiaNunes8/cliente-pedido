package tde3.pedido_cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tde3.pedido_cliente.DAO.PedidoDAO;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoDAO, Long> {


}
