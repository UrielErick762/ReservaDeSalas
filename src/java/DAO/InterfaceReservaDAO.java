
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Reserva;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author CLIENTE
 */
public interface InterfaceReservaDAO {
    
    public void cadastro(Reserva res) throws ClassNotFoundException, SQLException;
    
    public void atualiza(Reserva res) throws ClassNotFoundException, SQLException;
    
    public void deleta(Reserva res) throws ClassNotFoundException, SQLException;
    
    public List<Reserva> reservasPorSalaData(int idSala, LocalDate data) throws ClassNotFoundException, SQLException;
    
    public List<Reserva> reservasPorUsuario(int idUsuario) throws ClassNotFoundException, SQLException;
    
    public List<Reserva> consultarPorUsuario(int idUsuario) throws ClassNotFoundException, SQLException;

}