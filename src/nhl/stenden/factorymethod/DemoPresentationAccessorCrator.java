package nhl.stenden.factorymethod;

public class DemoPresentationAccessorCrator extends AccessorCreator
{
    @Override
    public Accessor create()
    {
        return new DemoPresentation();
    }
}
