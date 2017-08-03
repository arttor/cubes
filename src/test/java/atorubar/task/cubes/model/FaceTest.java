package atorubar.task.cubes.model;

import atorubar.task.cubes.util.InputUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FaceTest {
    private Face face;

    @Before
    public void setUp() throws Exception {
        /*
        "-0-0-" +
        "0000-" +
        "-0000" +
        "0000-" +
        "00-0-"
        */
        face = InputUtil.getCubeFacesExample1()[3];
    }

    @Test(expected = IllegalArgumentException.class)
    public void tetIllegalArgumentsInConstructor() throws Exception {
        new Face("asdasd");
    }


    @Test
    public void getLtCorner() throws Exception {
        assertEquals(face.getLtCorner(), 0);
    }

    @Test
    public void getRtCorner() throws Exception {
        assertEquals(face.getRtCorner(), 0);
    }

    @Test
    public void getRbCorner() throws Exception {
        assertEquals(face.getRbCorner(), 0);
    }

    @Test
    public void getLbCorner() throws Exception {
        assertEquals(face.getLbCorner(), 1);
    }

    @Test
    public void rotate() throws Exception {
        Face rotated = face.rotate(1);
        assertEquals(face.getTopEdge(), rotated.getRightEdge());
        assertEquals(face.getRightEdge(), rotated.getBotEdge());
        assertEquals(face.getBotEdge(), rotated.getLeftEdge());
        assertEquals(face.getLeftEdge(), rotated.getTopEdge());

        assertEquals(face.getLtCorner(), rotated.getRtCorner());
        assertEquals(face.getRtCorner(), rotated.getRbCorner());
        assertEquals(face.getRbCorner(), rotated.getLbCorner());
        assertEquals(face.getLbCorner(), rotated.getLtCorner());

    }

    @Test
    public void isEdgesMatch() throws Exception {
        assertTrue(Face.isEdgesMatch(face.getLeftEdge(), face.getRightEdge()));
        assertFalse(Face.isEdgesMatch(face.getRightEdge(), face.getRightEdge()));
    }

    @Test
    public void getTopEdge() throws Exception {
        assertEquals(face.getTopEdge(), 0b101);
    }

    @Test
    public void getReversedTopEdge() throws Exception {
        assertEquals(face.getTopEdge(), 0b101);
    }

    @Test
    public void turnOver() throws Exception {
        Face inverted = face.turnOver();

        assertEquals(face.getTopEdge(), inverted.getReversedTopEdge());
        assertEquals(face.getRightEdge(), inverted.getReversedLeftEdge());
        assertEquals(face.getBotEdge(), inverted.getReversedBotEdge());
        assertEquals(face.getLeftEdge(), inverted.getReversedRightEdge());

        assertEquals(face.getLtCorner(), inverted.getRtCorner());
        assertEquals(face.getLbCorner(), inverted.getRbCorner());

    }

}
