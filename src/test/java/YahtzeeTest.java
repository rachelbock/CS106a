import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class YahtzeeTest {

    @Test
    public void exampleTest() {
        int a = 3;
        int b = 2;

        Assert.assertEquals(5, a + b);
    }

    @Test
    public void readHighScoresTest() {
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<Integer> intList = new ArrayList<Integer>();

        readHighScores("src/main/resources/HighScores.txt", strList, intList);

            Assert.assertEquals(2, strList.size());
            Assert.assertEquals("Rachel", strList.get(0));
            Assert.assertEquals(10, intList.get(1).intValue());

        insertNewScore(strList, intList, "Jim", 50);
        writeHighScores("src/main/resources/newHighScores.txt", strList, intList);

        Assert.assertEquals(50, intList.get(1).intValue());

    }

    @Test
    public void readEmptyTest() {
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        readHighScores("src/main/resources/blergh.text", strList, intList);

        Assert.assertEquals(0, strList.size());
        Assert.assertEquals(0, intList.size());
    }

    public void readHighScores(String path, ArrayList<String> names, ArrayList<Integer> scores){
        try {

            FileReader reader = new FileReader(path);
            BufferedReader rd = new BufferedReader(reader);
            while (true) {
                String str = rd.readLine();
                if (str == null) {
                    break;
                }


                String [] splitString = str.split(" ");
                names.add(splitString[0]);

                String scoreStr = splitString[1];
                int score = Integer.parseInt(scoreStr);
                scores.add(score);

            }

         } catch (IOException e)

        {
            System.out.println("file doesn't exist");
        }


    }

    public void writeHighScores (String path, ArrayList<String> names, ArrayList<Integer> scores) {

        try {

            FileWriter fWriter = new FileWriter (path);
            BufferedWriter writer = new BufferedWriter(fWriter);

            for (int i = 0; i < names.size(); i++) {
                writer.write(names.get(i) + " " + scores.get(i) + "\n");
            }

            writer.close();
            fWriter.close();


        } catch (IOException e)
        {
            System.out.println("File doesn't exist");
        }
    }

    public void insertNewScore (ArrayList<String> names, ArrayList <Integer> scores, String newName, int newScore) {

        for (int i = 0; i < scores.size(); i ++) {
            if (newScore >= scores.get(i)) {
                scores.add(i,newScore);
                names.add(i,newName);
            break;
            }

        }


        //names.add(3,newName);

    }

}

