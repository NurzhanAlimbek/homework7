package Patterns;
import java.util.ArrayList;
import java.util.List;
public interface Mediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}



class ChatRoom implements Mediator {
    private List<User> users = new ArrayList<>();

    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату");
    }
}
abstract class User {
    protected Mediator mediator;
    protected String name;

    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class ChatUser extends User {
    public ChatUser(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " получает сообщение: " + message);
    }
}

class MediatorDemo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Алибек");
        User user2 = new ChatUser(chatRoom, "Нуржан");
        User user3 = new ChatUser(chatRoom, "Данияр");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.send("Привет всем!");
        user2.send("Привет, Алибек!");
    }
}
