package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EspecialidadeMedica;
import util.ConnectionFactory;

public class EspecialidadeMedicaDAO {

    // CREATE - Inserir uma especialidade
    public void inserir(EspecialidadeMedica especialidade) {

        String sql = "INSERT INTO especialidade_medica (nome, descricao) VALUES (?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getNome());
            stmt.setString(2, especialidade.getDescricao());

            stmt.executeUpdate();

            System.out.println("Especialidade cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar especialidade.");
            e.printStackTrace();
        }
    }

    // READ - Listar todas as especialidades
    public List<EspecialidadeMedica> listar() {

        List<EspecialidadeMedica> especialidades = new ArrayList<>();

        String sql = "SELECT id, nome, descricao FROM especialidade_medica ORDER BY id";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                EspecialidadeMedica especialidade = new EspecialidadeMedica();

                especialidade.setId(rs.getInt("id"));
                especialidade.setNome(rs.getString("nome"));
                especialidade.setDescricao(rs.getString("descricao"));

                especialidades.add(especialidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar especialidades.");
            e.printStackTrace();
        }

        return especialidades;
    }

    // READ - Buscar uma especialidade pelo ID
    public EspecialidadeMedica buscarPorId(int id) {

        String sql = "SELECT id, nome, descricao FROM especialidade_medica WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    EspecialidadeMedica especialidade = new EspecialidadeMedica();

                    especialidade.setId(rs.getInt("id"));
                    especialidade.setNome(rs.getString("nome"));
                    especialidade.setDescricao(rs.getString("descricao"));

                    return especialidade;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar especialidade.");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE - Atualizar uma especialidade
    public void atualizar(EspecialidadeMedica especialidade) {

        String sql = "UPDATE especialidade_medica SET nome = ?, descricao = ? WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getNome());
            stmt.setString(2, especialidade.getDescricao());
            stmt.setInt(3, especialidade.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Especialidade atualizada com sucesso!");
            } else {
                System.out.println("Especialidade não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar especialidade.");
            e.printStackTrace();
        }
    }

    // DELETE - Excluir uma especialidade
    public void deletar(int id) {

        String sql = "DELETE FROM especialidade_medica WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Especialidade excluída com sucesso!");
            } else {
                System.out.println("Especialidade não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir especialidade.");
            e.printStackTrace();
        }
    }
}
