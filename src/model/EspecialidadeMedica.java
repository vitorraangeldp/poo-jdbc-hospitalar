package model;

public class EspecialidadeMedica {

    private int id;
    private String nome;
    private String descricao;

    // Construtor vazio
    public EspecialidadeMedica() {
    }

    // Construtor completo
    public EspecialidadeMedica(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Construtor sem ID
    public EspecialidadeMedica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getter e Setter do ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter do nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter da descrição
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // toString
    @Override
    public String toString() {
        return "EspecialidadeMedica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
