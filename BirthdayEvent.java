
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class BirthdayEvent extends Event {
    private int m_guests;

    public int getGuests() {
        return m_guests;
    }

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    public static final int NO_FIELDS = 7;
    public static final int IDX_GUESTS = 6;
    
    public void setGuests(int guests) {
        m_guests = guests;
    }

    public BirthdayEvent(int id, String title, Calendar start_date, Calendar end_date, String description, int repetition, int guests) {
        super(id, title, start_date, end_date, description, repetition);
        m_guests = guests;
        m_type = Event.WORK_EVENT;
    }
    
    
    
    @Override
    public String toCSV() {
        String csv = super.toCSV() + ", "+m_guests;
        return csv;
    }
}
