/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservasdesalas.CommandReserva;
import com.mycompany.reservasdesalas.InterfaceCommand;
import Entity.*;
import DAO.SalaDAO;
import DAO.UsuarioDAO;  
import DAO.ReservaDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizaReservaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        
         try {
            
            int idReserva = Integer.parseInt(request.getParameter("txtid"));
            LocalDate data = LocalDate.parse(request.getParameter("txtdata"));
            LocalTime horaInicio = LocalTime.parse(request.getParameter("txthoraInicio"));
            LocalTime horaFim = LocalTime.parse(request.getParameter("txthoraFim"));
            boolean status = Boolean.parseBoolean(request.getParameter("txtstatus"));
            int idSala = Integer.parseInt(request.getParameter("txtidSala"));
            int idUsuario = Integer.parseInt(request.getParameter("txtidUsuario"));
            String observacao = request.getParameter("txtobservacao");

            Sala sala = new SalaDAO().consultaPorId(idSala);
            Usuario usuario = new UsuarioDAO().consultar(idUsuario);
            ReservaDAO resDAO = new ReservaDAO();
            
            Reserva reserv = new Reserva.ReservaBuilder()
                    .comIdReserva(idReserva)
                    .comData(data)
                    .comHoraInicio(horaInicio)
                    .comHoraFim(horaFim)
                    .comStatus(status)
                    .comIdSala(sala)
                    .comIdUsuario(usuario)
                    .comObservacao(observacao)
                    .constroi();
            
            resDAO.atualiza(reserv);
            msg = "Reserva atualizada com sucesso!";

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao atualizar a reserva: " + e.getMessage();
        }

       
        request.setAttribute("msg", msg);
        return "redirect:frontcontrollerServlet?action=consultarMinhasReservas";
    }

}
  //dasa 