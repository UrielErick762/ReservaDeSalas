package DAO;

import Entity.Reserva;
import Entity.Sala;
// Parte Mafe - Import da classe concreta para resolver o problema de instanciar classe abstrata
import Entity.SalaPadrao;
import Entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

// parte mafe
public class ReservaDAO implements InterfaceReservaDAO {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        return Conexao.getConexaoMySQL();
    }

    // parte mafe
    @Override
    public void cadastro(Reserva res) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "INSERT INTO Reserva(data, horaInicio, horaFim, status, idSala, idUsuario, observacao) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        comando.setDate(1, java.sql.Date.valueOf(res.getData()));
        comando.setTime(2, Time.valueOf(res.getHoraInicio()));
        comando.setTime(3, Time.valueOf(res.getHoraFim()));
        comando.setBoolean(4, res.isStatus());
        comando.setInt(5, res.getIdSala().getId());
        comando.setInt(6, res.getIdUsuario().getId());
        comando.setString(7, res.getObservacao());

        comando.execute();
        con.close();
    }
    
    // parte mafe
    @Override
    public void atualiza(Reserva res) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "UPDATE Reserva SET data = ?, horaInicio = ?, horaFim = ?, status = ?, idSala = ?, idUsuario = ?, observacao = ? WHERE idReserva = ?"
        );
        comando.setDate(1, java.sql.Date.valueOf(res.getData())); 
        comando.setTime(2, Time.valueOf(res.getHoraInicio()));
        comando.setTime(3, Time.valueOf(res.getHoraFim()));
        comando.setBoolean(4, res.isStatus());
        comando.setInt(5, res.getIdSala().getId());
        comando.setInt(6, res.getIdUsuario().getId());
        comando.setString(7, res.getObservacao());
        comando.setInt(8, res.getIdReserva());
        comando.execute();
        con.close();
    }

    // parte mafe
    @Override
    public void deleta(Reserva res) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "DELETE FROM Reserva WHERE idReserva = ?"
        );
        comando.setInt(1, res.getIdReserva());
        comando.execute();
        con.close();
    }

    // parte mafe
    @Override
    public List<Reserva> reservasPorSalaData(int idSala, java.time.LocalDate data) throws ClassNotFoundException, SQLException {
        List<Reserva> reservas = new ArrayList<>();
        Connection con = getConexao();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM Reserva WHERE idSala = ? AND data = ?"
        );
        ps.setInt(1, idSala);
        ps.setDate(2, java.sql.Date.valueOf(data));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Reserva r = new Reserva();
            r.setIdReserva(rs.getInt("idReserva"));
            
            // Parte Mafe - Uso de SalaPadrao para evitar erro de classe abstrata
            Sala sala = new SalaPadrao();
            sala.setId(rs.getInt("idSala"));
            r.setIdSala(sala);
            
            Usuario usu = new Usuario();
            usu.setIdUsuario(rs.getInt("idUsuario"));
            // Parte Mafe - Correção: atribuindo usuário corretamente
            r.setIdUsuario(usu);
            
            r.setData(rs.getDate("data").toLocalDate());
            r.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
            r.setHoraFim(rs.getTime("horaFim").toLocalTime());
            r.setObservacao(rs.getString("observacao"));
            r.setStatus(rs.getBoolean("status"));
            reservas.add(r);
        }
        con.close();
        return reservas;
    }
    
    // parte mafe
    @Override
    public List<Reserva> reservasPorUsuario(int idUsuario) throws ClassNotFoundException, SQLException {
        List<Reserva> reservas = new ArrayList<>();
        Connection con = getConexao();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM Reserva WHERE idUsuario = ? ORDER BY data, horaInicio"
        );
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Reserva r = new Reserva();
            r.setIdReserva(rs.getInt("idReserva"));
            
            // Parte Mafe - Uso de SalaPadrao
            Sala sala = new SalaPadrao();
            sala.setId(rs.getInt("idSala"));
            r.setIdSala(sala);
            
            Usuario usu = new Usuario();
            usu.setIdUsuario(rs.getInt("idUsuario"));
            r.setIdUsuario(usu);
            
            r.setData(rs.getDate("data").toLocalDate());
            r.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
            r.setHoraFim(rs.getTime("horaFim").toLocalTime());
            r.setObservacao(rs.getString("observacao"));
            r.setStatus(rs.getBoolean("status"));
            reservas.add(r);
        }

        con.close();
        return reservas;
    }
    
    // parte mafe
    @Override
    public List<Reserva> consultarPorUsuario(int idUsuario) throws ClassNotFoundException, SQLException {
        List<Reserva> reservas = new ArrayList<>();
        Connection con = getConexao();

        String sql = "SELECT * FROM Reserva WHERE idUsuario = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Reserva r = new Reserva();
            r.setIdReserva(rs.getInt("idReserva"));
            
            // Parte Mafe - Correção do erro "Sala is abstract"
            Sala sala = new SalaPadrao(); 
            sala.setId(rs.getInt("idSala"));
            r.setIdSala(sala);
            
            Usuario usu = new Usuario();
            usu.setIdUsuario(rs.getInt("idUsuario"));
            r.setIdUsuario(usu);
            
            r.setData(rs.getDate("data").toLocalDate());
            r.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
            r.setHoraFim(rs.getTime("horaFim").toLocalTime());
            r.setObservacao(rs.getString("observacao"));
            r.setStatus(rs.getBoolean("status"));
            reservas.add(r);
        }

        con.close();
        return reservas;
    }
}