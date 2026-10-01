package app;

import java.util.List;

import dao.EspecialidadeMedicaDAO;
import dao.MedicoDAO;
import model.EspecialidadeMedica;
import model.Medico;

public class Main {

    public static void main(String[] args) {

        EspecialidadeMedicaDAO especialidadeDAO = new EspecialidadeMedicaDAO();
        MedicoDAO medicoDAO = new MedicoDAO();

        // ==========================================
        // 1. LISTAR ESPECIALIDADES
        // ==========================================

        System.out.println("==========================================");
        System.out.println("       ESPECIALIDADES MÉDICAS");
        System.out.println("==========================================");

        List<EspecialidadeMedica> especialidades = especialidadeDAO.listar();

        for (EspecialidadeMedica especialidade : especialidades) {
            System.out.println(especialidade);
        }

        // ==========================================
        // 2. CADASTRAR MÉDICO
        // ==========================================

        System.out.println("\n==========================================");
        System.out.println("       CADASTRANDO MÉDICO");
        System.out.println("==========================================");

        EspecialidadeMedica especialidade = especialidadeDAO.buscarPorId(1);

        if (especialidade != null) {

            Medico novoMedico = new Medico(
                    "CRM-67890",
                    "Dra. Maria Oliveira",
                    "(21) 97777-6666",
                    especialidade
            );

            medicoDAO.inserir(novoMedico);

        } else {
            System.out.println("Especialidade de ID 1 não encontrada.");
        }

        // ==========================================
        // 3. LISTAR MÉDICOS COM INNER JOIN
        // ==========================================

        System.out.println("\n==========================================");
        System.out.println("       MÉDICOS CADASTRADOS");
        System.out.println("==========================================");

        List<Medico> medicos = medicoDAO.listar();

        for (Medico medico : medicos) {
            System.out.println(medico);
        }

        // ==========================================
        // 4. ATUALIZAR MÉDICO
        // ==========================================

        if (!medicos.isEmpty()) {

            Medico medico = medicos.get(0);

            medico.setTelefone("(21) 96666-5555");

            medicoDAO.atualizar(medico);

            System.out.println("\n==========================================");
            System.out.println("       MÉDICO APÓS ATUALIZAÇÃO");
            System.out.println("==========================================");

            System.out.println(medicoDAO.buscarPorId(medico.getId()));
        }

        // ==========================================
        // 5. EXEMPLO DE BUSCA POR ID
        // ==========================================

        if (!medicos.isEmpty()) {

            int id = medicos.get(0).getId();

            System.out.println("\n==========================================");
            System.out.println("       BUSCA POR ID");
            System.out.println("==========================================");

            System.out.println(medicoDAO.buscarPorId(id));
        }
    }
}