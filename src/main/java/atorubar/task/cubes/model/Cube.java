package atorubar.task.cubes.model;

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
     * @return true if puzzle solved
     */
    public boolean isSolved() {
        return Face.isEdgesMatch(faces[0].getLeftEdge(), faces[4].getReversedLeftEdge())
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
                && Face.isEdgesMatch(faces[5].getBotEdge(), faces[1].getReversedTopEdge());
    }
}
