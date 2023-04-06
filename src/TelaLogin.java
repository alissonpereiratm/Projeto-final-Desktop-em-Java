import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class TelaLogin extends JFrame {
    TelaCadastroProduto telaCadastro = new TelaCadastroProduto();

    JFrame jFrame = new JFrame("Login");
    JPanel jPanel = new JPanel();
    JButton bSalvar = new JButton();
    JLabel usuario = new JLabel();
    JLabel senha = new JLabel();
    JTextArea tusuario = new JTextArea();
    JPasswordField password = new JPasswordField();

    public void telaInicial() {

        jFrame.setSize(500, 250);
        jPanel.setSize(500, 250);

        jPanel.setLayout(null);
        jFrame.setVisible(true);
        jFrame.add(jPanel);
        jPanel.add(bSalvar);
        jPanel.add(usuario);
        jPanel.add(senha);
        jPanel.add(password);
        jPanel.add(tusuario);

        usuario.setText("Usuário");
        usuario.setBounds(50, 95, 80, 30);
        tusuario.setBounds(100, 100, 100, 20);

        senha.setText("Senha");
        senha.setBounds(250, 95, 80, 30);
        password.setBounds(300, 100, 80, 22);

        bSalvar.setText("ENTRAR");
        bSalvar.setBounds(200, 150, 90, 30);
        bSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                telaCadastro.telaCadastro();
            }

        });

    }

}
