import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import Repository.UsuarioRepository;
import model.Usuario;

public class TelaLogin extends JFrame {
    TelaCadastroProduto telaCadastro = new TelaCadastroProduto();
    UsuarioRepository repositoryUsuario = new UsuarioRepository();
    JFrame jFrame = new JFrame("Login");
    JPanel jPanel = new JPanel();
    JButton bSalvar = new JButton();
    JButton bVoltar = new JButton();
    JLabel usuario = new JLabel();
    JLabel senha = new JLabel();
    JLabel titulo = new JLabel();
    JTextArea tusuario = new JTextArea();
    JPasswordField password = new JPasswordField();

    public void telaInicial() {

        jFrame.setSize(500, 250);
        jPanel.setSize(500, 250);

        jPanel.setLayout(null);
        jFrame.setVisible(true);
        jFrame.add(jPanel);
        jPanel.add(bSalvar);
        jPanel.add(bVoltar);
        jPanel.add(usuario);
        jPanel.add(senha);
        jPanel.add(password);
        jPanel.add(tusuario);
        jPanel.add(titulo);

        usuario.setText("Usuário");
        usuario.setBounds(50, 95, 80, 30);
        tusuario.setBounds(100, 100, 100, 20);

        titulo.setText("ACESSO ADMINISTRADOR");
        titulo.setBounds(170, 30, 150, 30);

        senha.setText("Senha");
        senha.setBounds(250, 95, 80, 30);
        password.setBounds(300, 100, 80, 22);

        bVoltar.setText("VOLTAR");
        bVoltar.setBounds(100, 150, 90, 30);
        bVoltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                jFrame.dispose();
                try {
                    telaPrincipal.telaCadastro();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });

        bSalvar.setText("ENTRAR");
        bSalvar.setBounds(300, 150, 90, 30);
        bSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                try {
                    usuario = repositoryUsuario.consultaUsuario();
                } catch (SQLException e1) {
                    System.out.println("ERRO CONSULTA USUARIO");
                }
                char[] passwordChars = password.getPassword();
                String passwordString = new String(passwordChars);
                int passwordInt = Integer.parseInt(passwordString);

                if (usuario.getLogin().equalsIgnoreCase(tusuario.getText()) && usuario.getSenha() == passwordInt) {
                    jFrame.dispose();
                    telaCadastro.telaCadastro();

                } else {
                    JOptionPane.showMessageDialog(null, "USUÁRIO E/OU SENHA INVÁLIDO!\nTENTE NOVAMENTE!");
                    password.setText(null);
                    tusuario.setText(null);
                }

            }

        });

    }

}
