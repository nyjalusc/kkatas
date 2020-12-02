package arrays.cw;

import java.util.Map;
import java.util.HashMap;

class CountAtoms {

    public static String getSubstr(String string) {
        HashMap<Character, Character> braces = new HashMap<>();
        char brace = string.charAt(0);
        braces.put('(', ')');
        braces.put('{', '}');
        braces.put('[', ']');
        int i = 1, count = 0;
        String res = "";
        char ch;
        while (i < string.length()) {
            ch = string.charAt(i);
            if (ch == brace) {
                count++;
            }
            if (ch == braces.get(brace)) {
                if (count == 0) return res;
                count--;
            }
            res += ch;
            i++;
        }
        if (i == string.length()) throw new IllegalArgumentException();
        return res;
    }

    public static void helper(String str, HashMap<String, Integer> result) {
        int i = 0, mult;
        char ch;
        String substr, multstr;
        while (i < str.length()) {
            substr = "";
            multstr = "";
            mult = 1;
            ch = str.charAt(i);
            if ((ch == '(') || (ch == '{') || (ch == '[')) {
                substr = getSubstr(str.substring(i));
                i += substr.length() + 1;
            } else if (Character.isUpperCase(ch)) {
                substr += ch;
                while ((i + 1 < str.length()) && (Character.isLowerCase(str.charAt(i + 1)))) {
                    i++;
                    substr += str.charAt(i);
                }
            } else throw new IllegalArgumentException();
            if (str.compareTo(substr) == 0) {
                {
                    if (!result.containsKey(str)) result.put(str, 1);
                    else result.put(str, result.get(str) + 1);
                    return;
                }
            }

            if ((i + 1 < str.length()) && (Character.isDigit(str.charAt(i + 1)))) {
                while ((i + 1 < str.length()) && (Character.isDigit(str.charAt(i + 1)))) {
                    i++;
                    multstr += str.charAt(i);
                }
                mult = Integer.parseInt(multstr);
            }
            for (int j = 0; j < mult; j++) {
                helper(substr, result);
            }
            i++;
        }
    }

    public static Map<String, Integer> getAtoms(String formula) {
        HashMap<String, Integer> res = new HashMap<>();
        helper(formula, res);
        return res;
    }
}