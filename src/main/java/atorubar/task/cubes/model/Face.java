package atorubar.task.cubes.model;

/**
 * Class represents Cube face 5x5 size
 */
public class Face {
    /**
     * Cube face size. Can't be more than 8 in current implementation
     */
    public static final int SIZE = 5;
    public static final byte FILLED_EDGE = 0b11111;

    /**
     * In this abstraction we represent cube face as 4 edges because we only need to compare edges in this puzzle.
     * Edges represented as byte numbers where each bit represents edge cell in this way:
     * (numbers in picture are bit positions)
     * --------01234
     * ------4|^^^^^|0
     * ------3|.....|1
     * ------2|.....|2
     * ------1|.....|3
     * ------0|_____|4
     * --------43210
     */
    private byte topEdge = 0;
    private byte rightEdge = 0;
    private byte botEdge = 0;
    private byte leftEdge = 0;
    // we need to store reversed edges for comparision
    private byte reversedTopEdge = 0;
    private byte reversedRightEdge = 0;
    private byte reversedBotEdge = 0;
    private byte reversedLeftEdge = 0;

    private Face(Face face) {
        this.topEdge = face.topEdge;
        this.rightEdge = face.rightEdge;
        this.botEdge = face.botEdge;
        this.leftEdge = face.leftEdge;
        this.reversedTopEdge = face.reversedTopEdge;
        this.reversedRightEdge = face.reversedRightEdge;
        this.reversedBotEdge = face.reversedBotEdge;
        this.reversedLeftEdge = face.reversedLeftEdge;
    }

    /**
     * Creates Cube Face from input string.
     *
     * @param faceAsString flat representation of cube face which means that first N characters, where N is face size,
     *                     represent first row, second N characters represent second row and so on.
     *                     Space character ' ' will be read as empty cell and all other characters will be read as filled cell.
     *                     Example of valid string for 5x5 size cube face:
     *                     String faceAsString = " *** " + "0000 " + " aaaa" + " --- " + "66666"; will be represented as:
     *                     |  [][][]  |
     *                     |[][][][]  |
     *                     |  [][][][]|
     *                     |[][][][][]|
     */
    public Face(String faceAsString) {
        if (faceAsString == null || faceAsString.length() != SIZE * SIZE) {
            throw new IllegalArgumentException("Wrong format. Cube face have to contain exactly " + SIZE * SIZE + " cells");
        }

        for (int i = 0; i < faceAsString.length(); i++) {
            if (faceAsString.charAt(i) != ' ') {
                int xPos = i % SIZE;
                int yPos = i / SIZE;
                // fill top edge
                if (yPos == 0) {
                    topEdge = fillCellOnEdge(topEdge, xPos);
                    reversedTopEdge = fillCellOnEdge(reversedTopEdge, SIZE - 1 - xPos);
                }
                // fill bottom edge
                if (yPos == SIZE - 1) {
                    botEdge = fillCellOnEdge(botEdge, SIZE - 1 - xPos);
                    reversedBotEdge = fillCellOnEdge(reversedBotEdge, xPos);
                }
                // fill left edge
                if (xPos == 0) {
                    leftEdge = fillCellOnEdge(leftEdge, SIZE - 1 - yPos);
                    reversedLeftEdge = fillCellOnEdge(reversedLeftEdge, yPos);

                }
                // fill right edge
                if (xPos == SIZE - 1) {
                    rightEdge = fillCellOnEdge(rightEdge, yPos);
                    reversedRightEdge = fillCellOnEdge(reversedRightEdge, SIZE - 1 - yPos);
                }
            }
        }
    }

    /**
     * Rotates Cube Face clockwise on 90 degrees
     */
    private void rotate() {
        byte tmp = topEdge;
        topEdge = leftEdge;
        leftEdge = botEdge;
        botEdge = rightEdge;
        rightEdge = tmp;
        tmp = reversedTopEdge;
        reversedTopEdge = reversedLeftEdge;
        reversedLeftEdge = reversedBotEdge;
        reversedBotEdge = reversedRightEdge;
        reversedRightEdge = tmp;
    }

    /**
     * Rotates Cube Face clockwise on 90 degrees specified number of times
     *
     * @param numberOfRotations number of rotations. This parameter have to be positive number.
     * @return rotated cube face
     */
    public Face rotate(int numberOfRotations) {
        if (numberOfRotations < 0) {
            throw new IllegalArgumentException("numberOfRotations must be positive number");
        }
        // there is no point to rotate 4 times or more
        numberOfRotations = numberOfRotations % 4;
        Face rotated = new Face(this);

        while (numberOfRotations > 0) {
            rotated.rotate();
            numberOfRotations--;
        }
        return rotated;
    }

    public static boolean isEdgesMatch(byte edgeA, byte edgeB) {
        return FILLED_EDGE == (edgeA ^ edgeB);
    }

    private byte fillCellOnEdge(byte edge, int cellNum) {
        return (byte) (edge | (1 << cellNum));
    }

    public byte getTopEdge() {
        return topEdge;
    }

    public byte getRightEdge() {
        return rightEdge;
    }

    public byte getBotEdge() {
        return botEdge;
    }

    public byte getLeftEdge() {
        return leftEdge;
    }

    public byte getReversedTopEdge() {
        return reversedTopEdge;
    }

    public byte getReversedRightEdge() {
        return reversedRightEdge;
    }

    public byte getReversedBotEdge() {
        return reversedBotEdge;
    }

    public byte getReversedLeftEdge() {
        return reversedLeftEdge;
    }
}
