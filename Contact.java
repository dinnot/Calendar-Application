
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class Contact extends CsvStructure {

    private int m_id;
    private String m_name;
    private Calendar m_dob;
    private String m_landphone;
    private String m_mobilephone;
    private String m_workphone;
    private String m_email;
    private String m_workemail;
    
    public static final int NO_FIELDS = 8;
    public static final int IDX_ID = 0;
    public static final int IDX_NAME = 1;
    public static final int IDX_DOB = 2;
    public static final int IDX_LANDPHONE = 3;
    public static final int IDX_MOBILEPHONE = 4;
    public static final int IDX_WORKPHONE = 5;
    public static final int IDX_EMAIL = 6;
    public static final int IDX_WORKEMAIL = 7;

    public Calendar getDob() {
        return m_dob;
    }

    public void setDob(Calendar dob) {
        m_dob = dob;
    }

    public String getEmail() {
        return m_email;
    }

    public void setEmail(String email) {
        m_email = email;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        m_id = id;
    }

    public String getLandphone() {
        return m_landphone;
    }

    public void setLandphone(String landphone) {
        m_landphone = landphone;
    }

    public String getMobilephone() {
        return m_mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        m_mobilephone = mobilephone;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getWorkemail() {
        return m_workemail;
    }

    public void setWorkemail(String workemail) {
        m_workemail = workemail;
    }

    public String getWorkphone() {
        return m_workphone;
    }

    public void setWorkphone(String workphone) {
        m_workphone = workphone;
    }

    public Contact() {
    }

    public Contact(int m_id, String m_name, Calendar m_dob, String m_landphone, String m_mobilephone, String m_workphone, String m_email, String m_workemail) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_dob = m_dob;
        this.m_landphone = m_landphone;
        this.m_mobilephone = m_mobilephone;
        this.m_workphone = m_workphone;
        this.m_email = m_email;
        this.m_workemail = m_workemail;
    }
    
    
    
    @Override
    public String toCSV() {
        String csv = m_id+","+m_name+","+"\""+Data.fromDate(m_dob)+"\",\""+m_landphone+"\",\""+m_mobilephone+"\",\""+m_workphone+"\","+"\",\""+m_email+"\",\""+m_workemail+"\"";
        return csv;
    }
    
}
