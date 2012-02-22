
import java.util.ArrayList;


/**
 *
 * @author Dinnot
 */
public class SettingsCollection extends Collection<Setting> {

    public SettingsCollection() {
        m_data = new ArrayList<Setting>();
    }
    
    public void set(String name, String value) {
        for(int i = 0; i < m_data.size(); i++) {
            if(m_data.get(i).getName().equals(name)) {
                m_data.get(i).setValue(value);
                return;
            }
        }
        Setting data = new Setting(name, value);
        m_data.add(data);
    }
    
   public String get(String name) {
       for(int i = 0; i < m_data.size(); i++) {
            if(m_data.get(i).getName().equals(name)) {
                return m_data.get(i).getValue();
            }
        }
       throw new IllegalArgumentException("The Setting "+name+" is not defined!");
   }
   
   public boolean exists(String name) {
       for(int i = 0; i < m_data.size(); i++) {
            if(m_data.get(i).getName().equals(name)) {
                return true;
            }
        }
       return false;
   }
   
   public int getInt(String name) {
       for(int i = 0; i < m_data.size(); i++) {
            if(m_data.get(i).getName().equals(name)) {
                return m_data.get(i).getValueInt();
            }
        }
       throw new IllegalArgumentException("The Setting "+name+" is not defined!");
   }
   
   public double getFloat(String name) {
       for(int i = 0; i < m_data.size(); i++) {
            if(m_data.get(i).getName().equals(name)) {
                return m_data.get(i).getValueFloat();
            }
        }
       throw new IllegalArgumentException("The Setting "+name+" is not defined!");
   }
    
}
