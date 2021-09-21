package com.example.firstProj.donnees;
import lombok.Data;

@Data
public class OcrResult {
   private String result;

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}

@Override
public String toString() {
	return result;
}

   
   
   
}
