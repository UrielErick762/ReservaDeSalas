package DAO;

import Entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// parte mafe
public class UsuarioDAO implements InterfaceUsuarioDAO {

    // parte mafe
    @Override
    public Usuario buscarPorEmailESenha(String email, String senha) throws Exception {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM Usuario WHERE email = ? AND senha = ?"
        );
        ps.setString(1, email);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            con.close();
            return u;
        }

        con.close();
        return null;
    }
    
    // parte mafe
    @Override
    public Usuario consultar(int idUsuario) throws Exception{
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?");
        ps.setInt(1, idUsuario);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            con.close();
            return u;
        }
        con.close();
        return null;
    }
}