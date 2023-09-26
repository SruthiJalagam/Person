package com.java.person.service;

import com.java.person.bean.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FileWriterService {

    @Value("${spring.application.fileDirectory}")
    public String fileDirectory;

    @Value("${spring.application.fileName}")
    public String fileName;

    @Value("${spring.application.fileExtension}")
    public String fileExtension;

    public String writeFile(ArrayList<Person>  personArrayList) {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = fileDirectory + fileName + timeStamp + fileExtension;

        try {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
            CSVFormat csvOutputFormat = CSVFormat.Builder.create().setHeader("FirstName", "LastName", "Age", "DOB", "Address", "PhoneNo").build();
            CSVPrinter csvPrinter = new CSVPrinter(writer, csvOutputFormat);

            for(Person personEach : personArrayList) {
                csvPrinter.printRecord(personEach.getFirstName(),personEach.getLastName(),personEach.getAge(),personEach.getDob(),personEach.getAddress(),personEach.getPhoneNo());
            }
            csvPrinter.flush();
            writer.close();

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }
}
