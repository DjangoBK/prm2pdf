package prm2TP2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
public class DictionnaireGenerator {
	private Map<String, Integer> dicOccurence;
	private List<String> files;
	
	public DictionnaireGenerator(List<String> files) {
		this.dicOccurence = new HashMap<String, Integer>();
		this.files = files;
	}
	
	public void extractFromFile(String f){
		PdfReader reader;
		try {
			reader = new PdfReader(f);
			System.err.println("reader OK");
			
			for(int i= 1; i<=reader.getNumberOfPages() ; i++) {			
				String stringPage = PdfTextExtractor.getTextFromPage(reader, i);
				System.err.println("Extractor OK");
				stringPage = stringPage.replaceAll("\\p{Punct}","");
				stringPage = stringPage.replaceAll("\\d", "");
				stringPage = stringPage.toLowerCase();
				String[] lineWord = stringPage.split(" ");
				
				for(String word : lineWord) {
					if(this.dicOccurence.containsKey(word)) {
						this.dicOccurence.put(word, this.dicOccurence.get(word) + 1);
					}
					else {
						this.dicOccurence.put(word, 1);
					}
				}
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void buildDico(){
		for(String f : files) {
			System.out.println("------------------------ Extract from : "+f);
			extractFromFile(f);
		}
	}

	public Map<String, Integer> getDicOccurence() {
		return dicOccurence;
	}

	public void setDicOccurence(Map<String, Integer> dicOccurence) {
		this.dicOccurence = dicOccurence;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}
	
	
}
