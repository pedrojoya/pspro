import java.util.Optional;

public class ProcessInfoMain {

    public static void main(String[] args) {
        Optional<String> currentUser = ProcessHandle.current().info().user();
        ProcessHandle.allProcesses()
                .filter(processHandle -> processHandle.info().user().equals(currentUser))
                .forEach(ProcessInfoMain::showProcess);
    }

    private static void showProcess(ProcessHandle processHandle) {
        ProcessHandle.Info info = processHandle.info();
        System.out.printf("PID: %d, USER: %s, CMD: %s%n",
                processHandle.pid(),
                info.user().orElse("none"),
                info.command().orElse("none")
        );
    }
}
