package atorubar.task.cubes.model;

import java.io.IOException;

/**
 * Class represents 3-dimensional Cube
 */
public class Cube {
    /**
     * Number of faces for 3-dimensional cube
     */
    public static final int SIZE = 6;
    private final Face[] faces;

    /**
     * Creates Cube from given faces
     *
     * @param faces array of 6 cube faces in unfolded form:
     *              [0][1][2]
     *              ---[3]---
     *              ---[4]---
     *              ---[5]---
     */
    public Cube(Face[] faces) {
        if (faces == null || faces.length < SIZE) {
            throw new IllegalArgumentException("Wrong format. Cube have to contain exactly " + SIZE + " faces");
        } else if (faces.length > SIZE) {
            throw new IllegalArgumentException("Hey! We are working only with three-dimensional cubes, Einstein!");
        }
        this.faces = faces;
    }


    /**
     * Checks if puzzle solved for current faces positions and rotations
     *
     * @return true if puzzle solved
     */
    public boolean isSolved() {
        return // check 12 edges
                Face.isEdgesMatch(faces[0].getLeftEdge(), faces[4].getReversedLeftEdge())
                        && Face.isEdgesMatch(faces[0].getRightEdge(), faces[1].getReversedLeftEdge())
                        && Face.isEdgesMatch(faces[0].getTopEdge(), faces[5].getReversedLeftEdge())
                        && Face.isEdgesMatch(faces[0].getBotEdge(), faces[3].getReversedLeftEdge())

                        && Face.isEdgesMatch(faces[2].getLeftEdge(), faces[1].getReversedRightEdge())
                        && Face.isEdgesMatch(faces[2].getRightEdge(), faces[4].getReversedRightEdge())
                        && Face.isEdgesMatch(faces[2].getTopEdge(), faces[5].getReversedRightEdge())
                        && Face.isEdgesMatch(faces[2].getBotEdge(), faces[3].getReversedRightEdge())

                        && Face.isEdgesMatch(faces[1].getBotEdge(), faces[3].getReversedTopEdge())
                        && Face.isEdgesMatch(faces[3].getBotEdge(), faces[4].getReversedTopEdge())
                        && Face.isEdgesMatch(faces[4].getBotEdge(), faces[5].getReversedTopEdge())
                        && Face.isEdgesMatch(faces[5].getBotEdge(), faces[1].getReversedTopEdge())
                        // check 8 corners
                        && faces[0].getRbCorner() + faces[1].getLbCorner() + faces[3].getLtCorner() == 1
                        && faces[0].getRtCorner() + faces[1].getLtCorner() + faces[5].getLbCorner() == 1
                        && faces[0].getLbCorner() + faces[3].getLbCorner() + faces[4].getLtCorner() == 1
                        && faces[0].getLtCorner() + faces[4].getLbCorner() + faces[5].getLtCorner() == 1

                        && faces[2].getLbCorner() + faces[1].getRbCorner() + faces[3].getRtCorner() == 1
                        && faces[2].getLtCorner() + faces[1].getRtCorner() + faces[5].getRbCorner() == 1
                        && faces[2].getRbCorner() + faces[3].getRbCorner() + faces[4].getRtCorner() == 1
                        && faces[0].getRtCorner() + faces[4].getRbCorner() + faces[5].getRtCorner() == 1;
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println(i);
            faces[i].print();
        }
    }
}
