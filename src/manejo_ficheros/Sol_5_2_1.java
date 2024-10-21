package manejo_ficheros;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Sol_5_2_1 {
	public void main(String[] args) {
		LeeFichero();
	}

	public void LeeFichero() {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			try {
				Document doc = builder.parse("xml_files/coches.xml");
				doc.normalize();
				// crea una lista con todos los nodos alumnos
				NodeList coches = doc.getElementsByTagName("coche");
				for (int i = 0; i < coches.getLength(); i++) {
					Element coche = (Element) coches.item(i);
					String matricula = coche.getAttribute("matricula");
					Element eMarca = (Element) coche.getElementsByTagName("marca").item(0);
					String marca = eMarca.getTextContent();
					Element eAnio = (Element) coche.getElementsByTagName("anio").item(0);
					String anio = eAnio.getTextContent();
					System.out.printf("La matricula del coche %s es %s, tiene %s años y su marca es %s"
							+ System.lineSeparator(), matricula, marca, anio, marca);
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finalizada la ejecución del método LeeFichero");
	}

}
