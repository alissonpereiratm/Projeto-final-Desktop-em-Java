package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Produto;

public class ProdutoRepository {

    public void inserir(Produto produto) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "INSERT INTO produto(id,nome,valor)" + "VALUES (NEXTVAL('produto_seq'),?,?)";
            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getValor());
            stm.execute();

        } catch (Exception e) {
            System.out.println("ERRO INSERIR");
        } finally {
            conexao.desconectar(conn);
        }

    }

    public Produto buscaID(int id) throws SQLException {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        Produto produto = new Produto();
        String sql = "Select * From produto where id=" + id;
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        try {

            while (resultado.next()) {
                produto.setCodigo(Integer.parseInt(resultado.getString("id")));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(Double.parseDouble(String.valueOf(resultado.getDouble("valor"))));
            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Produto");
        } finally {
            conexao.desconectar(conn);
        }

        return produto;

    }

    public ArrayList<Produto> consultaProdutos() throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
      
        String sql = "Select * From produto";
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        try {

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCodigo(Integer.parseInt(resultado.getString("id")));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(Double.parseDouble(String.valueOf(resultado.getDouble("valor"))));
                produtos.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Produto");
        } finally {
            conexao.desconectar(conn);
        }

        return produtos;

    }

    public void editar(Produto produto) {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "UPDATE produto set nome=?,valor=? WHERE id=?";
            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getValor());
            stm.setInt(3, produto.getCodigo());
            stm.execute();

        } catch (Exception e) {
            System.out.println("ERRO EDITAR");
        } finally {
            conexao.desconectar(conn);
        }

    }

    public void excluir(int id) {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "DELETE FROM produto where id=" + id;
            Statement stm = conn.createStatement();
            stm.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("ERRO EXCLUIR");
        } finally {
            conexao.desconectar(conn);
        }

    }

}
