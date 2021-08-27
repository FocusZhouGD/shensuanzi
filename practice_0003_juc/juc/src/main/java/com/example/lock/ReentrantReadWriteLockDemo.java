package com.example.lock;

/**
 * // 读锁不互斥、写锁互斥
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        Count2 count2 = new Count2();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    count2.get();
                }
            }.start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    count2.put();
                }
            }.start();
        }
    }
    /**
     * Thread-1get begin
     * Thread-0get begin
     * Thread-2get begin
     * Thread-4get begin
     * Thread-3get begin
     * Thread-2get end
     * Thread-3get end
     * Thread-1get end
     * Thread-0get end
     * Thread-4get end
     * Thread-5put begin
     * Thread-5pet end
     * Thread-8put begin
     * Thread-8pet end
     * Thread-7put begin
     * Thread-7pet end
     * Thread-9put begin
     * Thread-9pet end
     * Thread-6put begin
     * Thread-6pet end
     */
}
