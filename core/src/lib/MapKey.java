package lib;

import com.badlogic.gdx.Input;

public class MapKey {
    private static mHashtable h = new mHashtable();

    public static void load() {
        h.put("A", Integer.valueOf(97));
        h.put("B", Integer.valueOf(98));
        h.put("C", Integer.valueOf(99));
        h.put("D", Integer.valueOf(100));
        h.put("E", Integer.valueOf(101));
        h.put("F", Integer.valueOf(102));
        h.put("G", Integer.valueOf(103));
        h.put("H", Integer.valueOf(104));
        h.put("I", Integer.valueOf(105));
        h.put("J", Integer.valueOf(106));
        h.put("K", Integer.valueOf(107));
        h.put("L", Integer.valueOf(108));
        h.put("M", Integer.valueOf(109));
        h.put("N", Integer.valueOf(110));
        h.put("O", Integer.valueOf(111));
        h.put("P", Integer.valueOf(112));
        h.put("Q", Integer.valueOf(113));
        h.put("R", Integer.valueOf(114));
        h.put("S", Integer.valueOf(115));
        h.put("T", Integer.valueOf(116));
        h.put("U", Integer.valueOf(117));
        h.put("V", Integer.valueOf(118));
        h.put("W", Integer.valueOf(119));
        h.put("X", Integer.valueOf(120));
        h.put("Y", Integer.valueOf(121));
        h.put("Z", Integer.valueOf(122));
        h.put("0", Integer.valueOf(48));
        h.put("1", Integer.valueOf(49));
        h.put("2", Integer.valueOf(50));
        h.put("3", Integer.valueOf(51));
        h.put("4", Integer.valueOf(52));
        h.put("5", Integer.valueOf(53));
        h.put("6", Integer.valueOf(54));
        h.put("7", Integer.valueOf(55));
        h.put("8", Integer.valueOf(56));
        h.put("9", Integer.valueOf(57));
        h.put("SPACE", Integer.valueOf(32));
        h.put("F1", -21);
        h.put("F2", -22);
        h.put("EQUALS", -25);
        h.put("MINUS", 45);
        h.put("F3", -23);
        h.put("UP", 1);
        h.put("DOWN", 6);
        h.put("LEFT", 2);
        h.put("RIGHT", 5);
        h.put("BACKSPACE", -8);
        h.put("PERIOD", Integer.valueOf(46));
        h.put("AT", Integer.valueOf(64));
        h.put("TAB", -26);
        h.put("DELETE", -8);
        h.put("ENTER", 8);
        h.put(".", 46);
        h.put("/", 47);
        h.put("TAB", -22);
        h.put("-", 95);
    }

    public static int map(int kyeCode) {
        try {
            String k = Input.Keys.toString(kyeCode).toUpperCase();
            int v = (Integer) h.get(k);
            return v;
        } catch (Exception var3) {
            return 0;
        }
    }
}
