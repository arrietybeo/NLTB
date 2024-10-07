package code.model;

public class ChatInfo {
   public String name;
   public int ID;
   public String content;

   public ChatInfo() {
   }

   public ChatInfo(String from, String content, int color) {
      this.name = from;
      this.content = content.toLowerCase();
   }
}
