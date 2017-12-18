package example.com.ustadiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;
import example.com.ustadiapp.model.AvailableListModel;
public class AvailableListAdapter extends ArrayAdapter<AvailableListModel>{
    private final Context context;
    private final ArrayList<AvailableListModel> values;
    private final LayoutInflater inflater;
    public AvailableListAdapter(@NonNull Context context, ArrayList<AvailableListModel> values,LayoutInflater inflater) {
        super(context, -1);
        this.context=context;
        this.values=values;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.popup_user,parent,false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.username);
        TextView venu = (TextView) convertView.findViewById(R.id.assigned_at);
        TextView slot = (TextView) convertView.findViewById(R.id.slot);
        name.setText(values.get(position).getUserName());
        venu.setText(values.get(position).getVenu());
        slot.setText(values.get(position).getSlot()+"");
        return convertView;
    }
    @Override
    public int getCount() {
        return values.size();
    }
    @Nullable
    @Override
    public AvailableListModel getItem(int position) {
        return values.get(position);
    }
}
