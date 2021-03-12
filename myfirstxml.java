package tt_testzadanie;

import java.io.File;		//library for working with files
import java.io.FileWriter;
import java.sql.Connection;  //library for working with SQL
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;       //library for working with XML
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;  //DOM-library for XML
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class myfirstxml {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
		
		Connection connection = getMySQLConnection(); 	//install connection
		System.out.print("Connection installed!" + "\n" + "\n");  //console message about connection
		
		
		File importXML = new File("importsql.xml");		//file to reading
		DocumentBuilderFactory importDBFactory = DocumentBuilderFactory.newInstance();	//obtain fabric
		DocumentBuilder importDBuilder = importDBFactory.newDocumentBuilder();    //builder for parse XML
		Document importdoc = importDBuilder.parse(importXML);    //parse XML
		System.out.print("Root element: " + importdoc.getDocumentElement().getNodeName());   //root element
		NodeList nodelist = importdoc.getElementsByTagName("human"); //
		
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
		
		Statement statement = connection.createStatement();  //Class for SQL-query
        ResultSet resultset = statement.executeQuery("SELECT * FROM humans"); //Result data
        
		File file = new File("exportsql.xml");  //create file
		FileWriter fw = new FileWriter(file);  //creating object for writing

		int columns = resultset.getMetaData().getColumnCount(); 
   
		while(resultset.next()) {	// cycle output
            for (int i = 1; i <= columns; i++) { 
                System.out.print(resultset.getString(i) + "\t"); //console output 
                fw.write(resultset.getMetaData().getColumnName(i) + "   " + resultset.getString(i)  +"\n");  //put in file

            }
            System.out.println();
        }
		
		fw.flush();
		fw.close();				//close filewriter
		statement.close();		//close statement
		resultset.close();		//close resultset
		connection.close(); 	//close connection
	
	}
			       
        catch (ClassNotFoundException e) {
            e.printStackTrace(); // error handling  Class.forName
            System.out.println("JDBC драйвер для MySQL не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // error handling  DriverManager.getConnection
            System.out.println("Ошибка SQL!");
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
