import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import model.Pedido;
import model.Produto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelaPrincipal {

    JFrame frame = new JFrame();
    JPanel painel = new JPanel();

    JTable tabelaProdutos = new JTable();
    JButton adicionar = new JButton();
    JButton finalizar = new JButton();
    JButton remover = new JButton();
    JButton adm = new JButton();
    JRadioButton delivery = new JRadioButton();
    JRadioButton retirada = new JRadioButton();
    ButtonGroup grupoEntrega = new ButtonGroup();
    JRadioButton credito = new JRadioButton();
    JRadioButton debito = new JRadioButton();
    ButtonGroup grupoPagamento = new ButtonGroup();
    JLabel entrega = new JLabel();
    JLabel pagamento = new JLabel();
    JTextArea nome = new JTextArea();
    JLabel lnome = new JLabel();
    JTextArea endereco = new JTextArea();
    JLabel lendereco = new JLabel();
    JTextArea quantidade = new JTextArea();
    JLabel lquantidade = new JLabel();
    JTextArea total = new JTextArea();
    JLabel ltotal = new JLabel();

    JTextPane informacoes = new JTextPane();
    JComboBox comboProdutos = new JComboBox<>();
    JLabel lprodutos = new JLabel();
    JLabel imagem = new JLabel();

    DefaultTableModel model = new DefaultTableModel();
    PedidoRepository repositoryPedido = new PedidoRepository();
    TelaLogin teloLogin = new TelaLogin();
    ArrayList<Pedido> pedidos = new ArrayList<>();

    public void telaCadastro() throws SQLException {

        Pedido pedido = new Pedido();
        ArrayList<Produto> produtos = new ArrayList<>();
        ProdutoRepository repositoryProduto = new ProdutoRepository();
        produtos = repositoryProduto.consultaProdutos();

        frame.setSize(1920, 1080);
        painel.setSize(1920, 1080);
        painel.setLayout(null);

        frame.setTitle("ELA NATURELLA");
        frame.setVisible(true);
        frame.add(painel);
        painel.add(pagamento);
        painel.add(credito);
        painel.add(debito);
        painel.add(delivery);
        painel.add(retirada);
        painel.add(entrega);
        painel.add(finalizar);
        painel.add(remover);
        painel.add(adicionar);
        painel.add(nome);
        painel.add(lnome);
        painel.add(lendereco);
        painel.add(endereco);
        painel.add(quantidade);
        painel.add(lquantidade);
        painel.add(total);
        painel.add(ltotal);
        painel.add(comboProdutos);
        painel.add(lprodutos);
        painel.add(imagem);
        painel.add(tabelaProdutos);
        painel.add(adm);

        adm.setText("ADM");
        adm.setBounds(10, 10, 60, 15);
        adm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                teloLogin.telaInicial();
                frame.dispose();
            }

        });

        imagem.setIcon(new ImageIcon("src/img/LogoNa.png"));
        imagem.setBounds(125, 1, 435, 400);
        lnome.setText("NOME:");
        lnome.setBounds(130, 425, 50, 20);
        nome.setBounds(180, 425, 175, 20);

        lendereco.setText("ENDEREÇO:");
        lendereco.setBounds(100, 475, 75, 20);
        endereco.setBounds(180, 475, 175, 20);

        tabelaProdutos.setBounds(725, 45, 600, 500);

        entrega.setText("MÉTODO ENTREGA:");
        entrega.setBounds(57, 515, 150, 18);
        delivery.setText("DELIVERY");
        delivery.setBounds(175, 515, 85, 18);
        retirada.setText("RETIRADA");
        retirada.setBounds(175, 540, 85, 18);

        grupoEntrega.add(delivery);
        grupoEntrega.add(retirada);

        pagamento.setText("MÉTODO PAGAMENTO:");
        pagamento.setBounds(370, 515, 150, 18);
        credito.setText("CRÉDITO");
        credito.setBounds(510, 515, 85, 18);
        debito.setText("DÉBITO");
        debito.setBounds(510, 540, 85, 18);

        grupoPagamento.add(debito);
        grupoPagamento.add(credito);

        comboProdutos.setBounds(515, 425, 150, 20);
        lprodutos.setText("SELECIONE PRODUTO:");
        lprodutos.setBounds(370, 425, 150, 20);
        comboProdutos.addItem(null);
        for (Produto pedido1 : produtos) {
            comboProdutos.addItem(pedido1);
        }

        lquantidade.setText("QUANTIDADE:");
        lquantidade.setBounds(420, 475, 100, 20);
        quantidade.setBounds(515, 475, 50, 20);

        finalizar.setText("ENVIAR PEDIDO");
        finalizar.setBounds(900, 600, 130, 30);
        finalizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (repositoryPedido.consultaPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA UM PEDIDO");
                    } else {

                        repositoryPedido.excluirTodosPedidos();

                        new consultaAction().actionPerformed(e);
                        total.setText(null);
                        endereco.setText(null);
                        nome.setText(null);
                        grupoPagamento.clearSelection();
                        comboProdutos.setSelectedItem(null);
                        grupoEntrega.clearSelection();
                        quantidade.setText(null);
                        Icon logo = new ImageIcon("src/img/logo.png");
                        JOptionPane.showMessageDialog(null,
                                "\n\nPEDIDO REALIZADO COM SUCESSO!\nNATURELLA AGRADECE SUA PREFERÊNCIA!\n\n", null,
                                JOptionPane.INFORMATION_MESSAGE, logo);
                    }
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }

        });

        remover.setText("REMOVER ITEM");
        remover.setBounds(750, 600, 130, 30);
        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Pedido> pedidosDB2 = new ArrayList<>();
                int linhaSelecionada = tabelaProdutos.getSelectedRow();
                repositoryPedido.excluir((int) tabelaProdutos.getValueAt(linhaSelecionada, 0));
                try {
                    pedidosDB2 = repositoryPedido.consultaPedidos();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                new consultaAction().actionPerformed(e);
                if (pedidosDB2.size() > 0) {
                    double totalProduto = 0;
                    for (Pedido pedido2 : pedidosDB2) {

                        totalProduto += pedido2.getPreco() * pedido2.getQuantidade();
                        total.setText("R$" + Double.toString(totalProduto));
                    }
                } else {
                    total.setText(null);
                }

            }

        });

        adicionar.setText("ADICIONAR ITEM");
        adicionar.setBounds(300, 600, 140, 30);
        adicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pedido.setNome(nome.getText());
                pedido.setEndereco(endereco.getText());
                if (delivery.isSelected()) {
                    pedido.setEntrega(delivery.getText());

                } else {

                    pedido.setEntrega(retirada.getText());

                }
                if (comboProdutos.getSelectedItem() instanceof Produto) {

                    Produto itemSelecionado = (Produto) comboProdutos.getSelectedItem();
                    pedido.setProduto(itemSelecionado.getNome());
                    pedido.setPreco(itemSelecionado.getValor());

                }
                pedido.setQuantidade(Integer.parseInt(quantidade.getText()));

                if (credito.isSelected()) {
                    pedido.setPagamento(credito.getText());

                } else {

                    pedido.setPagamento(debito.getText());

                }

                repositoryPedido.inserir(pedido);

                comboProdutos.setSelectedItem(null);

                quantidade.setText(null);
                new consultaAction().actionPerformed(e);

            }

        });

        ltotal.setText("VALOR TOTAL:");
        ltotal.setBounds(1090, 600, 100, 30);
        total.setBounds(1190, 600, 110, 25);
        Font font = total.getFont().deriveFont(20f);
        total.setForeground(Color.RED);
        total.setFont(font);

    }

    public class consultaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            while (tabelaProdutos.getModel().getColumnCount() > 0) {
                ((DefaultTableModel) tabelaProdutos.getModel()).setRowCount(0);
                ((DefaultTableModel) tabelaProdutos.getModel()).setColumnCount(0);

            }
            ArrayList<Pedido> pedidosDB = new ArrayList<>();
            try {
                pedidosDB = repositoryPedido.consultaPedidos();
            } catch (SQLException e1) {

            }

            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Endereço");
            model.addColumn("Entrega");
            model.addColumn("Pagamento");
            model.addColumn("Quantidade");
            model.addColumn("R$ Unitario");
            model.addColumn("Descrição");

            model.addRow(new Object[] { "Código", "Nome", "Endereço", "Descrição", "R$ Unitário",
                    "Quantidade", "Pagamento", "Entrega" });
            for (Pedido pedido : pedidosDB) {
                model.addRow(new Object[] { pedido.getCodigo(), pedido.getNome(), pedido.getEndereco(),
                        pedido.getProduto(), pedido.getPreco(), pedido.getQuantidade(), pedido.getPagamento(),
                        pedido.getEntrega() });

                tabelaProdutos.setModel(model);
                double totalProduto = 0;
                for (Pedido pedido2 : pedidosDB) {
                    totalProduto += pedido2.getPreco() * pedido2.getQuantidade();

                }

                TableColumnModel columnModel = tabelaProdutos.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(60);
                columnModel.getColumn(1).setPreferredWidth(100);
                columnModel.getColumn(2).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                columnModel.getColumn(4).setPreferredWidth(100);
                columnModel.getColumn(5).setPreferredWidth(100);
                columnModel.getColumn(6).setPreferredWidth(100);
                columnModel.getColumn(7).setPreferredWidth(100);

                total.setText("R$" + Double.toString(totalProduto));

            }
        }

    }

}