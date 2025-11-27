
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Usuario;

/**
 *
 * @author CLIENTE
 */
public interface InterfaceUsuarioDAO {
    
    Usuario buscarPorEmailESenha(String email, String senha) throws Exception;
    
    Usuario consultar(int idUsuario) throws Exception;
    }