package indi.github.icear.simpleclass.data.historydata.entity;

import java.util.Calendar;
import java.util.List;

import indi.github.icear.simpleclass.data.calendardata.entity.EventInfo;

/**
 * Created by icear on 2017/11/11.
 * 储存历史记录数据
 */

public class HistoryData {
    private int protocol;//操作使用的协议
    private Object argv1;//备用参数1
    private Object argv2;//备用参数2
    private Calendar createTime;//创建时间
    private List<EventInfo> eventInfoList;//插入的eventInfo数据

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public Object getArgv1() {
        return argv1;
    }

    public void setArgv1(Object argv1) {
        this.argv1 = argv1;
    }

    public Object getArgv2() {
        return argv2;
    }

    public void setArgv2(Object argv2) {
        this.argv2 = argv2;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public List<EventInfo> getEventInfoList() {
        return eventInfoList;
    }

    public void setEventInfoList(List<EventInfo> eventInfoList) {
        this.eventInfoList = eventInfoList;
    }
}
