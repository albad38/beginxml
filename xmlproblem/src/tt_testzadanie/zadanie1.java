package tt_testzadanie;		//package

import java.io.File;		//library for working with files
import java.io.FileWriter;

import java.sql.Connection;  	//library for working with SQL
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;	//XML-library
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;  //DOM-library for XML
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class zadanie1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = getMySQLConnection(); 	//install connection
			System.out.print("Connection installed!" + "\n" + "\n");  //console message about connection
			
			Statement statement = connection.createStatement();  //Class for SQL-query
	        	ResultSet resultset = statement.executeQuery("SELECT * FROM humans"); //Result data
	        	
			System.out.print(resultset.getMetaData().getCatalogName(1));	//catalogname tt_test, database
			
			System.out.println();
			System.out.println();
			
			DocumentBuilderFactory fromMySQL = DocumentBuilderFactory.newInstance();  //obtain fabric
			DocumentBuilder builder = fromMySQL.newDocumentBuilder();	//builder for parse XML
			Document forMySQL = builder.newDocument();	 //new document forMySQL
			
			Element rootElement = forMySQL.createElement(resultset.getMetaData().getCatalogName(1)); 
			forMySQL.appendChild(rootElement); //tt_test
			
			int columns = resultset.getMetaData().getColumnCount(); //columns count = 6
			   
			while(resultset.next()) {	// cycle output
	            for (int i = 1; i <= columns; i++) { 
	                System.out.print(resultset.getString(i) + "\t"); 	//field attribute   
	            }
	            System.out.println();              
	        }
			
			resultset.close();		//close resultset	

	        ResultSet resultset2 = statement.executeQuery("SELECT * FROM humans"); //Result data

	        while (resultset2.next()) {		//cycle output 
				String id = resultset2.getString("id");
				String fname = resultset2.getString("fname");
				String sname = resultset2.getString("sname"); 
				String tname = resultset2.getString("tname"); 
				String job = resultset2.getString("job"); 
				String experience = resultset2.getString("experience");  //attributes
			
				rootElement.appendChild(gethumans(forMySQL, id, fname, sname, tname, job, experience));			
			}
		
	    TransformerFactory tfact = TransformerFactory.newInstance();
            Transformer transformer = tfact.newTransformer();
            DOMSource source = new DOMSource(forMySQL);
            
            StreamResult file = new StreamResult(new File("fromMySQL.xml"));   //create output file
            transformer.transform(source, file);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            System.out.println("XML-file has been created!");   
            


			
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
		
	 private static Node gethumans(Document forMySQL, String id, String fname, String sname, String tname, String job, String experience) {  //create Node
	        Element humans = forMySQL.createElement("Humans");
	  	        
	        humans.appendChild(gethumansElements(forMySQL, humans, "id", id));   //create id-element
	        humans.appendChild(gethumansElements(forMySQL, humans, "fname", fname));	//create fname-element	
	        humans.appendChild(gethumansElements(forMySQL, humans, "sname", sname)); 	//create sname-element
	        humans.appendChild(gethumansElements(forMySQL, humans, "tname", tname)); 	//create tname-element
	        humans.appendChild(gethumansElements(forMySQL, humans, "job", job)); 		//create job-element
	        humans.appendChild(gethumansElements(forMySQL, humans, "experience", experience)); 	//create experience-element
	        
	        return humans;
	    }
	 
	 private static Node gethumansElements(Document forMySQL, Element element, String name, String value) {  //gehumansElements
	        Element node = forMySQL.createElement(name);
	        node.appendChild(forMySQL.createTextNode(value));
	        return node;
	    }
	
}
