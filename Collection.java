
import java.util.ArrayList;

/**
 * General csv collection to be subclassed by all Collections (EventsCollection, SettingsCollection, TasksCollection etc)
 * @author Dinnot
 */
public class Collection<CSVSTR extends CsvStructure> {
    protected ArrayList<CSVSTR> m_data;
    
    public void add(CSVSTR elem) {
        m_data.add(elem);
    }
    
    public String toCSV() {
        String csv = "";
        for(int i = 0; i < m_data.size(); i++) {
            if(i > 0) {
                csv += System.getProperty("line.separator");
            }
            csv += m_data.get(i).toCSV();
        }
        return csv;
    }
}
