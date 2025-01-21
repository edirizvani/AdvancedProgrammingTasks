import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            if(n!=5)
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs, (Object[]) params);

                    }
                }
            }
        }
    }

}
class ChatRoom{
    String name;
    TreeSet<String> users;

    public ChatRoom(String name) {
        this.name = name;
        users = new TreeSet<>();
    }
    public void addUser(String name) {
        users.add(name);
    }
    public void removeUser(String name) {
        users.remove(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        if(users.size()==0){
            sb.append("EMPTY\n");
            return sb.toString();
        }
        users.stream().forEach(user->sb.append(user+"\n"));
        return sb.toString();

    }

    boolean hasUser(String name) {
        return users.contains(name);
    }

    public String getName() {
        return name;
    }

    int numUsers(){
        return users.size();
    }

}

class ChatSystem {

    private TreeMap<String,ChatRoom> rooms;
    private TreeSet<String> users;

    public ChatSystem() {
        rooms = new TreeMap<String,ChatRoom>();
        users = new TreeSet<String>();
    }

    public void addRoom ( String room_name ) {
        rooms.put(room_name, new ChatRoom(room_name));
    }

    public void removeRoom ( String room_name ) {
        rooms.remove(room_name);
    }

    public ChatRoom getRoom(String room_name) throws NoSuchRoomException {
        if ( !rooms.containsKey(room_name)) throw new NoSuchRoomException(room_name);
        return rooms.get(room_name);
    }

    public String getUser(String user) throws NoSuchUserException {
        if ( !users.contains(user)) throw new NoSuchUserException(user);
        return user;
    }

    public void register(String user) throws NoSuchUserException {
        users.add(user);
        Comparator<ChatRoom> comparator=Comparator.comparing(ChatRoom::numUsers).thenComparing(ChatRoom::getName);

        List<ChatRoom> sorted=rooms.values().stream().sorted(comparator).limit(1).collect(Collectors.toList());

        if(!sorted.isEmpty()){
            sorted.get(0).addUser(getUser(user));
        }
    }

    public void registerAndJoin(String user , String room) throws NoSuchUserException, NoSuchRoomException{
        users.add(user);
        joinRoom(user, room);
    }

    public void joinRoom(String user , String room ) throws NoSuchUserException, NoSuchRoomException {
        getRoom(room).addUser(getUser(user));
    }

    public void leaveRoom(String user , String room ) throws NoSuchUserException, NoSuchRoomException {
        getRoom(room).removeUser(getUser(user));
    }

    public void followFriend(String user , String user2 ) throws NoSuchUserException, NoSuchRoomException {
        for ( Map.Entry<String,ChatRoom> cr : rooms.entrySet() )
            if ( cr.getValue().hasUser(getUser(user2)) ) joinRoom(getUser(user), cr.getKey());
    }
    public void unfollowFriend(String user , String user2 ) throws NoSuchUserException, NoSuchRoomException {
        for ( Map.Entry<String,ChatRoom> cr : rooms.entrySet()){
            if(!cr.getValue().hasUser(getUser(user2)) && cr.getValue().hasUser(getUser(user)) ){
                leaveRoom(getUser(user), cr.getKey());
            }
        }
    }


}

class NoSuchUserException extends Exception {


    public NoSuchUserException() {
        super("default");
    }

    public NoSuchUserException(String user) {
        super(user);
    }

}


class NoSuchRoomException extends Exception {

    public NoSuchRoomException() {
        super("default");
    }

    public NoSuchRoomException(String user) {
        super(user);
    }

}
