package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public void addHexagon(int l, int topLeftX, int topLeftY, TETile[][] world) {
        int[] yList = getYList(l, topLeftY);
        int[][] xList = getXList(l, topLeftX);
        draw(world, xList, yList);
    }


    /**
     * Given length and topLeftY coordinate,return one dim array of y cors
     * of the hexagon to be plotted, from bottom to top
     */
    private static int[] getYList(int l, int topLeftY) {
        int height = 2 * l;
        int[] yList = new int[height];
        int bottom = topLeftY - height + 1;
        for (int i = 0; i < height; i++) {
            yList[i] = bottom;
            bottom++;
        }
        return yList;
    }

    /**
     * Given length and topLeftX coordinate,return two dim array of x cors
     * of the hexagon to be plotted, from bottom to top, left to right
     * undrawed x are marked -1
     */
    private static int[][] getXList(int l, int topLeftX) {
        int height = l * 2;
        int maxLength = 3 * l - 2;
        int[][] xList = new int[height][maxLength];
        int startIdx = l - 1;
        int endIdx = startIdx + l - 1;
        int startX = topLeftX;
        //note that l is the half height
        for (int i = 0; i < height; i++) {
            if (i < l) { //fill the bottom half part.
                int leftX = startX;
                for (int j = 0; j < maxLength; j++) {
                    if ((j < startIdx) || (j > endIdx)) { /* not a valid index */
                        xList[i][j] = -1; //-1 meaning not a valid index
                    } else {
                        xList[i][j] = leftX;
                        leftX++;
                    }
                }
                startX = startX - 1;
                //change start and end index
                startIdx--;
                endIdx++;
            }

            else { //fill the top half part
                if (i == l) {
                    startIdx++;
                    endIdx--;
                    startX++;
                }
                int leftX = startX;
                for(int j = 0; j < maxLength; j++) {
                    if ((j < startIdx || (j > endIdx))) { //not a valid index
                        xList[i][j] = -1;
                    }else {
                        xList[i][j] = leftX;
                        leftX ++;
                    }
                }
                startX = startX + 1;
                startIdx++;
                endIdx--;
            }

        }


        return xList;
    }

    /*
    * draw the plot given world and coordinates
    * */
    private static void draw(TETile[][] world, int[][] xList, int[] yList) {
        int maxlength = xList[0].length;
        int height = xList.length;
        for (int yidx = 0; yidx < height; yidx++) {
            for (int xidx = 0; xidx < maxlength; xidx++) {
                int x = xList[yidx][xidx];
                int y = yList[yidx];
                if (x != -1) { /*valid x */
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }
}
