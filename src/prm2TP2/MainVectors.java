package prm2TP2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.opencsv.CSVWriter;

public class MainVectors {
	
	static String pdfTest="C:/Users/pbril/Documents/M2 MIAGE/PRM/PRM2/TP2/pdf/00001-Stage - Développement d'une solution HomeNetWorking.pdf";
	final static String PDF_FOLDER = "C:/Users/pbril/Documents/M2 MIAGE/PRM/PRM2/TP2/pdf/";
	
	public static void main(String[] args) {
		
		//On recupere tous les noms de fichiers 
		List<String> results = new ArrayList<String>();
		File[] filesInFolder = new File(PDF_FOLDER).listFiles();
		for (File fileInFodler : filesInFolder) {
		    if (fileInFodler.isFile()) {
		        results.add(fileInFodler.getName());
		    }
		}
		//On remplit la liste de fichiers pour declarer notre DictionnaireGenerator
		ArrayList<String> files = new ArrayList<String>();
		for(String f : results) {
			if(f.contains(".pdf") && !f.contains("catalogue")) {
				files.add(PDF_FOLDER+f);
			}
		}
		
		VectorGenerator comp = new VectorGenerator();
		
		Map<String, List<Integer>> vectors = new HashedMap();
		
		File file = new File("vectors.csv");
		FileWriter outputfile;
		try {
			outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			
			for(String f : files) {
				System.out.println(f + " traitement ");
				comp.generateVector(f);
				vectors.put(f, comp.getPresent());
				String line = f;
				for(Integer i : comp.getPresent()) {
					line+=","+i;
				}
				String[] res = {line};
				writer.writeNext(res);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for(Map.Entry<String, List<Integer>> entry : vectors.entrySet()) {
			for(Map.Entry<String, List<Integer>> entry2 : vectors.entrySet()) {			
				if(!entry.getKey().equals(entry2.getKey()) && entry.getValue().equals(entry2.getValue())) {
					System.err.println(entry.getKey() + " = " + entry2.getKey());
				}
			}
		}*/
			
		
	}
}
