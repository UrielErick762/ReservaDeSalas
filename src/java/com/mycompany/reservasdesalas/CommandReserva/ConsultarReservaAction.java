package com.mycompany.reservasdesalas.CommandReserva;

import com.mycompany.reservasdesalas.InterfaceCommand;
import DAO.ReservaDAO;
//Parte Mafe - Imports necessários para a V2 funcionar
import DAO.SalaDAO;
import Entity.Reserva;
import Entity.Sala;
import Entity.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Parte Mafe - Import para segurança de sessão
import javax.servlet.http.HttpSession;

/**
 * @author uriel
 */
public class ConsultarReservaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        ReservaDAO rdao = new ReservaDAO();
        
        //Parte Mafe - Precisamos do SalaDAO para buscar a lista de salas que exige
        SalaDAO sdao = new SalaDAO();

        try {
            //Parte Mafe - SEGURANÇA: Pegamos o usuário da sessão (quem está logado)
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            if (usuario == null) {
                return "redirect:index.html";
            }

            //Parte Mafe - Busca as reservas APENAS deste usuário
            List<Reserva> reservas = rdao.consultarPorUsuario(usuario.getIdUsuario());

            //Parte Mafe - Busca TODAS as salas para conseguir mostrar os nomes delas
            List<Sala> salas = sdao.consultaTudo();

            //Parte Mafe - Envia para o JSP com os nomes exatos ("reservas" e "salas")
            request.setAttribute("reservas", reservas);
            request.setAttribute("salas", salas);
            
            msg = "Consulta realizada com sucesso!";

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao consultar reservas: " + e.getMessage();
        }

        request.setAttribute("msg", msg);
        
        //Parte Mafe - Aponta para o arquivo (que é o correto com MVC)
        return "view/MinhasReservas.jsp"; 
    }
}