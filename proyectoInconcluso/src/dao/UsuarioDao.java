/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;


/**
 *
 * @author chelo
 */
public class UsuarioDao {
    
    Conexion con;

    public UsuarioDao() {
        this.con = new Conexion();
    }
    
    public ArrayList<Usuario> getUsuarios(){
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM usuario ";
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                int idUsuario = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                usuarios.add(new Usuario(idUsuario, nombre));
            }
            //accesoBD.close();
            return usuarios;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
        
        
        
    }
    public Usuario getUsuario(int id){
           Usuario u= new Usuario();
           Connection accesoBD = con.getConexion();
            
             try{
            String sql="SELECT * FROM usuario WHERE id_usuario="+id+";";
            
            Statement st = accesoBD.createStatement(); //Objeto que viaja hasta la base de datos y lleva la consulta
            ResultSet resultados = st.executeQuery(sql); //Resultados enviados de vuelta
           
            
             if(resultados.first()) { //es una iterador propio de resultset que entrega un boleano y cambia a la siguiente tupla para obtener datos de todas las tuplas.
                String nombre = resultados.getString("nombre");
                
                int idUsuario = resultados.getInt("id_usuario");
                u = new Usuario(nombre, idUsuario); 
            }
                 if (u.getId_usuario()==0) {
                     System.out.println("No se ha encontrado información sobre el id: "+id);
                 }
            return u;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }   
    }
    public boolean insertarUsuario(Usuario u ){
        Connection accesoBD = con.getConexion();
        
        //insert into persona (nombre,apellido,edad)
        //values('pedro','puentes','18');
        
        try{
            String sql= "INSERT INTO usuario (nombre,apellido,edad) "+"VALUES('"+u.getNombre()+"',,"+u.getId_usuario()+")";
            
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);//execute update es para modificar la bd 
            return true;
        }catch(Exception e){
            System.out.println("Error al insertar persona");
            e.printStackTrace();
        }
        return false;
        
        
    }
   
}

