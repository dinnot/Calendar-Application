
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class SocialEvent extends Event {
    private int m_guests;

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    public static final int NO_FIELDS = 7;
    public static final int IDX_GUESTS = 6;
    
    public int getGuests() {
        return m_guests;
    }

    public void setGuests(int guests) {
        m_guests = guests;
    }

    public SocialEvent(int id, String title, Calendar start_date, Calendar end_date, String description, int repetition, int guests) {
        super(id, title, start_date, end_date, description, repetition);
        m_guests = guests;
        m_type = Event.SOCIAL_EVENT;
    }
    
    @Override
    public String toCSV() {
        String csv = super.toCSV() + ", "+m_guests;
        return csv;
    }
}
