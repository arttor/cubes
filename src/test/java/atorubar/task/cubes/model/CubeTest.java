package atorubar.task.cubes.model;

import atorubar.task.cubes.util.InputUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CubeTest {

    @Test
    public void isSolved() throws Exception {
        Cube cube = new Cube(InputUtil.getCubeFaces());
        assertFalse(cube.isSolved());
        cube = new Cube(InputUtil.getSolvedCubeFaces());
        assertTrue(cube.isSolved());
    }

    @Test(expected = IllegalArgumentException.class)
    public void tetIllegalArgumentsInConstructor() throws Exception {
        new Cube(Arrays.copyOf(InputUtil.getCubeFaces(), 5));
    }
}
