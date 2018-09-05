package example.com.firebasesavingdata;

import android.widget.ArrayAdapter;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class TrackDetails{

    String albumId;
    String trackAlbum;
    int trackYear;

    public TrackDetails() {
    }

    public TrackDetails(String albumId, String trackAlbum, int trackYear) {
        this.albumId = albumId;
        this.trackAlbum = trackAlbum;
        this.trackYear = trackYear;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getTrackAlbum() {
        return trackAlbum;
    }

    public int getTrackYear() {
        return trackYear;
    }

}
