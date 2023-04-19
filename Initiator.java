import java.util.*;
import edu.duke.*;

public class Initiator {
    public HashMap<Integer, String> everyBox;
    public HashMap<Integer, String> everyRow;
    public HashMap<Integer, String> everyColumn;
    public String[] solved;
    public ArrayList<Integer> allEmptyIndex;
    private String input;
    
    public Initiator(String sudoku) {
        solved = new String[81];
        allEmptyIndex = new ArrayList<Integer>();
        everyRow = new HashMap<Integer,String>();
        everyColumn = new HashMap<Integer,String>();
        everyBox = new HashMap<Integer,String>();
        input = sudoku.replace("\n", "");
    }
    
    public static boolean isNumeric(String string) {
        int intValue;
    		
        if(string == null || string.equals("")) {
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void solvedBuilder() {
        for(int i=0; i<81; i++) {
            if (isNumeric(input.substring(i,i+1))){
                solved[i] = input.substring(i,i+1);
            }
            else {
                allEmptyIndex.add(i);
            }
        }
    }
    
    public void rowBuilder() {
        for (int i = 0; i < 9; i++) {
            String eachRow = input.substring(9*i,9*i+9);
            everyRow.put(i, eachRow);
        }
    }
    
    public void columnBuilder() {
        for (int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<9; j++) {
                sb.append(input.substring(9*j+i,9*j+1+i));
            }
            String eachColumn = sb.toString();
            everyColumn.put(i,eachColumn);
        }
    }
    
    public void boxBuilder() {
        for (int i = 0; i<9; i++) {
            StringBuilder sb = new StringBuilder();
            if (i == 0) {
                sb.append(input.substring(0,3));
                sb.append(input.substring(9,12));
                sb.append(input.substring(18,21));
            }
            
            if (i == 1) {
                sb.append(input.substring(3,6));
                sb.append(input.substring(12,15));
                sb.append(input.substring(21,24));
            }
            
            if (i == 2) {
                sb.append(input.substring(6,9));
                sb.append(input.substring(15,18));
                sb.append(input.substring(24,27));
            }
            
            if (i == 3) {
                sb.append(input.substring(27,30));
                sb.append(input.substring(36,39));
                sb.append(input.substring(45,48));
            }
            
            if (i == 4) {
                sb.append(input.substring(30,33));
                sb.append(input.substring(39,42));
                sb.append(input.substring(48,51));
            }
            
            if (i == 5) {
                sb.append(input.substring(33,36));
                sb.append(input.substring(42,45));
                sb.append(input.substring(51,54));
            }
            
            if (i == 6) {
                sb.append(input.substring(54,57));
                sb.append(input.substring(63,66));
                sb.append(input.substring(72,75));
            }
            
            if (i == 7) {
                sb.append(input.substring(57,60));
                sb.append(input.substring(66,69));
                sb.append(input.substring(75,78));
            }
            
            if (i == 8) {
                sb.append(input.substring(60,63));
                sb.append(input.substring(69,72));
                sb.append(input.substring(78,81));
            }            
            everyBox.put(i,sb.toString());
        }
    }
}
