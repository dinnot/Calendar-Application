import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
/**
 *
 * @author Dinnot
 */
public class Data {
    private static ContactsCollection contacts;
    private static EventsCollection events;
    private static SettingsCollection settings;
    private static int max_event_id;
    private static int max_contact_id;

    public static ContactsCollection getContacts() {
        return contacts;
    }

    public static EventsCollection getEvents() {
        return events;
    }

    public static SettingsCollection getSettings() {
        return settings;
    }
                                                        
    public static int allocateEventId() {
        return ++max_event_id;
    }
    
    public static int allocateContactId() {
        return ++max_contact_id;
    }
    
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public static void addSetting(String name, String value, int type) throws Exception {
        settings.add(new Setting(name, value, type));
        writeSettings();
    }
    
    public static void addContact(int id, String name, Calendar dob, String landphone, String mobilephone, String workphone, String email, String workemail) throws Exception {
        contacts.add(new Contact(id, name, dob, landphone, mobilephone, workphone, email, workemail));
        writeContacts();
    }
    
    public static void addEvent() throws Exception {
        writeEvents();
    }
    
    public static void addSocialEvent() throws Exception {
        writeEvents();
    }
    
    public static void addWorkEvent() throws Exception {
        writeEvents();
    }
    
    public static void addBirthdayEvent() throws Exception {
        writeEvents();
    }
    
    private static void writeSettings() throws Exception {
        String data = settings.toCSV();
        FileWriter fw = new FileWriter("data/settings.csv");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(data);
        writer.close();
        
    }
    
    private static void writeEvents() throws Exception {
        String data = events.toCSV();
        FileWriter fw = new FileWriter("data/events.csv");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(data);
        writer.close();
        
    }
    
    private static void writeContacts() throws Exception {
        String data = contacts.toCSV();
        FileWriter fw = new FileWriter("data/contacts.csv");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(data);
        writer.close();
        
    }
    
    public static void init() throws Exception {
        settings = new SettingsCollection();
        events = new EventsCollection();
        contacts = new ContactsCollection();
        testDir();
        initSettings();
        initContacts();
        initEvents();
    }
    
    private static void testDir() {
        File data = new File("data");
        if(!data.isDirectory() || !data.exists()) {
            data.mkdir();
        }
    }
    
    private static void initContacts() throws Exception {
        try {
            File contacts_file = new File("data/contacts.csv");
            if(!contacts_file.exists())
                contacts_file.createNewFile();
            CSVReader reader = new CSVReader(new FileReader(contacts_file));
            String [] nextLine;
            int line = 0;
            while ((nextLine = reader.readNext()) != null) {
                line++;
                if(nextLine.length != Contact.NO_FIELDS) {
                    throw new Exception("Wrong format for csv file contacts.csv at line "+line);
                } else {
                    contacts.add(new Contact(
                            Data.parseInt(nextLine[Contact.IDX_ID]), 
                            nextLine[Contact.IDX_NAME], 
                            Data.parseDate(nextLine[Contact.IDX_DOB]), 
                            nextLine[Contact.IDX_LANDPHONE], 
                            nextLine[Contact.IDX_MOBILEPHONE], 
                            nextLine[Contact.IDX_WORKPHONE], 
                            nextLine[Contact.IDX_EMAIL], 
                            nextLine[Contact.IDX_WORKEMAIL])
                        );
                    int id = Data.parseInt(nextLine[Contact.IDX_ID]);
                    if(id > max_contact_id)
                        max_contact_id = id;
                }
            }
        } catch(IOException e) {
            throw new Exception("Something wrong with file reading... Please contact an administrator");
        }
        
    }
    
