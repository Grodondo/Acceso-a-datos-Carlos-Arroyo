package manejo_ficheros;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Sol_5_3_1 {

	public void main(String[] args) {
        //crearXml();
        aumentarAnioMatriculacion();
    }
	
	public void aumentarAnioMatriculacion() {
	    File file_xml = new File("./coches_out.xml");

	    DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
	    try {
	        DocumentBuilder builder = factoria.newDocumentBuilder();
	        Document doc = builder.parse(file_xml);
	        doc.getDocumentElement().normalize();

	        NodeList coches = doc.getElementsByTagName("coche");
	        for (int i = 0; i < coches.getLength(); i++) {
	            Element coche = (Element) coches.item(i);
	            Element eAnio = (Element) coche.getElementsByTagName("anio").item(0);
	            int anio = Integer.parseInt(eAnio.getTextContent());
	            anio++;
	            eAnio.setTextContent(Integer.toString(anio));
	        }

	        TransformerFactory factoriaT = TransformerFactory.newDefaultInstance();
	        Transformer tranformador = factoriaT.newTransformer();
	        Source s = new DOMSource(doc);
	        Result r = new StreamResult(file_xml);
	        tranformador.transform(s, r);

	        System.out.println("Año de matriculación incrementado exitosamente");

	    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	        e.printStackTrace();
	    }
	}
	
	public void crearXml() {
		File f = new File("./coche.dat");
		try {
			
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			
			while(dis.available() > 0) {
				String args[] = dis.readUTF().split(" ");
				EscribeFichero(args[0], args[1], args[2]);
			}
			dis.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void EscribeFichero(String matricula, String marca, String anioMatriculacion) {
	
		File file_xml = new File("./coches_out.xml");
		
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factoria.newDocumentBuilder();
			Document doc = null;
			if (file_xml.exists()) {
				//System.out.println("El fichero ya existe");
                doc = builder.parse(file_xml);
                doc.getDocumentElement().normalize();
			}else {
				DOMImplementation dImp = builder.getDOMImplementation();
				doc = dImp.createDocument(null, "coches", null);
				doc.setXmlVersion("1.0");
				
			}
		
			Element coche = doc.createElement("coche");
			coche.setAttribute("matricula", matricula);
			CreaNodo(doc, coche, "marca", marca);
			CreaNodo(doc, coche, "anio", anioMatriculacion);
			doc.getDocumentElement().appendChild(coche);
			
			TransformerFactory factoriaT = TransformerFactory.newDefaultInstance();
			try {
				Transformer tranformador = factoriaT.newTransformer();
				Source s = new DOMSource(doc);
				Result r = new StreamResult(file_xml);
				tranformador.transform(s, r);
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finalizada la ejecución del método EscribeFichero");
	}

	private void CreaNodo(Document doc, Element padre, String nombreElemento, String valorElemento) {
		Element nodo = doc.createElement(nombreElemento);
		Text valor = doc.createTextNode(valorElemento);
		nodo.appendChild(valor);
		padre.appendChild(nodo);
	}
}
