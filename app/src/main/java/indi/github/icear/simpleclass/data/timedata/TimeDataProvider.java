package indi.github.icear.simpleclass.data.timedata;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import indi.github.icear.simpleclass.SimpleClassApplication;
import indi.github.icear.simpleclass.data.timedata.entity.TimeData;
import indi.github.icear.simpleclass.data.timedata.entity.TimeQuantum;
import indi.github.icear.simpleclass.data.timedata.util.DateDeserializer;
import indi.github.icear.simpleclass.data.timedata.util.TimeDeserializer;
import indi.github.icear.util.ConvertUtil;


/**
 * Created by icear on 2017/9/13.
 * 提供各个教室的上课时间表以及相关信息
 */
public class TimeDataProvider {
    /*
     * 由TimeManager管理器来管理时间表
     * 设定提供一个初始化函数用于提前从数据表中读取时间表
     * 再提供一个函数来读取指定的时间
     */
    private static String TAG = TimeDataProvider.class.getSimpleName();
    private TimeData timeData;

    /**
     * 建议通过{@link SimpleClassApplication#getTimeDataProvider()}函数获得实例
     * 以保证TimeDataProvider可以获得完整的生命周期
     *
     * @see SimpleClassApplication#getTimeDataProvider()
     */
    public TimeDataProvider() {
        try {
            init();
        } catch (IOException e) {
            Log.d(TAG, "Exception occur during init");
            e.printStackTrace();
            //理论上这里不会触发
        }
    }

    /**
     * 获得时间数据
     *
     * @return 时间数据
     */
    public Map<String, List<TimeQuantum>> getTimeList() {
        return timeData.getTimeSchedule();
    }

    /**
     * 初始化函数，用于从本地读取预置数据
     *
     * @throws IOException 预置数据读写异常
     */
    public void init() throws IOException {
        Log.i(TAG, "TimeDataProvider start init...");

        //Done 检查获取input流为空的情况->如果无法获取到时间数据，那就让它崩溃好了
        //取得文件
//        InputStream inputStream = Object.class.getResourceAsStream("/raw/timeschedule.json");
        InputStream inputStream = SimpleClassApplication.getApplication().getResources().openRawResource(indi.github.icear.simpleclass.R.raw.timeschedule);
        Log.d(TAG, "read \"timeschedule.json\" with inputStream: " + inputStream);
        if (inputStream == null) {
            Log.w(TAG, "the inputStream is null");
            //无法获取文件，这不合理，应该让程序崩溃
            throw new NullPointerException("the inputStream is null, maybe forgot to set time schedule data?");
        }

        //这里会抛出IOException，和文件读写有关
        String timeDataString = ConvertUtil.toString(inputStream);
        Log.d(TAG, "convert data to String:");
        Log.d(TAG, timeDataString);

        inputStream.close();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Time.class, new TimeDeserializer())//注册TimeDeserializer
                .registerTypeAdapter(Date.class, new DateDeserializer())//注册DateDeserializer
                .create();//创建Gson解析器
        timeData = gson.fromJson(timeDataString, new TypeToken<TimeData>() {
        }.getType());

        if (timeData != null) {
            Log.i(TAG, "successful loaded data.");
            Log.d(TAG, timeData.toString());
        } else {
            Log.w(TAG, "Oh no! We loaded nothing!");
            //如果读取到的数据为空，这不合理，应该让程序崩溃
            throw new ReadNoDataException("load nothing from json data file");
        }
    }

    /**
     * 获取指定的课程时间段
     *
     * @param classLocation 教室地点
     * @param index         课程位置
     * @return 仅在按照参数索引到目标时返回课程时间段，未查找到目标时返回Null
     * @exception DataNotProvidedException 当请求了没有被设定的time数据时抛出此异常
     */
    public TimeQuantum getClassTime(String classLocation, int index) throws DataNotProvidedException {
        if (index < 1) {
            throw new IllegalArgumentException("index: " + index);
        }

        if (timeData.getTimeSchedule().containsKey(classLocation)) {
            return timeData.getTimeSchedule().get(classLocation).get(index - 1);
        } else {
            throw new DataNotProvidedException("time data about " + classLocation + " had not been provided");
        }
    }

    /**
     * 获得学期第一天的日期
     *
     * @return 日期,
     */
    public Date getFirstSemesterDay() {
        return timeData.getSemesterStartDay();
    }

    /**
     * 获得时间数据的时区
     *
     * @return 时区
     */
    public String getTimeZone() {
        return timeData.getTimeZone();
    }

    private class ReadNoDataException extends RuntimeException {
        ReadNoDataException(String v) {
            super(v);
        }
    }

    public class DataNotProvidedException extends Exception {
        DataNotProvidedException(String v) {
            super(v);
        }
    }
}
