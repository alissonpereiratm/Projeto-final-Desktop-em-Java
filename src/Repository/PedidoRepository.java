package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Pedido;

public class PedidoRepository {

    public void inserir(Pedido pedido) {
        Conexao conexao = new Conexao();

        Connection conn = conexao.conectar();
        try {
            String sql = "insert into pedido (id,nome,endereco,entrega,pagamento,produto,preco,quantidade)"
                    + "values (nextval ('pedido_seq'),?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, pedido.getNome());
            stm.setString(2, pedido.getEndereco());
            stm.setString(3, pedido.getEntrega());
            stm.setString(4, pedido.getPagamento());
            stm.setString(5, pedido.getProduto());
            stm.setDouble(6, pedido.getPreco());
            stm.setInt(7, pedido.getQuantidade());

            stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

    }

    public ArrayList<Pedido> consultaPedidos() throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String sql = "Select * From pedido";
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        try {

            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodigo(resultado.getInt("id"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setEndereco(resultado.getString("endereco"));
                pedido.setEntrega(resultado.getString("entrega"));
                pedido.setPagamento(resultado.getString("pagamento"));
                pedido.setProduto(resultado.getString("produto"));
                pedido.setQuantidade(resultado.getInt("quantidade"));
                pedido.setPreco(resultado.getDouble("preco"));
                pedidos.add(pedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não conseguiu consultar a tabela Produto");
        } finally {
            conexao.desconectar(conn);
        }

        return pedidos;

    }

    public void excluir(int id) {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "DELETE FROM pedido where id=" + id;
            Statement stm = conn.createStatement();
            stm.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("ERRO EXCLUIR");
        } finally {
            conexao.desconectar(conn);
        }

    }

    public void excluirTodosPedidos() {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "DELETE FROM pedido";
            Statement stm = conn.createStatement();
            stm.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("ERRO EXCLUIR");
        } finally {
            conexao.desconectar(conn);
        }

    }

}
