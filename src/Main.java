


import com.zaxxer.hikari.HikariDataSource;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {

   static Connection connection;

    public static void main(String[] args) throws JAXBException, SQLException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
        connection = ConnectionDb.getConnection();
        unmarshall(jaxbContext);
    }
    public static void unmarshall(JAXBContext jaxbContext) throws SQLException {
        PreparedStatement insertPerson;
        PreparedStatement insertHobby;
        insertHobby = connection.prepareStatement(
                "insert into hobby_list(user_id,hobby, complexity) values (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        insertPerson = connection.prepareStatement(
                "insert into persons(name, birthday) values (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        try {
            int userId=0;
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PersonList unmarshalledPersons = (PersonList) jaxbUnmarshaller.unmarshal(new FileInputStream("res/Persons.xml"));
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            for (Person p : unmarshalledPersons.getPersons()) {
                userId++;
                insertPerson.setString(1,p.getName());
                java.sql.Date DateSql = new java.sql.Date(p.getBirthday().getTime());
                insertPerson.setDate(2,DateSql);
                insertPerson.executeUpdate();
                for (HobbyList hobbyList : p.getHobbyList()) {
                    for(Hobby hobby:hobbyList.getHobby()) {
                        insertHobby.setInt(1,userId);
                        insertHobby.setString(2,hobby.getHobbyName());
                        insertHobby.setInt(3,hobby.getComplexity());
                        insertHobby.executeUpdate();;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}
