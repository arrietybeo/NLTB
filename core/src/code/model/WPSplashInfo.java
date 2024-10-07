package code.model;

import javax.microedition.lcdui.Image;
import lib.mSystem;

public class WPSplashInfo {
   public int[][] P0_X = mSystem.mIntArray(4, 8);
   public int[][] P0_Y = mSystem.mIntArray(4, 8);
   public int[][] PF_X = mSystem.mIntArray(4, 8);
   public int[][] PF_Y = mSystem.mIntArray(4, 8);
   public int[][] PF_W = mSystem.mIntArray(4, 8);
   public int[][] PF_H = mSystem.mIntArray(4, 8);
   public Image image;
}
