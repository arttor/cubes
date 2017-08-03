package atorubar.task.cubes;

import atorubar.task.cubes.model.Cube;
import atorubar.task.cubes.util.InputUtil;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CubeSolverTest {


    @Test
    public void solvePuzzleFromExample() throws Exception {
        CubeSolver solver = new CubeSolver();
        solver.readFaces(InputUtil.getCubeFacesExample1());
        Cube solution = solver.solve();
        assertNotNull(solution);
        System.out.println(solution);
        System.out.println("Solved for " + solver.getNumberOfIterations() + " iterations");
        Files.write(Paths.get("./solution.txt"), solution.toString().getBytes());
    }

    @Test
    public void checkSolvedPuzzleFromExample() throws Exception {
        CubeSolver solver = new CubeSolver();
        solver.readFaces(InputUtil.getSolvedCubeFaces());
        Cube solution = solver.solve();
        assertNotNull(solution);
        assertEquals(solver.getNumberOfIterations(), 1);
        System.out.println(solution);
    }
}
