package com.test.o.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.test.o.objects.Productos;

public class Metodos {
	public List<Productos> calculoIva (List<Productos> prod){
		final Double porcentajeiva = 0.16D;
		
		for(Productos a: prod) {
			a.setIva(a.getPrecio()*porcentajeiva);
			a.setPreciomasiva(a.getPrecio()+a.getIva());
		}
		
		return prod;
	}
	
public Boolean insertProductos(List<Productos> prod) {
		
		String connectionUrl =
                "jdbc:sqlserver://DESKTOP-TTASE42\\SQLEXPRESS:1433;"
                        + "database=testBd;"
                        + "user=serverJess;"
                        + "password=serverjess77;";
        try{
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	
        	Connection connection = DriverManager.getConnection(connectionUrl);
        	if(connection != null) {
        		System.out.println("------------- Conectado a BD");
        		String insertar = "INSERT INTO Productos (id, nombre, descripcion, precio, cantidad, iva, preciomasiva) Values (?,?,?,?,?,?,?)";
        		PreparedStatement ps = null;
        		try {
        			for(Productos x: prod) {
        				ps = connection.prepareStatement(insertar);
            			ps.setInt(1, x.getId());
            			ps.setString(2, x.getNombre());
            			ps.setString(3, x.getDescripcion());
            			ps.setDouble(4, x.getPrecio());
            			ps.setInt(5, x.getCantidad());
            			ps.setDouble(6, x.getIva());
            			ps.setDouble(7, x.getPreciomasiva());
            			ps.executeUpdate();
        			}
				} catch (SQLException e) {
					e.printStackTrace();
				}
        		return true;
        	}
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
	}
}
