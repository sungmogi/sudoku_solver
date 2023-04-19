import java.util.*;
import edu.duke.*;
import java.io.*;

public class Solver {
    public HashMap<Integer, ArrayList<String>> memo;
    private Initiator test;
    private IndexP ip;
    private StringBuilder sb;
    
    public Solver(String input) {
        memo = new HashMap<Integer, ArrayList<String>>();        
        test = new Initiator(input);
        ip = new IndexP();
        sb = new StringBuilder();

    }
    
    public ArrayList<String> findNoLeft(String nine) {
        ArrayList<String> noLeft = new ArrayList<String>();
        for (int i = 1; i < 10; i++) {
            Integer x = new Integer(i);
            noLeft.add(x.toString());
        }
        
        for (int i = 0; i<9; i++) {
            if (Initiator.isNumeric(nine.substring(i,i+1))) {
                noLeft.remove(nine.substring(i,i+1));
            }
        }
        return noLeft;
    }   
    
    public void memoWriter(int index) {
        ArrayList<String> memoCand = findNoLeft(test.everyRow.get((ip.whichRow(index))));
        String column = test.everyColumn.get((ip.whichColumn(index)));
        for (int i=0; i<9; i++) {
            
            if(memoCand.contains(column.substring(i,i+1))) {
                memoCand.remove(column.substring(i,i+1));
            }
        }
        String box = test.everyBox.get((ip.whichBox(index)));
        for (int i=0; i<9; i++) {
            if(memoCand.contains(box.substring(i,i+1))) {
                memoCand.remove(box.substring(i,i+1));
            }
        }
        memo.put(index,memoCand);
    }
    
    public int rcToIndex(int row, int column) {
        return row*9+column;
    }
    
    public int boxToIndex(int box, int indexInBox) {
        int x = 0;

        if (box == 1) {
            x = 3;
        }
        if (box == 2) {
            x = 6;
        }
        if (box == 3) {
            x = 27;
        }
        if (box == 4) {
            x = 30;
        }
        if (box == 5) {
            x = 33;
        }
        if (box == 6) {
            x = 54;
        }
        if (box == 7) {
            x = 57;
        }
        if (box == 8) {
            x = 60;
        }
        
        if (indexInBox == 0) {
            return x;
        }
        if (indexInBox == 1) {
            return x+1;
        }
        if (indexInBox == 2) {
            return x+2;
        }
        if (indexInBox == 3) {
            return x+9;
        }
        if (indexInBox == 4) {
            return x+10;
        }
        if (indexInBox == 5) {
            return x+11;
        }
        if (indexInBox == 6) {
            return x+18;
        }
        if (indexInBox == 7) {
            return x+19;
        }
        if (indexInBox == 8) {
            return x+20;
        }
        
        return 0;
    }
    
    public void memoEditor() {
        HashMap<String,Integer> uniqueHash = new HashMap<String,Integer>();
        for (int i = 0; i < 9; i++) {
            uniqueHash = uniqueInRow(i);
            for (String unique : uniqueHash.keySet()) {
                ArrayList<String> only = new ArrayList<String>();
                only.add(unique);
                int location = uniqueHash.get(unique);
                memo.put(location,only);
            }
        }
        filler();        
        for (int i = 0; i < 9; i++) {
            uniqueHash = uniqueInColumn(i);
            for (String unique : uniqueHash.keySet()) {
                ArrayList<String> only = new ArrayList<String>();
                only.add(unique);
                int location = uniqueHash.get(unique);
                memo.put(location,only);
            }
        }
        filler();
        for (int i = 0; i < 9; i++) {
            uniqueHash = uniqueInBox(i);
            for (String unique : uniqueHash.keySet()) {
                ArrayList<String> only = new ArrayList<String>();
                only.add(unique);
                int location = uniqueHash.get(unique);
                memo.put(location,only);
            }
        }
        filler();
    }
    
