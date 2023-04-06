package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuarioRepository {

    public Usuario consultaUsuario() throws SQLException {
        Usuario usuario = new Usuario();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String sql = "Select * From usuario";
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        try {

            while (resultado.next()) {

                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(Integer.parseInt(String.valueOf(resultado.getInt("senha"))));

            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Pedido");
        } finally {
            conexao.desconectar(conn);
        }

        return usuario;

    }

}
