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

public class CartList extends ArrayAdapter<Cart>{
    private Activity context;
    private List<Cart> carts;


    public CartList(Activity context, List<Cart> carts) {
        super(context, R.layout.layout_cart_list,carts);
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_cart_list, null, true);

        TextView textViewTrack = (TextView) listViewItem.findViewById(R.id.textViewCart);

        Cart track = carts.get(position);

        int p = position+1;
        textViewTrack.setText(p+". " +String.valueOf(track.getName())+"\n");


        return listViewItem;
    }
}
