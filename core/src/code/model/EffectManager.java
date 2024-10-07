package code.model;

import code.effect.new_skill.EffectSkill;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;

public class EffectManager extends mVector {
   public static EffectManager lowEffects = new EffectManager();
   public static EffectManager hiEffects = new EffectManager();

   public void updateAll() {
      for(int i = this.size() - 1; i >= 0; --i) {
         Effect e = (Effect)this.elementAt(i);
         if (e != null) {
            e.update();
            if (e.wantDestroy) {
               this.removeElementAt(i);
            }
         }
      }

   }

   public void paintAll(mGraphics g) {
      for(int i = 0; i < this.size(); ++i) {
         Effect e = (Effect)this.elementAt(i);
         if (e != null) {
            if (e.isEffAuto) {
               if (GameScr.isInScreen(e)) {
                  e.paint(g);
               }
            } else {
               e.paint(g);
            }
         }
      }

   }

   public static void addHiEffect(int x, int y, int type) {
      hiEffects.addElement(new EffectSkill(x, y, type, 0));
   }

   public static void addLowDataeffectSkill(int type, int x, int y, int lvpaint) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, type);
      lowEffects.addElement(dataeff);
   }

   public static void addLowDataeffectSkill(int type, int x, int y, int lvpaint, boolean canremove) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, type);
      dataeff.canremove = canremove;
      lowEffects.addElement(dataeff);
   }

   public static void addHiDataeffectSkill(int type, int x, int y, int lvpaint) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, type);
      hiEffects.addElement(dataeff);
   }

   public static void addHiDataeffectSkill_delay(int type, int x, int y, int lvpaint, int delay) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, type, delay);
      hiEffects.addElement(dataeff);
   }

   public static void addHiDataeffectSkill(int type, int x, int y, byte[] arr) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, arr);
      hiEffects.addElement(dataeff);
   }

   public static void addHiDataeffectSkill_(int type, int x, int y, int lvpaint, Char mychar) {
      DataSkillEff dataeff = new DataSkillEff(type, x, y, type);
      dataeff.setMychar(mychar);
      hiEffects.addElement(dataeff);
   }

   public static void addHiEffect(Effect effect) {
      hiEffects.addElement(effect);
   }

   public static void addLowEffect(Effect effect) {
      lowEffects.addElement(effect);
   }

   public static void addLowEffect(int x, int y, int type) {
      lowEffects.addElement(new EffectSkill(x, y, type, 0));
   }
}
