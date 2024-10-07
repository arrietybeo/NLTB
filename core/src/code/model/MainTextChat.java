package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;

public class MainTextChat {
   public String text;
   public byte color;
   public int w;
   public int h;

   public MainTextChat(String text, byte color) {
      this.text = text;
      this.color = color;
      this.prepareData(text);
   }

   public void prepareData(String chat) {
      String[] chats = FontTeam.normalFontColor.splitFontBStrInLine(chat, 100);
      this.h = FontTeam.normalFontColor.getHeight() + FontTeam.normalFontColor.getHeight() / 2;
      int aa = 0;
      this.w = 30;

      for(int i = 0; i < chats.length; ++i) {
         aa += FontTeam.normalFontColor.getWidth(chats[i]);
         if (aa > GameCanvas.msgchat.wDia) {
            aa = GameCanvas.msgchat.wDia - 5;
         }

         if (aa > this.w) {
            this.w = aa;
         }
      }

   }
}
