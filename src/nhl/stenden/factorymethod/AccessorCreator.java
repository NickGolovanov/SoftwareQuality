package nhl.stenden.factorymethod;

public abstract class AccessorCreator
{
    public Accessor getAccessor()
    {
        Accessor accessor = create();
        return accessor;
    }

    public abstract Accessor create();

}
