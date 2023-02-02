package com.codetrik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;


public class Blocker<T> {
    Logger logger = LoggerFactory.getLogger("Blocker");
    private final BlockingQueue<T> blockingQueue;

    public Blocker(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void putDataInBlockingQueue(T data) {
        try {
            this.blockingQueue.put(data);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public T takeDataInBlockingQueue() throws InterruptedException {
        return this.blockingQueue.take();
    }
}
