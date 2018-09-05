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

public class TrackDetailsList extends ArrayAdapter<TrackDetails>{
    private Activity context;
    private List<TrackDetails> tracks;


    public TrackDetailsList(Activity context, List<TrackDetails> tracks) {
        super(context, R.layout.layout_track_detail_list,tracks);
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_track_detail_list, null, true);

        TextView textViewTrackAlbum = (TextView) listViewItem.findViewById(R.id.textViewAlbumName);
        TextView textViewReleaseyear = (TextView) listViewItem.findViewById(R.id.textViewRelease);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.albumId);


        TrackDetails track = tracks.get(position);

        textViewTrackAlbum.setText(String.valueOf(track.getTrackAlbum()));
        textViewReleaseyear.setText(String.valueOf(track.getTrackYear()) );
        textViewId.setText(String.valueOf(track.getAlbumId()));

        return listViewItem;
    }
}
