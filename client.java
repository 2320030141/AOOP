public class SmartHomeClient {
    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();
        
        smartHome.activateMorningMode();
        smartHome.leaveHome();
        smartHome.activateNightMode();
    }
}
