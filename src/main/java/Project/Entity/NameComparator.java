package Project.Entity;

import java.util.Comparator;

public class NameComparator implements Comparator {
    public int compare(Object o1,Object o2){
        Gem s1=(Gem) o1;
        Gem s2=(Gem)o2;

        return s1.getName().compareTo(s2.getName());
    }
}