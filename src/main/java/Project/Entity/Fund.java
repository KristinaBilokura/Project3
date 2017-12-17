package Project.Entity;
import Project.DAO.DAOgem;
import javax.xml.bind.annotation.*;
import java.util.Iterator;
import java.util.List;
@XmlRootElement(name = "pavilion")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(factoryMethod = "newInstance")
public class Fund implements Cloneable, Iterable<Gem> {
    @XmlElementWrapper(name = "gems")
    @XmlElement(name = "gem")
    private List<Gem> gems;
    public Fund(List<Gem> gems) {
        this.gems = gems;
    }
    public List<Gem> getGems() {
        return gems;
    }
    public void setGems(List <Gem> gems) {
        this.gems = gems;
    }
    public void addGem(Gem gem) {
        gems.add(gem);
    }
    public static Fund newInstance() {
        return new Fund(null);
    }
    @Override
    public String toString() {
        if (!gems.equals(null)) {
            String s = "This fund contains:\n";
            for (Gem gem : gems) {
                try {
                    DAOgem.getConnection(gem.getId(),gem.getName(),gem.getOrigin(),gem.getValue());
                    DAOgem.getConnection(gem.getId(),gem.getVisualParameters().getColor(),gem.getVisualParameters().getTransparency(),gem.getVisualParameters().getGemCutting());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                s += gem.toString() + "\n";

            }
            return s.substring(0, s.length() - 1);
        } else return "This fund does not contain gems.";
    }
    @Override
    public Iterator<Gem> iterator() {
        return new Iterator<Gem>() {
            private int index;
            private List<Gem> list = getGems();
            @Override
            public boolean hasNext() {
                return index < list.size();
            }
            @Override
            public Gem next() {
                return list.get(index++);
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
