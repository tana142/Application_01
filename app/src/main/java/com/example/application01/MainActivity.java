package com.example.application01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textview_time ,textview_day, textview_weekin, textview_tagin, txv_save;
    Spinner spinner;
    Button btn_tune;
    int year, month, day, hour, minute;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();
        setTime_Day();
        final String[] strings = new String[]{"Family", "Game", "Android", "VTC", "Friend", "FPT"};
        final boolean[]checks = new boolean[]{true, false, false, false, false, false};
        final String[] strings1 = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" , "Sunday"};
        final  boolean[] checks1 = new boolean[] {true, true , false, false, false, false, false};
        final  String[] stringTune = new String[]{"Nexus Tune", "Winphone Tune", "Peep Tune", "Nokia Tune", "Etc"};
        final  boolean[] booleanTune = new boolean[]{false, false, false, false, false};
        //spinner
        final List<String> list = new ArrayList<String>();
        list.add("Work");
        list.add("Friend");
        list.add("Family");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list  );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        // Tune
        btn_tune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //
                PopupMenu popupMenu = new PopupMenu(MainActivity.this , btn_tune);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Choose Tune:");
                        switch (menuItem.getItemId()){
                            case R.id.item1:{

                                break;
                            }
                            case R.id.item2:{

                                builder.setSingleChoiceItems(stringTune, -1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //code
                                    }
                                });
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //code
                                    }
                                });
                                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //code
                                    }
                                });
                                break;

                            }
                        }
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // dialogTimePicker

        textview_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        set_Time_format(i,i1);
                        hour = i;
                        minute = i1;
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
        // dialogDatePicker
        textview_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog  = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        set_Date_Format(i,i1,i2);
                        year = i; month = i1; day = i2;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        //dialog_tag
        textview_tagin.setText(strings[0]);
        textview_tagin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose tags:");
                builder.setMultiChoiceItems(strings, checks, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checks[i] = b;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = "";
                        for(int j = 0 ; j < checks.length ;  j++){
                            if(checks[j] ){
                                if(s==""){
                                    s = strings[j];
                                }
                                else{
                                    s += ", " + strings[j];
                                }
                            }
                        }
                        textview_tagin.setText( s.toString());
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //dialog_week
        textview_weekin.setText(swichCase_week(0));
        textview_weekin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose week:");
                builder.setMultiChoiceItems(strings1, checks1, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checks1[i] = b;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = "";
                        for(int j = 0; j < checks1.length ; j++){
                            if(checks1[j]){
                                if(s==""){
                                    s = swichCase_week(j);
                                }
                                else{
                                    s += ", "+ swichCase_week(j);
                                }
                            }
                        }
                        textview_weekin.setText(s.toString());
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // code
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        //save
        txv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,txv_save);
                popupMenu.inflate(R.menu.popup_menu_save);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.item3){
                            MainActivity.this.finish();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }
    public String swichCase_week(int i ){

        String s = "";
        switch (i){
            case 0:{
                s = "Mon";
                break;
            }
            case 1: {
                s = "Tue";
                break;
            }
            case 2: {
                s = "Wed";
                break;
            }
            case 3: {
                s = "Thu";
                break;
            }
            case 4: {
                s = "Fri";
                break;
            }
            case 5: {
                s = "Sat";
                break;
            }
            case 6: {
                s = "Sun";
                break;
            }
        }
        return s;
    }
    public void AnhXa(){
        textview_time = findViewById(R.id.textview_time);
        textview_day = findViewById(R.id.textview_day);
        textview_weekin = findViewById(R.id.textview_weekin);
        textview_tagin = findViewById(R.id.textview_tagin);
        spinner = (Spinner) findViewById(R.id.spinner_001);
        btn_tune =  (Button) findViewById(R.id.btn_tune);
        txv_save = (TextView) findViewById(R.id.textview_2);
    }
    public  void setTime_Day(){
        Calendar calendar = Calendar.getInstance();
         year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        set_Date_Format(year , month, day);
        set_Time_format(hour,minute);
    }
    public void set_Date_Format(int year,int month, int day){
        month +=1;
        if(day < 10 && month <10){
            String date = "0" + day + "/0" + month + "/"+year;
            textview_day.setText(date.toString());
        }
        else if(day < 10){
            String date = "0" + day + "/" + month + "/"+year;
            textview_day.setText(date.toString());
        }
        else if(month <10){
            String date = "" + day + "/0" + month + "/"+year;
            textview_day.setText(date.toString());
        }
        else{
            String date = "" + day + "/" + month + "/"+year;
            textview_day.setText(date.toString());
        }
    }
    public void set_Time_format(int hour , int minute){
        if(minute <10 && hour < 10){
            String times = "0" + hour + ":0"+ minute;
            textview_time.setText(times.toString());
        }
        else if(minute <10){
            String times = "" + hour + ":0"+ minute;
            textview_time.setText(times.toString());
        }
        else if( hour < 10){
            String times = "0" + hour + ":"+ minute;
            textview_time.setText(times.toString());
        }
        else {
            String times = "" + hour + ":"+ minute;
            textview_time.setText(times.toString());
        }
    }
}