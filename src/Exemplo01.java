import java.io.IOException;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class Exemplo01 {

	public static void main(String[] args) throws IOException {
		
		// Create the model
		Model model = ModelFactory.createDefaultModel();

		// Definitions
		String URI = "http://somewhere/JoaoSilva"; // Base URI
		String info = "Joao Silva"; // Resource to be created/added in model
		
		// Create Resource
		Resource res = model.createResource(URI);
		// Add property
		res.addProperty(VCARD.FN, info);
		info = "Jo";
		res.addProperty(VCARD.NICKNAME, info);

		//Create Resource
		URI = "http://somewhere/MarioSilva";
		info = "Mario Silva";
		res = model.createResource(URI);
		// Add Property
		res.addProperty(VCARD.FN, info);
		
		// Write the model in Turtle form
		model.write(System.out, "Turtle");
	}

}
