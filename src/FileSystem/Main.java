package FileSystem;

import java.util.ArrayList;
import java.util.List;

import FileSystem.composite.FileSystemInterface;
import FileSystem.composite.models.File;
import FileSystem.composite.models.Folder;

public class Main {
    public static void main(String[] args) {
        FileSystemInterface folder1 = new Folder("Folder1");
        FileSystemInterface folder2 = new Folder("Folder2");
        FileSystemInterface folder3 = new Folder("Folder3");

        FileSystemInterface file1 = new File("File1", 10004);
        FileSystemInterface file2 = new File("File2", 376847);
        FileSystemInterface file3 = new File("File3", 14141);
        FileSystemInterface file4 = new File("File4", 64647);
        FileSystemInterface file5 = new File("File5", 86886);

        folder1.add(file1);
        folder1.add(file2);
        folder1.add(file3);

        folder2.add(file4);
        folder2.add(file5);

        folder3.add(folder2);

        List<String> lsFolder1 = new ArrayList<>();
        folder1.ls(lsFolder1);

        List<String> lsFolder2 = new ArrayList<>();
        folder2.ls(lsFolder2);

        List<String> lsRecursiveFolder3 = new ArrayList<>();
        folder3.recursiveLs(lsRecursiveFolder3);

        System.out.println(lsFolder1);
        System.out.println(lsFolder2);
        System.out.println(lsRecursiveFolder3);
    }
}
