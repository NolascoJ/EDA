package core;

import org.apache.lucene.search.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;


public class TheSearcher {
	
	private static IndexReader getIndexReader() throws IOException {

			// Print the path for debugging
			String indexPath = Utils.getPrefixDir() + "/index/";
			System.out.println("Trying to open index at: " + indexPath);

			// Use absolute path to avoid duplication
			Directory indexDir = FSDirectory.open(Paths.get(indexPath));

			return DirectoryReader.open(indexDir);
	}


    public static void main( String[] args ) {
    	
        try {
           	IndexReader index = getIndexReader();
        	IndexSearcher searcher= new IndexSearcher(index);
        	searcher.setSimilarity(new ClassicSimilarity());


        	
        	// field of interest
			String fieldName = "content"; // Define the field to search within
			String queryStr = "fly"; // The term to search for

			Term myTerm = new Term(fieldName, queryStr); // Create a Term object for the search
// 			Query query = new PrefixQuery(myTerm); // Uncomment to use a PrefixQuery (finds terms starting with "fly")
// 			Query query = TermRangeQuery.newStringRange(fieldName, "g", "me", false, false); // Uncomment to use a TermRangeQuery (finds terms between "g" and "me")
// 			Query query = new PhraseQuery(fieldName,"game" , "video" , "review"); // Uncomment to use a PhraseQuery (finds the exact phrase "game video review")
			Query query = new WildcardQuery(myTerm); // Use a WildcardQuery (allows wildcard characters like * and ?)
// 			Query query = new FuzzyQuery(myTerm); // Uncomment to use a FuzzyQuery (finds terms similar to "fly")
        	// run the query
        	long startTime = System.currentTimeMillis();       	
        	TopDocs topDocs = searcher.search(query, 20);
        	long endTime = System.currentTimeMillis();
        	
			// show the resultset
			System.out.println(String.format("Query=> %s\n", query));
			System.out.println(String.format("%d topDocs documents found in %d ms.\n", topDocs.totalHits,
					(endTime - startTime) ) );
        	
			ScoreDoc[] orderedDocs = topDocs.scoreDocs;

			int position= 1;
			System.out.println("Resultset=> \n");
			
			for (ScoreDoc aD : orderedDocs) {
				
				// print info about finding
				int docID= aD.doc;
				double score = aD.score;
				System.out.println(String.format("position=%-10d  score= %10.7f", position, score ));
				
				// print docID, score
				System.out.println(aD);
				
				// obtain ALL the stored fields
				Document aDoc = searcher.doc(docID);
				System.out.println("Stored fields: " + aDoc);
				System.out.println(aDoc.get("path"));
				//System.out.println(aDoc.get("content"));
				 /*
				Explanation rta = searcher.explain(query, docID);
	            System.out.println(rta);*/
	         
	            position++;
	            System.out.println();
			}

			index.close();
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    

}