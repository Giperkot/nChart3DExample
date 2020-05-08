package com.nulana.nchart3d.example.ColumnChart2D.dataSource;

import com.nulana.NChart.NChartValueAxis;
import com.nulana.NChart.NChartValueAxisDataSource;
import com.nulana.nchart3d.example.ColumnChart2D.ConstHelper;

import java.util.Date;

public class NumberAxisDataSource implements NChartValueAxisDataSource {
    @Override
    public String name(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public Number min(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public Number max(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public Number step(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public String[] ticks(NChartValueAxis nChartValueAxis) {
        return new String[0];
    }

    @Override
    public String[] extraTicks(NChartValueAxis nChartValueAxis) {
        return new String[0];
    }

    @Override
    public Number length(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public String doubleToString(double v, NChartValueAxis nChartValueAxis) {
        return String.valueOf((int) v);//String.format("%d", v);
    }

    @Override
    public Date minDate(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public Date maxDate(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public Number dateStep(NChartValueAxis nChartValueAxis) {
        return null;
    }

    @Override
    public String dateToString(Date date, double v, NChartValueAxis nChartValueAxis) {
        return null;
    }
}
