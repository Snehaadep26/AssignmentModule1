package com.example.bargraph2;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.bargraph2.PackageGraph.ApiClient;
import com.example.bargraph2.PackageGraph.ApiService;
import com.example.bargraph2.PackageGraph.GetDashBoardStandardResponse;
import com.example.bargraph2.PackageGraph.Summary;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {


    BarChart mChart;
    GetDashBoardStandardResponse getDashBoardStandardResponse;
    Retrofit retrofit;
    ApiService apiService;
    int summarySize;
    float[] valOne; //= {40, 40, 40, 82, 60,10};
    float[] valTwo;
    float[] valThree;
    ArrayList<Summary> summaryArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mChart = (BarChart) findViewById(R.id.barchart);
        apiInIt();
        getDashBoardStandard();
        GroupBarChart();

    }

    public void getDashBoardStandard() {
        Call<GetDashBoardStandardResponse> call = apiService.getDashBoardResponse();
        call.enqueue(new Callback<GetDashBoardStandardResponse>() {
            @Override
            public void onResponse(Call<GetDashBoardStandardResponse> call, Response<GetDashBoardStandardResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                }

                getDashBoardStandardResponse = response.body();
                summarySize = getDashBoardStandardResponse.getSummary().size();
                valOne=new float[summarySize];
                valTwo=new float[summarySize];
                valThree=new float[summarySize];
                ArrayList<Summary> summaryArrayList1 = (ArrayList<Summary>) getDashBoardStandardResponse.getSummary();
                Log.i("Inside Summary",summaryArrayList1.get(0).getDate());
                for (Summary s
                        : summaryArrayList1) {
                    // Log.i("overdue",String.valueOf(s.getOverdue()));
                    summaryArrayList.add(new Summary(s.getOverdue(),
                            s.getSubmitted(),
                            s.getDueForSubmission()));
                }
                for (int i=0;i<summarySize;i++)
                {
                    valOne[i]=summaryArrayList.get(i).getOverdue();
                    valTwo[i]=summaryArrayList.get(i).getSubmitted();
                    valThree[i]=summaryArrayList.get(i).getDueForSubmission();
                }
                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(0).getOverdue()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(0).getSubmitted()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(0).getDueForSubmission()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(1).getOverdue()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(1).getSubmitted()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(summaryArrayList.get(1).getDueForSubmission()),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<GetDashBoardStandardResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error in graph", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void apiInIt() {

        retrofit = ApiClient.getRetrofit();
        apiService = ApiClient.getApiService();
    }

    public void GroupBarChart() {

        mChart = findViewById(R.id.barchart);
        mChart.setDrawBarShadow(false);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setTouchEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);

//
//            mChart.getAxisLeft().setDrawGridLines(false);
//            mChart.getAxisRight().setDrawGridLines(false);
//            mChart.getAxisRight().setEnabled(false);
//            mChart.animateY(1500);
//            mChart.getLegend().setEnabled(false);
//            mChart.getAxisRight().setDrawLabels(false);
//            mChart.setDoubleTapToZoomEnabled(false);
//            mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        // empty labels so that the names are spread evenly
        String[] labels = {"", "11/07", "12/07", "13/07", "14/07", "15/07", "16/07", "17/07", ""};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(2);
        leftAxis.setLabelCount(8, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        mChart.getAxisRight().setEnabled(true);
        mChart.getLegend().setEnabled(true);
        ArrayList<BarEntry> barOne = new ArrayList<>();
        ArrayList<BarEntry> barTwo = new ArrayList<>();
        ArrayList<BarEntry> barThree = new ArrayList<>();

        barOne.add(new BarEntry(1,6));
        barOne.add(new BarEntry(2,valTwo));
        barOne.add(new BarEntry(3,2));
        barTwo.add(new BarEntry(1,valOne));
        barTwo.add(new BarEntry(2,9));
        barTwo.add(new BarEntry(3,valThree));
        barThree.add(new BarEntry(1,valOne));
        barThree.add(new BarEntry(2,7));
        barThree.add(new BarEntry(3,valThree));
        barOne.add(new BarEntry(1,1));
        barOne.add(new BarEntry(2,valTwo));
        barOne.add(new BarEntry(3,5));
        barTwo.add(new BarEntry(1,valOne));
        barTwo.add(new BarEntry(2,3));
        barTwo.add(new BarEntry(3,valThree));
        barThree.add(new BarEntry(1,4));
        barThree.add(new BarEntry(2,valTwo));
        barThree.add(new BarEntry(3,valThree));
        barOne.add(new BarEntry(1,valOne));
        barTwo.add(new BarEntry(2,2));
        barThree.add(new BarEntry(3,5));


//        for (int i = 0; i < valOne.length; i++) {
//            barOne.add(new BarEntry(i, valOne[i+1]));
//            barTwo.add(new BarEntry(i, valTwo[i]));
//            barThree.add(new BarEntry(i, valThree[i]));
//        }

        BarDataSet set1 = new BarDataSet(barOne, "barOne");
        set1.setColor(Color.argb(250, 152, 118, 230));
        BarDataSet set2 = new BarDataSet(barTwo, "barTwo");
        set2.setColor(Color.rgb(16, 137, 255));
        BarDataSet set3 = new BarDataSet(barThree, "barThree");
        set3.setColor(Color.RED);
        BarData barData=new BarData(set1,set2,set3);
        mChart.setData(barData);

        set1.setHighlightEnabled(true);
        set2.setHighlightEnabled(true);
        set3.setHighlightEnabled(true);
        set1.setDrawValues(true);
        set2.setDrawValues(true);
        set3.setDrawValues(true);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        BarData data = new BarData(dataSets);
        float groupSpace = 0.2f;
        float barSpace = 0f;
        float barWidth = 0.3f;
        // (barSpace + barWidth) * 2 + groupSpace = 1
        data.setBarWidth(barWidth);
        // so that the entire chart is shown when scrolled from right to left
        xAxis.setAxisMaximum(labels.length - 0.5f);
        mChart.setData(data);
        mChart.setScaleEnabled(false);
        mChart.setVisibleXRangeMaximum(7f);
        mChart.groupBars(1f, groupSpace, barSpace);
        mChart.invalidate();


        XYMarkerView mv = new XYMarkerView(this, new IndexAxisValueFormatter());
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv);

    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;


        mChart.getBarBounds((BarEntry) e);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);


        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }
}