package model;

public class Categoria {
    private int id;
    private String nome;
    private int qntdCategoria;
    private String empresaCNPJ;

    public Categoria() {
    }

    public Categoria(int id, String nome, int qntdCategoria, String empresaCNPJ) {
        this.id = id;
        this.nome = nome;
        this.qntdCategoria = qntdCategoria;
        this.empresaCNPJ = empresaCNPJ;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQntdCategoria() {
        return qntdCategoria;
    }

    public String getEmpresaCNPJ() {
        return empresaCNPJ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQntdCategoria(int qntdCategoria) {
        this.qntdCategoria = qntdCategoria;
    }

    public void setEmpresaCNPJ(String empresaCNPJ) {
        this.empresaCNPJ = empresaCNPJ;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nome=" + nome + ", qntdCategoria=" + qntdCategoria + ", empresaCNPJ="
                + empresaCNPJ + "]";
    }
}