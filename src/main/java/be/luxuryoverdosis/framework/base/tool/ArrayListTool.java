package be.luxuryoverdosis.framework.base.tool;

import java.util.ArrayList;

public class ArrayListTool {
	public static ArrayList<Integer> toArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int id :array) {
        	arrayList.add(id);
        }
        
        return arrayList;
	}
	
    public static ArrayList<String> toArrayList(String[] array) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String id :array) {
            arrayList.add(id);
        }
        
        return arrayList;
    }
	
}
