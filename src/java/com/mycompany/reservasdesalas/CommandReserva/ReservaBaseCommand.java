package com.mycompany.reservasdesalas.CommandReserva;

import DAO.ReservaDAO;
import DAO.SalaDAO;
import DAO.UsuarioDAO;
import com.mycompany.reservasdesalas.InterfaceCommand;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReservaBaseCommand implements InterfaceCommand {
    
    protected ReservaDAO reservaDAO = new ReservaDAO();
    protected UsuarioDAO usuarioDAO = new UsuarioDAO();
    protected SalaDAO salaDAO = new SalaDAO();

    protected int getInt(HttpServletRequest req, String param) {
        return Integer.parseInt(req.getParameter(param));
    }

    protected boolean getBool(HttpServletRequest req, String param) {
        return Boolean.parseBoolean(req.getParameter(param));
    }

    protected LocalDate getDate(HttpServletRequest req, String param) {
        return LocalDate.parse(req.getParameter(param));
    }

    protected LocalTime getTime(HttpServletRequest req, String param) {
        return LocalTime.parse(req.getParameter(param));
    }

    protected String finaliza(HttpServletRequest req, String msg) {
        req.setAttribute("msg", msg);
        return "redirect:frontcontrollerServlet?action=consultarMinhasReservas";
    }

    protected abstract String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String msg;

        try {
            return process(req, resp);
        } catch (Exception e) {
            msg = "Erro: " + e.getMessage();
            e.printStackTrace();
            req.setAttribute("msg", msg);
            return "redirect:frontcontrollerServlet?action=consultarMinhasReservas";
        }
    }
}
