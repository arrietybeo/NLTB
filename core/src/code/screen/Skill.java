package code.screen;

import java.util.Hashtable;

import lib.mGraphics;

public class Skill {
    public static SkillPaint[] skillPaints;
    public static SkillOptionTemplate[] optiontemplates;
    public static Hashtable skills = new Hashtable();
    public static final byte TYPE_AUTO = 0;
    public static final byte TYPE_ATTACK = 0;
    public static final byte TYPE_BUFF = 1;
    public static final byte TYPE_RELIVE = 3;
    public SkillTemplate template;
    public short skillId;
    public int point;
    public int level;
    public int timeReplay;
    public long lastTimeUseThisSkill;
    public int dx;
    public int dxTemp;
    public int dy;
    public int dyTemp;
    public int maxFight;
    public int manaUse;
    public SkillOption[] options;

    public static void put(Skill skill) {
        skills.put((skill.skillId), skill);
    }

    public static Skill get(short skillId) {
        return (Skill) skills.get((skillId));
    }

    public String strTimeReplay() {
        if (this.timeReplay % 1000 == 0) {
            return String.valueOf(this.timeReplay / 1000);
        } else {
            int time = this.timeReplay % 1000;
            return this.timeReplay / 1000 + "." + (time % 100 == 0 ? time / 100 : time / 10);
        }
    }

    public Skill getSkillNext() {
        try {
            if (this.point < this.template.maxPoint) {
                for (int i = 0; i < this.template.skills.length; ++i) {
                    if (this.template.skills[i].equals(this)) {
                        return this.template.skills[i + 1];
                    }
                }
            }
        } catch (Exception var2) {
        }

        return null;
    }

    public int coolDown() {
        int delta = 0;
        return -1;
    }

    public static void paint(Skill skill, int x, int y, mGraphics g) {
        int size = 16;
    }
}
