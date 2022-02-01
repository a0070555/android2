package uk.tees.ac.a0070555.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "uk.tees.ac.a0070555.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openMap(View view) {
        // Build the intent.
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

        // Try to invoke the intent.
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }
    }

    public void openWeb(View view) {
        Uri webpage = Uri.parse("https://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        // Try to invoke the intent.
        try {
            startActivity(webIntent);
        } catch (ActivityNotFoundException e) {
            messageBox("openWeb", e.getMessage());
        }
    }

    public void sendMail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rushdi20022@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email from Android");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is an email sent from android");

        // Try to invoke the intent.
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {

        }
    }

    public void calendar(View view) {
        // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2021, 0, 23, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2021, 0, 23, 10, 30);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");

        // Try to invoke the intent.
        try {
            startActivity(calendarIntent);
        } catch (ActivityNotFoundException e) {

        }
    }

    public void chooser(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        // Always use string resources for UI text.
        // This says something like "Share this photo with"
        String title = getResources().getString(R.string.chooser_title);

        // Create intent to show chooser
        Intent chooser = Intent.createChooser(intent, title);

        // Try to invoke the intent.
        try {
            startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    private void messageBox(String method, String message)
    {
        Log.d("EXCEPTION: " + method,  message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}