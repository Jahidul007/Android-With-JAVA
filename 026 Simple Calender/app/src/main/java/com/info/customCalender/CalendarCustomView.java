package aliahmed.info.customCalender;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarCustomView extends LinearLayout  implements CalenderUtils {
    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    public ExpandableHeightGridView calendarGridView;
    private Button addEventButton;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM, yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private GridAdapter mAdapter;
    List<EventObjects> eventObjectses = new ArrayList<>();
    Calendar mcurrentDate;

    Button button,addEvent;
    LinearLayout eventLayout;

    EditText title , details;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,reference;

    String sDate;

    ListView listView;

    List<Event> artistList;


    public CalendarCustomView(Context context, List<EventObjects> eventObjectses) {
        super(context);
        this.eventObjectses = eventObjectses;
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        setCurrentDateClickEvent();
    }

    public CalendarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        setCurrentDateClickEvent();
        Log.d(TAG, "I need to call this method");
    }

    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calender_layout, this);
        previousButton = (ImageView) view.findViewById(R.id.previous_month);
        nextButton = (ImageView) view.findViewById(R.id.next_month);
        currentDate = (TextView) view.findViewById(R.id.display_current_date);
        calendarGridView = (ExpandableHeightGridView) view.findViewById(R.id.calendar_grid);
        calendarGridView.setExpanded(true);

        button = (Button) findViewById(R.id.buttonaddEvent);
        eventLayout = (LinearLayout) findViewById(R.id.liEvent);

        title = (EditText) findViewById(R.id.editTextTitle);
        details = (EditText) findViewById(R.id.editTextdetails);

        addEvent = (Button) findViewById(R.id.AddEvent);

        listView = (ListView) findViewById(R.id.listView);

        artistList = new ArrayList<>();

        System.out.println("SDate: "+ sDate);





        addEvent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventToList();

            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                eventLayout.setVisibility(VISIBLE);

                //button.setVisibility(INVISIBLE);

            }
        });


    }

    private void AddEventToList() {

        String eTitle  = title.getText().toString().trim();
        String eDetails  = details.getText().toString().trim();

        myRef = database.getReference("event").child(sDate);

        String dates = (eTitle+","+sDate);

        if (!eTitle.isEmpty() && !eDetails.isEmpty()){

            Event event = new Event(eTitle,eDetails,dates);

            myRef.child(dates).setValue(event);

            title.setText("");
            details.setText("");
            eventLayout.setVisibility(INVISIBLE);
            Toast.makeText(getContext(),"Add to List",Toast.LENGTH_SHORT).show();


        } else if(eTitle.isEmpty()){

            title.setError("Date is Empty");
            title.requestFocus();

        }else{

            details.setError("Title is Empty");
            details.requestFocus();

        }


    }

    private void setPreviousButtonClickEvent() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarCustomView.this.previousMonths();
//                cal.add(Calendar.MONTH, -1);
//                setUpCalendarAdapter();
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("event").child(sDate);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    Event artist = artistSnapshot.getValue(Event.class);

                    artistList.add(artist);
                }

                EventList adapter = new EventList(getContext(),artistList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setNextButtonClickEvent() {

        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarCustomView.this.nextMonth();
//                cal.add(Calendar.MONTH, 1);
//                setUpCalendarAdapter();
            }
        });
    }

    private void setCurrentDateClickEvent() {
        currentDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               mcurrentDate = Calendar.getInstance();
                final Calendar date;
                date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, final int dayOfMonth) {
                        cal.set(year, monthOfYear, dayOfMonth);
                        setUpCalendarAdapter();
                    }
                }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });

    }

    public String setGridCellClickEvents() {
        final String[] text = new String[1];
//        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                text[0] = "Clicked " + parent.getAdapter().getItem(position);
//                    Toast.makeText(context, "Clicked " + parent.getAdapter().getItem(position), Toast.LENGTH_LONG).show();
//            }
//        });
        return text[0];
    }


    public void setUpCalendarAdapter() {
        List<Date> dayValueInCells = new ArrayList<Date>();
        Calendar mCal = (Calendar) cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
        mAdapter = new GridAdapter(context, dayValueInCells, cal, eventObjectses);
        calendarGridView.setAdapter(mAdapter);

        reference = FirebaseDatabase.getInstance().getReference("event").child(sDate);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    Event artist = artistSnapshot.getValue(Event.class);

                    artistList.add(artist);
                }

                EventList adapter = new EventList(getContext(),artistList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void nextMonth() {
        cal.add(Calendar.MONTH, 1);
        setUpCalendarAdapter();
    }

    @Override
    public void previousMonths() {
        cal.add(Calendar.MONTH, -1);
        setUpCalendarAdapter();
    }

    public void setSelectedDates(EventObjects eventObjectses) {
        this.eventObjectses.add(eventObjectses);
        mAdapter.notifyDataSetChanged();
    }

    public void setRangesOfDate(List<EventObjects> eventObjectses) {
        this.eventObjectses = eventObjectses;
        setUpCalendarAdapter();
        mAdapter.notifyDataSetChanged();
    }

    public void removeSelectedDate(EventObjects eventObjectses) {
        for (int i = 0; i < this.eventObjectses.size(); i++) {
            if (this.eventObjectses.get(i).getDate().toString().equals(eventObjectses.getDate().toString())) {
                this.eventObjectses.remove(i);
                // TODO: 10/18/2017 here are some tricks
//                break;
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}

