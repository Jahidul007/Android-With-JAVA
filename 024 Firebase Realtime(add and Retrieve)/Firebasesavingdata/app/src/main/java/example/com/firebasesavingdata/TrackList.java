package example.com.firebasesavingdata;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class TrackList extends ArrayAdapter<Track> {

    private Activity context;
    private List<Track> tracks;


    public TrackList(Activity context, List<Track> tracks) {
        super(context, R.layout.layout_track_list,tracks);
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_track_list, null, true);

        TextView textViewTrack = (TextView) listViewItem.findViewById(R.id.textViewTrackName);
        TextView textViewRating = (TextView) listViewItem.findViewById(R.id.textViewTrackRating);

        Track track = tracks.get(position);

        textViewTrack.setText(String.valueOf(track.getTrackName()));
        textViewRating.setText(String.valueOf(track.getTrackRating()) );

        return listViewItem;
    }
}
