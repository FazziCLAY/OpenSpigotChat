package ru.fazziclay.openspigotchat.debug;

import ru.fazziclay.openspigotchat.util.DebugUtils;
import ru.fazziclay.openspigotchat.util.Utils;

import java.util.Random;

public class Debugger {
    String fileName;
    String functionName;
    int random;

    public Debugger(String fileName, String functionName) {
        this.fileName       = fileName;
        this.functionName   = functionName;
        this.random         = Utils.getRandom(100, 999);

        DebugUtils.debug(String.format("[%s:%d] started %s", fileName, random, functionName));
    }

    public void args(String args) {
        DebugUtils.debug(String.format("[%s:%d] %s ARGS=%d", fileName, random, functionName, args));
    }

    public void log(String message) {
        DebugUtils.debug(String.format("[%s:%d] %s: %d", fileName, random, functionName, message));
    }
}
