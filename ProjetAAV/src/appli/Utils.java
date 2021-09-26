package appli;


public class Utils {

    // DEBUGGING FUNCTIONS
    public static boolean isVerbose() {
        return true;
    }

    /**
     *
     * @param array
     * @param formatSize Précision de la partie entière. 100 -> 3
     * @return
     */
    public static String floatArrayToString(float[] farray, int formatSize) {
        StringBuilder retval = new StringBuilder("[");
        final String formatString = "%"+(formatSize+3)+".2f%s";
        for (int i = 0; i < farray.length; i++) {
            retval.append(String.format(
                    formatString,
                    farray[i],
                    i + 1 < farray.length ? ", " : ""
            ));
        }
        return retval.append("]").toString();
    }
    public static String floatArrayToString(float[] farray) {
        return floatArrayToString(farray, 3);
    }
    public static String generateRangeString(int start, int end) {
        assert(start < end);
        float[] farray = new float[end - start];
        for (int i = start; i < end; i++) {
            farray[i - start] = i;
        }
        return floatArrayToString(farray);
    }
}
