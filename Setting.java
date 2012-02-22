
/**
 * Data structure to store a setting
 * @author Dinnot
 */
public class Setting extends CsvStructure{
    public static final int TYPE_STRING = 0;
    public static final int TYPE_INT = 1;
    public static final int TYPE_FLOAT = 2;
    
    public static final int NO_FIELDS = 3;
    public static final int IDX_NAME = 0;
    public static final int IDX_VALUE = 1;
    public static final int IDX_TYPE = 2;
    
    private String m_name;
    private String m_value;
    private int m_type;
    
    /*/
     * Setters and Getters
     */
    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public int getType() {
        return m_type;
    }

    public void setType(int type) {
        m_type = type;
    }

    public String getValue() {
        return m_value;
    }
    
    public int getValueInt() {
        if(m_type != Setting.TYPE_INT) {
            throw new IllegalArgumentException("Setting "+m_name+" is not of type INT");
        }
        return new Integer(m_value).intValue();
    }
    
    public double getValueFloat() {
        if(m_type != Setting.TYPE_FLOAT) {
            throw new IllegalArgumentException("Setting "+m_name+" is not of type FLOAT(double)");
        }
        return new Double(m_value).doubleValue();
    }

    public void setValue(String value) {
        m_value = value;
    }
    
    public void setValue(int value) {
        if(m_type != Setting.TYPE_INT) {
            throw new IllegalArgumentException("Setting "+m_name+" is not of type INT");
        }
        m_value = new Integer(value).toString();
    }
    
    public void setValue(double value) {
        if(m_type != Setting.TYPE_FLOAT) {
            throw new IllegalArgumentException("Setting "+m_name+" is not of type FLOAT(double)");
        }
        m_value = new Double(value).toString();
    }
    
    /**
     * Dummy constructor - for testing. Creates a default setting with the name "setting" and the value "default"
     */
    public Setting() {
        m_name = "setting";
        m_value = "default";
        m_type = Setting.TYPE_STRING;
    }
    
    /**
     * Creates a new Setting given the type
     * @param name The name (identifier) of the setting
     * @param value The value
     * @param type The type Setting.TYPE_INT, Setting.TYPE_STRING or Setting.TYPE_FLOAT
     */
    public Setting(String name, String value, int type) {
        m_name = name;
        m_value = value;
        m_type = type;
    }
    
    /**
     * Creates a new Setting with predefined String Type
     * @param name The name (identifier) of the setting
     * @param value The value
     */
    public Setting(String name, String value) {
        m_name = name;
        m_value = value;
        m_type = Setting.TYPE_STRING;
    }
    
    /**
     * Creates a new Setting with predefined INT Type
     * @param name The name (identifier) of the setting
     * @param value The value
     */
    public Setting(String name, int value) {
        m_name = name;
        m_value = new Integer(value).toString();
        m_type = Setting.TYPE_INT;
    }
    
    /**
     * Creates a new Setting with predefined FLOAT Type
     * @param name The name (identifier) of the setting
     * @param value The value
     */
    public Setting(String name, double value) {
        m_name = name;
        m_value = new Double(value).toString();
        m_type = Setting.TYPE_FLOAT;
    }

    /**
     * Computes the Setting as a row in the CSV file
     * @return The CSV line of the input, as a String
     */
    @Override
    public String toCSV() {
        String csv = "\""+m_name+"\",\""+m_value+"\","+m_type;
        return csv;
    }
    
}
