package DancingLinkAlg;

import HeaderSpecifiers.AreaValueIdentity;

import java.util.Map;

public class HeaderIdentity implements Identity{
    //assigns each character in the map an integer, if the key isnt in the map or the map is null,
    //use the ascii table. With the help of that integer will characters be compared
    public static Map<String,Integer> characterRanking;

    public String name;
    public HeaderIdentity(String name) {
        this.name = name;
    }
    // sort of default
    public boolean match(Identity toCheck) {
        if(toCheck instanceof HeaderIdentity) return match((HeaderIdentity)toCheck);
        return false;
    }
    public boolean match(HeaderIdentity toCheck) {
        return this.name.equals(toCheck.name);
    }
    public String getName() {
        return name;
    }

    public static void setCharacterRanking(Map<String,Integer> characterRanking) {
        AreaValueIdentity.characterRanking = characterRanking;
    }
    public static int getAssociatedInt(String c) {
        if(characterRanking == null || !characterRanking.containsKey(c)) return c.charAt(0);
        else return characterRanking.get(c);
    }

}
