import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlElement
    private String name;
    @XmlElement
    private Date birthday;
    @XmlElement(name = "hobby_list")
    private ArrayList<HobbyList> hobbyList= new ArrayList<HobbyList>();

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public  Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ArrayList<HobbyList> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(ArrayList<HobbyList> hobbyList) {
        this.hobbyList = hobbyList;
    }
}
