
import java.util.Calendar;
import java.util.Locale;
import javax.swing.Icon;


/**
 * Data Structure to hold an Event
 * @author Dinnot
 */
public class Event extends CsvStructure{
    
    public static final int NOT_REPEATING = 0;
    public static final int REPEATING_DAILY = 1;
    public static final int REPEATING_WEEKENDS = 2;
    public static final int REPEATING_WORKING_DAYS = 3;
    public static final int REPEATING_WEEKLY = 4;
    public static final int REPEATING_TWO_WEEKS = 5;
    public static final int REPEATING_FOUR_WEEKS = 6;
    public static final int REPEATING_MONTHLY = 7;
    public static final int REPEATING_YEARLY = 8;
    
    public static final int GENERAL_EVENT = 0;
    public static final int WORK_EVENT = 1;
    public static final int SOCIAL_EVENT = 2;
    public static final int BIRTHDAY_EVENT = 3;
    
    public static final int NO_FIELDS = 6;
    public static final int IDX_ID = 0;
    public static final int IDX_TITLE = 1;
    public static final int IDX_START_DATE = 2;
    public static final int IDX_END_DATE = 3;
    public static final int IDX_DESCRIPTION = 4;
    public static final int IDX_REPETITION = 5;
    
    protected int m_type;
    protected int m_id;
    protected String m_title;
    protected Calendar m_start_date;
    protected Calendar m_end_date;
    protected String m_description;
    protected int m_repetition;
    protected Icon icon;

    /*/
     *  Getters and Setters
     */
    public String getDescription() {
        return m_description;
    }

    public void setDescription(String description) {
        m_description = description;
    }

    public Calendar getEnd_date() {
        return m_end_date;
    }

    public void setEnd_date(Calendar end_date) {
        m_end_date = end_date;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        m_id = id;
    }

    public int getRepetition() {
        return m_repetition;
    }

    public void setRepetition(int repetition) {
        m_repetition = repetition;
    }

    public Calendar getStart_date() {
        return m_start_date;
    }

    public void setStart_date(Calendar start_date) {
        this.m_start_date = start_date;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        m_title = title;
    }
    
    /**
     * Creating a new Event with no values (dummy).
     * Mainly used for testing purposes.
     */
    public Event() {
        m_id = Data.allocateEventId();
        m_title = "Temp Event";
        m_start_date = Calendar.getInstance();
        m_end_date = Calendar.getInstance();
        m_end_date.add(Calendar.HOUR_OF_DAY, 1);
        m_description = "Dummy event";
        m_repetition = Event.NOT_REPEATING;
        m_type = Event.GENERAL_EVENT;
    }
    
    /**
     * Creating a new Event with given values.
     * @param id The id of the event as an int
     * @param title The title of the event as a String
     * @param start_date The starting date of the event, as a Calendar object
     * @param end_date The ending date of the event, as a Calendar object
     * @param description The description of the event, as a String
     * @param repetition  The repetition mode, as an int
     */
    public Event(int id, String title, Calendar start_date, Calendar end_date, String description, int repetition) {
        m_id = id;
        m_title = title;
        m_start_date = start_date;
        m_end_date = end_date;
        m_description = description;
        m_repetition = repetition;
        m_type = Event.GENERAL_EVENT;
    }
    
    
    /**
     * Computes the Event as a row in the CSV file
     * @return The CSV line of the input, as a String
     */
    @Override
    public String toCSV() {
        String csv = m_type+","+m_id+","+"\""+m_title+"\",\""+Data.fromDate(m_start_date)+"\",\""+Data.fromDate(m_end_date)+"\",\""+m_description+"\","+m_repetition;
        return csv;
    }
}
