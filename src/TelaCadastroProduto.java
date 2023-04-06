
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Repository.ProdutoRepository;
import model.Produto;

public class TelaCadastroProduto extends JFrame implements ActionListener {

    int opcao = 0;

    JFrame frameCadastro = new JFrame("Cadastro de Produtos");
    JPanel painelCadastro = new JPanel();
    JLabel lTituloCadastro = new JLabel();

    JLabel lNome = new JLabel();
    JTextArea tNome = new JTextArea();
    JButton bCadastrar = new JButton();
    JLabel lValor = new JLabel();
    JTextArea tValor = new JTextArea();
    JButton bEditar = new JButton();
    JButton bApagar = new JButton();
    JButton bVoltar = new JButton();

    JTable tabela = new JTable();
    DefaultTableModel model = new DefaultTableModel();

    ProdutoRepository repository = new ProdutoRepository();
    Produto produto = new Produto();

    public void telaCadastro() {

        frameCadastro.setSize(500, 450);
        painelCadastro.setSize(500, 450);
        painelCadastro.setLayout(null);

        lTituloCadastro.setText("Cadastrar Produto");
        lTituloCadastro.setBounds(190, 30, 150, 20);

        frameCadastro.setVisible(true);
        frameCadastro.add(painelCadastro);
        painelCadastro.add(lTituloCadastro);

        lNome.setText("Nome");
        lNome.setBounds(10, 95, 80, 30);
        tNome.setBounds(80, 100, 250, 20);

        lValor.setText("Valor");
        lValor.setBounds(10, 125, 80, 30);
        tValor.setBounds(80, 130, 130, 20);

        bVoltar.setText("Voltar");
        bVoltar.setBounds(350, 370, 100, 21);
        bVoltar.addActionListener(this);

        bApagar.setText("Apagar");
        bApagar.setBounds(230, 130, 100, 21);
        bApagar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                repository.excluir((int) tabela.getValueAt(linhaSelecionada, 0));

                new consultaAction().actionPerformed(e);
            }

        });

        bEditar.setText("Editar");
        bEditar.setBounds(350, 130, 100, 21);
        bEditar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                try {
                    produto = repository.buscaID((int) tabela.getValueAt(linhaSelecionada, 0));

                } catch (SQLException e1) {
                    System.out.println("ERRO NA BUSCA DO PRODUTO");
                    e1.printStackTrace();
                }

                tNome.setText(produto.getNome());
                tValor.setText(Double.toString(produto.getValor()));
                opcao = 1;

            }

        });

        bCadastrar.setText("Salvar");
        bCadastrar.setBounds(350, 100, 100, 21);
        bCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opcao == 0) {
                    produto.setNome(tNome.getText());
                    produto.setValor(Double.parseDouble(tValor.getText()));
                    repository.inserir(produto);
                    tNome.setText(null);
                    tValor.setText(null);
                    new consultaAction().actionPerformed(e);
                } else {
                    produto.setNome(tNome.getText());
                    produto.setValor(Double.parseDouble(tValor.getText()));
                    repository.editar(produto);
                    tNome.setText(null);
                    tValor.setText(null);
                    opcao = 0;
                }
                new consultaAction().actionPerformed(e);
            }

        });

        tabela.setBounds(10, 200, 460, 150);
        painelCadastro.add(lNome);
        painelCadastro.add(tNome);
        painelCadastro.add(bCadastrar);
        painelCadastro.add(tabela);
        painelCadastro.add(lValor);
        painelCadastro.add(tValor);
        painelCadastro.add(bEditar);
        painelCadastro.add(bApagar);
        painelCadastro.add(bVoltar);

        new consultaAction().actionPerformed(null);

    }

    public class consultaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            while (tabela.getModel().getColumnCount() > 0) {
                ((DefaultTableModel) tabela.getModel()).setRowCount(0);
                ((DefaultTableModel) tabela.getModel()).setColumnCount(0);
            }
            ArrayList<Produto> produtos = new ArrayList<>();
            try {
                produtos = repository.consultaProdutos();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Valor");

            model.addRow(new Object[] { "Código", "Nome", "Valor" });

            for (Produto produto : produtos) {
                model.addRow(new Object[] { produto.getCodigo(), produto.getNome(), produto.getValor() });
            }
            tabela.setModel(model);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
     TelaPrincipal telaPrincipal= new TelaPrincipal();
        frameCadastro.dispose();
  try {
  telaPrincipal.telaCadastro();
} catch (SQLException e1) {
  
}
       
    }

}