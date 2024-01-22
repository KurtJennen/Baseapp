package be.luxuryoverdosis.framework.base.tool;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

public class ArrayTool {
	public static int[] sortArray(int[] number) {
		for(int i = 0; i < number.length - 1; i++) {
			for(int j = i + 1; j < number.length; j++) {
				if(number[i] > number[j]) {
					int temp = number[i];
					number[i] = number[j];
					number[j] = temp;
				}
			}
		}
		
		return number;
	}
	
	public static int[] sortArray(int[] number, int length) {
		for(int i = 0; i < length - 1; i++) {
			for(int j = i + 1; j < length; j++) {
				if(number[i] > number[j]) {
					int temp = number[i];
					number[i] = number[j];
					number[j] = temp;
				}
			}
		}
		
		return number;
	}
	
	public static int[] addToArray(int[] number, int value) {
		int length = 0;
		
		if(number != null) {
			length = number.length;
		}
		
		int[] newArray = new int[length + 1];
		
		if(number != null) {
			for(int i = 0; i < length; i++) {
				newArray[i] = number[i];
			}
		}
		newArray[newArray.length - 1] = value;
		
		return newArray;
	}
	
	public static boolean isValueInArray(int[] number, int value) {
		for(int i = 0; i < number.length; i++) {
			if(number[i] == value) {
				return true;
			}
		}
		return false;
	}
	
	public static int positionValueInArray(int[] number, int value) {
		if(number == null) {
			return -1;
		}
		
		for(int i = 0; i < number.length; i++) {
			if(number[i] == value) {
				return i;
			}
		}
		//return 0;
		return -1;
	}
	
	public static void makeZero(int[] number) {
		for(int i = 0; i < number.length; i++) {
			number[i] = 0;
		}
	}
	
	public static boolean isValueTimesInArray(int[] number, int value, int times) {
		int count = 0;
		for(int i = 0; i < number.length; i++) {
			if(number[i] == value) {
				count++;
			}
		}
		if(count == times) {
			return true;
		}
		
		return false;
	}
	
	public static void isJobLength(int[] ids) {
		isLengthOk(ids, Constants.DUIZEND);
	}
	
	public static void isLengthOk(int[] ids, int length) {
		if(ids.length > length) {
			throw new ServiceException("errors.arraylength", new String[] {String.valueOf(length)});
		}
	}
}
