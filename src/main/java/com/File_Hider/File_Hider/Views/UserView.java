package com.File_Hider.File_Hider.Views;

import com.File_Hider.File_Hider.Exceptions.InvalidInputExeption;
import com.File_Hider.File_Hider.Models.Data;
import com.File_Hider.File_Hider.Repository.DataRepository;
import com.File_Hider.File_Hider.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class UserView {
    private String email;
    @Autowired
    DataRepository dataRepository;
    @Autowired
    DataService dataService;

    //Default constructor
    public UserView(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
        Function info:CommandLine for available functionality of the particular user
     */
    public void availableFunctionality() throws IOException, InvalidInputExeption {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Welcome" + this.email);
            System.out.println("Press 1 to show all hiden files");
            System.out.println("Press 2 to hide file");
            System.out.println("Press 3 to uhide the file");
            System.out.println("Press 4 to exit");

            int input = Integer.parseInt(sc.next());
            switch (input) {
                case 1 -> showAllHidenFiles();
                case 2 -> hideParticularFile();
                case 3 -> unHideParticularFile();
                case 4 -> System.exit(0);
            }
        } while (true);
    }

    /*
    Function info:Unhide particular file from db
    */
    private void unHideParticularFile() throws IOException {
        Scanner sc=new Scanner(System.in);
        //According to the email retrieve all the hiden files
        List<Data> dataList=dataRepository.findByemail(this.email);
        for(Data data:dataList){
            System.out.println("| ID | FILENAME |");
            System.out.println(data.getId()+"|"+data.getFileName());
        }

        //Taking input for the file id
        System.out.println("Please press the file id to be retrieved");
        Long fileInputId=sc.nextLong();
        Optional<Data> requestedFileData=dataRepository.findById(fileInputId);
        if(requestedFileData.isPresent()){
            dataService.unHideParticularFile(requestedFileData.get());
        }
    }

    /*
    Function info:Hide particular file based on the path mentioned
    */
    private void hideParticularFile() throws IOException {
        Scanner sc=new Scanner(System.in);

        List<Data> dataList=dataRepository.findByemail(this.email);
        System.out.println("Please enter the file path");
        String path=sc.nextLine();

        File f=new File(path);
        Data fileData=new Data(path,f.getName(),this.email);

        dataService.hideFile(fileData);
    }

    /*
    Function info:Show all the hidden files for the respective user
    */
    private void showAllHidenFiles() {
        List<Data> dataList=dataRepository.findByemail(this.email);
        for(Data data:dataList){
            System.out.println("| ID | FILENAME |");
            System.out.println(data.getId()+"|"+data.getFileName());
        }
    }
}
