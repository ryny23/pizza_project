package pizzaInnodbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pizza_connect {
	
	static ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();	

	public static void main(String[] args) {
		 
		System.out.println("********recupération des datas de la base mysql***");
		//recupération des datas de la base mysql
		System.out.println("connection a mysql pizzaboxinnodb");
			
		String url = "jdbc:mysql://localhost/pizzaboxinnodb";
		
		String login = "root";
		String passwd = "";
		
		Connection cn =null; 
		
		Statement st =null;
		ResultSet rs =null;
 
		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			String sql = "SELECT * FROM PIZZA"   ;

			// Etape 4 : exécution requête
			rs = st.executeQuery(sql);
			
			// Si récup données alors étapes 5 (parcours Resultset)
			System.out.println(rs);
			
			while (rs.next()) {
				System.out.println("-----------recup data from mysql------------------------");
				System.out.println(rs.getString("DESIGNPIZZ"));
				System.out.println(rs.getFloat("TARIFPIZZ"));
				System.out.println(rs.getInt("NROPIZZ"));
				System.out.println(rs.getString("image1_chemin"));
				
				//Pizza(String nomPizza, double prixPizza, Integer numeroPizza, String cheminImage)
				 Pizza p1= new Pizza(rs.getString("DESIGNPIZZ"),rs.getFloat("TARIFPIZZ"),rs.getInt("NROPIZZ"),rs.getString("image1_chemin"));
			     System.out.println(p1);//appel a la fonction tostring
			     pizzaList.add(p1);
			}
			 
			
		} catch (ClassNotFoundException e) {//si il y un probleme de driver
		 
			e.printStackTrace();
			
			
		} catch (SQLException e) {//si il y un probleme de sql
	 
			e.printStackTrace();
		}
		
		//*******************verifier si la liste de pizzas****************
		System.out.println("********verifier si la liste de pizzas***");
		for(Pizza maPizza : pizzaList) System.out.println(maPizza);
			
		
		
	}//fin du main 

}//fin de la classe
