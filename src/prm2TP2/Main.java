package prm2TP2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class Main {
	final static String PDF_FOLDER = "C:/Users/pbril/Documents/M2 MIAGE/PRM/PRM2/TP2/pdf/";
	public static void main(String[] args) throws IOException {
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
		DictionnaireGenerator dicGen = new DictionnaireGenerator(files);
		//On remplit la Map
		dicGen.buildDico();
		
		//CSV pour stocker le contenu du dictionnaire
		File file = new File("dictionnaire.csv");
		FileWriter outputfile = new FileWriter(file);
		CSVWriter writer = new CSVWriter(outputfile);
		String[] header = { "mot", "occurence" }; 
        writer.writeNext(header);
		
		
		
		
		for(Map.Entry<String, Integer> entry : dicGen.getDicOccurence().entrySet()) {
			if(entry.getValue() >= 500 && entry.getKey().length() > 3) {
				System.out.println(entry.getKey() + " => " + entry.getValue());
				String[] line = {entry.getKey(), ""+entry.getValue()};
				writer.writeNext(line);
			}
			
		}
		writer.close();
	}
}
