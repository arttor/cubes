package atorubar.task.cubes.model;

import atorubar.task.cubes.util.InputUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CubeTest {

    @org.junit.Test
    public void isSolved() throws Exception {
        Cube cube=new Cube(InputUtil.getCubeFaces());
        assertFalse(cube.isSolved());
        cube=new Cube(InputUtil.getSolvedCubeFaces());
        assertTrue(cube.isSolved());
    }

}