package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EspecialidadeMedica;
import model.Medico;
import util.ConnectionFactory;

public class MedicoDAO {

    // INSERT
    public void inserir(Medico medico) {

        String sql = "INSERT INTO medico (crm, nome, telefone, especialidade_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getTelefone());
            stmt.setInt(4, medico.getEspecialidade().getId());

            stmt.executeUpdate();

            System.out.println("Médico cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico.");
            e.printStackTrace();
        }
    }

    // SELECT - listar médicos
    public List<Medico> listar() {

        List<Medico> medicos = new ArrayList<>();

        String sql = """
                SELECT
                    m.id,
                    m.crm,
                    m.nome,
                    m.telefone,
                    e.id AS especialidade_id,
                    e.nome AS especialidade_nome,
                    e.descricao AS especialidade_descricao
                FROM medico m
                INNER JOIN especialidade_medica e
                    ON m.especialidade_id = e.id
                ORDER BY m.id
                """;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                EspecialidadeMedica especialidade = new EspecialidadeMedica(
                        rs.getInt("especialidade_id"),
                        rs.getString("especialidade_nome"),
                        rs.getString("especialidade_descricao")
                );

                Medico medico = new Medico(
                        rs.getInt("id"),
                        rs.getString("crm"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        especialidade
                );

                medicos.add(medico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar médicos.");
            e.printStackTrace();
        }

        return medicos;
    }

    // SELECT - buscar médico por ID
    public Medico buscarPorId(int id) {

        String sql = """
                SELECT
                    m.id,
                    m.crm,
                    m.nome,
                    m.telefone,
                    e.id AS especialidade_id,
                    e.nome AS especialidade_nome,
                    e.descricao AS especialidade_descricao
                FROM medico m
                INNER JOIN especialidade_medica e
                    ON m.especialidade_id = e.id
                WHERE m.id = ?
                """;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    EspecialidadeMedica especialidade = new EspecialidadeMedica(
                            rs.getInt("especialidade_id"),
                            rs.getString("especialidade_nome"),
                            rs.getString("especialidade_descricao")
                    );

                    return new Medico(
                            rs.getInt("id"),
                            rs.getString("crm"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            especialidade
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public void atualizar(Medico medico) {

        String sql = """
                UPDATE medico
                SET crm = ?, nome = ?, telefone = ?, especialidade_id = ?
                WHERE id = ?
                """;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getTelefone());
            stmt.setInt(4, medico.getEspecialidade().getId());
            stmt.setInt(5, medico.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Médico atualizado com sucesso!");
            } else {
                System.out.println("Médico não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar médico.");
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletar(int id) {

        String sql = "DELETE FROM medico WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Médico excluído com sucesso!");
            } else {
                System.out.println("Médico não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir médico.");
            e.printStackTrace();
        }
    }
}