package com.lifei.designPattern.observer;

/**
 * @Author lifei
 * @Date 2021/8/1
 * @Description: 具体线程实现
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        // Task不允许为null
        if (task == null)
            throw new IllegalArgumentException("task is required");
        this.lifecycle = lifecycle;
        this.task = task;

    }

    // 使用final防止复写
    @Override
    public final void run() {
        // 当执行线程逻辑单元时候，分别触发相应事件
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception ex) {
            this.update(Cycle.ERROR, null, ex);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
