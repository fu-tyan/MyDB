import java.io.*;
import java.util.ArrayList;

/**
 * Created by Admin on 24.04.14.
 */
public class MyDB {

    static ArrayList telephonesBook = new ArrayList();

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        try {
            reload();
        }
        catch (IOException ex)
        {
            System.out.println("Data base file not found.");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("create <key> <phone> <name> - add record to db");
        System.out.println("delete <key> - delete record from db");
        System.out.println("retrieve <key> - get record from db");
        System.out.println("update <key> <phone> <name> - update record in db");
        System.out.println("commit - save db");
        System.out.println("q - exit");

        while(true) {
            String[] inputCommand = br.readLine().split("\\s+");
            switch (inputCommand[0])
            {
                case "q":
                    System.exit(0);
                    break;
                case "create":
                    if(inputCommand.length == 4)
                    {
                        create(inputCommand[1], inputCommand[2], inputCommand[3]);
                        System.out.println("Successfully.");
                    } else {
                        System.out.println("Wrong arguments.");
                    }
                    break;
                case "delete":
                    if(inputCommand.length == 2)
                    {
                        delete(inputCommand[1]);
                        System.out.println("Successfully.");
                    } else {
                        System.out.println("Wrong arguments.");
                    }
                    break;
                case "retrieve":
                    if(inputCommand.length == 2)
                    {
                        String data = retrieve(inputCommand[1]);
                        System.out.println(data);
                    } else {
                        System.out.println("Wrong arguments.");
                    }
                    break;
                case "update":
                    if(inputCommand.length == 4)
                    {
                        update(inputCommand[1], inputCommand[2], inputCommand[3]);
                        System.out.println("Successfully.");
                    } else {
                        System.out.println("Wrong arguments.");
                    }
                    break;
                case "commit":
                    commit();
                    break;
                default:
                    System.out.println("Command not found.");
            }
        }
    }

    public static void create(String recordKey, String telephoneNumber, String telephoneOwner)
    {
        telephonesBook.add(recordKey);
        telephonesBook.add(telephoneNumber);
        telephonesBook.add(telephoneOwner);
    }

    public static String retrieve(String recordKey)
    {
        String Record = "";
        int recordIndex;

        recordIndex = telephonesBook.indexOf(recordKey);

        if (recordIndex >= 0)
        {
            Record = telephonesBook.get(recordIndex + 1) + " " + telephonesBook.get(recordIndex + 2);
        } else {
            Record = "Not found.";
        }

        return Record;

    }

    public static void delete(String recordKey)
    {
        int recordIndex;

        recordIndex = telephonesBook.indexOf(recordKey);

        if (recordIndex >= 0)
        {
            telephonesBook.remove(recordIndex);
            telephonesBook.remove(recordIndex);
            telephonesBook.remove(recordIndex);
        }
    }

    public static void update(String recordKey, String telephoneNumber, String telephoneOwner)
    {
        int recordIndex;

        recordIndex = telephonesBook.indexOf(recordKey);

        if (recordIndex >= 0)
        {
            telephonesBook.set(recordIndex + 1, telephoneNumber);
            telephonesBook.set(recordIndex + 2, telephoneOwner);
        }
    }

    public static void commit() throws IOException {
        try {
            ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/MyDB.ser"));
            fileOut.writeObject(telephonesBook);
            fileOut.close();
        }
        catch (IOException e) {
            System.out.println("Commit error.");
        }
    }

    public static void reload() throws IOException, ClassNotFoundException {
        ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("C:/MyDB.ser"));
        telephonesBook = (ArrayList) fileIn.readObject();
        fileIn.close();
    }

}
