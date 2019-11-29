import java.io.IOException;

import org.apache.jena.query.*;

public class a {
public static void main(String[] args) throws IOException {
		

	String queryString =
			"PREFIX p: <http://dbpedia.org/property/>"+
			"PREFIX dbpedia: <http://dbpedia.org/resource/>"+
			"PREFIX category: <http://dbpedia.org/resource/Category:>"+
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
			"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"+
			"PREFIX geo: <http://www.georss.org/georss/>"+

			"SELECT DISTINCT ?m ?n ?p ?d"+
			"WHERE {"+
			" ?m rdfs:label ?n."+
			" ?m skos:subject ?c."+
			" ?c skos:broader category:Churches_in_Paris."+
			" ?m p:abstract ?d."+
			" ?m geo:point ?p"+
			" FILTER ( lang(?n) = 'fr' )"+
			" FILTER ( lang(?d) = 'fr' )"+
			" }";

			// now creating query object
			Query query = QueryFactory.create(queryString);
			// initializing queryExecution factory with remote service.
			// **this actually was the main problem I couldn't figure out.**
			QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);{

			//after it goes standard query execution and result processing which can
			// be found in almost any Jena/SPARQL tutorial.
			try {
			    ResultSet results = qexec.execSelect();
			    for (; results.hasNext();) {

			    // Result processing is done here.
			    }
			}
			finally {
			   qexec.close();
			}
}
			
}}

	
