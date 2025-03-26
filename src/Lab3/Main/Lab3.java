package Lab3.Main;

import Lab3.Dataclass.List;
import Lab3.Dataclass.Pogoda;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Scanner;

public class Lab3 {

    private static String name = "Stambul";

    private static final String USER_AGENT = "Mozilla/5.0";


    private static String GET_URL = "http://api.openweathermap.org/data/2.5/forecast?q="+ name +"&APPID=bb3ab0757fea6e1c7018476632235983";



    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        SetCity("D:\\IdeaProjects\\Java\\src\\Lab3\\CityNames.txt");

        System.out.println("GET DONE");
        StringBuffer buffer = sendGET();
        Pogoda pogoda = gson.fromJson(buffer.toString(), Pogoda.class);


        WriteFile(pogoda);
        ReadFile("D:\\IdeaProjects\\Java\\src\\Lab3\\Pogoda.txt");


    }

    private static double KelvinToCelsium(double temp){
        return temp - 273.15;
    }

    private static void WriteFile(Pogoda pogoda) throws IOException {
        try{
            PrintWriter FileWriter = new PrintWriter(new FileWriter("D:\\IdeaProjects\\Java\\src\\Lab3\\Pogoda.txt"));
            FileWriter.println(pogoda.getCity().getName());
            for(List item: pogoda.getList()) {
                FileWriter.printf("=".repeat(80) + "%n");
                FileWriter.printf("%-12s %-12s %-12s %-12s %-12s%n", "Температура", "Чується як", "Тиск", "Вологість", "Час та дата");
                FileWriter.printf("%-12.1f %-12.1f %-12d %-12d %-12s%n",
                        KelvinToCelsium(item.getMain().getTemp()),
                        KelvinToCelsium(item.getMain().getFeelsLike()),
                        item.getMain().getPressure(),
                        item.getMain().getHumidity(),
                        item.getDtTxt()
                );
            }
        }
        catch (IOException e){
            System.out.println("Не вдалось записати дані в файл");
            e.printStackTrace();
        }
    }

    private static void SetCity(String fileName) throws IOException {
        try{
            File CityFile = new File(fileName);
            Scanner scanner = new Scanner(CityFile);
            while (scanner.hasNextLine()){
                name = scanner.nextLine();
                GET_URL = "http://api.openweathermap.org/data/2.5/forecast?q="+ name +"&APPID=bb3ab0757fea6e1c7018476632235983";
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Сталася помилка при читанні з файлу.");
            e.printStackTrace();
        }
    }

    private static void ReadFile(String fileName) throws IOException {
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Не вдалось знайти файл");
            e.printStackTrace();
        }
    }

    private static StringBuffer sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } else {
            System.out.println("GET request not worked");
        }
        return response;
    }
}