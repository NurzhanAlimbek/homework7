package Patterns;
import java.util.Stack;

public interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() {
        System.out.println("Свет включен");
    }
    public void off() {
        System.out.println("Свет выключен");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта");
    }
    public void close() {
        System.out.println("Дверь закрыта");
    }
}

class Thermostat {
    private int temperature = 20;

    public void increase() {
        temperature++;
        System.out.println("Температура увеличена до " + temperature);
    }

    public void decrease() {
        temperature--;
        System.out.println("Температура уменьшена до " + temperature);
    }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class DoorOpenCommand implements Command {
    private Door door;
    public DoorOpenCommand(Door door) { this.door = door; }
    public void execute() { door.open(); }
    public void undo() { door.close(); }
}

class DoorCloseCommand implements Command {
    private Door door;
    public DoorCloseCommand(Door door) { this.door = door; }
    public void execute() { door.close(); }
    public void undo() { door.open(); }
}

class TempIncreaseCommand implements Command {
    private Thermostat thermostat;
    public TempIncreaseCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.increase(); }
    public void undo() { thermostat.decrease(); }
}

class TempDecreaseCommand implements Command {
    private Thermostat thermostat;
    public TempDecreaseCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.decrease(); }
    public void undo() { thermostat.increase(); }
}


class RemoteControl {
    private Stack<Command> history = new Stack<>();

    public void pressButton(Command command) {
        command.execute();
        history.push(command);
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("Нет команд для отмены");
        }
    }
}

class CommandDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command doorOpen = new DoorOpenCommand(door);
        Command doorClose = new DoorCloseCommand(door);
        Command tempUp = new TempIncreaseCommand(thermostat);
        Command tempDown = new TempDecreaseCommand(thermostat);

        RemoteControl remote = new RemoteControl();

        remote.pressButton(lightOn);
        remote.pressButton(doorOpen);
        remote.pressButton(tempUp);

        System.out.println("Отмена последней команды:");
        remote.pressUndo();
        remote.pressUndo();
        remote.pressUndo();
        remote.pressUndo();
    }
}
