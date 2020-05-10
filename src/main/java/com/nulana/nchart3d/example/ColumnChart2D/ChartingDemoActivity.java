/**
 * Disclaimer: IMPORTANT:  This Nulana software is supplied to you by Nulana
 * LTD ("Nulana") in consideration of your agreement to the following
 * terms, and your use, installation, modification or redistribution of
 * this Nulana software constitutes acceptance of these terms.  If you do
 * not agree with these terms, please do not use, install, modify or
 * redistribute this Nulana software.
 *
 * In consideration of your agreement to abide by the following terms, and
 * subject to these terms, Nulana grants you a personal, non-exclusive
 * license, under Nulana's copyrights in this original Nulana software (the
 * "Nulana Software"), to use, reproduce, modify and redistribute the Nulana
 * Software, with or without modifications, in source and/or binary forms;
 * provided that if you redistribute the Nulana Software in its entirety and
 * without modifications, you must retain this notice and the following
 * text and disclaimers in all such redistributions of the Nulana Software.
 * Except as expressly stated in this notice, no other rights or licenses, 
 * express or implied, are granted by Nulana herein, including but not limited 
 * to any patent rights that may be infringed by your derivative works or by other
 * works in which the Nulana Software may be incorporated.
 *
 * The Nulana Software is provided by Nulana on an "AS IS" basis.  NULANA
 * MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE NULANA SOFTWARE OR ITS USE AND
 * OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 * IN NO EVENT SHALL NULANA BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 * MODIFICATION AND/OR DISTRIBUTION OF THE NULANA SOFTWARE, HOWEVER CAUSED
 * AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 * STRICT LIABILITY OR OTHERWISE, EVEN IF NULANA HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright (C) 2020 Nulana LTD. All Rights Reserved.
 */
 
