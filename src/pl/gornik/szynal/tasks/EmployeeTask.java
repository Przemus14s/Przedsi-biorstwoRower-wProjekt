package pl.gornik.szynal.tasks;

public abstract class EmployeeTask {
    private String taskName;

    public EmployeeTask(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }


    public abstract void executeTask();


    public void showTaskDetails() {
        System.out.println("Zadanie: " + taskName);
    }


    public abstract String getEmployeeStatus();
}
