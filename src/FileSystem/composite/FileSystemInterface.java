package FileSystem.composite;

import java.util.List;

import FileSystem.exceptions.CannotPerformOperationException;

public interface FileSystemInterface {
    public void add(FileSystemInterface fsi) throws CannotPerformOperationException;

    public void remove(FileSystemInterface fsi) throws CannotPerformOperationException;

    public void ls(List<String> lsList);

    public void recursiveLs(List<String> rLsList);

    public String getName();
}
