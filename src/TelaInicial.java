import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import model.Pedido;
import model.Produto;

public class TelaInicial extends JFrame {
    TelaCadastroProduto telaCadastro= new TelaCadastroProduto();
    ArrayList<Produto> produtos = new ArrayList<>();


    JFrame jFrame = new JFrame();
    JPanel jPanel = new JPanel();
    JButton bSalvar = new JButton();
    JRadioButton feminino = new JRadioButton();
    JRadioButton masculino = new JRadioButton();
    ButtonGroup genero = new ButtonGroup();
    JLabel lGenero = new JLabel();
    JComboBox<Produto> comboProduto = new JComboBox<Produto>();
    JLabel img = new JLabel();
    JPasswordField password = new JPasswordField();

    public void telaInicial() throws SQLException {
        ProdutoRepository repositoryProduto = new ProdutoRepository();
        produtos = repositoryProduto.consultaProdutos();
        jFrame.setSize(500, 250);
        jPanel.setSize(500, 250);
        jPanel.setLayout(null);
        jFrame.setVisible(true);
        jFrame.add(jPanel);
        jPanel.add(comboProduto);
        // jPanel.add(feminino);
        // jPanel.add(masculino);
        // jPanel.add(lGenero);
        jPanel.add(bSalvar);
        jPanel.add(img);
        jPanel.add(password);

        password.setBounds(150, 100, 80, 20);

        img.setIcon(new ImageIcon("src/img/cao.jpg"));
        img.setBounds(350, 100, 100, 100);

        comboProduto.setBounds(120, 50, 200, 20);
        comboProduto.addItem(null);
        for (Produto produto : produtos) {
            comboProduto.addItem(produto);
        }

        feminino.setText("Feminino");
        feminino.setBounds(100, 50, 80, 20);

        masculino.setText("Masculino");
        masculino.setBounds(190, 50, 100, 20);

        genero.add(feminino);
        genero.add(masculino);

        lGenero.setText("Gênero:");
        lGenero.setBounds(50, 50, 80, 20);

        bSalvar.setText("SALVAR");
        bSalvar.setBounds(200, 150, 90, 30);
        bSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastro.telaCadastro();
                jFrame.dispose();

            }

        });
        ;
     

    }

}
