package atorubar.task.cubes.model;

/**
 * Class represents Cube face NxN size
 */
public class Face {
    /**
     * Cube face size. Can't be more than 8 in current implementation
     */
    public static final int SIZE = 5;
    public static final char EMPTY_CELL = '-';
    public static final String EMPTY_ROW_STR = "          ";

    /**
     * Bit mask for edges comparison
     */
    private static final byte FILLED_EDGE = 0b111;
    private static final int EDGE_SIZE = SIZE - 2;
    private static final String E = "  ";
    private static final String F = "[]";
    private static final String FILLED_EDGE_STR = "[][][]";

    /**
     * In this abstraction we represent cube face as 4 edges and 4 angles because we only need to compare
     * this parameters to solve puzzle. Edges represented as byte numbers where each bit represents edge cell in this way:
     * (numbers in picture are bit positions)
     * ---------012
     * -----LT|^^^^^|RT-corner
     * ------2|.....|0
     * ------1|.....|1
     * ------0|.....|2
     * -----LB|_____|RB-corner
     * ---------210
     */
    private byte topEdge;
    private byte rightEdge;
    private byte botEdge;
    private byte leftEdge;
    private byte ltCorner;
    private byte rtCorner;
    private byte rbCorner;

    public byte getLtCorner() {
        return ltCorner;
    }

    public byte getRtCorner() {
        return rtCorner;
    }

    public byte getRbCorner() {
        return rbCorner;
    }

    public byte getLbCorner() {
        return lbCorner;
    }

    private byte lbCorner = 0;

    // we need to store reversed edges for comparision
    private byte reversedTopEdge = 0;
    private byte reversedRightEdge = 0;
    private byte reversedBotEdge = 0;
    private byte reversedLeftEdge = 0;

    public Face(Face face) {
        this.topEdge = face.topEdge;
        this.rightEdge = face.rightEdge;
        this.botEdge = face.botEdge;
        this.leftEdge = face.leftEdge;
        this.reversedTopEdge = face.reversedTopEdge;
        this.reversedRightEdge = face.reversedRightEdge;
        this.reversedBotEdge = face.reversedBotEdge;
        this.reversedLeftEdge = face.reversedLeftEdge;
        this.ltCorner = face.ltCorner;
        this.rtCorner = face.rbCorner;
        this.rbCorner = face.rbCorner;
        this.lbCorner = face.lbCorner;
    }

    /**
     * Creates Cube Face from input string.
     *
     * @param faceAsString flat representation of cube face which means that first N characters, where N is face size,
     *                     represent first row, second N characters represent second row and so on.
     *                     Minus character '-' will be read as empty cell and all other characters will be read as filled cells.
     *                     Example of valid string for 5x5 size cube face:
     *                     String faceAsString = "-***-" + "0000-" + "-aaaa" + "-GGG-" + "66666"; will be represented as:
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
            if (faceAsString.charAt(i) != EMPTY_CELL) {
                int xPos = i % SIZE;
                int yPos = i / SIZE;

                // fill top edge
                if (yPos == 0) {
                    if (xPos == 0) {
                        ltCorner = 1;
                    } else if (xPos == SIZE - 1) {
                        rtCorner = 1;
                    } else {
                        topEdge = fillCellOnEdge(topEdge, xPos - 1);
                        reversedTopEdge = fillCellOnEdge(reversedTopEdge, SIZE - xPos - 2);
                    }
                }
                // fill bottom edge
                if (yPos == SIZE - 1) {
                    if (xPos == 0) {
                        lbCorner = 1;
                    } else if (xPos == SIZE - 1) {
                        rbCorner = 1;
                    } else {
                        botEdge = fillCellOnEdge(botEdge, SIZE - xPos - 2);
                        reversedBotEdge = fillCellOnEdge(reversedBotEdge, xPos - 1);
                    }
                }
                // fill left edge
                if (xPos == 0) {
                    if (yPos == 0) {
                        ltCorner = 1;
                    } else if (yPos == SIZE - 1) {
                        lbCorner = 1;
                    } else {
                        leftEdge = fillCellOnEdge(leftEdge, SIZE - yPos - 2);
                        reversedLeftEdge = fillCellOnEdge(reversedLeftEdge, yPos - 1);
                    }
                }
                // fill right edge
                if (xPos == SIZE - 1) {
                    if (yPos == 0) {
                        rtCorner = 1;
                    } else if (yPos == SIZE - 1) {
                        rbCorner = 1;
                    } else {
                        rightEdge = fillCellOnEdge(rightEdge, yPos - 1);
                        reversedRightEdge = fillCellOnEdge(reversedRightEdge, SIZE - yPos - 2);
                    }
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
        tmp = ltCorner;
        ltCorner = lbCorner;
        lbCorner = rbCorner;
        rbCorner = rtCorner;
        rtCorner = tmp;

    }

    private byte getEdgeCell(byte edge, int position) {
        return (byte) ((edge >> position) & 1);
    }

    private String getEdgeCellAsStr(byte edge, int position) {
        return getEdgeCell(edge, position) == 0 ? E : F;
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

    /**
     * Returns string representations of specified row
     *
     * @param rowNum row number. Have to be between 0 and Face{@link #SIZE}
     * @return Face row as String where two spaces "  " stands for empty cell and brackets "[]" for filled cell
     */
    public String getRowAsString(int rowNum) {
        if (rowNum < 0 || rowNum >= SIZE) {
            throw new IllegalArgumentException("Invalid rowNum parameter value. Have to be 0 <= rowNum < FACE_SIZE");
        }
        StringBuilder result = new StringBuilder();
        if (rowNum == 0) {
            result.append(ltCorner == 0 ? E : F);
            for (int i = 0; i < EDGE_SIZE; i++) {
                result.append(getEdgeCellAsStr(topEdge, i));
            }
            result.append(rtCorner == 0 ? E : F);
        } else if (rowNum == SIZE - 1) {
            result.append(lbCorner == 0 ? E : F);
            for (int i = 0; i < EDGE_SIZE; i++) {
                result.append(getEdgeCellAsStr(reversedBotEdge, i));
            }
            result.append(rbCorner == 0 ? E : F);
        } else {
            result.append(getEdgeCellAsStr(reversedLeftEdge, rowNum - 1));
            result.append(FILLED_EDGE_STR);
            result.append(getEdgeCellAsStr(rightEdge, rowNum - 1));
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            result.append(getRowAsString(i));
            result.append("\n");
        }
        return result.toString();
    }
}
