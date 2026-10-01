package model;

public class Medico {

    private int id;
    private String crm;
    private String nome;
    private String telefone;
    private EspecialidadeMedica especialidade;

    // Construtor vazio
    public Medico() {
    }

    // Construtor completo
    public Medico(int id, String crm, String nome, String telefone,
                  EspecialidadeMedica especialidade) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    // Construtor sem ID
    public Medico(String crm, String nome, String telefone,
                  EspecialidadeMedica especialidade) {
        this.crm = crm;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    // Getter e Setter do ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter do CRM
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    // Getter e Setter do nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter do telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Getter e Setter da especialidade
    public EspecialidadeMedica getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeMedica especialidade) {
        this.especialidade = especialidade;
    }

    // toString
    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", especialidade=" + especialidade +
                '}';
    }
}