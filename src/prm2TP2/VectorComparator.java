package prm2TP2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class VectorComparator {
	Map<String, String> pdf;
	final String VECTORS_PATH="C:/Users/pbril/Documents/M2 MIAGE/PRM/PRM2/TP2/vectors.csv";
	
	public VectorComparator() {
		initMap();
	}
	
	public boolean isEqual() {
		return true;
	}
	
	public void initMap() {
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
			br = new BufferedReader(new FileReader(VECTORS_PATH));
			while ((line = br.readLine()) != null) {
				String path = line.split(",")[0];
				String vec = line.replace(path+",", "");
				pdf.put(path,vec);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
