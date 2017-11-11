package indi.github.icear.simpleclass.data.historydata;

import java.util.List;

import indi.github.icear.simpleclass.data.historydata.entity.HistoryData;

/**
 * Created by icear on 2017/11/11.
 * 历史记录数据提供管理器
 * 用于提供管理历史记录（即创建事件和日历等操作的历史记录）的API
 */

public class HistoryDataProivder {

    private List<HistoryData> historyDataList;

    /*
        将历史数据储存到本地
        返回储存结果
     */
    public boolean saveHistoryDataToLocal() {
        return false;
    }

    /*
        从本地读取历史数据
        返回储存结果
     */
    public boolean readHistoryDataFromLocal() {
        return false;
    }

    public List<HistoryData> getHistoryDataList() {
        return historyDataList;
    }

    public void setHistoryDataList(List<HistoryData> historyDataList) {
        this.historyDataList = historyDataList;
    }

    public class Protocol {
        public static final int EVENT_WRITE = 850;
    }
}
