package prm2TP2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class VectorGenerator {
	final String DICTIONNAIRE_PATH="C:/Users/pbril/Documents/M2 MIAGE/PRM/PRM2/TP2/mots.csv";
	List<String> dico;
	List<Integer> present;
	
	public VectorGenerator() {
		dico = new ArrayList<String>();
		present = new ArrayList<Integer>();
		initMots();
	}
	
	public void generateVector(String pdf) {
		PdfReader reader;
		try {
			reader = new PdfReader(pdf);			
			for(int i= 1; i<=reader.getNumberOfPages() ; i++) {			
				String stringPage = PdfTextExtractor.getTextFromPage(reader, i);
				for(int j = 0 ; j < dico.size() ; j++) {
					if(stringPage.contains(dico.get(j))) {
						present.set(j, 1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initMots() {
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
			br = new BufferedReader(new FileReader(DICTIONNAIRE_PATH));
			while ((line = br.readLine()) != null) {
				dico.add(line);
				present.add(0);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getDico() {
		return dico;
	}

	public void setDico(List<String> dico) {
		this.dico = dico;
	}

	public List<Integer> getPresent() {
		return present;
	}

	public void setPresent(List<Integer> present) {
		this.present = present;
	}

	public String getDICTIONNAIRE_PATH() {
		return DICTIONNAIRE_PATH;
	}

	
	
}
