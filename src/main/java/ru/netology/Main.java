package ru.netology;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

    public static void main(String[] args) throws ParseException {

        String fileName = "new_data.json";
        String json = readString(fileName);
        List<Employee> staff = jsonToList(columnMapping, json);

        System.out.println("Из JSON-файла new_data.json создано экземпляров класса Employee: " + staff.size()
                + "\n\nЭкземпляры класса:");
        staff.forEach(System.out::println);
    }

    public static String readString(String fileName) {

        StringBuilder jString = new StringBuilder();

        try (BufferedReader jsonFile = new BufferedReader((new FileReader(fileName)))) {
            String s;
            while ((s = jsonFile.readLine()) != null) jString.append(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jString.toString();
    }


    public static List<Employee> jsonToList(String[] column, String json) throws ParseException {

        String[] attr = new String[columnMapping.length];
        List<Employee> staff = new ArrayList<>();

        JSONParser pars = new JSONParser();
        Object obj = pars.parse(json);
        JSONArray jsonObj = (JSONArray) obj;

        for (Object o : jsonObj) {
            JSONObject jsonArrayElement = (JSONObject) o;

            for (int j = 0; j < column.length; j++) {
                attr[j] = (String) jsonArrayElement.get(column[j]).toString();
            }
            Employee stf = new Employee(attr);
            staff.add(stf);
        }
        return staff;
    }
}