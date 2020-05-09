import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name = "hobby_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class HobbyList {
    @XmlElement(name = "hobby")
    private ArrayList<Hobby> hobby=new ArrayList<Hobby>();

    public ArrayList<Hobby> getHobby() {
        return hobby;
    }

    public void setHobby(ArrayList<Hobby> hobby) {
        this.hobby = hobby;
    }
}
