import java.io.IOException;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class b {
    static public void main(String...argv) {
        String queryStr = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
        		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
        		"PREFIX dc: <http://purl.org/dc/elements/1.1/>\r\n" + 
        		"PREFIX : <http://dbpedia.org/resource/>\r\n" + 
        		"PREFIX dbpedia2: <http://dbpedia.org/property/>\r\n" + 
        		"PREFIX dbpedia: <http://dbpedia.org/>\r\n" + 
        		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
        		
        		+ "SELECT ?property ?hasValue ?isValueOf\r\n" + 
        		"WHERE {\r\n" + 
        		"  { <http://dbpedia.org/resource/Bohemian_Rhapsody> ?property ?hasValue }\r\n" + 
        		"  UNION\r\n" + 
        		"  { ?isValueOf ?property <http://dbpedia.org/resource/Bohemian_Rhapsody> }\r\n" + 
        		"} LIMIT 100";
        Query query = QueryFactory.create(queryStr);
        JsonObject abc;

        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            // Set the DBpedia specific timeout.
            //((QueryEngineHTTP)qexec).addParam("timeout", "30000") ;

            // Execute.
            ResultSet rs = qexec.execSelect();
           // ResultSetFormatter.out(System.out, rs, query);
            ResultSetFormatter.outputAsJSON(System.out, rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
