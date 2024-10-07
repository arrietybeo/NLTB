package code.screen;

import lib.mGraphics;
import lib.mVector;

public class ChatBox {
   public mVector content = new mVector();
   public String[] lineContent;
   public int sayRun;
   public int sayExit;
   public int sayWidth;
   public int sayTime = 200;
   public int line = 0;
   public int indexLine = 0;

   public ChatBox(String str) {
      if (str.length() < 10) {
         this.sayWidth = 64;
      } else {
         this.sayWidth = 100;
      }

      this.content.addElement(str.trim());
      this.line = this.content.size();
   }

   public void setSayTime(int sayTime) {
      this.sayTime = sayTime;
   }

   public boolean isClear() {
      if (this.sayRun <= 5) {
         ++this.sayRun;
      }

      ++this.sayExit;
      return this.sayTime != -1 && this.sayExit > this.sayTime;
   }

   public void paint(mGraphics g, int x, int y, int height) {
   }

   public static mVector splitContent(String src) {
      mVector lines = new mVector();
      String line = "";

      for(int i = 0; i < src.length(); ++i) {
         if (src.charAt(i) == '\\' && i + 1 < src.length() && src.charAt(i + 1) == 'n') {
            ++i;
            lines.addElement(line + "...");
            line = "";
         } else if (src.charAt(i) == '\n') {
            lines.addElement(line + "...");
            line = "";
         } else {
            line = line + src.charAt(i);
            if (i == src.length() - 1 && !line.trim().equals("")) {
               lines.addElement(line);
            }
         }
      }

      return lines;
   }

   public void updateKey() {
   }

   public void onPress(int idButton, Object obj) {
   }

   public void viewNext() {
   }
}
