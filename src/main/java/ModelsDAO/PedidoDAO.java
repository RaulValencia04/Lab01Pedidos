/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsDAO;

/**
 *
 * @author raulvalencia
 */
import Models.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_cliente, fecha, total, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            stmt.setDouble(3, pedido.getTotal());
            stmt.setInt(4, pedido.getEstado());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getDate("fecha"),
                    rs.getDouble("total"),
                    rs.getInt("estado")
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public boolean actualizarPedido(Pedido pedido) {
        String sql = "UPDATE pedidos SET id_cliente = ?, fecha = ?, total = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            stmt.setDouble(3, pedido.getTotal());
            stmt.setInt(4, pedido.getEstado());
            stmt.setInt(5, pedido.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
    