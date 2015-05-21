package smarthouse.smartronic2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Index extends ActionBarActivity {

    String selectedOption;
    String[] Logout = {"Yes", "No"};

    public final static String EXTRA_MESSAGE = "extra message!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
        menu.add(R.string.home);
        menu.add(R.string.logout);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        Button b1 = (Button) findViewById(R.id.settings);
        b1.setTypeface(font);

        Typeface font2 = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        Button b2 = (Button) findViewById(R.id.missions);
        b2.setTypeface(font2);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        CharSequence option =item.getTitle();
        String homeString = getResources().getString(R.string.home);
        String logoutString = getResources().getString(R.string.logout);

        if (item.hasSubMenu() == false) {
            if (option == logoutString) {
                this.displaySelectedItemMessage("Logout", this.Logout);
            } else if (option == homeString) {
                Intent intent = new Intent(getApplicationContext(), Index.class);
                startActivity(intent);
            }
            else {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "an error occurred!", Toast.LENGTH_SHORT);
                toast.show();
            }
            selectedOption = item.getTitle().toString();
        }
        return true;
    }


    private void displaySelectedItemMessage(String title, String[] item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(item, (android.content.DialogInterface.OnClickListener) this);
        builder.show();
    }

    // is called when user clicks on Security
    public void goSecurity(View view) {

        //System.out.println("in security method");
        Intent intent = new Intent(Index.this, Security.class);
        String message = "going to Security";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // is called when user clicks on Room
    public void goRooms(View view) {
        Intent intent = new Intent(Index.this, Room.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public static String getExtraMessage() {
        return EXTRA_MESSAGE;
    }
}
