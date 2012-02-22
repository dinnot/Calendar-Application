
import java.awt.Color;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinnot
 */
public class DayPanel extends JPanel{
    public DayPanel(Calendar date, int showing_month) {
        super();
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        String print_day = new Integer(day).toString();
        if(day == 1)
            print_day += " "+date.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale(Data.getSettings().get("locale")));
        print_day = " "+print_day+" ";
        TitledBorder border = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), print_day);
        if(month == showing_month)
            border.setTitleColor(Color.black);
        else
            border.setTitleColor(Color.GRAY);
        setBorder(border);
        setBackground(Color.WHITE);
    }
}
