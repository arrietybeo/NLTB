package code.model;

import code.screen.Res;
import lib.mHashtable;

public class Chunk {
   public static mHashtable arr = new mHashtable();
   public static ChunkDemo[] demo;
   public short idBig;
   public byte type;
   public short[] listIDChunkPaint = new short[52];
   public ChunkData[] chunkData;
   public SmallImage[] allSmallImg = null;

   public ChunkTemplate[] getTemplate() {
      return demo[this.type].templates;
   }

   public int getIcon(int frame) {
      return this.listIDChunkPaint[frame];
   }

   public int getBigId() {
      return this.idBig;
   }

   public static Chunk getHead(int chunkId, int genderId) {
      try {
         if (arr.get(String.valueOf(chunkId)) == null) {
            Res.loadChunkPrivate(chunkId);
         }

         return (Chunk)arr.get(String.valueOf(chunkId));
      } catch (Exception var3) {
         if (genderId == 0) {
            if (arr.get("4") == null) {
               Res.loadChunkPrivate(4);
            }

            return (Chunk)arr.get("4");
         } else {
            if (arr.get("0") == null) {
               Res.loadChunkPrivate(chunkId);
            }

            return (Chunk)arr.get("0");
         }
      }
   }

   public static Chunk getBody(int chunkId, int genderId) {
      try {
         if (arr.get(String.valueOf(chunkId)) == null) {
            Res.loadChunkPrivate(chunkId);
         }

         return (Chunk)arr.get(String.valueOf(chunkId));
      } catch (Exception var3) {
         if (genderId == 0) {
            if (arr.get("9") == null) {
               Res.loadChunkPrivate(9);
            }

            return (Chunk)arr.get("9");
         } else {
            if (arr.get("8") == null) {
               Res.loadChunkPrivate(8);
            }

            return (Chunk)arr.get("8");
         }
      }
   }

   public static Chunk getLeg(int chunkId, int genderId) {
      try {
         if (arr.get(String.valueOf(chunkId)) == null) {
            Res.loadChunkPrivate(chunkId);
         }

         return (Chunk)arr.get(String.valueOf(chunkId));
      } catch (Exception var3) {
         if (genderId == 0) {
            if (arr.get("11") == null) {
               Res.loadChunkPrivate(11);
            }

            return (Chunk)arr.get("11");
         } else {
            if (arr.get("10") == null) {
               Res.loadChunkPrivate(10);
            }

            return (Chunk)arr.get("10");
         }
      }
   }

   public static Chunk getWeapon(int chunkId) {
      try {
         if (arr.get(String.valueOf(chunkId)) == null) {
            Res.loadChunkPrivate(chunkId);
         }

         return (Chunk)arr.get(String.valueOf(chunkId));
      } catch (Exception var2) {
         return null;
      }
   }
}
