package core;


import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.search.IndexSearcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.IOException;
import java.nio.file.Paths;


public class TheSearcherQueryParser {
	
	private static IndexReader getIndexReader() throws IOException {
		
		// target index directory
		Directory indexDir = FSDirectory.open( Paths.get(Utils.getPrefixDir() + "/index/"));
		
		return DirectoryReader.open( indexDir );
		
	}


    public static void main( String[] args ) {
    	
        try {
           	IndexReader index = getIndexReader();
        	IndexSearcher searcher= new IndexSearcher(index);
        	searcher.setSimilarity(new ClassicSimilarity());




			String queryStr ="content:Fly OR content:Moon";

        	
        	QueryParser queryparser = new QueryParser("content", new SimpleAnalyzer() );
         	Query query= queryparser.parse(queryStr);
        	
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
				
				// obtain the stored fields
				Document aDoc = searcher.doc(docID);
				System.out.println("stored fields: " + aDoc);
//				Explanation rta = searcher.explain(query, docID);
//	            System.out.println(rta);
	         
	            position++;
	            System.out.println();
			}

			index.close();
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }


	/*
	TERM FRECUENCY: CALCULA LA CANTIDAD DE VECES QUE APARECE LA PALABRA EN CADA DOCUEMNTO

	INVERSE DOCUMENT FRECUENCY: 1 + ln(N+1/ (df + 1))
	N= ctad de docuementos
	df = ctdad de documentos que tienen el termino

	FORMULA LOCAL = sqrt( TERM FRECUENCY en doc x / ctad TERMS EN DOC x )

	SCORE (DOC , query) = FORMULA LOCAL * IDF. Si el query tiene muchos terms es la suma


	IMPORTANTISIMO SI TENGO UN EJ Y CAMBIO LOS TXTS RE INDEXAR. Y SI NO USO LAS COSAS

	GRAN DIFERENCIA ENTRE ESTO Y EL SEARCHER. EN EL SEARCHER TENGO QUE CREAR UN TERMINO (FIELD,QUERYSTR) Y AHI TENGO
	QUE INVOCAR A LA QUERY CON ESE TERM.
	ACA PUEDO USAR OTRA NOTACION Y UN STRING LO PARSEA A QUERY CON ALGUN ANALIYZER



	// TEOREMA MAESTRO
		T(N) = a * T(N/b) + C* N^d.

		SI a < b^d -> O(N^d)
		SI a = b^d -> O(N^d Log(N)
		SI a > b^d -> O( N ^ (log en base b de a) )            ->  logb(a)

	//USEFUL
		Arrays.copyOf(arr , start,end)  nlogn
		Arrays.copyOf(arr) nlogn

	//  String[] words1 = str1.split("\\s+");
	str.replaceFirst(s2, "") ; cambio la primera aparicion del str2 en str por "vacios"

		String(char [] arr) constructor valido con una rray de chars.
		puedo usar .concat , .substring...
	*\
	 */
    

}