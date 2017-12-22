package net.nexg.exchange.util.message;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList; 

public class JsonConvertScheme {
	public static void main(String argv[]) {
		try { 
			File fXmlFile = new File("MessageConfig.xml"); 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXmlFile); //optional, but recommended //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work 
			doc.getDocumentElement().normalize(); 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); 
			NodeList nList = doc.getChildNodes();
			System.out.println(" nList.getLength() :" +  nList.getLength()); 

			for (int temp = 0; temp < nList.getLength(); temp++) { 
				Node nNode = nList.item(temp); 
				System.out.println("\nDepth[1] Current Element :" + nNode.getNodeName());
				
				if ( nNode.getNodeName().equals("#comment") 
						|| nNode.getNodeName().equals("#text") )
					continue;

				NodeList nListDepth1 = nNode.getChildNodes();
				
				
				System.out.println(" nListDepth1.getLength() :" +  nListDepth1.getLength()); 

				for (int tempDepth1 = 0; tempDepth1 < nListDepth1.getLength(); tempDepth1++) { 
					Node nNodeDepth2 = nListDepth1.item(tempDepth1); 
				
					if ( nNodeDepth2.getNodeName().equals("#comment") 
							|| nNodeDepth2.getNodeName().equals("#text") )
						continue;
					System.out.println("\n	Depth[2] Current Element :" + nNodeDepth2.getNodeName());
					
					for (int tempDepth2_1 = 0; tempDepth2_1 < nNodeDepth2.getAttributes().getLength(); tempDepth2_1++) {
						NamedNodeMap nnm2_1 = nNodeDepth2.getAttributes();
						System.out.println("\n		Depth[2][" + tempDepth2_1  + "] :" + nnm2_1.item(tempDepth2_1));
						System.out.println("\n		nnm2_1.item(tempDepth2_1).getNodeName() :" + nnm2_1.item(tempDepth2_1).getNodeName());
						System.out.println("\n		nnm2_1.item(tempDepth2_1).getNodeValue() :" + nnm2_1.item(tempDepth2_1).getNodeValue());
						if ( nnm2_1.item(tempDepth2_1).getNodeName().startsWith("total_length"))
							continue;
					}
					
					
					
					NodeList nListDepth2 = nNodeDepth2.getChildNodes();

					for (int tempDepth2 = 0; tempDepth2 < nListDepth2.getLength(); tempDepth2++) { 
						Node nNodeDepth3 = nListDepth2.item(tempDepth2); 
					
						if ( nNodeDepth3.getNodeName().equals("#comment") 
								|| nNodeDepth3.getNodeName().equals("#text") )
							continue;

						System.out.println("\n		Depth[3] Current Element :" + nNodeDepth3.getNodeName());


						NodeList nListDepth3 = nNodeDepth3.getChildNodes();

						for (int tempDepth3 = 0; tempDepth3 < nListDepth3.getLength(); tempDepth3++) { 
							Node nNodeDepth4 = nListDepth3.item(tempDepth3); 
						
							if ( nNodeDepth4.getNodeName().equals("#comment") 
									|| nNodeDepth4.getNodeName().equals("#text") )
								continue;

							System.out.println("\n			Depth[4] Current Element :" + nNodeDepth4.getNodeName());
							
							for (int tempDepth4 = 0; tempDepth4 < nNodeDepth4.getAttributes().getLength(); tempDepth4++) {
								NamedNodeMap nnm = nNodeDepth4.getAttributes();
								
								System.out.println("\n				Depth[4][" + tempDepth4  + "] :" + nnm.item(tempDepth4));
							
							}
						}
					}
				}
			}
			

		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	} 
}
