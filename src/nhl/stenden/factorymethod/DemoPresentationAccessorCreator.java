package nhl.stenden.factorymethod;

public class DemoPresentationAccessorCreator extends AccessorCreator
{
    @Override
    public Accessor create()
    {
        return new DemoPresentation();
    }
}
