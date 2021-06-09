package br.com.teste.teste.model;

public class Produtos {

    private int id;
    private String nome;
    private int quantidade;
    private double preco, total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double precoTotal() {
        return quantidade * preco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + quantidade + " - " + preco + " - " + precoTotal();
    }
}
