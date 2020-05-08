package com.nulana.nchart3d.example.ColumnChart2D.dataSource;

import com.nulana.NChart.NChartValueAxis;
import com.nulana.NChart.NChartValueAxisDataSource;
import com.nulana.nchart3d.example.ColumnChart2D.ConstHelper;
import com.nulana.nchart3d.example.ColumnChart2D.DataStore;

import java.util.Date;

public class DateAxisDataSource implements NChartValueAxisDataSource {
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

    /**
     * Блин, ребят. Дату в double - это бесчеловечно...
     * @param v
     * @param nChartValueAxis
     * @return
     */
    @Override
    public String doubleToString(double value, NChartValueAxis nChartValueAxis) {
        if (value > DataStore.tradeDate.length || value < 0 || DataStore.tradeDate[(int)value] == null) {
            return "";
        }

        return ConstHelper.dateFormatClassic.format(DataStore.tradeDate[(int)value]);

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
        return ConstHelper.dateFormat.format(date);
    }
}
