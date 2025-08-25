package com.gestao.imobiliaria;

import com.gestao.imobiliaria.dao.*;
import com.gestao.imobiliaria.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
    private static final InquilinoDAO inquilinoDAO = new InquilinoDAO();
    private static final LocacaoDAO locacaoDAO = new LocacaoDAO();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("    SISTEMA DE GESTÃO IMOBILIÁRIA FAMILIAR");
        System.out.println("==================================================");

        try {
            // Testa a conexão com o banco
            DatabaseConnection.getConnection();
            
            while (true) {
                exibirMenuPrincipal();
                int opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        gerenciarPropriedades();
                        break;
                    case 2:
                        gerenciarInquilinos();
                        break;
                    case 3:
                        gerenciarLocacoes();
                        break;
                    case 4:
                        gerarRelatorios();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o sistema: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
            scanner.close();
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Gerenciar Propriedades");
        System.out.println("2. Gerenciar Inquilinos");
        System.out.println("3. Gerenciar Locações");
        System.out.println("4. Gerar Relatórios");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }

    private static void gerenciarPropriedades() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        GERENCIAR PROPRIEDADES");
            System.out.println("-".repeat(40));
            System.out.println("1. Cadastrar Propriedade");
            System.out.println("2. Listar Todas as Propriedades");
            System.out.println("3. Listar Propriedades Disponíveis");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("-".repeat(40));
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarPropriedade();
                    break;
                case 2:
                    listarTodasPropriedades();
                    break;
                case 3:
                    listarPropriedadesDisponiveis();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void cadastrarPropriedade() {
        System.out.println("\n--- CADASTRAR PROPRIEDADE ---");

        System.out.print("Tipo da propriedade (ex: apartamento, casa, kitnet): ");
        String tipo = scanner.nextLine().trim();

        System.out.print("Descrição da propriedade: ");
        String descricao = scanner.nextLine().trim();

        System.out.print("Região: ");
        String regiao = scanner.nextLine().trim();

        System.out.print("Endereço completo: ");
        String endereco = scanner.nextLine().trim();

        try {
            System.out.print("Área em m²: ");
            BigDecimal areaM2 = new BigDecimal(scanner.nextLine().trim());

            Propriedade propriedade = new Propriedade(tipo, descricao, regiao, endereco, areaM2);
            propriedadeDAO.cadastrarPropriedade(propriedade);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Área deve ser um número válido.");
        }
    }

    private static void listarTodasPropriedades() {
        List<Propriedade> propriedades = propriedadeDAO.listarTodasPropriedades();

        if (propriedades.isEmpty()) {
            System.out.println("Nenhuma propriedade encontrada.");
            return;
        }

        System.out.println("\n=== TODAS AS PROPRIEDADES ===");
        for (Propriedade p : propriedades) {
            System.out.println("ID: " + p.getIdPropriedade());
            System.out.println("Tipo: " + p.getTipo());
            System.out.println("Descrição: " + p.getDescricao());
            System.out.println("Região: " + p.getRegiao());
            System.out.println("Endereço: " + p.getEndereco());
            System.out.println("Área (m²): " + p.getAreaM2());
            System.out.println("Data de Criação: " + p.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            System.out.println("-".repeat(40));
        }
    }

    private static void listarPropriedadesDisponiveis() {
        List<Propriedade> propriedades = propriedadeDAO.listarPropriedadesDisponiveis();

        if (propriedades.isEmpty()) {
            System.out.println("Nenhuma propriedade disponível encontrada.");
            return;
        }

        System.out.println("\n=== PROPRIEDADES DISPONÍVEIS ===");
        for (Propriedade p : propriedades) {
            System.out.println("ID: " + p.getIdPropriedade());
            System.out.println("Tipo: " + p.getTipo());
            System.out.println("Descrição: " + p.getDescricao());
            System.out.println("Região: " + p.getRegiao());
            System.out.println("Endereço: " + p.getEndereco());
            System.out.println("Área (m²): " + p.getAreaM2());
            System.out.println("Data de Criação: " + p.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            System.out.println("-".repeat(40));
        }
    }

    private static void gerenciarInquilinos() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("         GERENCIAR INQUILINOS");
            System.out.println("-".repeat(40));
            System.out.println("1. Cadastrar Inquilino");
            System.out.println("2. Listar Todos os Inquilinos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("-".repeat(40));
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarInquilino();
                    break;
                case 2:
                    listarInquilinos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void cadastrarInquilino() {
        System.out.println("\n--- CADASTRAR INQUILINO ---");

        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine().trim();

        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        if (cpf.length() == 11 && cpf.matches("\\d+")) {
            Inquilino inquilino = new Inquilino(cpf, nome, email);
            inquilinoDAO.cadastrarInquilino(inquilino);
        } else {
            System.out.println("Erro: CPF deve conter exatamente 11 dígitos.");
        }
    }

    private static void listarInquilinos() {
        List<Inquilino> inquilinos = inquilinoDAO.listarInquilinos();

        if (inquilinos.isEmpty()) {
            System.out.println("Nenhum inquilino encontrado.");
            return;
        }

        System.out.println("\n=== INQUILINOS CADASTRADOS ===");
        for (Inquilino i : inquilinos) {
            System.out.println("ID: " + i.getIdInquilino());
            System.out.println("CPF: " + i.getCpfInquilino());
            System.out.println("Nome: " + i.getNomeInquilino());
            System.out.println("Email: " + i.getEmailInquilino());
            System.out.println("-".repeat(40));
        }
    }

    private static void gerenciarLocacoes() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("         GERENCIAR LOCAÇÕES");
            System.out.println("-".repeat(40));
            System.out.println("1. Cadastrar Locação");
            System.out.println("2. Listar Todas as Locações");
            System.out.println("3. Atualizar Status dos Contratos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("-".repeat(40));
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarLocacao();
                    break;
                case 2:
                    listarTodasLocacoes();
                    break;
                case 3:
                    locacaoDAO.verificarAtivacaoContratos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void cadastrarLocacao() {
        System.out.println("\n--- CADASTRAR LOCAÇÃO ---");

        // Listar inquilinos disponíveis
        System.out.println("\nInquilinos disponíveis:");
        List<Inquilino> inquilinos = inquilinoDAO.listarInquilinos();
        if (inquilinos.isEmpty()) {
            System.out.println("Nenhum inquilino cadastrado. Cadastre um inquilino primeiro.");
            return;
        }

        // Listar propriedades disponíveis
        System.out.println("\nPropriedades disponíveis:");
        List<Propriedade> propriedades = propriedadeDAO.listarPropriedadesDisponiveis();
        if (propriedades.isEmpty()) {
            System.out.println("Nenhuma propriedade disponível. Cadastre uma propriedade primeiro.");
            return;
        }

        try {
            System.out.print("\nID do Inquilino: ");
            int idInquilino = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("ID da Propriedade: ");
            int idPropriedade = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Valor do aluguel: R$ ");
            BigDecimal valor = new BigDecimal(scanner.nextLine().trim());

            System.out.print("Data de início (dd/MM/yyyy): ");
            LocalDate dataInicio = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.print("Data de fim (dd/MM/yyyy): ");
            LocalDate dataFim = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if (dataFim.isBefore(dataInicio) || dataFim.isEqual(dataInicio)) {
                System.out.println("Erro: Data de fim deve ser posterior à data de início.");
                return;
            }

            Locacao locacao = new Locacao(idInquilino, idPropriedade, valor, dataInicio, dataFim);
            locacaoDAO.cadastrarLocacao(locacao);

        } catch (NumberFormatException e) {
            System.out.println("Erro: Dados numéricos inválidos.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
        }
    }

    private static void listarTodasLocacoes() {
        List<Object[]> locacoes = locacaoDAO.listarTodasLocacoes();

        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação encontrada.");
            return;
        }

        System.out.println("\n=== TODAS AS LOCAÇÕES ===");
        for (Object[] locacao : locacoes) {
            String status = (Boolean) locacao[7] ? "ATIVO" : "INATIVO";
            System.out.println("ID Locação: " + locacao[0]);
            System.out.println("Inquilino: " + locacao[1]);
            System.out.println("Propriedade: " + locacao[2] + " (" + locacao[3] + ")");
            System.out.println("Valor: R$ " + locacao[4]);
            System.out.println("Data Início: " + ((LocalDate) locacao[5]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Data Fim: " + ((LocalDate) locacao[6]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Status: " + status);
            System.out.println("-".repeat(40));
        }
    }

    private static void gerarRelatorios() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("           RELATÓRIOS");
            System.out.println("-".repeat(40));
            System.out.println("1. Propriedades Disponíveis");
            System.out.println("2. Contratos Ativos");
            System.out.println("3. Inquilinos com Mais Contratos");
            System.out.println("4. Contratos Expirando nos Próximos 30 Dias");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("-".repeat(40));
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    listarPropriedadesDisponiveis();
                    break;
                case 2:
                    listarContratosAtivos();
                    break;
                case 3:
                    listarInquilinosComMaisContratos();
                    break;
                case 4:
                    listarContratosExpirandoProximos30Dias();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private static void listarContratosAtivos() {
        List<Object[]> contratos = locacaoDAO.listarContratosAtivos();

        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato ativo encontrado.");
            return;
        }

        System.out.println("\n=== CONTRATOS ATIVOS ===");
        for (Object[] contrato : contratos) {
            System.out.println("ID Locação: " + contrato[0]);
            System.out.println("Inquilino: " + contrato[1]);
            System.out.println("Propriedade: " + contrato[2] + " (" + contrato[3] + ")");
            System.out.println("Valor: R$ " + contrato[4]);
            System.out.println("Data Início: " + ((LocalDate) contrato[5]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Data Fim: " + ((LocalDate) contrato[6]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("-".repeat(40));
        }
    }

    private static void listarInquilinosComMaisContratos() {
        List<Object[]> inquilinos = inquilinoDAO.listarInquilinosComMaisContratos();

        if (inquilinos.isEmpty()) {
            System.out.println("Nenhum inquilino encontrado.");
            return;
        }

        System.out.println("\n=== INQUILINOS COM MAIS CONTRATOS ===");
        for (Object[] inquilino : inquilinos) {
            System.out.println("ID: " + inquilino[0]);
            System.out.println("Nome: " + inquilino[1]);
            System.out.println("Email: " + inquilino[2]);
            System.out.println("Total de Contratos: " + inquilino[3]);
            System.out.println("-".repeat(40));
        }
    }

    private static void listarContratosExpirandoProximos30Dias() {
        List<Object[]> contratos = locacaoDAO.listarContratosExpirandoProximos30Dias();

        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato expirando nos próximos 30 dias.");
            return;
        }

        System.out.println("\n=== CONTRATOS EXPIRANDO NOS PRÓXIMOS 30 DIAS ===");
        for (Object[] contrato : contratos) {
            LocalDate dataFim = (LocalDate) contrato[6];
            long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), dataFim);
            
            System.out.println("ID Locação: " + contrato[0]);
            System.out.println("Inquilino: " + contrato[1]);
            System.out.println("Propriedade: " + contrato[2] + " (" + contrato[3] + ")");
            System.out.println("Valor: R$ " + contrato[4]);
            System.out.println("Data Início: " + ((LocalDate) contrato[5]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Data Fim: " + dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Dias restantes: " + diasRestantes);
            System.out.println("-".repeat(40));
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

