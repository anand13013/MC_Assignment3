package anand.assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView na, ag, em;
    Button s;
    public static final String FileName = "SharePref" ;
    public static final String Name = "nameKey";
    public static final String Age = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        na=(TextView)findViewById(R.id.name);
        ag=(TextView)findViewById(R.id.age);
        em = (TextView) findViewById(R.id.email);
        s = (Button) findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
    }

    public void Save(View view) {
        String n = na.getText().toString();
        String e = em.getText().toString();
        String a = ag.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Age, a);
        editor.putString(Email, e);
        editor.commit();
        Toast.makeText(MainActivity.this,"Saved Information",Toast.LENGTH_LONG).show();
    }

    public void clear(View view) {
        na = (TextView) findViewById(R.id.name);
        ag = (TextView) findViewById(R.id.age);
        em = (TextView) findViewById(R.id.email);
        na.setText("");
        ag.setText("");
        em.setText("");
        Toast.makeText(MainActivity.this,"Cleared Information",Toast.LENGTH_LONG).show();

    }

    public void Get(View view) {
        na = (TextView) findViewById(R.id.name);
        ag = (TextView) findViewById(R.id.age);
        em = (TextView) findViewById(R.id.email);
        sharedpreferences = getSharedPreferences(FileName,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            na.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Age)) {
            ag.setText(sharedpreferences.getString(Age, ""));
        }
        if (sharedpreferences.contains(Email)) {
            em.setText(sharedpreferences.getString(Email, ""));

        }Toast.makeText(MainActivity.this,"Retrieved Information",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
