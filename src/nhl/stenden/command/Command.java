package nhl.stenden.command;

/**
 * Command interface for implementing the Command pattern.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Each command implementation handles one specific action
 * - Open-Closed Principle (OCP): New commands can be added without modifying existing code
 * - Dependency Inversion Principle (DIP): High-level modules depend on this abstraction
 */
public interface Command
{
    void execute();
}
