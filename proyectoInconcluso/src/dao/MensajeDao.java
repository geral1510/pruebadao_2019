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
import modelo.Mensaje;
import modelo.Usuario;


/**
 *
 * @author chelo
 */
public class MensajeDao {
    
    Conexion con;

    public MensajeDao() {
        this.con = new Conexion();
    }
    
    public ArrayList<Mensaje> getMensajes(){
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM mensaje ";
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                
                int id_mensaje = resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                
               UsuarioDao ud= new UsuarioDao(); //para obtener el nombre de la persona a travez de la clave foreanea 
                Usuario id = ud.getUsuario(id_usr_emisor);
                Usuario ids= ud.getUsuario(id_usr_receptor);
                
                mensajes.add(new Mensaje(id_mensaje, id, ids, contenido));
            }
            //accesoBD.close();
            return mensajes;
            

                
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
        
        
        
    }
    public Mensaje getMensaje(int id){
           Mensaje m= new Mensaje();
           Connection accesoBD = con.getConexion();
            
             try{
            String sql="SELECT * FROM mensaje WHERE id_usuario="+id+";";
            
            Statement st = accesoBD.createStatement(); //Objeto que viaja hasta la base de datos y lleva la consulta
            ResultSet resultados = st.executeQuery(sql); //Resultados enviados de vuelta
           
            
             if(resultados.first()) { //es una iterador propio de resultset que entrega un boleano y cambia a la siguiente tupla para obtener datos de todas las tuplas.
                int id_mensaje = resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                UsuarioDao ud= new UsuarioDao(); //para obtener el nombre de la persona a travez de la clave foreanea 
                
                Usuario ids= ud.getUsuario(id_usr_receptor);
                
                m= new Mensaje(id_mensaje, ids, ids, contenido); 
            }
                 if (m.getId_mensaje()==0) {
                     System.out.println("No se ha encontrado información sobre el id: "+id);
                 }
            return m;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }   
    }
    public boolean insertarMensaje(Mensaje m ){
        Connection accesoBD = con.getConexion();
        
        //insert into persona (nombre,apellido,edad)
        //values('pedro','puentes','18');
        
        try{
            String sql= "INSERT INTO mensaje (id_mensaje,contenido,id_usr_emisor,id_usr_receptor) "+"VALUES('"+m.getId_mensaje()+"','"+m.getContenido()+"',"+m.getEmisor()+"','"+m.getRemitente()+"')";
            
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

