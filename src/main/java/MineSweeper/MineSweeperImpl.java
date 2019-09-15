package MineSweeper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MineSweeperImpl implements MineSweeper {

    private String mineField;

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {

        String[] mineArray = mineField.split("\\n");

        for (int i = 1; i < mineArray.length; i++) {
            if (mineArray[i].length() != mineArray[0].length()) {
                throw new IllegalArgumentException("Dimensions are not matched");
            }
        }


        Pattern pattern = Pattern.compile("[^\\.\\*]");
        Matcher matcher;

        for (String s : mineArray) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                throw new IllegalArgumentException("Input contains unresolved symbols");
            }
        }

        this.mineField = mineField;

    }

    @Override
    public String getHintField() throws IllegalStateException {

        if (mineField == null){
            throw new IllegalStateException("Mine field has not been initialised");
        }

        String[] mineArray = mineField.split("\\n");

        int rows = mineArray.length;
        int columns = mineArray[0].length();

        int mines;

        // I bet making it work with strings was the whole idea, right? xD
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (mineArray[i].charAt(j) == '*') continue;

                mines = 0;

                if (i != 0) {

                    if (j != 0) {

                        if (mineArray[i - 1].charAt(j - 1) == '*') mines++;

                    }

                    if (mineArray[i - 1].charAt(j) == '*') mines++;

                    if (j != columns - 1) {

                        if (mineArray[i - 1].charAt(j + 1) == '*' && j != columns - 1) mines++;

                    }
                }

                if (j != 0) {

                    if (mineArray[i].charAt(j - 1) == '*') mines++;

                }

                if (j != columns - 1) {

                    if (mineArray[i].charAt(j + 1) == '*') mines++;

                }



                if (i != rows - 1) {

                    if (j != 0) {

                        if (mineArray[i + 1].charAt(j - 1) == '*') mines++;

                    }

                    if (mineArray[i + 1].charAt(j) == '*') mines++;

                    if (j != columns - 1) {

                        if (mineArray[i + 1].charAt(j + 1) == '*' && j != columns - 1) mines++;

                    }
                }

                mineArray[i] = this.replaceCharWithInt(mineArray[i], j, mines);

            }
        }

        return String.join("\n", mineArray);
    }

    private String replaceCharWithInt(String s, int replaceAt, int replacement){

        String start = s.substring(0, replaceAt);
        String end = s.substring(replaceAt + 1);

        return start + replacement + end;
    }

}