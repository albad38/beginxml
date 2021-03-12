package tt_testzadanie;

import java.io.File;		//библиотека для работы с файлами
import java.io.FileWriter;
import java.sql.Connection;  //библиотека для работы с SQL
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;       //библиотека для работы с XML
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;  //библиотека объектной модели документа для работы с HTML, XHTML, XML-документами
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class myfirstxml {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
		
		Connection connection = getMySQLConnection(); 	//установление соединения
		System.out.print("Соединение установлено" + "\n" + "\n");  //сообщение об установке соединения	
		
		
		File importXML = new File("importsql.xml");		//файл, который нужно прочитать
		DocumentBuilderFactory importDBFactory = DocumentBuilderFactory.newInstance();	//получение фабрики
		DocumentBuilder importDBuilder = importDBFactory.newDocumentBuilder();    //билдер, парсящий XML
		Document importdoc = importDBuilder.parse(importXML);    //парсинг XML-файла
		System.out.print("Root element: " + importdoc.getDocumentElement().getNodeName());   //корневой элемент
		NodeList nodelist = importdoc.getElementsByTagName("human"); //
		
		for (int i = 0; i < nodelist.getLength(); i++) { 	//циклический вывод XML на экран
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
		
		Statement statement = connection.createStatement();  //создание класса для SQL-запроса
        ResultSet resultset = statement.executeQuery("SELECT * FROM humans"); //результирующие данные согласно SQL-запросу
        
		File file = new File("exportsql.xml");  //создание файла
		FileWriter fw = new FileWriter(file);  //создание объекта для записи

		int columns = resultset.getMetaData().getColumnCount(); 
   
		while(resultset.next()) {	// Перебор строк с данными
            for (int i = 1; i <= columns; i++) { 
                System.out.print(resultset.getString(i) + "\t"); //вывод считанных данных на консоль 
                fw.write(resultset.getMetaData().getColumnName(i) + "   " + resultset.getString(i)  +"\n");  //запись считанных строк в файл

            }
            System.out.println();
        }
		
		fw.flush();
		fw.close();				//закрываем файл-райтер
		statement.close();
		resultset.close();		
		connection.close(); 	//закрываем соединение
	
	}
			       
        catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для MySQL не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL!");
        }
	}
	
	private static Connection getMySQLConnection() throws Exception {   //метод подключения к базе данных
		// TODO Auto-generated method stub
		String driver = "com.mysql.cj.jdbc.Driver";   //драйвер подключения к MySQL
		String url = "jdbc:mysql://localhost/tt_test";  //ссылка на базу данных
		String username = "root"; // логин (имя пользователя)
		String password = "";   //пароль
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
}
