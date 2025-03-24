package nhl.stenden.factorymethod;

public class XMLAccessorCreator extends AccessorCreator
{
    @Override
    public Accessor create()
    {
        return new XMLAccessor();
    }
}
