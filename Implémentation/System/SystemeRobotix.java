package System;

public class SystemeRobotix {
   
    /* it implements the singleton pattern because I want it to be unique */
   
    private static SystemeRobotix instance;

    private SystemeRobotix(){
    }

    public static SystemeRobotix getInstance(){
        if(SystemeRobotix.instance == null){
            SystemeRobotix.instance = new SystemeRobotix();
        }
    
        return SystemeRobotix.instance;
    }
}
