package br.com.restassuredapitesting.utils;

import org.json.simple.JSONObject;

public class Utils {


    public static  String getContractsBasePath(String pack, String contract) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }
    public static JSONObject validPayloadBooking(String nome, String sobrenome) {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", nome);
        payload.put("lastname", sobrenome);
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }

    public static JSONObject invalidPayloadBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", 54321);
        payload.put("lastname", 12345);
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }


    public static JSONObject parameterizedPayloadBooking(
            String firstName,
            String lastName,
            Double totalPrice,
            Boolean depositPaid,
            String additionalNeeds,
            String checkin,
            String checkout
            ) {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

    /*    bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");*/
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        payload.put("firstname", firstName);
        payload.put("lastname", lastName);
        payload.put("totalprice", totalPrice);
        payload.put("depositpaid", depositPaid);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", additionalNeeds);

        return payload;
    }
}
