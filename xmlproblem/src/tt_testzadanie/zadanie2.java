package tt_testzadanie;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class zadanie2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = getMySQLConnection(); 	//install connection
			Statement statement = connection.createStatement();  //Class for SQL-query
			ResultSet resultset = statement.executeQuery("LOAD XML INFILE 'C:/Users/badsa/eclipse-workspace/xmlproblem/toMySQL.xml' INTO TABLE humans ROWS IDENTIFIED BY '<humans>';"); //Result data
			System.out.print("Connection installed!" + "\n" + "\n");  //console message about connection
			
			File importXML = new File("toMySQL.xml");		//file to reading
			DocumentBuilderFactory importDBFactory = DocumentBuilderFactory.newInstance();	//obtain fabric
			DocumentBuilder importDBuilder = importDBFactory.newDocumentBuilder();    //builder for parse XML
			Document importdoc = importDBuilder.parse(importXML);    //parse XML
			System.out.print("Root element: " + importdoc.getDocumentElement().getNodeName() + "\n" + "\n");   //root element
			NodeList nodelist = importdoc.getElementsByTagName("humans"); 
					
			for (int i = 0; i < nodelist.getLength(); i++) { 	//cycle output XML content
				Node node = nodelist.item(i);
				System.out.println("\nCurrent Element: " + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node; 
					System.out.println("id :" + " " + element.getElementsByTagName("id").item(0).getTextContent());		
					System.out.println("fname :" + " " + element.getElementsByTagName("fname").item(0).getTextContent());
					System.out.println("sname :" + " " + element.getElementsByTagName("sname").item(0).getTextContent());
					System.out.println("tname :" + " " + element.getElementsByTagName("tname").item(0).getTextContent());
					System.out.println("job :" + " " + element.getElementsByTagName("job").item(0).getTextContent());
					System.out.println("experience :" + " " + element.getElementsByTagName("experience").item(0).getTextContent());

				}
				System.out.print("-----------------------------------");
				System.out.println("                                   ");
				System.out.println("                                   ");
				
				}
			
			
		}        catch (ClassNotFoundException e) {
            e.printStackTrace(); // error handling  Class.forName
            System.out.println("JDBC driver for MySQL is not found!");
        } catch (SQLException e) {
            e.printStackTrace(); // error handling  DriverManager.getConnection
            System.out.println("SQL-error!");
        }

	}

	private static Connection getMySQLConnection() throws Exception {   //method connection to mysql db
		// TODO Auto-generated method stub
		String driver = "com.mysql.cj.jdbc.Driver";   //driver for connection to MySQL 
		String url = "jdbc:mysql://localhost/tt_test";  //db URL
		String username = "root"; // login
		String password = "";   //password
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}

