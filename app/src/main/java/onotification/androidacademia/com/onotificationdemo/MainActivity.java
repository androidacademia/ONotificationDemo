package onotification.androidacademia.com.onotificationdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitle,editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextMessage = (EditText)findViewById(R.id.editTextMessage);
    }

    public void showNotification(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        if (!(title.isEmpty() || message.isEmpty())){
            GenerateNotification generateNotification = new GenerateNotification(this);
            Intent intent = new Intent(this,MainActivity.class);
            generateNotification.sendNotification(title,message,intent);
        }
     }

}
