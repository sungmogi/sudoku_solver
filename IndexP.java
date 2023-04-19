import java.util.*;
import edu.duke.*;

public class IndexP {    
    public int whichRow(int index){
        int div = index/9;
        return div;
    }
    
    public int whichColumn(int index){
        int remainder = index % 9;
        return remainder;
    }
    
    public int whichBox(int index){
        if ((index >= 0 && index < 3) || (index >= 9 && index < 12) || (index >= 18 && index < 21)) {
            return 0;
        }
        if ((index >= 3 && index < 6) || (index >= 12 && index < 15) || (index >= 21 && index < 24)) {
            return 1;
        }
        if ((index >= 6 && index < 9) || (index >= 15 && index < 18) || (index >= 24 && index < 27)) {
            return 2;
        }
        if ((index >= 27 && index < 30) || (index >= 36 && index < 39) || (index >= 45 && index < 48)) {
            return 3;
        }
        if ((index >= 30 && index < 33) || (index >= 39 && index < 42) || (index >= 48 && index < 51)) {
            return 4;
        }
        if ((index >= 33 && index < 36) || (index >= 42 && index < 45) || (index >= 51 && index < 54)) {
            return 5;
        }
        if ((index >= 54 && index < 57) || (index >= 63 && index < 66) || (index >= 72 && index < 75)) {
            return 6;
        }
        if ((index >= 57 && index < 60) || (index >= 66 && index < 69) || (index >= 75 && index < 78)) {
            return 7;
        }
        if ((index >= 60 && index < 63) || (index >= 69 && index < 72) || (index >= 78 && index < 81)) {
            return 8;
        }
        return -1;
    }
}
