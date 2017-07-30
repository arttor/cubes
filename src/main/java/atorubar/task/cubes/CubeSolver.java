package atorubar.task.cubes;

import atorubar.task.cubes.model.Cube;
import atorubar.task.cubes.model.Face;

import javax.annotation.Nullable;

/**
 * Cube puzzle solver class. This implementation is not thread-safe
 */
public class CubeSolver extends Cube {
    @Nullable
    private Cube solved;
    private int numberOfIterations;

    /**
     * Returns number of iterations from last solution
     *
     * @return number of iterations - sum of cube faces rotations and permutations
     */
    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    /**
     * Read cube faces to solve puzzle
     *
     * @param cubeFaces array of 6 cube faces.
     */
    public void readFaces(Face[] cubeFaces) {
        if (cubeFaces == null || cubeFaces.length != SIZE) {
            throw new IllegalArgumentException("Wrong format. Cube have to contain exactly " + SIZE + " faces");
        }
        // copy faces
        this.faces = new Face[SIZE];
        for (int i = 0; i < cubeFaces.length; i++) {
            this.faces[i] = new Face(cubeFaces[i]);
        }
        solved = null;
        numberOfIterations = 0;
    }

    /**
     * Solves the cube puzzle
     *
     * @return solved cube or null if there is no solution
     */
    @Nullable
    public Cube solve() {
        if (faces == null) {
            throw new IllegalStateException("Please read Cube faces before solving");
        }
        if (solved != null) {
            return solved;
        }

        // go through all possible (4^6)-1  faces rotations where 6-number of faces and 4-number of rotations
        for (int i = 0; i < Math.pow(4, 6); i++) {
            int[] rotations = new int[6];
            int div = i;
            for (int r = 5; r >= 0; r--) {
                int tmp = div / 4;
                rotations[r] = div - (tmp * 4);
                if (tmp == 0) {
                    break;
                }
                div = tmp;
            }
            Face[] rotatedFaces = new Face[6];
            for (int j = 0; j < faces.length; j++) {
                rotatedFaces[j] = faces[j].rotate(rotations[j]);
            }
            // go through all array permutations
            permute(rotatedFaces, 0);
        }
        return solved;
    }

    // go through all array permutations by using recursion
    private void permute(Face[] arr, int k) {
        if (solved != null) {
            return;
        }
        for (int i = k; i < arr.length; i++) {
            arrSwap(arr, i, k);
            permute(arr, k + 1);
            if (solved != null) {
                return;
            }
            arrSwap(arr, k, i);
        }
        if (k == arr.length - 1) {
            Cube cube = new Cube(arr);
            numberOfIterations++;
            if (cube.isSolved()) {
                solved = cube;
            }
        }
    }

    /**
     * Swaps the elements in the array
     *
     * @param array source array
     * @param a     the index of one element to be swapped
     * @param b     the index of the other element to be swapped
     */
    private static void arrSwap(Face[] array, int a, int b) {
        Face tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

}
