import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import org.json.*;

public class Dbpedia_querier {
	static public void main(String...argv) {
		String queryQueenSubject = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
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
        		
        		+ "SELECT DISTINCT ?subject\r\n" + 
        		"WHERE {{\r\n" + 
        		"?album a dbo:Album; "
        		+ "a dbo:MusicalWork;"
        		+ "dbp:thisAlbum ?albumName;"
        		+ "dbo:artist ?band."
        		+ "?band a dbo:Band;"
        		+ "rdfs:label 'Queen (band)'@en;"

        		+ "}"
        		+ "UNION{?band a dbo:Band;"
        		+ "rdfs:label 'Queen (band)' @en;"
        		+ "dct:subject ?subject.}"
        		+ "MINUS{?subject rdfs:label 'Queen (band)' @en}}"
        		+ " LIMIT 100";
		
		String queryQueenSubject_Specific = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
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
        		
        		+ "SELECT DISTINCT ?album ?recommended\r\n" + 
        		"WHERE {{\r\n" + 
        		"?album a dbo:Album; "
        		+ "a dbo:MusicalWork;"
        		+ "dbp:thisAlbum ?albumName;"
        		+ "dbo:artist ?band."
        		+ "?band a dbo:Band;"
        		+ "rdfs:label 'Queen (band)'@en;"
        		+ "}"
        		+ "UNION{"
        		+ "?band a dbo:Band;"
        		+ "rdfs:label 'Queen (band)'@en;" 
        		+ "dct:subject ?subject." 
        		+ "?recommended dct:subject ?subject;" 
        		+ "a dbo:Band;}"
        		+ "}"
        		+ " LIMIT 100";
		
        Query query = QueryFactory.create(queryQueenSubject_Specific);

        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            // Set the DBpedia specific timeout.
            //((QueryEngineHTTP)qexec).addParam("timeout", "30000") ;

            // Execute.
            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs);
            //ResultSetFormatter.out(System.out, rs, query);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 

//            ResultSetFormatter.outputAsJSON(outputStream, rs);
//
//            // and turn that into a String
//            String srtJson = new String(outputStream.toByteArray());
//            
//            //System.out.println(srtJson);
//            JSONObject objJson = new JSONObject(srtJson);
//            System.out.println(srtJson);
            //ResultSetFormatter.outputAsJSON(System.out, rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
