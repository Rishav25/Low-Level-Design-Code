package FileSystem.composite.models;

import java.util.ArrayList;
import java.util.List;

import FileSystem.composite.FileSystemInterface;
import FileSystem.exceptions.CannotPerformOperationException;

public class Folder implements FileSystemInterface {
    String name;
    List<FileSystemInterface> fsiList;

    public Folder(String name) {
        this.name = name;
        this.fsiList = new ArrayList<>();
    }

    @Override
    public void add(FileSystemInterface fsi) throws CannotPerformOperationException {
        fsiList.add(fsi);
    }

    @Override
    public void remove(FileSystemInterface fsi) throws CannotPerformOperationException {
        if (fsiList.contains(fsi)) {
            fsiList.remove(fsi);
        } else {
            throw new CannotPerformOperationException("Folder does not contain the requested file/folder");
        }
    }

    @Override
    public void ls(List<String> lsList) {
        for (FileSystemInterface fsi : fsiList) {
            lsList.add(fsi.getName());
        }
    }

    @Override
    public void recursiveLs(List<String> recursiveLsList) {
        for (FileSystemInterface fsi : fsiList) {
            if (fsi instanceof Folder)
                recursiveLsList.add(fsi.getName());
            fsi.recursiveLs(recursiveLsList);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}
