package smarthouse.smartronic2;


import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Room extends ActionBarActivity {

    HashMap<String, Integer> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        // Buttons

        Button saloonButton = (Button) findViewById(R.id.saloonTextView);
        Button kitchenButton = (Button) findViewById(R.id.kitchenTextView);
        Button bedRoomButton= (Button) findViewById(R.id.bedRoomTextView);
        Button kidRoomButton = (Button) findViewById(R.id.kidRoomTextView);

        buttonHandler(saloonButton, kitchenButton, bedRoomButton, kidRoomButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        menu.add("Logout");
        menu.add("Home");
        getMenuInflater().inflate(R.menu.menu_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonHandler(Button saloonButton, Button kitchenButton, Button bedRoomButton, Button kidRoomButton) {

        saloonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewStub stub = (ViewStub) findViewById(R.id.saloon_stub);
                Integer saloon = R.id.x;
                View view = findViewById(R.id.x);
                checkViewStubArray("saloon", saloon, view);
            }
        });

        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewStub stub = (ViewStub) findViewById(R.id.kitchen1);
                Integer kitchen = R.id.kitchen;
                View view = findViewById(R.id.kitchen);
                checkViewStubArray("kitchen", kitchen, view);
            }
        });

        bedRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bedroom = R.id.bedroom;
                View view = findViewById(R.id.bedroom);
                checkViewStubArray("living_room", bedroom, view);
            }
        });

        kidRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer kidroom = R.id.kidRoom;
                View view = findViewById(R.id.kidRoom);
                checkViewStubArray("kidroom", kidroom, view);
            }
        });
    }

    public HashMap<String, Integer> addViewStubArray(String name, Integer i) {

        user.put(name, i);
        //System.out.println(user.entrySet());
        //System.out.println(user.size());
        return user;
    }

    public HashMap<String, Integer> deleteViewStubArray(String name) {

        user.remove(name);
        //System.out.println("delete section entry set:" + user.entrySet());
        return user;
    }

    public HashMap<String, Integer> checkViewStubArray(String name, Integer i, View layout) {

        Iterator entries = user.entrySet().iterator();
        //System.out.println("Clicked layouts are:" + user.size());
        View view = findViewById(i);
        View view1 = findViewById(R.id.kitchen);

        if (!entries.hasNext()) {
            view1.setVisibility(View.GONE);
            addViewStubArray(name, i);
            view.setVisibility(View.VISIBLE);
        }

        while (entries.hasNext()) {
            //System.out.println("layout array has next!!");
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();

            if (key.equals(name) && value.equals(i)) {

                Toast.makeText(getApplicationContext(), "You are already in : " + key, Toast.LENGTH_SHORT).show();
                //System.out.println("Not First Click");
                System.out.println(user.entrySet());
                view.setVisibility(View.GONE);
                deleteViewStubArray(name);

            } else {

                layout = findViewById(value);
                layout.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                deleteViewStubArray(key);
                addViewStubArray(name, i);
                //System.out.println("Entry key is:" + key + "entry value is: " + value);
            }
        }
        return user;
    }

    /*public void goLamp1Saloon(View view) {
        System.out.println("in lamp2 saloon");
        ViewStub stub = (ViewStub) findViewById(R.id.lamp_on_off);
        View inflated = stub.inflate();
    }

    public void goLamp2Saloon(View view) {
        System.out.println("in lamp1 saloon");
        ViewStub stub = (ViewStub) findViewById(R.id.lamp2_on_off);
        View inflated = stub.inflate();
    }

    public void goTvSaloon(View view) {
        System.out.println("in tv saloon");
        ViewStub stub = (ViewStub) findViewById(R.id.tv_on_off);
        View inflated = stub.inflate();
    }

    public void goCurtainSaloon(View view) {
        System.out.println("in curtain saloon");
        ViewStub stub = (ViewStub) findViewById(R.id.curtain_settings);
        View inflated = stub.inflate();

    }

    public void onOffToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            System.out.println("excuse me while i am on clicked");
        } else {
            System.out.println("excuse me while i am on clicked");

        }
    }*/


}
