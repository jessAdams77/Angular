package com.test.o.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.test.o.objects.Productos;

@WebServlet("/formServlet")
public class FormServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3003662635571378121L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

		String str = null;	
		StringBuffer sb = null;
		JSONObject jObj = null;
		BufferedReader br = null;

		try {					
			br = req.getReader();
			sb = new StringBuffer();

			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			jObj = new JSONObject( sb.toString());
			
			JSONArray arr = jObj.getJSONArray("productos");
			
			Gson gson = new Gson();
			List<Productos> listProd = new ArrayList<Productos>();
			
			for(Object x:  arr) {
				Productos prod = gson.fromJson(x.toString(), Productos.class);
				listProd.add(prod);
			}
			
			List<Productos> listProdconiva = calculoIva(listProd);
			
			String json = gson.toJson(listProdconiva);
			
			JSONObject jsonString = new JSONObject().put("productosI", new JSONArray(json));
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(jsonString);
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Productos> calculoIva (List<Productos> prod){
		final Double porcentajeiva = 0.16D;
		for(Productos a: prod) {
			Double precio = a.getPrecio()*porcentajeiva;
			Double precioFinal = Math.round(precio * 100) / 100d;
			a.setIva(precioFinal);
			a.setPreciomasiva(a.getPrecio()+a.getIva());
		}
		
		return prod;
	}
	
	

}
