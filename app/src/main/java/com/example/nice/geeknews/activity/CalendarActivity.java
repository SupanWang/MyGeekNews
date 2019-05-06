package com.example.nice.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.util.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

   public  CalendarDay mDate;
    private Toolbar tool_bar;
    private MaterialCalendarView view_calender;
    private TextView tv_calender_enter;
    private String TAG="CalendarActivity";
    private String date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
        initData();
    }

    private void initData() {
        view_calender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        view_calender.setOnDateChangedListener(new OnDateSelectedListener() {



            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
               mDate = date;
//                Log.d(TAG, "mDate: "+mDate);
                int year = mDate.getYear();
                int month = mDate.getMonth();
                int day = mDate.getDay();
                StringBuffer buffer = new StringBuffer();
                buffer.append(year);
                if (month>10){
                    buffer.append(month);
                }else {
                    buffer.append("0"+month);
                }
                if (day>10){
                    buffer.append(day);
                }else {
                    buffer.append("0"+day);
                }

                date1 = buffer.toString();
                Log.d(TAG, "initData: "+ date1);
            }
        });

    }

    private void initView() {
        tool_bar = (Toolbar) findViewById(R.id.tool_bar);
        view_calender = (MaterialCalendarView) findViewById(R.id.view_calender);
        tv_calender_enter = (TextView) findViewById(R.id.tv_calender_enter);

        tool_bar.setTitle("选择日期");
        setSupportActionBar(tool_bar);

        //返回按钮的监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_calender_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDate!=null) {
                    Intent intent = getIntent();
                    intent.putExtra("date" , date1);
                    setResult(23,intent);
                }
                finish();
            }
        });
    }



}
