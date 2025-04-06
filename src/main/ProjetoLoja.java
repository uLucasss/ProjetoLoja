package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Cliente;
import util.ClienteDAO;

public class ProjetoLoja {

    public static void main(String[] args) {
        
        // Funcionalidade provisória
        ClienteDAO clienteDAO = new ClienteDAO();
        
        while (true) {
            String opcao = JOptionPane.showInputDialog(
                "Sistema de Cadastro de Clientes\n\n" +
                "1 - Cadastrar novo cliente\n" +
                "2 - Listar clientes (em tabela)\n" +
                "3 - Atualizar cliente\n" +
                "4 - Excluir cliente\n" +
                "5 - Sair\n\n" +
                "Digite a opção desejada:");
            
            if (opcao == null || opcao.equals("5")) {
                JOptionPane.showMessageDialog(null, "Sistema encerrado.");
                break;
            }
            
            try {
                switch (opcao) {
                    case "1":
                        cadastrarCliente(clienteDAO);
                        break;
                    case "2":
                        listarClientesEmTabela(clienteDAO);
                        break;
                    case "3":
                        atualizarCliente(clienteDAO);
                        break;
                    case "4":
                        excluirCliente(clienteDAO);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }
    
    private static void cadastrarCliente(ClienteDAO clienteDAO) { // Método provisório para cadastrar
        Cliente cliente = new Cliente();
        
        cliente.setNome(JOptionPane.showInputDialog("Digite o nome do cliente:"));
        cliente.setCpf(JOptionPane.showInputDialog("Digite o CPF do cliente:"));
        cliente.setEndereco(JOptionPane.showInputDialog("Digite o endereço do cliente:"));
        
        clienteDAO.inserirCliente(cliente);
    }
    
    private static void listarClientesEmTabela(ClienteDAO clienteDAO) { // Método provisório para listar
        List<Cliente> clientes = clienteDAO.listarClientes();
        
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
            return;
        }
        
        String[] colunas = {"ID", "Nome", "CPF", "Endereço"};
        Object[][] dados = new Object[clientes.size()][4];
        
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            dados[i][0] = c.getId();
            dados[i][1] = c.getNome();
            dados[i][2] = c.getCpf();
            dados[i][3] = c.getEndereco();
        }
        
        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true);
        
        tabela.setRowHeight(25);                                     // Expaçamento da tabela
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // Expaçamento da tabela
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(800, 500)); // Dimensões do scrollPane
        
        JOptionPane.showMessageDialog(
            null, 
            scrollPane,  
            "Lista de Clientes", 
            JOptionPane.PLAIN_MESSAGE
        );
    }
    
    private static void atualizarCliente(ClienteDAO clienteDAO) { // Método provisório para atualizar
        String idTexto = JOptionPane.showInputDialog("Digite o ID do cliente a ser atualizado:");
        int id = Integer.parseInt(idTexto);
        
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(JOptionPane.showInputDialog("Digite o novo nome:"));
        cliente.setCpf(JOptionPane.showInputDialog("Digite o novo CPF:"));
        cliente.setEndereco(JOptionPane.showInputDialog("Digite o novo endereço:"));
        
        clienteDAO.atualizarCliente(cliente);
    }
    
    private static void excluirCliente(ClienteDAO clienteDAO) { // Método provisório para excluir
        String idTexto = JOptionPane.showInputDialog("Digite o ID do cliente a ser excluído:");
        int id = Integer.parseInt(idTexto);
        
        clienteDAO.excluirCliente(id);
    }
}
