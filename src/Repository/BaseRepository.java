package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class BaseRepository<T> {

    protected String getTableName() {
        return null;
    }

    public List<T> findAll() {
        Conexao conexao = new Conexao();
        List<T> list = new ArrayList<>();

        String sql = "Select * From " + getTableName();
        try (Connection conn = conexao.conectar();
                Statement stm = conn.createStatement();
                ResultSet resultado = stm.executeQuery(sql)) {
            while (resultado.next()) {
                populaRegistro(resultado, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Produto> consultaProdutos() throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();
        Conexao conexao = new Conexao();

        String sql = "Select * From produto";
        // Qualquer classe que implemente autoCloseable, implicitamente realiza um
        // close() ao finalizar a instrução, colocar dentro do bloco try
        try (Connection conn = conexao.conectar();
                Statement stm = conn.createStatement();
                ResultSet resultado = stm.executeQuery(sql)) {

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCodigo(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(resultado.getDouble("valor"));
                produtos.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Produto");
        }

        return produtos;

    }

    protected void populaRegistro(ResultSet resultado, List<T> lista) throws SQLException{

    }

}
