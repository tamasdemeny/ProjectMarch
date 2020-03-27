package hu.tamasdemeny;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DebugTask1 {


    /**
     * HIBAJEGY:
     *      Valamiert rossz datummal pecsetel!!!
     *      Nezzetek meg lecci!
     */


        public static void main(String[] args) {

            Calendar cal1 = Calendar.getInstance();
            Stamper stamperNew = Stamper.getInstance(2020, 2, 26);
            Stamper stamperOld = Stamper.getInstance(2020, 2, 19);
            String documentNew = stamperNew.stampIt("Ez a legfrissebb dokumentum (vagyis mai).", "yyyy.MM.dd");
            String documentOld = stamperOld.stampIt("Picit régebbi (egy hetes).", "yyyy.MM.dd");
            System.out.println(String.format("%s\n----------------\n%s", documentNew, documentOld));
        }

    }

    class Stamper {
        private Stamper(int year, int month, int day) {

            cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, day);
        }

        public String stampIt(String input, String formatter) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatter);
            return String.format("%s\n<%s>", input, sdf.format(cal.getTime()));
        }

        private Calendar cal;
        private static Stamper instance;

        public static Stamper getInstance(int y, int m, int d) {
            //if (Stamper.instance == null) {
                Stamper.instance = new Stamper(y, m, d);

            //}

            return Stamper.instance;
        }
    }

