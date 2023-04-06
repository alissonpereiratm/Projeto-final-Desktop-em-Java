package model;

public class Pedido {
    private int codigo;
    private String nome;
    private String endereco;
    private String entrega;
    private String pagamento;
    private String produto;
    private double preco;
private int quantidade;

    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getEntrega() {
        return entrega;
    }
    public String getPagamento() {
        return pagamento;
    }
    public String getProduto() {
        return produto;
    }
    public Double getPreco() {
        return preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    

}
