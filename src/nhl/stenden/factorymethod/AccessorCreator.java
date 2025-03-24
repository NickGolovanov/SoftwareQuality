package nhl.stenden.factorymethod;

public abstract class AccessorCreator
{
    public Accessor getAccessor()
    {
        Accessor accessor = this.create();
        return accessor;
    }

    public abstract Accessor create();

}
