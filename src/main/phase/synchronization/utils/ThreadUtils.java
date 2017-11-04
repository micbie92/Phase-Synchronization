package main.phase.synchronization.utils;

import org.apache.log4j.Logger;

/**
 * Created by Michał Bielecki on 22.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class ThreadUtils {

    private static Logger LOGGER = Logger.getLogger(ThreadUtils.class);

    public static final String PROCESS_NAME_THREAD = "simThread";

    public static final Thread getProcessThread(){
        Thread processThread = getThreadByName(PROCESS_NAME_THREAD);
        return processThread;
    }

    private static final Thread getThreadByName(String threadName) {
        return Thread.getAllStackTraces().keySet().parallelStream()
                .filter(k -> threadName.equals(k.getName()))
                .findAny().orElse(null);
    }

}