    public void memoEditorAdv() {
        for (int i = 0; i < 9; i++) {
            HashMap<String,ArrayList<Integer>> boxAndRow = new HashMap<String,ArrayList<Integer>>();
            HashMap<String,ArrayList<Integer>> boxAndCol = new HashMap<String,ArrayList<Integer>>();  
            boxAndRow = examineBoxAndRowCol(i,"row");
            boxAndCol = examineBoxAndRowCol(i,"col");
            for (String eachMemo : boxAndRow.keySet()) {
                if (boxAndRow.get(eachMemo).size() == 1) {
                    int onlyRow = boxAndRow.get(eachMemo).get(0);
                    String row = test.everyRow.get(onlyRow);
                    for (int j = 0; j<9; j++) {
                        if (!Initiator.isNumeric(row.substring(j,j+1))) {
                            ArrayList<String> eachMemoInIndex = new ArrayList<String>();
                            eachMemoInIndex = memo.get(rcToIndex(onlyRow,j));
                            if (ip.whichBox(rcToIndex(onlyRow,j)) != i) {
                                eachMemoInIndex.remove(eachMemo);
                            }
                            memo.put(rcToIndex(onlyRow,j),eachMemoInIndex);
                        }
                    }
                }
            }
            for (String eachMemo : boxAndCol.keySet()) {
                if (boxAndCol.get(eachMemo).size() == 1) {
                    int onlyCol = boxAndCol.get(eachMemo).get(0);
                    String col = test.everyColumn.get(onlyCol);
                    for (int j = 0; j<9; j++) {
                        if (!Initiator.isNumeric(col.substring(j,j+1))) {
                            ArrayList<String> eachMemoInIndex = new ArrayList<String>();
                            eachMemoInIndex = memo.get(rcToIndex(j,onlyCol));
                            if (ip.whichBox(rcToIndex(j,onlyCol)) != i) {
                                eachMemoInIndex.remove(eachMemo);
                            }
                            memo.put(rcToIndex(j,onlyCol),eachMemoInIndex);
                        }
                    }
                }
            }
        }
        filler();
    }
    
    public HashMap<String,Integer> uniqueInRow(int rowNo) {
        HashMap<String,Integer> uniqueIntegers = new HashMap<String,Integer>();
        HashMap<String,Integer> numberCount = new HashMap<String,Integer>();
        HashMap<String,Integer> location = new HashMap<String,Integer>();
        String row = test.everyRow.get(rowNo);
        ArrayList<Integer> emptyInRow = new ArrayList<Integer>();
        ArrayList<String> numbers = new ArrayList<String>();
        for (int i=1; i<10; i++) {
            numbers.add(Integer.toString(i));
            
        }       
        
        for (int i=0; i<9; i++) {
            if(!Initiator.isNumeric(row.substring(i,i+1))) {
                emptyInRow.add(rcToIndex(rowNo,i));
            }
            else {
                numbers.remove(row.substring(i,i+1));
            }
        }
        
        for (String each : numbers) {
            numberCount.put(each,0);
        }
        
        for (Integer empties : emptyInRow) {
            for (String each : memo.get(empties)) {
                numberCount.put(each, numberCount.get(each)+1);
                location.put(each,empties);
            }
        }
        
        for (String string : numberCount.keySet()) {
            if (numberCount.get(string) == 1) {
                uniqueIntegers.put(string,location.get(string));
            }
        }
        
        return uniqueIntegers;
    }
    
    public HashMap<String,Integer> uniqueInColumn(int columnNo) {
        HashMap<String,Integer> uniqueIntegers = new HashMap<String,Integer>();
        HashMap<String,Integer> numberCount = new HashMap<String,Integer>();
        HashMap<String,Integer> location = new HashMap<String,Integer>();
        String column = test.everyColumn.get(columnNo);
        ArrayList<Integer> emptyInColumn = new ArrayList<Integer>();
        ArrayList<String> numbers = new ArrayList<String>();
        for (int i=1; i<10; i++) {
            numbers.add(Integer.toString(i));
            
        }       
        
        for (int i=0; i<9; i++) {
            if(!Initiator.isNumeric(column.substring(i,i+1))) {
                emptyInColumn.add(rcToIndex(i,columnNo));
            }
            else {
                numbers.remove(column.substring(i,i+1));
            }
        }
        
        for (String each : numbers) {
            numberCount.put(each,0);
        }
        
        for (Integer empties : emptyInColumn) {
            for (String each : memo.get(empties)) {
                numberCount.put(each, numberCount.get(each)+1);
                location.put(each,empties);
            }
        }
        
        for (String string : numberCount.keySet()) {
            if (numberCount.get(string) == 1) {
                uniqueIntegers.put(string,location.get(string));
            }
        }
        
        return uniqueIntegers;
    }
    
