package atorubar.task.cubes.util;

import atorubar.task.cubes.model.Face;

/**
 * Util class for cubes input
 */
public class InputUtil {
    private InputUtil(){
        throw new IllegalStateException("Do not instantiate utility class");
    }

    /**
     * Get cube faces
     *
     * @return returns cube faces from given example
     */
    public static Face[] getCubeFaces() {

        Face[] faces = new Face[6];
        faces[0] = new Face("--0--" +
                "-000-" +
                "00000" +
                "-000-" +
                "--0--");
        faces[1] = new Face("0-0-0" +
                "00000" +
                "-000-" +
                "00000" +
                "0-0-0");
        faces[2] = new Face("--0--" +
                "-0000" +
                "0000-" +
                "-0000" +
                "--0--");
        faces[3] = new Face("-0-0-" +
                "0000-" +
                "-0000" +
                "0000-" +
                "00-0-");
        faces[4] = new Face("-0-0-" +
                "00000" +
                "-000-" +
                "00000" +
                "0-0--");
        faces[5] = new Face("-0-0-" +
                "-0000" +
                "0000-" +
                "-0000" +
                "00-00");
        return faces;
    }

    /**
     * Get solved cube faces
     *
     * @return returns solved cube faces from given example
     */
    public static Face[] getSolvedCubeFaces() {

        Face[] faces = new Face[6];
        faces[0] = new Face("--0--" +
                "-000-" +
                "00000" +
                "-000-" +
                "--0--");
        faces[1] = new Face("-0-0" +
                "00000" +
                "-000-" +
                "00000" +
                "-0-00");
        faces[2] = new Face(" 0 0 " +
                "-000-" +
                "00000" +
                "-000-" +
                "--0--");
        faces[3] = new Face("0-0--" +
                "00000" +
                "-000-" +
                "00000" +
                "-0-0-");
        faces[4] = new Face("0-0-0" +
                "00000" +
                "-000-" +
                "00000" +
                "0-0-0");
        faces[5] = new Face("-0-0-" +
                "0000-" +
                "-0000" +
                "0000-" +
                "00-0-");
        return faces;
    }
}
