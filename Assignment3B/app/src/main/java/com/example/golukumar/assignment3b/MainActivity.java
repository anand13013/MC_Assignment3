package com.example.golukumar.assignment3b;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String filename = "myexample.txt";
    private String filepath = "MyFileStorage";
    File myInternalFile;
    File myExternalFile;
    EditText textmess;
    TextView ireadmess, ereadmess;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory , filename);

        Button internalSave =
                (Button) findViewById(R.id.isave);


        Button internalread =
                (Button) findViewById(R.id.iread);


        Button internaldelete =
                (Button) findViewById(R.id.idelete);


        Button externalsave =
                (Button) findViewById(R.id.esave);

        Button externalread =
                (Button) findViewById(R.id.eread);
        Button externaldelete =
                (Button) findViewById(R.id.edelete);
        textmess = (EditText)findViewById(R.id.textmessage);

        ireadmess = (TextView)findViewById(R.id.imessage);
        ereadmess = (TextView)findViewById(R.id.emessage);
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            externalsave.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }

    }
    public void onInternalSave(View v)
    {

        FileOutputStream os = null;
        try {
            os = openFileOutput(filename,Context.MODE_PRIVATE);
            os.write(textmess.getText().toString().getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textmess.setText("");
        Toast.makeText(MainActivity.this, "myExample.txt saved to Internal Storage...", Toast.LENGTH_LONG).show();
        ireadmess.setText("");
    }
    public void onInternalRead(View v)
    {
        String myData = "";
        try {
            FileInputStream fis = new FileInputStream(myInternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ireadmess.setText(myData);
        if(myData.equals(""))
        {
            Toast.makeText(MainActivity.this, "myExample.txt file couldn't be found from Internal Storage...", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(MainActivity.this, "myExample.txt data retrieved from Internal Storage...", Toast.LENGTH_LONG).show();
        Log.v("name", String.valueOf(myInternalFile)) ;
    }
    public void onInternalDelete(View v)
    {

        boolean isfileDeleted = myInternalFile.delete();

        if(isfileDeleted)
        {
            Toast.makeText(MainActivity.this, "myExample.txt file has been deleted from Internal Storage...", Toast.LENGTH_LONG).show();

        }
    }
    public void onExternalSave(View v)
    {
        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(textmess.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textmess.setText("");
        Toast.makeText(MainActivity.this, "myExample.txt saved to External Storage..", Toast.LENGTH_LONG).show();
    }
    public void onExternalRead(View v)
    {
        String myData = "";
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ereadmess.setText(myData);
        if(myData.equals(""))
        {
            Toast.makeText(MainActivity.this, "myExample.txt file couldn't be found from External Storage...", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(MainActivity.this, "myExample.txt data retrieved from Internal Storage...", Toast.LENGTH_LONG).show();
        Log.v("name", String.valueOf(myExternalFile)) ;
    }
    public void onExternalDelete(View v)
    {
        boolean isdeleted = myExternalFile.delete();
        if(isdeleted)
        {
            Toast.makeText(MainActivity.this, "myExample.txt file has been deleted from external storage.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "myExample.txt file could not be found", Toast.LENGTH_LONG).show();
        }
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
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