    private static void initEvents() throws Exception {
        try {
            File events_file = new File("data/events.csv");
            if(!events_file.exists())
                events_file.createNewFile();
            CSVReader reader = new CSVReader(new FileReader(events_file));
            String [] nextLine;
            int line = 0;
            while ((nextLine = reader.readNext()) != null) {
                line++;
                int no_fields;
                Event e;
                int id;
                switch(Data.parseInt(nextLine[0])) {
                    case Event.GENERAL_EVENT:{   no_fields = Event.NO_FIELDS;
                        e = new Event(
                                    Data.parseInt(nextLine[Event.IDX_ID]),
                                    nextLine[Event.IDX_TITLE],
                                    Data.parseDate(nextLine[Event.IDX_START_DATE]),
                                    Data.parseDate(nextLine[Event.IDX_END_DATE]),
                                    nextLine[Event.IDX_DESCRIPTION],
                                    Data.parseInt(nextLine[Event.IDX_REPETITION])
                                );
                        id = Data.parseInt(nextLine[Event.IDX_ID]);
                        break;
                    }
                    case Event.BIRTHDAY_EVENT:{  no_fields = BirthdayEvent.NO_FIELDS;
                        e = new BirthdayEvent(
                                    Data.parseInt(nextLine[BirthdayEvent.IDX_ID]),
                                    nextLine[BirthdayEvent.IDX_TITLE],
                                    Data.parseDate(nextLine[BirthdayEvent.IDX_START_DATE]),
                                    Data.parseDate(nextLine[BirthdayEvent.IDX_END_DATE]),
                                    nextLine[BirthdayEvent.IDX_DESCRIPTION],
                                    Data.parseInt(nextLine[BirthdayEvent.IDX_REPETITION]),
                                    Data.parseInt(nextLine[BirthdayEvent.IDX_GUESTS])
                                );
                        id = Data.parseInt(nextLine[BirthdayEvent.IDX_ID]);
                        break;
                    }
                    case Event.WORK_EVENT:{      no_fields = WorkEvent.NO_FIELDS;
                        e = new WorkEvent(
                                    Data.parseInt(nextLine[WorkEvent.IDX_ID]),
                                    nextLine[WorkEvent.IDX_TITLE],
                                    Data.parseDate(nextLine[WorkEvent.IDX_START_DATE]),
                                    Data.parseDate(nextLine[WorkEvent.IDX_END_DATE]),
                                    nextLine[WorkEvent.IDX_DESCRIPTION],
                                    Data.parseInt(nextLine[WorkEvent.IDX_REPETITION]),
                                    Data.parseInt(nextLine[WorkEvent.IDX_GUESTS])
                                );
                        id = Data.parseInt(nextLine[WorkEvent.IDX_ID]);
                        break;
                    }
                    case Event.SOCIAL_EVENT:{    no_fields = SocialEvent.NO_FIELDS;
                        e = new SocialEvent(
                                    Data.parseInt(nextLine[SocialEvent.IDX_ID]),
                                    nextLine[SocialEvent.IDX_TITLE],
                                    Data.parseDate(nextLine[SocialEvent.IDX_START_DATE]),
                                    Data.parseDate(nextLine[SocialEvent.IDX_END_DATE]),
                                    nextLine[SocialEvent.IDX_DESCRIPTION],
                                    Data.parseInt(nextLine[SocialEvent.IDX_REPETITION]),
                                    Data.parseInt(nextLine[SocialEvent.IDX_GUESTS])
                                );
                        
                        id = Data.parseInt(nextLine[SocialEvent.IDX_ID]);
                        break;
                    }
                    default : {
                        throw new Exception("Unknown event type in events.csv at line "+line);
                    }
                }
                if(nextLine.length != no_fields) {
                    throw new Exception("Wrong format for csv file events.csv at line "+line);
                } else {
                    events.add(e);
                    if(id > max_event_id)
                        max_event_id = id;
                }
            }
        } catch(IOException e) {
            throw new Exception("Something wrong with file reading... Please contact an administrator");
        }
    }
    
    private static void initSettings() throws Exception {
        try {
            File settings_file = new File("data/settings.csv");
            if(!settings_file.exists()) 
                settings_file.createNewFile();
            CSVReader reader = new CSVReader(new FileReader(settings_file));
            String [] nextLine;
            int line = 0;
            while ((nextLine = reader.readNext()) != null) {
                line++;
                if(nextLine.length != Setting.NO_FIELDS) {
                    throw new Exception("Wrong format for csv file settings.csv at line "+line);
                } else {
                    settings.add(new Setting(
                            nextLine[Setting.IDX_NAME],
                            nextLine[Setting.IDX_VALUE],
                            Data.parseInt(nextLine[Setting.IDX_TYPE]))
                        );
                }
            }
            //adding must-have pre-defined settings
            if(!settings.exists("locale"))
                addSetting("locale", "EN", Setting.TYPE_STRING);
        } catch(IOException e) {
            throw new Exception("Something wrong with file reading... Please contact an administrator: "+e.getMessage());
        }
    }
    
    public static int parseInt(String value) {
        return new Integer(value).intValue();
    }
    
    public static double parseDouble(String value) {
        return new Double(value).doubleValue();
    }
    
    public static Calendar parseDate(String value) { //the format is dd/mm/yyyy hh:mm:ss
        value = value.trim();
        int space = value.indexOf(" ");
        String date = value.substring(0, space);
        String time = value.substring(space+1);
        int sep1 = date.indexOf("/");
        int sep2 = date.indexOf("/", sep1+1);
        int sep3 = time.indexOf(":");
        int sep4 = time.indexOf(":", sep3);
        String day = date.substring(0, sep1);
        String month = date.substring(sep1+1, sep2);
        String year = date.substring(sep2+1);
        String hour = date.substring(0, sep3);
        String minute = date.substring(sep3+1, sep4);
        String second = date.substring(sep4+1);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Data.parseInt(day));
        cal.set(Calendar.MONTH, Data.parseInt(month));
        cal.set(Calendar.YEAR, Data.parseInt(year));
        cal.set(Calendar.HOUR_OF_DAY, Data.parseInt(hour));
        cal.set(Calendar.MINUTE, Data.parseInt(minute));
        cal.set(Calendar.SECOND, Data.parseInt(second));
        
        return cal;
    }
    
    public static String fromDate(Calendar date) {
        String day = date.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.LONG, new Locale(settings.get("locale")));
        String month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale(settings.get("locale")));
        String year = date.getDisplayName(Calendar.YEAR, Calendar.LONG, new Locale(settings.get("locale")));
        String hour = date.getDisplayName(Calendar.HOUR_OF_DAY, Calendar.LONG, new Locale(settings.get("locale")));
        String minute = date.getDisplayName(Calendar.MINUTE, Calendar.LONG, new Locale(settings.get("locale")));
        String second = date.getDisplayName(Calendar.SECOND, Calendar.LONG, new Locale(settings.get("locale")));
        
        return day+'/'+month+'/'+year+' '+hour+':'+minute+':'+second;
    }
    
//    public static void main(String args[]) {
//        parseDate("blabla");
//    }
    
}
