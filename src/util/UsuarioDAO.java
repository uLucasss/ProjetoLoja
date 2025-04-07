package util;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {
    
    private final ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    private static List<Usuario> listaUsuarios = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return listaUsuarios;
    }
    
    public void removerUsuario(int indice) {
        if (indice >= 0 && indice < listaUsuarios.size()) {
            listaUsuarios.remove(indice);
        }
    }
    
    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    
    // CREATE (Inserir Usuário)
 
    // READ (Listar Usuário)
    
    // UPDATE (Atualizar Usuário)
    
    // DELETE (Excluir Usuário)
    
    
}
