package atorubar.task.cubes;

import atorubar.task.cubes.model.Cube;
import atorubar.task.cubes.util.InputUtil;

public class Main {

    public static void main(String[] args) {
        CubeSolver cubeSolver = new CubeSolver();
        cubeSolver.readFaces(InputUtil.getCubeFaces());
        Cube result = cubeSolver.solve();
        if (result != null) {
            result.print();
        }
    }

}
