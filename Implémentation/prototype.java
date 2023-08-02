import System.SystemeRobotix;

public class Prototype {
    public static void main(String[] args) {

        SystemeRobotix s = SystemeRobotix.getInstance();
        System.out.println(s);
        SystemeRobotix s2 = SystemeRobotix.getInstance();

        System.out.println(s);
    }
}
