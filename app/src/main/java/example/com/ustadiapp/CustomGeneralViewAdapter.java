package example.com.ustadiapp;

/**
 * Created by naeem on 12/12/17.
 */

//public class CustomGeneralViewAdapter {

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.AvailableListModel;
import example.com.ustadiapp.model.Day;
import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.GeneralCardModel;
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.model.User;

public class CustomGeneralViewAdapter extends RecyclerView.Adapter<CustomGeneralViewAdapter.CustomGeneralViewHolder> {



    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Duty> list;
    private FragmentManager manager;

    public CustomGeneralViewAdapter(Context context, ArrayList<Duty> list, FragmentManager manager){
        this.context=context;
        this.manager=manager;
        this.list=list;
        this.inflater= LayoutInflater.from(context);

    }

    @Override
    public CustomGeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomGeneralViewHolder(inflater.inflate(R.layout.general_card,parent,false));
    }

    @Override
    public void onBindViewHolder(CustomGeneralViewHolder holder, int position) {
        holder.time.setText(list.get(position).getSlot().getStartTime()+" "+list.get(position).getSlot().getEndTime());
        holder.date.setText(list.get(position).getDate().getDay()+"");
        holder.venu.setText(list.get(position).getVenu());
        holder.name.setText(list.get(position).getUser().getUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showDialog(View listView){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setCancelable(true).setView(listView).create().show();
    }
    public ArrayList<AvailableListModel> searchAvailables(int position){
        ArrayList<AvailableListModel> availables = new ArrayList<>();

        for (Duty item: list) {
            if (item.getSlot()!=list.get(position).getSlot()&& item.getUser()!=list.get(position).getUser()){
                availables.add(new AvailableListModel(item.getUser(),item.getVenu(),item.getSlot().getId()));
            }
        }
        return availables;
    }

    public class CustomGeneralViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView date;
        private TextView venu;
        private TextView name;
        private ImageView imageView;
        public CustomGeneralViewHolder(final View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.venu);
            date = (TextView) itemView.findViewById(R.id.date);
            name=(TextView)itemView.findViewById(R.id.name);
             imageView=(ImageView)itemView.findViewById(R.id.launcher);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popupMenu=new PopupMenu(context,v);
                    popupMenu.getMenuInflater().inflate(R.menu.radio_menu,popupMenu.getMenu());
                    popupMenu.show();
                    Log.i(LOG,"clicked: "+getAdapterPosition());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.available_radio:
//                                    itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.material_lime_a700));
                                    Log.i(LOG,"available radio clicked");
                                    return true;
                                case R.id.swap_radio:
                                    ListView listView = new ListView(context);
                                    AvailableListAdapter adapter = new AvailableListAdapter(context,searchAvailables(getAdapterPosition()),inflater);
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                                            Log.i(LOG,"userId: "+values.get(position).getUserId());
                                        }
                                    });
                                    showDialog(listView);

                                    Log.i(LOG,"swap radio clicked");
                                    return true;
                            }
                            return false;
                        }
                    });

                }
            });
        }

    }

}

