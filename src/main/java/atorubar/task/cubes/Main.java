package atorubar.task.cubes;

import atorubar.task.cubes.model.Cube;
import atorubar.task.cubes.model.Face;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Face[] faces=readCubes();
        Cube c=new Cube(faces);

        for(int i=0;i<Math.pow(4,6);i++){
            int[]rotations = new int[6];
            int dev=i;
            for(int r=5;r>=0;r--){
                int tmp = dev/4;
                rotations[r]=dev-(tmp*4);
                if(tmp==0)break;
                dev=tmp;
            }
            Face[] rotatedFaces = new Face[6];
            for (int j = 0; j < faces.length; j++) {
                rotatedFaces[j]=faces[j].rotate(rotations[j]);
            }
            //Cube cube = new Cube(rotatedFaces);
            permute(Arrays.asList(rotatedFaces),0);
        }

        int t=0;
    }

    static void permute(java.util.List<Face> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            Cube cube = new Cube((Face[])arr.toArray());
            if(cube.isSolved()){
                int re=234;
            }
        }
    }

    private static Face[] readCubes(){

        Face[] faces = new Face[6];
        faces[0]=new Face("  0  " +
                " 000 " +
                "00000" +
                " 000 " +
                "  0  ");
        faces[1]=new Face("0 0 0" +
                "00000" +
                " 000 " +
                "00000" +
                "0 0 0");
        faces[2]=new Face("  0  " +
                " 0000" +
                "0000 " +
                " 0000" +
                "  0  ");
        faces[3]=new Face(" 0 0 " +
                "0000 " +
                " 0000" +
                "0000 " +
                "00 0 ");
        faces[4]=new Face(" 0 0 " +
                "00000" +
                " 000 " +
                "00000" +
                "0 0  ");
        faces[5]=new Face(" 0 0 " +
                " 0000" +
                "0000 " +
                " 0000" +
                "00 00");
        return faces;
    }

}
