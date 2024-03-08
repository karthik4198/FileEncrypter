package com.File_Hider.File_Hider.Services;

import com.File_Hider.File_Hider.Models.Data;
import com.File_Hider.File_Hider.Repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;
    /*
    Function info:Hiding the file from particular location and saving it in the db
     */
    public void hideFile(Data fileData) throws IOException {
        File f=new File(fileData.getPath());
        FileReader fr=new FileReader(f);
        byte[] bytes = convertFileReaderToByteArray(fr);
        fileData.setFileContent(bytes);
        fr.close();
        f.delete();
        dataRepository.save(fileData);
        System.out.println("File hidden!!From the particular location");
    }
    /*
    Function info:Converting the data into a byte[] array
     */
    public static byte[] convertFileReaderToByteArray(FileReader fileReader) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        int bytesRead;
        while ((bytesRead = fileReader.read(buffer, 0, bufferSize)) != -1) {
            baos.write(new String(buffer, 0, bytesRead).getBytes());
        }
        return baos.toByteArray();
    }

    /*
    Function info:UnHide particular File by ID
     */
    public void unHideParticularFile(Data data) throws IOException {
        FileOutputStream fos=new FileOutputStream(data.getPath());
        fos.write(data.getFileContent());
        fos.close();
        //Deleting entry from db
        dataRepository.deleteById(data.getId());
        System.out.println("File Unhiden!! please find it in the respective path");
    }

}
