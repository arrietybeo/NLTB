package code.screen;

import code.model.Char;
import code.model.Point;

public class SkillPaint {
   public int id;
   public int pluginEndId;
   public SkillTemp[] templates;

   public SkillRun convert(int dir, Char c, Point point, int maxFight, int skillTemplateId) {
      SkillRun skillPoint = new SkillRun();
      skillPoint.dir = dir;
      skillPoint.pluginStart = this.templates[dir].pluginPaint;
      skillPoint.pluginEnd = PluginPaint.arr[this.pluginEndId];
      skillPoint.infos = this.templates[dir].infos;
      skillPoint.c = c;
      skillPoint.point = point;
      skillPoint.maxFight = maxFight;
      skillPoint.skillTemplateId = skillTemplateId;
      skillPoint.setDart();
      return skillPoint;
   }
}
