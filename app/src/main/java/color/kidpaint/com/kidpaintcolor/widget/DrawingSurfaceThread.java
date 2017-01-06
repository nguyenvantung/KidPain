package color.kidpaint.com.kidpaintcolor.widget;

/**
 * Created by Tung Nguyen on 1/4/2017.
 */
public class DrawingSurfaceThread {
    private Thread internalThread;
    private Runnable threadRunnable;
    private boolean running;

    private class InternalRunnable implements Runnable {
        @Override
        public void run() {
            Thread.yield();
            internalRun();
        }
    }

    DrawingSurfaceThread(Runnable runnable) {
        threadRunnable = runnable;
        internalThread = new Thread(new InternalRunnable(),
                "DrawingSurfaceThread");
        internalThread.setDaemon(true);
    }

    private void internalRun() {
        while (running) {
            threadRunnable.run();
        }
    }

    /**
     * Starts the internal thread only if the thread runnable is not null, the
     * internal thread has not been terminated and the thread is not already
     * alive.
     */
    synchronized void start() {
        if (running || threadRunnable == null || internalThread == null
                || internalThread.getState().equals(Thread.State.TERMINATED)) {
            return;
        }
        // Log.d(PaintroidApplication.TAG, "DrawingSurfaceThread.start up");
        if (!internalThread.isAlive()) {
            running = true;
            internalThread.start();
            // Log.d(PaintroidApplication.TAG, "DrawingSurfaceThread.started");
        }
    }

    synchronized void stop() {
        running = false;
        if (internalThread != null && internalThread.isAlive()) {
            boolean retry = true;
            while (retry) {
                try {
                    internalThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }

    synchronized void setRunnable(Runnable runnable) {
        threadRunnable = runnable;
    }
}
