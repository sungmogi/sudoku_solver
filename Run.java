import java.util.*;
import edu.duke.*;

public class Run {
    public void solve() {
        FileResource fr = new FileResource();
        Solver first = new Solver(fr.asString());
        first.solver();
        System.out.println("problem:");
        System.out.println(fr.asString());
        //System.out.println(first.memo);
        
        Solver[] solvers = new Solver[12];
        for (int i = 0; i<12; i++) {
            if (i == 0) {
                solvers[i] = new Solver(first.newSudoku());
                solvers[i].solver();
            }
            else {
                solvers[i] = new Solver(solvers[i-1].newSudoku());
                solvers[i].solver();
            }
        }
        System.out.println("\n");
        System.out.println("solution:");
        System.out.println(solvers[11].newSudoku());
        System.out.println(solvers[11].memo);
        for (int i=0; i<9; i++) {
            System.out.println(solvers[11].examineBoxAndRowCol(i,"row"));
        }
        for (int i=0; i<9; i++) {
            System.out.println(solvers[11].examineBoxAndRowCol(i,"col"));
        }
    }
}
