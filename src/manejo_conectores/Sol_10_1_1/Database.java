package manejo_conectores.Sol_10_1_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

	private String url_con;
	private String user;
	private String pass;
	private String routeCreateTable;
	private String routeInsertData;

	public Database() {
		loadConfigNoProperties(new File("Database\\bd_csv.config"));
	}

	private void loadConfigNoProperties(File f) {
		BufferedReader br = null;
		System.out.println("Config file used: " + f);
		
		try {
			br = new BufferedReader(new FileReader(f));
			String linea;
			while ((linea = br.readLine()) != null) {
				// Ignorar líneas vacías o comentarios
				if (linea.trim().isEmpty() || linea.startsWith("#")) {
					continue;
				}

				// Dividir cada línea en clave y valor usando el carácter '='
				String[] partes = linea.split("=", 2);
				if (partes.length == 2) {
					String clave = partes[0].trim();
					String valor = partes[1].trim();

					// Asignar el valor a la variable correspondiente
					switch (clave) {
					case "con":
						this.url_con = valor;
						break;
					case "user":
						this.user = valor;
						break;
					case "pass":
						this.pass = valor;
						break;
					case "scriptCreateTable":
						this.routeCreateTable = valor;
						break;
					case "scriptInsertValues":
						this.routeInsertData = valor;
						System.out.println("Script insert values: " + valor);
						break;
					default:
						System.out.println("Clave desconocida en el archivo de configuración: " + clave);
					}
				} else {
					System.out.println("Formato incorrecto en la línea: " + linea);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("No se pudo cerrar el BufferedReader - loadConfigNoProperties");
				}
			}
		}
	}

	
	// Deprecated porque se ha pedido no usar properties
	@SuppressWarnings("unused")
	private void loadConfig() {
		Properties props = new Properties();

		try {
			props.load(new FileInputStream(new File("bd.config")));
			url_con = props.getProperty("conexion");
			user = props.getProperty("usuario");
			pass = props.getProperty("contraseña");
			routeCreateTable= props.getProperty("script_creacion_tabla");
			routeInsertData = props.getProperty("script_insercion_datos");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection createConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url_con, user, pass);
		} catch (Exception e) {
			System.err.println("Conexión fallida");
		}

		return con;
	}

	public String getRouteCreateTable() {
		return routeCreateTable;
	}

	public String getRouteInsertData() {
		return routeInsertData;
	}

}