package com.nulana.nchart3d.example.ColumnChart2D;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import com.nulana.NChart.NChartAreaSeries;
import com.nulana.NChart.NChartBrush;
import com.nulana.NChart.NChartBrushScale;
import com.nulana.NChart.NChartColumnSeries;
import com.nulana.NChart.NChartCrosshair;
import com.nulana.NChart.NChartCrosshairDelegate;
import com.nulana.NChart.NChartFont;
import com.nulana.NChart.NChartMargin;
import com.nulana.NChart.NChartPoint;
import com.nulana.NChart.NChartPointState;
import com.nulana.NChart.NChartSeries;
import com.nulana.NChart.NChartSeriesDataSource;
import com.nulana.NChart.NChartSolidColorBrush;
import com.nulana.NChart.NChartTooltip;
import com.nulana.NChart.NChartView;
import com.nulana.nchart3d.example.ColumnChart2D.dataSource.DateAxisDataSource;
import com.nulana.nchart3d.example.ColumnChart2D.dataSource.NumberAxisDataSource;
import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class ChartingDemoActivity extends Activity implements NChartSeriesDataSource, NChartCrosshairDelegate {
    private NChartView mNChartView;

//    private NChartPoint[] chartPoints;
    private NChartBrushScale brushScale;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        CSVReader csvReader;
        ;
        try {
            csvReader = new CSVReader(new InputStreamReader(getAssets().open("usd.csv")));
            List<String[]> usdIndexList = csvReader.readAll();

            double[] waPricesToday = new double[usdIndexList.size()];
            double[] waPriceTomorrow = new double[usdIndexList.size()];
            Date[] tradeDates = new Date[usdIndexList.size()];

            for (int i = 1; i < usdIndexList.size(); ++i) {
                waPricesToday[i] = Double.parseDouble(usdIndexList.get(i)[3]);
                waPriceTomorrow[i] = Double.parseDouble(usdIndexList.get(i)[5]);
                tradeDates[i] = ConstHelper.dateFormat.parse(usdIndexList.get(i)[2]);
            }

            DataStore.waPriceToday = waPricesToday;
            DataStore.waPriceTomorrow = waPriceTomorrow;
            DataStore.tradeDate = tradeDates;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка чтения csv", e);
        }

        setContentView(R.layout.main);
        mNChartView = (NChartView) findViewById(R.id.surface);
        loadView();
    }

    private void loadView() {

        mNChartView.getChart().setLicenseKey("FcsuLW6+CthCt1B2TfJdiT/J9NgozqBEg33qaUnXlLwH8y1iLe6WAQBizcGf47tJ+BXfxI9FbhYp1so5YCfE9llZ1WYNISzPFtX8+JyR0OJV9nVVF0rRZIfIKAdD1JRKHUqA58n8YCS0gXbO56+SGxE9H/5YsoF6c4P0fqi5tc75TwfHb6xBSNuhWWjlqJXhNQ7lQDEQkMcPHRVQ6lwuJB/xMowmFrN6+MXHDI+JZsqCBrKr/8OTkoLsQLNpYrAZt1XJw7jnt+d9GLQFb70tmK9y5rBQyZo14DcR5kyzHEnlai5RqtxV9nA/LOC5HVf9klxJmezuODb+GfXzxFauOz7nDmyFgbg8jqOrDwUv9ZLbVao/Yu2Vpim1qklbT/MsSrHEzSD+/OcAUZ7l6csoDtD+u0o8EGaQI39bclc9f3qNZsYRJfA4YRurT0OaHItjvJx+aCxjm4mlhb4HkVyyiEkRoZOZWlCHQ+FYrXe5TUG23tMq4muOhuBm/4RQFhFWlQ4evV2AAf9fHkLdgcUzssLoM+V0Svhm8sPLZpOl3vMq/F9UBUQNnNgNk59NaZWfkT2oLiqeCU2mkAjl6s+Y0snRpKlDJkrP3f20gFfD4iFsrtZuPitGdWBdOMlNvBdB4Ru+zzsKBXBU6refw5qeA/xAwzGq6VGHUgM+2X8EWuk=");
        mNChartView.getChart().getCartesianSystem().setMargin(new NChartMargin(10.0f, 10.0f, 10.0f, 20.0f));
        NChartColumnSeries todayUsdSeries = new NChartColumnSeries();
        NChartAreaSeries tommorowUsdSeries = new NChartAreaSeries();

        NChartBrush[] brushes = {
                new NChartSolidColorBrush(Color.CYAN),
                new NChartSolidColorBrush(Color.GREEN),
                new NChartSolidColorBrush(Color.YELLOW),
                new NChartSolidColorBrush(Color.RED),
        };

        Number[] numbers = {20, 40, 60};

        brushScale = new NChartBrushScale(brushes, numbers);

        // Create crosshair.
        NChartCrosshair cs = new NChartCrosshair();

        // Set color for crosshair's haris.
        cs.getXHair().setColor(Color.RED);
        cs.getYHair().setColor(Color.BLUE);

        // Set thickness for crosshair's hairs.
        cs.setThickness(2.0f);

        // Set value for crosshair. Alternatively it's possible to create crosshair connected to chart point using the method
        // new NChartCrosshair(int color, float thickness, NChartPoint targetPoint);
        cs.getXHair().setValue(300);
        cs.getYHair().setValue(20);

        // Set crosshair delegate to handle moving.
        cs.setDelegate(this);

        // Set crosshair snapping to X-Axis ticks.
        cs.getXHair().setSnapToMinorTicks(true);
        cs.getXHair().setSnapToMajorTicks(true);

        // Add tooltip to the hair parallel to Y-Axis.
        cs.getXHair().setTooltip(createTooltip());
        /*cs.setShouldJumpToTouch(true);
        cs.getYHair().setDraggable(false);*/
        updateTooltipText(cs.getXHair().getTooltip(), cs.getXHair().getValue(), cs);

        todayUsdSeries.tag = 1;
        todayUsdSeries.setDataSource(this);
        todayUsdSeries.setBrush(new NChartSolidColorBrush(Color.argb(255, 0, 255, 0)));

        tommorowUsdSeries.setBrush(new NChartSolidColorBrush(Color.argb(150, 0, 0, 232)));
        tommorowUsdSeries.setDataSource(this);
        tommorowUsdSeries.tag = 2;

        mNChartView.getChart().addSeries(todayUsdSeries);
        mNChartView.getChart().addSeries(tommorowUsdSeries);
        mNChartView.getChart().updateData();
        mNChartView.getChart().getCartesianSystem().getYAxis().setDataSource(new NumberAxisDataSource());
        mNChartView.getChart().getCartesianSystem().getXAxis().setDataSource(new DateAxisDataSource());
        mNChartView.getChart().getCartesianSystem().addCrosshair(cs);
//        mNChartView.getChart().playTransition(1.0f, false);
    }

    protected void onResume() {
        super.onResume();
        mNChartView.onResume();
    }

    protected void onPause() {
        super.onPause();
        mNChartView.onPause();
    }

    private void fillPointArray (double[] arr, NChartPoint[] chartPoints, NChartSeries series) {
        chartPoints[0] = new NChartPoint(NChartPointState.PointStateAlignedToXWithXY(0, arr[0]), series);
        for (int i = 1; i < arr.length; ++i) {
            chartPoints[i] = new NChartPoint(NChartPointState.PointStateAlignedToXWithXY(i, arr[i]), series);
        }

//        return chartPoints;
    }

    public NChartPoint[] points(NChartSeries series) {

        NChartPoint[] chartPoints;

        try {
            if (series.tag == 1) {
                chartPoints = new NChartPoint[DataStore.waPriceToday.length];
                fillPointArray(DataStore.waPriceToday, chartPoints, series);
            } else {
                chartPoints = new NChartPoint[DataStore.waPriceTomorrow.length];
                fillPointArray(DataStore.waPriceTomorrow, chartPoints, series);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Ошибка построения", ex);
        }

        return chartPoints;
    }

    public String name(NChartSeries series) {
        return "My series";
    }

    public Bitmap image(NChartSeries series) {
        return null;
    }
    
    public NChartPoint[] extraPoints(NChartSeries series) {
        return null;
    }

    @Override
    public void DidBeginMoving(NChartCrosshair nChartCrosshair) {

    }

    @Override
    public void DidMove(NChartCrosshair nChartCrosshair) {
        updateTooltipText(nChartCrosshair.getXHair().getTooltip(), nChartCrosshair.getXHair().getValue(), nChartCrosshair);
    }

    @Override
    public void DidEndMoving(NChartCrosshair nChartCrosshair) {
        updateTooltipText(nChartCrosshair.getXHair().getTooltip(), nChartCrosshair.getXHair().getValue(), nChartCrosshair);
    }

    NChartTooltip createTooltip() {
        NChartTooltip result = new NChartTooltip();

        result.setBackground(new NChartSolidColorBrush(Color.argb(255, 255, 255, 255)));
        result.getBackground().setOpacity(0.9);
        result.setPadding(new NChartMargin(10.0f, 10.0f, 10.0f, 10.0f));
        result.setBorderColor(Color.argb(255, 128, 128, 128));
        result.setBorderThickness(1);
        result.setFont(new NChartFont(16));

        return result;
    }

    void updateTooltipText(NChartTooltip tooltip, double value, NChartCrosshair chartCrosshair) {
        if (value > DataStore.tradeDate.length || value < 0) {
            return;
        }

        chartCrosshair.getYHair().setValue(DataStore.waPriceToday[(int)value]);

        tooltip.setText(String.valueOf(DataStore.waPriceToday[(int)value]));
        tooltip.setBackground(brushScale.brushForValue(DataStore.waPriceToday[(int)value]));
    }
}
