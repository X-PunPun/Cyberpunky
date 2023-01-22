package Controlador;

import Modelo.*;
import com.sun.source.tree.NewArrayTree;
import java.sql.*;
import java.util.ArrayList;

public class Conexion {
    private PreparedStatement ps;
    private ResultSet rs;
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/db_ciberpunky";
    private String user = "root";
    private String pass = "123456789";
    
    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public Connection getConnection() {
        return conn;
    }
    
    //ingreso de usuario
    public ArrayList<Usuarios> Login(String usuario, String contraseña){
        ArrayList<Usuarios> res= new ArrayList<>();
        try{
            ps=conn.prepareStatement("select * from usuario where usuario=? and contraseña=?");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuarios user= new Usuarios();
                user.setId(rs.getString("idUser"));
                user.setNombre(rs.getString("usuario"));
                user.setContraseña(rs.getString("contraseña"));
                res.add(user);
            }
            if(res.isEmpty()){
                System.out.println("Error con login");
            }
            else{
                System.out.println("login exitoso");
            }
        } catch(Exception e){
            
        }
        return res;
    }

}
