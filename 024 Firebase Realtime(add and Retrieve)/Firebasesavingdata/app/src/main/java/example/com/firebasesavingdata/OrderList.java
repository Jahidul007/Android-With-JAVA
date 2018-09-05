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

public class OrderList extends ArrayAdapter<OrderMaterials> {
    private Activity context;
    private List<OrderMaterials> orderList;


    public OrderList(Activity context, List<OrderMaterials> orderList) {
        super(context, R.layout.layout_order_list,orderList);
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_order_list,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewArtist);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        OrderMaterials order = orderList.get(position);

        textViewName.setText(order.getProductName());
        textViewGenre.setText(order.getPhone()+"\n");

        return listViewItem;
    }
}
