package com.example.golukumar.sqlite;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseProvider database;
    EditText ed1, ed2 ,ed3, ed4;
    Button badd, bdelete, bupdate, bview, bclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DatabaseProvider(this);
        ed1 = (EditText)findViewById(R.id.name);
        ed2 = (EditText)findViewById(R.id.rollno);
        ed3 = (EditText)findViewById(R.id.email);
        ed4 = (EditText)findViewById(R.id.sid);
        badd = (Button)findViewById(R.id.button);
        bupdate = (Button)findViewById(R.id.button2);
        bview = (Button)findViewById(R.id.button3);
        bdelete = (Button)findViewById(R.id.button4);
        bclear = (Button) findViewById(R.id.clear);
    }

    public void Add(View view)
    {
      boolean isInserted =  database.insertData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString());

        if(isInserted)
        {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
    }
    public void Clear(View view)
    {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }

    public void ViewALL(View view)
    {
        Cursor res = database.getAllData();
        if(res.getCount() == 0)
        {
            showMessage("ERROR", "Nothing Found");
        }


        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("SID : " + res.getString(0)+ "\n");
            buffer.append("Name : " + res.getString(1)+ "\n");
            buffer.append("Roll : " + res.getString(2)+ "\n");
            buffer.append("Email : " + res.getString(3)+ "\n");
        }
        showMessage("Data", buffer.toString());
    }
    public void DeleteS(View view)
    {
        Integer deletedRows = database.deleteData(ed4.getText().toString());
        if(deletedRows>0)
        {
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
        }

    }
    public void UpdateD(View view) {
        //ed4.setVisibility(View.VISIBLE);
        boolean isUpdate = database.updateData(ed4.getText().toString(),
                ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString());
        if (isUpdate == true){
            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }
    }
    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void Update(View view)
    {

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