    public HashMap<String,Integer> uniqueInBox(int boxNo) {
        HashMap<String,Integer> uniqueIntegers = new HashMap<String,Integer>();
        HashMap<String,Integer> numberCount = new HashMap<String,Integer>();
        HashMap<String,Integer> location = new HashMap<String,Integer>();
        String box = test.everyBox.get(boxNo);
        ArrayList<Integer> emptyInBox = new ArrayList<Integer>();
        ArrayList<String> numbers = new ArrayList<String>();
        for (int i=1; i<10; i++) {
            numbers.add(Integer.toString(i));
            
        }       
        
        for (int i=0; i<9; i++) {
            if(!Initiator.isNumeric(box.substring(i,i+1))) {
                emptyInBox.add(boxToIndex(boxNo,i));
            }
            else {
                numbers.remove(box.substring(i,i+1));
            }
        }
        
        for (String each : numbers) {
            numberCount.put(each,0);
        }
        
        for (Integer empties : emptyInBox) {
            for (String each : memo.get(empties)) {
                numberCount.put(each, numberCount.get(each)+1);
                location.put(each,empties);
            }
        }
        
        for (String string : numberCount.keySet()) {
            if (numberCount.get(string) == 1) {
                uniqueIntegers.put(string,location.get(string));
            }
        }
        
        return uniqueIntegers;
    }
    
    public HashMap<String,ArrayList<Integer>> examineBoxAndRowCol(int boxId, String rowcol) {
        ArrayList<String> memodInBox = new ArrayList<String>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        HashMap<String,ArrayList<Integer>> boxAndRow = new HashMap<String,ArrayList<Integer>>();
        HashMap<String,ArrayList<Integer>> boxAndCol = new HashMap<String,ArrayList<Integer>>();        
        String box = test.everyBox.get(boxId);
        for (int i = 0; i<9; i++) {
            if (!Initiator.isNumeric(box.substring(i,i+1))) {
                indices.add(boxToIndex(boxId,i));                
            }
        }
        for (int each : indices) {
            ArrayList<String> memoInEach = new ArrayList<String>();
            memoInEach = memo.get(each);
            for (String eachMemo : memoInEach) {
                if (!memodInBox.contains(eachMemo)) {
                    memodInBox.add(eachMemo);
                }
            }
        }
        for (String eachMemo : memodInBox) {
            ArrayList<Integer> rows = new ArrayList<Integer>();
            ArrayList<Integer> columns = new ArrayList<Integer>();
            for (int each : indices) {
                if ((memo.get(each).contains(eachMemo)) && (!rows.contains(ip.whichRow(each)))) {
                    rows.add(ip.whichRow(each));
                }
                if ((memo.get(each).contains(eachMemo)) && (!columns.contains(ip.whichColumn(each)))) {
                    columns.add(ip.whichColumn(each));
                }
            }
            boxAndRow.put(eachMemo,rows);
            boxAndCol.put(eachMemo,columns);
        }
        if (rowcol == "row") {
            return boxAndRow;
        }
        if (rowcol == "col") {
            return boxAndCol;
        }
        return null;
    }
    
    public void filler() {
        for (Integer empty : test.allEmptyIndex) {
            ArrayList<String> eachMemo = memo.get(empty);
            if (eachMemo.size() == 1) {
                test.solved[empty] = eachMemo.get(0);
            }
        }
    }
    
    public String newSudoku() {
        for (String each : test.solved) {
            if (Initiator.isNumeric(each)) {
                sb.append(each);
            }
            else {
                sb.append("-");
            }
        }
        for (int i=0; i<9; i++) {
            sb.insert(10*i,"\n");
        }
        return sb.toString();
    }
    
    public void solver() {
        test.solvedBuilder();
        test.rowBuilder();
        test.columnBuilder();
        test.boxBuilder();
        
        for (Integer empty : test.allEmptyIndex) {
            memoWriter(empty);
        }
        filler();
        memoEditor();
        memoEditorAdv();
    }
}
