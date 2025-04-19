package core;

import java.io.*;             
import java.net.URL;
import java.util.HashMap;

import eda.IndexParametricService;
import eda.IndexWithDuplicates;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class DataAnalysis {
    public static void main(String[] args) throws IOException {
    	
	    // leemos el archivo
    	

       	// opcion 1:  probar con  / o sin barra
	    URL resource = DataAnalysis.class.getClassLoader().getResource("co_1980_alabama.csv");
	    Reader in = new FileReader(resource.getFile());

    	
    	
    	/*
    	// opcion 2:  probar con  / o sin barra
        URL resource= DataAnalysis.class.getResource("/co_1980_alabama.csv");
   	    Reader in = new FileReader(resource.getFile());
    	*/
    	
    	/*
    	// opcion 3: probar con / o sin barra
    	String fileName= "/co_1980_alabama.csv";
    	InputStream is = DataAnalysis.class.getClass().getResourceAsStream(fileName);
    	Reader in = new InputStreamReader(is);
    	*/
    	
    	/*
  		// opcion 4 
 		String fileName= "/co_1980_alabama.csv"; 
 		InputStream is = DataAnalysis.class.getResourceAsStream(fileName );
 		Reader in = new InputStreamReader(is); 	
    	 */
    	
    	
    	// opcion 5 
//   		String fileName= "co_1980_alabama.csv";
//   		InputStream is = DataAnalysis.class.getClassLoader().getResourceAsStream(fileName);
//   		Reader in = new InputStreamReader(is); 			
    	
    	
 		CSVFormat theCSV = CSVFormat.DEFAULT.builder()
   				.setHeader()
   				.setSkipHeaderRecord(true)
   			    .get();

   		Iterable<CSVRecord> records = theCSV.parse(in);
   		
   		// Genero las estructuras
   		
	    // Coleccion de valores
	    HashMap<Long, CSVRecord> datos= new HashMap<>();
	    
	    IndexParametricService<IdxRecord<Double, Long>> indexPolucion ;
	    
	    // Indice sobre polucion sin reflection
	    indexPolucion= new IndexWithDuplicates<>();
	    
	    // Indice sobre polucion con reflection
//	    indexPolucion = new IndexWithDuplicates<>(IdxRecord.class);

	    
	    
	    // Pueblo con los valores ambas estructuras
	    
	    // coleccion de datos
	    for (CSVRecord record : records) {
	    	// insertamos en la coleccionIdxRecord<Double, Long>IdxRecord<Double, Long>
	    	datos.put(record.getRecordNumber(), record);
	    	
	    	// insertamos lo minimo en el indice
	        String value = record.get("daily_max_8_hour_co_concentration");
	        long id = record.getRecordNumber();
	        indexPolucion.insert(new IdxRecord<>( Double.valueOf(value), id ));
	    }
	    in.close();

		// Buscamos si existio una polucion de 2.8
		IdxRecord<Double, Long> search = new IdxRecord<>(2.8);
		System.out.println(indexPolucion.search(search));

		// Buscamos el valor numerico de la minima polucion registrada
		System.out.println(indexPolucion.getMin().getKey());

		// Imprimimos toda la informacion del registro de menor polucion
		IdxRecord<Double, Long> min = new IdxRecord<>(indexPolucion.getMin().getKey());

		IdxRecord<Double, Long>[] range  = indexPolucion.range(min, min, true, true);
		for(IdxRecord<Double, Long> value : range){
			System.out.println(datos.get(value.getRow()));
		}

		// Imprimimos los valores de polución ordenados ascendentemente
		indexPolucion.sortedPrint();

		// Imprimir TODA la info pero ascendentemente ordenada por polución
		IdxRecord<Double, Long> min2 = new IdxRecord<>(indexPolucion.getMin().getKey());
		IdxRecord<Double, Long> max2 = new IdxRecord<>(indexPolucion.getMax().getKey());

		IdxRecord<Double, Long>[] range2  = indexPolucion.range(min2, max2, true, true);
		for(IdxRecord<Double, Long> value : range2){
			System.out.println(datos.get(value.getRow()));
		}

		// Vemos qué valores numéricos de polución se registraron entre [3.65, 3.84]
		IdxRecord<Double, Long> min3 = new IdxRecord<>(3.65);
		IdxRecord<Double, Long> max3 = new IdxRecord<>(3.84);

		IdxRecord<Double, Long>[] range3  = indexPolucion.range(min3, max3, true, true);
		for(IdxRecord<Double, Long> value : range3){
			System.out.println(datos.get(value.getRow()));
		}

		// Vemos qué valores numéricos de polución se registraron entre [3.65, 3.84)

		IdxRecord<Double, Long>[] range4 = indexPolucion.range(min3, max3, true, false);
		for(IdxRecord<Double, Long> value : range4){
			System.out.println(datos.get(value.getRow()));
		}

		// Vemos qué valores numéricos de polución se registraron entre [10.5, 12]
		IdxRecord<Double, Long> min4 = new IdxRecord<>(10.5);
		IdxRecord<Double, Long> max4 = new IdxRecord<>(12.0);

		IdxRecord<Double, Long>[] range5  = indexPolucion.range(min4, max4, true, true);
		for(IdxRecord<Double, Long> value : range5){
			System.out.println(datos.get(value.getRow()));
		}

	}



   
    }
    

