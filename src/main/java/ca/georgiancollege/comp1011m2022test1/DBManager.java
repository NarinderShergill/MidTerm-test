package ca.georgiancollege.comp1011m2022test1;
import java.sql.*;
import java.util.ArrayList;

/* Singleton */
public class DBManager
{
    /********************* SINGLETON SECTION *****************************/
    // Step 1. private static instance member variable
    private static DBManager m_instance = null;
    // Step 2. make the default constructor private
    private DBManager() {}
    // Step 3. create a public static entry point / instance method
    public static DBManager Instance()
    {
        // Step 4. Check if the private instance member variable is null
        if(m_instance == null)
        {
            // Step 5. Instantiate a new DBManager instance
            m_instance = new DBManager();
        }
        return m_instance;
    }
    /*********************************************************************/

    // private instance member variables
    private String m_user = "root";
    private String m_password = "@narinder0786";
    private String m_connectURL = "jdbc:mysql://localhost:3306/javatest";

    /**
     * This method reads the students table from the MySQL database
     * and returns an ArrayList of student type
     * @return
     */
    public ArrayList<Student> readStudentTable()
    {
        // Instantiates an ArrayList collection of type Student (empty container)
        ArrayList<Student> students = new ArrayList<Student>();

        String sql = "SELECT studentNum,firstName,lastName,telephone,homeAddress,province,avgGrade,major FROM students";

        try
                (
                        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            // while there is another record...loop
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                int studentNum = resultSet.getInt("studentNum");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String telephone = resultSet.getString("telephone");
                String address = resultSet.getString("homeAddress");
                String province = resultSet.getString("province");
                int avgGrade = resultSet.getInt("avgGrade");
                String major = resultSet.getString("major");

                // create an anonymous Student object with the data from the database
                // then add the new Student object to the students ArrayList
                students.add( new Student(studentNum, firstName, lastName,telephone,address,province,avgGrade,major));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return students;
    }


}

