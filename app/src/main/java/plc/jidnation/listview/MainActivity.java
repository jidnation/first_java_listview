package plc.jidnation.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<AdaptarItem> items = new ArrayList<>();
    MyCustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        items.add(new AdaptarItem( R.drawable.anime3,"Checking", "What are we doing here, please?"));
        items.add(new AdaptarItem( R.drawable.anime4,"Update", "Have you completed the task?"));
        items.add(new AdaptarItem( R.drawable.anime5,"Notification", "Reminder: Meeting at 3 PM."));
        items.add(new AdaptarItem( R.drawable.anime3,"Alert", "System maintenance scheduled tonight."));
        items.add(new AdaptarItem( R.drawable.anime4,"Inquiry", "Can you provide the latest report?"));
        items.add(new AdaptarItem( R.drawable.anime3,"Greeting", "Hello! How's everything going?"));
        items.add(new AdaptarItem( R.drawable.anime5,"Follow-Up", "Please confirm your availability."));
        items.add(new AdaptarItem( R.drawable.anime4,"Request", "Kindly review the attached document."));
        items.add(new AdaptarItem( R.drawable.anime3,"Feedback", "Your opinion is important to us!"));
        items.add(new AdaptarItem( R.drawable.anime5, "Reminder", "Don't forget to submit your timesheet."));

        ListView listView = findViewById(R.id.listViewer);
        adapter = new MyCustomerAdapter(items);

        listView.setOnItemClickListener ((parent, view, position, id) -> {
            AdaptarItem cardInfo = items.get(position);
            LayoutInflater myLayoutInflater = getLayoutInflater();
            View myView = myLayoutInflater.inflate(R.layout.list_details_layout, null);

            TextView titleText = myView.findViewById(R.id.itemInfoTitle);
            TextView idText = myView.findViewById(R.id.itemInfoId);
            TextView idDescription = myView.findViewById(R.id.itemInfoDescription);

            titleText.setText(cardInfo.title);
            idText.setText("ID: " + cardInfo.id);
            idDescription.setText(cardInfo.subTitle);

            Toast toast = new Toast(getApplicationContext());
            toast.setView(myView);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

        });

        listView.setAdapter(adapter);
    }

private class MyCustomerAdapter extends BaseAdapter {
        public ArrayList<AdaptarItem> listItems;
        public MyCustomerAdapter(ArrayList<AdaptarItem> listItems) {
            this.listItems = listItems;
        }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = getLayoutInflater();
        View myView;
        myView = myInflater.inflate(R.layout.list_layout, null);

        AdaptarItem itemInfo  = listItems.get(position);

        // Assign values from the item to the corresponding view
        ImageView itemID = myView.findViewById(R.id.itemImage);
        itemID.setImageResource(itemInfo.id);

        TextView title = myView.findViewById(R.id.listTitle);
        title.setText(itemInfo.title);

        TextView subTitle = myView.findViewById(R.id.listSubTitle);
        subTitle.setText(itemInfo.subTitle);

        return myView;
    }
}
}
