package nhl.stenden.factorymethod;

/**
 * Abstract factory for creating presentation accessors.
 * 
 * SOLID Principles Implementation:
 * - Open-Closed Principle (OCP): New accessor types can be added by extending this class
 * - Dependency Inversion Principle (DIP): High-level modules depend on this abstraction
 * - Liskov Substitution Principle (LSP): All concrete creators can be used interchangeably
 */
public abstract class AccessorCreator
{
    public Accessor getAccessor()
    {
        Accessor accessor = this.create();
        return accessor;
    }

    public abstract Accessor create();

}
