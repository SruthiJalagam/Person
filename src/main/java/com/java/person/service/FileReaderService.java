package com.java.person.service;

import com.java.person.bean.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileReaderService {

    @Value("${spring.application.fileDirectory}")
    public String fileDirectory;

    @Value("${spring.application.fileName}")
    public String fileName;

    @Value("${spring.application.fileExtension}")
    public String fileExtension;


    public List<Person> readCSVFile() {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = fileDirectory + fileName + timeStamp + fileExtension;
        List<Person> personList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                String firstName = csvRecord.get("FirstName");
                String lastName = csvRecord.get("LastName");
                String age = csvRecord.get("Age");
                String dob = csvRecord.get("DOB");
                String address = csvRecord.get("Address");
                String phoneNo = csvRecord.get("PhoneNo");

                Person person = new Person(firstName, lastName, age, dob, address, phoneNo);
                personList.add(person);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }
}
