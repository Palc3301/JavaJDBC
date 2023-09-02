package Atvd03;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class ExtrairPassageirosXML {
    public static void main(String[] args) {
        try {
            File inputFile = new File("passageiros.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList passageiroList = doc.getElementsByTagName("passageiro");

            int numPassageiros = passageiroList.getLength();
            System.out.println("Número de passageiros: " + numPassageiros);

            System.out.println("Origens e destinos:");
            for (int temp = 0; temp < passageiroList.getLength(); temp++) {
                Node passageiroNode = passageiroList.item(temp);

                if (passageiroNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element passageiroElement = (Element) passageiroNode;
                    String origem = passageiroElement.getElementsByTagName("origem").item(0).getTextContent();
                    String destino = passageiroElement.getElementsByTagName("destino").item(0).getTextContent();
                    System.out.println("Origem: " + origem + ", Destino: " + destino);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
