import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


@SuppressWarnings("unused")
public class Exemplo03 {

	public static void main(String[] args) {
		//Create RDF Model 
		Model model = ModelFactory.createDefaultModel();
		//Namespace
		String NS = "http://ic.unicamp.br/schema#";
		String NS1 = "http://ic.unicamp.br/prof#";
		//Define Prefixes
		model.setNsPrefix("Unicamp", NS);
		model.setNsPrefix("Unicamp-Profs", NS1);
		
		//Create Resources and add properties
		Resource Ac = model.createResource(NS+ "Academico");
		Ac.addProperty(RDF.type, RDFS.Class);
		Resource Ad = model.createResource(NS + "Administrativo");
		Ad.addProperty(RDF.type, RDFS.Class);
		
		Resource Pd = model.createResource(NS + "Professor_Doutor");
		Pd.addProperty(RDF.type, RDFS.Class);
		Pd.addProperty(RDFS.subClassOf, Ac);
		Resource Pa = model.createResource(NS + "Professor_Associado");
		Pa.addProperty(RDF.type, RDFS.Class);
		Pa.addProperty(RDFS.subClassOf, Ac);
		Resource Pt = model.createResource(NS + "Professor_Titular");
		Pt.addProperty(RDF.type, RDFS.Class);
		Pt.addProperty(RDFS.subClassOf, Ac);
		
		Resource prof = model.createResource(NS1 + "prof_1");
		prof.addProperty(RDF.type, Pd);
		prof.addProperty(VCARD.FN, "Pedro da Silva");
		prof.addProperty(VCARD.ADR,
				model.createResource()
					 .addProperty(VCARD.Pcode, "13084000")
					 .addProperty(VCARD.Street, "Rua Zeferino Vaz"));
		
		//Print the RDF triples in Turtle format
		model.write(System.out, "Turtle");
				

	}

}
