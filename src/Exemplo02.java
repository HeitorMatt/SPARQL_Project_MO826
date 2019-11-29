import java.io.IOException;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class Exemplo02 {

	public static void main(String[] args) {
		
		//Definitions
		Model model = ModelFactory.createDefaultModel();
		String NS1 = "http://somewhere/else#";
		String NS2 = "http://somespace/else#";
		//Set namespace prefixes
		model.setNsPrefix("NamesSp", NS1);
		model.setNsPrefix("NameProp", NS2);
		
		// Create Resource
		Resource root = model.createResource(NS1 + "root");
		Property isDad = model.createProperty(NS2 + "isDad");
		Property isBrother = model.createProperty(NS2 + "isBrother");
		Resource a = model.createResource(NS1 + "A");
		Resource b = model.createResource(NS1 + "B");
		Resource c = model.createResource(NS1 + "C");
		model.add(root, isDad, a).add(root, isDad, b);
		model.add(a, isBrother, b).add(b, isBrother, a).add(a, isDad, c);
		
		
		
		model.write(System.out, "Turtle");
		
		
	}

}
