package FileSystem.composite.models;

import java.util.List;

import FileSystem.composite.FileSystemInterface;
import FileSystem.exceptions.CannotPerformOperationException;

public class File implements FileSystemInterface {
    String name;
    float memory;

    public File(String name, float memory) {
        this.name = name;
        this.memory = memory;
    }

    @Override
    public void add(FileSystemInterface fsi) throws CannotPerformOperationException {
        throw new CannotPerformOperationException("Invalid Operation");
    }

    @Override
    public void remove(FileSystemInterface fsi) throws CannotPerformOperationException {
        throw new CannotPerformOperationException("Invalid Operation");
    }

    @Override
    public void ls(List<String> lsList) {
        lsList.add(getName());
    }

    @Override
    public void recursiveLs(List<String> recursiveLsList) {
        recursiveLsList.add(getName());
    }

    @Override
    public String getName() {
        return this.name;
    }

}
