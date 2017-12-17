package Project.Entity;
import javax.xml.bind.annotation.XmlEnumValue;
public enum  Preciousness {
    @XmlEnumValue(value = "PRECIOUS")
    PRECIOUS,
    @XmlEnumValue(value = "SEMIPRECIOUS")
    SEMIPRECIOUS;
}