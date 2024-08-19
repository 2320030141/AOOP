public class SmartHomeFacade {
    private Thermostat thermostat;
    private SmartLock smartLock;
    private SecurityCamera camera;

    public SmartHomeFacade() {
        this.thermostat = new Thermostat();
        this.smartLock = new SmartLock();
        this.camera = new SecurityCamera();
    }

    public void activateMorningMode() {
        System.out.println("Activating Morning Mode...");
        thermostat.setTemperature(22);
        smartLock.unlock();
        camera.turnOff();
    }

    public void activateNightMode() {
        System.out.println("Activating Night Mode...");
        thermostat.setTemperature(18);
        smartLock.lock();
        camera.turnOn();
    }

    public void leaveHome() {
        System.out.println("Activating Leave Home Mode...");
        thermostat.setTemperature(16);
        smartLock.lock();
        camera.turnOn();
        camera.record();
    }
}
