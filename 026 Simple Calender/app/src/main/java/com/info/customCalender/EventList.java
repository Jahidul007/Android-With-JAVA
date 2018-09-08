package aliahmed.info.customCalender;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EventList extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> artistList;


    public EventList(Context context, List<Event> artistList) {
        super( context, R.layout.layout_list,artistList);
        this.context = (Activity) context;
        this.artistList = artistList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_list,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewDetail);

        Event artist = artistList.get(position);

        textViewName.setText(artist.getDate());
        textViewGenre.setText(artist.getDetail());

        return listViewItem;
    }


}
