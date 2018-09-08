package aliahmed.info.customCalender;

public class Event {

    String title;
    String detail;
    String date;

    public Event() {
    }

    public Event(String title, String detail,String date ) {
        this.title = title;
        this.detail = detail;
        this.date  = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getDate() {
        return date;
    }
}
