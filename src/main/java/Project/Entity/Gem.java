package Project.Entity;
import Project.DAO.DAOgem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Gem implements Cloneable {
    private String id;
    private String name;
    private String origin;
    private Preciousness preciousness;
    private VisualParameters visualParameters;
    private double value;
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public void setValue(double value) {
        if (value < 0.01) {
            throw new IllegalArgumentException("Not correct value(weight) for gem");
        } else this.value = value;
    }
    @XmlElement
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    @XmlElement
    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }
    @XmlElement
    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }
    public double getValue() {
        value = new BigDecimal(value).setScale(2, RoundingMode.UP).doubleValue();
        return (double) value;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getOrigin() {
        return origin;
    }
    public Preciousness getPreciousness() {
        return preciousness;
    }
    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    @Override
    public String toString() {
        String text = String.format("\"%s\" -%s %s.From oridin - %s,this rock is %s, weight is %s, visual parameters are: %s",
                id,getClass().getSimpleName(), this.name,this.origin,this.preciousness, this.value, this.visualParameters);

        return text;
    }


}
