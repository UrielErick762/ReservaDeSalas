package com.mycompany.reservasdesalas.CommandReserva;

import com.mycompany.reservasdesalas.InterfaceCommand;
import DAO.ReservaDAO;
import DAO.SalaDAO;
import Entity.Reserva;
import Entity.Sala;
import Entity.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author uriel
 */
public class ConsultarReservaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        ReservaDAO rdao = new ReservaDAO();
        
        SalaDAO sdao = new SalaDAO();

        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            if (usuario == null) {
                return "redirect:index.html";
            }

            //Busca as reservas APENAS deste usuário
            List<Reserva> reservas = rdao.consultarPorUsuario(usuario.getIdUsuario());
            
            List<Sala> salas = sdao.consultaTudo();

            request.setAttribute("reservas", reservas);
            request.setAttribute("salas", salas);
            
            msg = "Consulta realizada com sucesso!";

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao consultar reservas: " + e.getMessage();
        }

        request.setAttribute("msg", msg);
        
        return "view/MinhasReservas.jsp"; 
    }
}