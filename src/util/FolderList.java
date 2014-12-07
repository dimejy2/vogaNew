package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderList {
	
	public List<String> getContents(String path){
		List<String> temp = new ArrayList<>(); 
		File dir =  new File(path); 
		File [] dirList  = dir.listFiles(); 
		
		for( File f : dirList ){
			if (f.isFile()) {
				temp.add(f.getName()); 
			}
		}
		return temp; 
	}
	
	
	
//	public static void main(  String[] args){
//		
//		FolderList test = new FolderList(); 
//		
//		List<String> temp =  test.getContents("src//images"); 
//		
//		for( String s : temp ) System.out.println(s); 
//		
//		
//	}
}
