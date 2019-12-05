import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.jena.atlas.json.JSON;
import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;



public class Dbpedia_querier {
	static public void main(String...argv) {
		String queryLinkinPark_Recommend_Album = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
        		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
        		"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n"+
        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
        		"PREFIX dc: <http://purl.org/dc/elements/1.1/>\r\n" + 
        		"PREFIX : <http://dbpedia.org/resource/>\r\n" + 
        		"PREFIX dbp: <http://dbpedia.org/property/>\r\n" + 
        		"PREFIX dbpedia: <http://dbpedia.org/>\r\n" + 
        		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
        		"PREFIX dct: <http://purl.org/dc/terms/>"
        		
        		+ "SELECT DISTINCT ?song ?recommended\r\n" + 
        		"WHERE {{\r\n" + 
        		"?song a dbo:Single; "
        		+ "rdfs:label 'Papercut (Linkin Park song)' @en;"
        		+ "dbo:musicalArtist ?band."
        		+ "?band a dbo:Band;"
        		+ "rdfs:label 'Linkin Park'@en;"
        		+ "}"
        		+ "UNION{"
        		+ "?song a dbo:Single;"
        		+ "rdfs:label 'Papercut (Linkin Park song)' @en;"
        		+ "dbo:musicalArtist ?band."
        		+ "?band a dbo:Band;"
        		+ "rdfs:label 'Linkin Park'@en."
        		+ "?song dct:subject ?subject." 
        		+ "?recommended dct:subject ?subject;" 
        		+ "a dbo:Album;}"
        		
        		+ "}"
        		+ " LIMIT 200";
		
        Query query = QueryFactory.create(queryLinkinPark_Recommend_Album);

        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {

            // Execute.
            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
