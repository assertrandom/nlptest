package com.mlk007.nlp.nlptest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCosineIndex {

	
	private static final Logger logger = LoggerFactory.getLogger(CheckCosineIndex.class);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.debug("String");
		
		CheckCosineIndex checkIndex = new CheckCosineIndex();
		
		List<String> alignFrom = checkIndex.readFile("/Users/manalesl/Downloads/doc-india.txt");
		List<String> alignTo = checkIndex.readFile("/Users/manalesl/Downloads/doc-canada.txt");
		
		String s1 = "The process of evolution is explained by various theories (Lamarckism, Darwinism and Neo- Darwinism). Different types of evidences support the theories.";
		String s2 = "Evolution: Origin of life; biological evolution and evidences for biological evolution (paleontology, comparative anatomy, embryology and molecular evidence); Darwin's contribution, modern synthetic theory of evolution; mechanism of evolution - variation (mutation and recombination) and natural selection with examples, types of natural selection; Gene flow and genetic drift; Hardy - Weinberg's principle; adaptive radiation; human evolutio";
		try {
			System.out.println(CosineDocumentSimilarity.getCosineSimilarity(s1, s2));
			for (int i = 0; i < alignFrom.size(); i++) {
				
				double d1 = 0;
				
				String str = "";
				
				for (int j = 0; j < alignTo.size(); j++) {
					double d2 = CosineDocumentSimilarity.getCosineSimilarity(alignFrom.get(i), alignTo.get(j));
					
					if (d2 > d1) {
						str = alignTo.get(j);
						d1 = d2;
					}
				}
				
				logger.debug("Finished {} ", i ); 
				logger.debug("Best match for ");
				logger.debug(alignFrom.get(i));
				logger.debug(" with score " + d1);
				logger.debug(str);
				logger.debug("");
				logger.debug("");
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("Finished ");

	}

	
	private List<String> readFile(String fileName) {
		
		List<String> stringList = new ArrayList<String>();
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(fileName));
 
			while ((sCurrentLine = br.readLine()) != null) {
				stringList.add(sCurrentLine);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return stringList;
	}
}
