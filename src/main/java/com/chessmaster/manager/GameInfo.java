package main.java.com.chessmaster.manager;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    private List<String> infoList;
    private int msgCount;
    private boolean suppressAdd;

    public GameInfo() {
        setSuppressAdd(false);
        msgCount = 0;
        infoList = new ArrayList<>();
    }

    public void addMsg(String msg) {
        if (isSuppressAdd()) {
            return;
        }
        msgCount++;
        if (infoList.size() >= 6) {
            infoList.remove(0);
        }
        infoList.add(msgCount + ". " + msg);
    }

    public List<String> getInfo() {
        return infoList;
    }

    public boolean isSuppressAdd() {
        return suppressAdd;
    }

    public void setSuppressAdd(boolean suppressAdd) {
        this.suppressAdd = suppressAdd;
    }
}
