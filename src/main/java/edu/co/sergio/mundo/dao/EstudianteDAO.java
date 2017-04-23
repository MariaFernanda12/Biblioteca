package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Estudiante;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Isabel-Fabian
 * @since 12/08/2015
 * @version 2
 * Clase que permite la gestion de la tabla Depto en la base de datos.
 * 
 * CREATE TABLE Depto(
 *     id_depto integer,
 *     nom_depto varchar(40),
 *     PRIMARY KEY(id_depto)
 * );
 */
 

public class EstudianteDAO implements IBaseDatos<Estudiante> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Estudiante> findAll() {
		List<Estudiante> departamentos= null;
	    String query = "SELECT * FROM Depto";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null;
	
	    while (rs.next()){
	    	if(departamentos == null){
	    		departamentos= new ArrayList<Estudiante>();
	    	}
	      
	        Estudiante registro= new Estudiante();
	        id = rs.getInt("id_depto");
	        registro.setClave(id);
	        
	        nombre = rs.getString("nom_depto");
	        registro.setNom(nombre) ;
	        
	        departamentos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return departamentos;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Estudiante t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Depto (id_depto,nom_depto)"  + " values (?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getClave());
                        preparedStmt.setString (2, t.getNom());
			result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Funcion que permite realizar la actualizacion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de actualizacion es exitosa.
	 */
	public boolean update(Estudiante t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Depto set nom_depto = ? where id_depto = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom());
                    preparedStmt.setInt   (2, t.getClave());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}

	/**
	 * Funcion que permite realizar la eliminario de registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de borrado es exitosa.
	 */
	public boolean delete(Estudiante t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Depto where id_depto = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getClave());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}

    @Override
    public boolean search(Estudiante t) {
     boolean result=false;
     Connection connection = null;
     try{
        connection = Conexion.getConnection(); 
     }catch(URISyntaxException ex){
         
     }
    String query = "SELECT * FROM nombre WHERE nombreEstu='usuario' AND claveEstu ='clave' = ?";
    PreparedStatement preparedStmt=null;
    try{
        preparedStmt = connection.prepareStatement(query);
        result= preparedStmt.execute();
    }catch(SQLException e){
        e.printStackTrace();
    }
     
     return result;
    }
}
