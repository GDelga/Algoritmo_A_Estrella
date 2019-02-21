/**
 * 
 */
package command;

import java.lang.reflect.Constructor;

import org.w3c.dom.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class FactoriaCommandImp extends FactoriaCommand {

	@Override
	public Command generarComando(int evento) {
		Command command = null;
		try {
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
            InputSource inputSource = new InputSource("Commands.xml");
            String regularExpression = "//*[@id='"+ evento +"'][1]";
            Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			if(element != null) {
				String className = element.getTextContent().trim();				
				Class<?> commandClass = Class.forName(className);
				Constructor<?> constructor = commandClass.getConstructor();	
				command = (Command) constructor.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return command;
	
	}
}