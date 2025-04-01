package nhl.stenden.observer;

public class SystemApplicationExit implements ApplicationExit
{
    @Override
    public void exit(int status)
    {
        System.exit(status);
    }
}
