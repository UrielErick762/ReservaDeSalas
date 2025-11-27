package DAO;

import Entity.Sala;
import Entity.SalaLaboratorio;
import Entity.SalaPadrao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO implements InterfaceSalaDAO {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        return Conexao.getConexaoMySQL();
    }
    
    private Sala instanciaSalaPorTipo(String tipo){
        if (tipo == null){ 
            return new SalaPadrao();
        }
        
        switch (tipo.toUpperCase()){
            case "LAB":
                return new SalaLaboratorio();
            default:
                return new SalaPadrao();
        }
    }
    
    @Override
    public Sala consultaPorId(int idSala) throws ClassNotFoundException, SQLException {
        Sala sala = null;
        Connection con = getConexao();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Sala WHERE idSala = ?");
        ps.setInt(1, idSala);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            sala = instanciaSalaPorTipo(rs.getString("tipo"));
            sala.setId(rs.getInt("idSala"));
            sala.setNome(rs.getString("nome"));
            sala.setCapacidade(rs.getInt("capacidade"));
            sala.setPredio(rs.getString("predio"));
            sala.setAndar(rs.getInt("andar"));
            sala.setNumero(rs.getInt("numero"));
            sala.setStatus(rs.getBoolean("status"));
            sala.setHorarioDisp(rs.getString("horarioDisp"));
        }
        con.close();
        return sala;
    }

    @Override
    public List<Sala> consultaTudo() throws ClassNotFoundException, SQLException {
        List<Sala> lista = new ArrayList<>();
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM Sala");
        ResultSet rs = comando.executeQuery();

        while (rs.next()) {
            Sala sala = instanciaSalaPorTipo(rs.getString("tipo"));
            sala.setId(rs.getInt("idSala"));
            sala.setNome(rs.getString("nome"));
            sala.setCapacidade(rs.getInt("capacidade"));
            sala.setPredio(rs.getString("predio"));
            sala.setAndar(rs.getInt("andar"));
            sala.setNumero(rs.getInt("numero"));
            sala.setStatus(rs.getBoolean("status"));
            sala.setHorarioDisp(rs.getString("horarioDisp"));
            lista.add(sala);
        }
        con.close();
        return lista;
    }
}