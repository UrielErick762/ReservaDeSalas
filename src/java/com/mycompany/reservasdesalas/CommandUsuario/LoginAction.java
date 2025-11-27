package com.mycompany.reservasdesalas.CommandUsuario;

import DAO.UsuarioDAO;
import Entity.Usuario;
import com.mycompany.reservasdesalas.InterfaceCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);

        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogado", usuario);
            
            //MUDANÇAS MAFE
            // BOAS PRÁTICAS MVC (Padrão PRG):
            // Não chame o JSP direto ("view/Salas.jsp").
            // Redirecione para a Action que prepara os dados da tela.
            return "redirect:frontcontrollerServlet?action=verificarDisponibilidadeSala"; 
            
        } else {
            return "redirect:index.html?erro=1"; 
        }
    }
}