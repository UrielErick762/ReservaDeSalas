package com.mycompany.reservasdesalas.CommandSala;

import com.mycompany.reservasdesalas.InterfaceCommand;
import DAO.SalaDAO;
import DAO.ReservaDAO;
import Entity.Reserva;
import Entity.Sala;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultarSalaAction implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        SalaDAO sdao = new SalaDAO();
        ReservaDAO rdao = new ReservaDAO();
        LocalDate hoje = LocalDate.now();

        try {
            String idStr = request.getParameter("txtid");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                Sala sala = sdao.consultaPorId(id);
                request.setAttribute("sala", sala);
            } else {
                List<Sala> listaSalas = sdao.consultaTudo();
                request.setAttribute("salas", listaSalas);
                
                Map<Sala, Boolean> disponibilidade = new HashMap<>();
                
                for (Sala s : listaSalas) {
                    List<Reserva> reservasHoje = rdao.reservasPorSalaData(s.getId(), hoje);
                    
                    boolean disponivel = reservasHoje.isEmpty();
                    disponibilidade.put(s, disponivel); 
                }
                
                request.setAttribute("disponibilidade", disponibilidade);
            }

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
            msg = "Erro ao consultar salas: " + e.getMessage();
            request.setAttribute("msg", msg);
            return "view/erro.jsp";
        }

        request.setAttribute("msg", msg);
        return "view/Salas.jsp";
    }
}