package midterm2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;

public class TasksManagerTest {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        System.out.println("Tasks reading");
        manager.readTasks(System.in);
        System.out.println("By categories with priority");
        manager.printTasks(System.out, true, true);
        System.out.println("-------------------------");
        System.out.println("By categories without priority");
        manager.printTasks(System.out, false, true);
        System.out.println("-------------------------");
        System.out.println("All tasks without priority");
        manager.printTasks(System.out, false, false);
        System.out.println("-------------------------");
        System.out.println("All tasks with priority");
        manager.printTasks(System.out, true, false);
        System.out.println("-------------------------");

    }
}

interface ITask{

    int getPriority();

    LocalDateTime getDeadline();

}
class Task implements ITask{
    String category;
    String name;
    String description;

    public Task(String category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' ;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public LocalDateTime getDeadline() {
        return LocalDateTime.MAX;
    }
}
abstract class TaskDecorator implements ITask{
  ITask task;

    public TaskDecorator(ITask task) {
        this.task = task;
    }
}
class PriorityTaskDecorator extends TaskDecorator {
    int priority;

    public PriorityTaskDecorator(ITask task, int priority) {
        super(task);
        this.priority = priority;
    }

    @Override
    public String toString() {
        return super.task.toString()+ ", priority=" + priority ;
    }

    @Override
    public int getPriority() {
        return priority;
    }
    @Override
    public LocalDateTime getDeadline() {
        return super.task.getDeadline();
    }
}
class DeadlineTaskDecorator extends TaskDecorator {
    LocalDateTime localDateTime;

    public DeadlineTaskDecorator(ITask task, LocalDateTime localDateTime) {
        super(task);
        this.localDateTime = localDateTime;
    }
    @Override
    public String toString() {
        return super.task.toString()+ ", deadline=" + localDateTime ;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    @Override
    public LocalDateTime getDeadline() {
        return localDateTime;
    }
}


class BuilderTaskFactory{
    static LocalDateTime time_after_which_no_build=LocalDateTime.of(2020,6,2,0,0,0);
    static ITask buildTask(String[] info) throws DeadlineNotValidException {
        ITask task=new Task(info[0], info[1], info[2]);
        if(info.length == 3){
            return task;
        }
        if (info.length >= 4) {
            task=tryAppendingDecorator(task,info[3]);
            if(info.length==4){
                return task;
            }
        }
        return tryAppendingDecorator(task,info[4]);
    }
    private static ITask tryAppendingDecorator(ITask task,String what_to_append) throws DeadlineNotValidException {
        try {
            int priority = Integer.parseInt(what_to_append);
            return new PriorityTaskDecorator(task, priority);

        } catch (Exception e) {
            LocalDateTime localDateTime = LocalDateTime.parse(what_to_append);
            if(localDateTime.isBefore(time_after_which_no_build) && !localDateTime.isEqual(time_after_which_no_build)){
                throw new DeadlineNotValidException(String.format("The deadline %s has already passed",localDateTime));
            }
                return new DeadlineTaskDecorator(task, localDateTime);
        }
    }
}
class DeadlineNotValidException extends Exception {
    public DeadlineNotValidException(String message) {
        super(message);
    }
}

class TaskManager {
    Map<String ,List<ITask>> tasks;

    public TaskManager() {
        tasks = new TreeMap<>();
    }

    public void readTasks (InputStream inputStream){
        Scanner scanner = new Scanner(new InputStreamReader(inputStream));
        while(scanner.hasNextLine()){
            //2.6.2020
            String[] info=scanner.nextLine().split(",");
            ITask task=null;
            try {
                task = BuilderTaskFactory.buildTask(info);
            } catch (DeadlineNotValidException e) {
                System.out.println(e.getMessage());
                continue;
            }
            tasks.putIfAbsent(info[0],new ArrayList<>());
            tasks.get(info[0]).add(task);
        }
    }


    public void printTasks(OutputStream os, boolean includePriority, boolean byCategory) {
        PrintWriter pw = new PrintWriter(os);

        Comparator<ITask> priorityComparator = Comparator.comparing(ITask::getPriority).thenComparing(task -> Duration.between(LocalDateTime.now(), task.getDeadline()));
        Comparator<ITask> simpleComparator = Comparator.comparing(task -> Duration.between(LocalDateTime.now(), task.getDeadline()));

        if (byCategory) {
            tasks.forEach((category, t) -> {
                pw.println(category.toUpperCase());
                t.stream().sorted(includePriority ? priorityComparator : simpleComparator).forEach(E-> pw.println(E.toString() +'}'));
            });
        }
        else {
            tasks.values().stream()
                    .flatMap(Collection::stream)
                    .sorted(includePriority ? priorityComparator : simpleComparator).
            forEach(E-> pw.println(E.toString() +'}'));
        }

        pw.flush();
    }
}