package coder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import junit.framework.TestCase;

import java.util.ArrayList;

import model.Location;
import model.Locations;
import model.Names;
import request.LoadRequest;
import request.RegisterRequest;

/**
 * Created by Seong-EunCho on 3/7/17.
 */
public class DecoderTest extends TestCase {
    Gson gson;
    Decoder d;

    public void setUp() throws Exception {
        super.setUp();
        d = new Decoder();
        gson = new Gson();

    }

    public void testDecodeRegister() throws Exception {
        String jsonstr = "{\"userName\": ,\"password\":\"testpw\",\"email\":\"email.gmail.com\",\"firstName\":\"bob\",\"lastName\":\"kim\",\"gender\":\"male\"}";
        try {
            RegisterRequest r = gson.fromJson(jsonstr, RegisterRequest.class);
            System.out.println(r.toString());
        } catch (JsonSyntaxException e){
            System.out.println("Missing data");
        }
    }

    public void testDecodeNames() throws Exception{
        String jsonstr = "{\"data\":[\"Jolynn\",\"Maryland\",\"Marjorie\",\"Arletta\",\"Sanda\",\"Kyong\",\"Rosette\",\"Tera\",\"Spencer\",\"Jannette\",\"Gladys\",\"Nichole\",\"Terisa\",\"Tenesha\",\"Rebecca\",\"Alpha\",\"Elena\",\"Marsha\",\"Kelle\",\"Deedee\",\"Celsa\",\"Marget\",\"Anette\",\"Cicely\",\"Burma\",\"Madge\",\"Harriette\",\"Carlita\",\"Savanna\",\"Sharon\",\"Terresa\",\"Sharilyn\",\"Neida\",\"Idella\",\"Guillermina\",\"Kylee\",\"Dona\",\"Tilda\",\"Cher\",\"Belinda\",\"Danna\",\"Alexia\",\"Reynalda\",\"Dani\",\"Caroyln\",\"Celeste\",\"Zenaida\",\"Hailey\",\"Roxanna\",\"Pinkie\",\"Caitlin\",\"Karlene\",\"Alpha\",\"Myung\",\"Julianne\",\"Carin\",\"Tenisha\",\"Mariela\",\"Jeniffer\",\"Tawny\",\"Suzanna\",\"Corrina\",\"Lorena\",\"Joni\",\"Leola\",\"Deja\",\"Jeni\",\"Charmain\",\"Ardelia\",\"Kristy\",\"Chan\",\"Karan\",\"Venus\",\"Vernetta\",\"Lucina\",\"Bette\",\"Kimberli\",\"Elna\",\"Hiedi\",\"Venetta\",\"Latasha\",\"Marlen\",\"Madge\",\"Leia\",\"Josette\",\"Marie\",\"Jaunita\",\"See\",\"Cheryle\",\"Leonore\",\"Vivan\",\"Wendolyn\",\"Maragaret\",\"Freda\",\"Christie\",\"Tonya\",\"Marylou\",\"Arletha\",\"Taren\",\"Doloris\",\"Lisa\",\"Evelynn\",\"Dionne\",\"Mallie\",\"Stephaine\",\"Shayna\",\"Iona\",\"Yuonne\",\"Ila\",\"Marquitta\",\"Lorene\",\"Sharyl\",\"Lonna\",\"Haley\",\"Leota\",\"Tiana\",\"Myrtle\",\"Shae\",\"Luana\",\"Judi\",\"Lucy\",\"Magaly\",\"Karie\",\"Andree\",\"Shanta\",\"Daysi\",\"Brande\",\"Sanora\",\"Carmelina\",\"Delilah\",\"Sharon\",\"Yvone\",\"Leann\",\"Ching\",\"Gearldine\",\"Dell\",\"Tenisha\",\"Aurora\",\"Farrah\",\"Jenifer\",\"Shin\",\"Luetta\",\"Criselda\",\"Alene\",\"Salina\",\"Drusilla\",\"Freeda\",\"Elnora\",\"Cierra\",\"Jolanda\",\"Ludivina\"]}\n";
        Names n = d.decodeNames(jsonstr);
        System.out.println(n.toString());
    }

    public void testDeocdeLocations() throws Exception{
        String jsonstr = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"latitude\": \"82.5000\",\n" +
                "      \"longitude\": \"-61.6667\",\n" +
                "      \"city\": \"Alert\",\n" +
                "      \"country\": \"Canada\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"latitude\": \"81.6000\",\n" +
                "      \"longitude\": \"-15.3333\",\n" +
                "      \"city\": \"Nord\",\n" +
                "      \"country\": \"Denmark\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"latitude\": \"79.9833\",\n" +
                "      \"longitude\": \"-84.0667\",\n" +
                "      \"city\": \"Eureka\",\n" +
                "      \"country\": \"Canada\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"latitude\": \"78.9167\",\n" +
                "      \"longitude\": \"11.9333\",\n" +
                "      \"city\": \"Ny-Ålesund\",\n" +
                "      \"country\": \"Norway\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Locations l = d.decodeLocations(jsonstr);
        System.out.println(l.toString());
    }

    public void testDecodeLoad() throws Exception {
        String json = "{\n" +
                "\t\"users\":[{\"username\":\"user0\",\"password\":\"password0\",\"email\":\"mail0@gmail.com\",\"firstName\":\"firstName0\",\"lastName\":\"lastName0\",\"gender\":\"f\",\"personID\":\"55b868bb-fe75-432a-add8-d03c6e697a40\"},{\"username\":\"user1\",\"password\":\"password1\",\"email\":\"mail1@gmail.com\",\"firstName\":\"firstName1\",\"lastName\":\"lastName1\",\"gender\":\"f\",\"personID\":\"b9989be1-a615-4cdd-ab05-f963f093a4b9\"},{\"username\":\"user2\",\"password\":\"password2\",\"email\":\"mail2@gmail.com\",\"firstName\":\"firstName2\",\"lastName\":\"lastName2\",\"gender\":\"f\",\"personID\":\"67a324c7-3983-463b-af1f-229007d99261\"},{\"username\":\"user3\",\"password\":\"password3\",\"email\":\"mail3@gmail.com\",\"firstName\":\"firstName3\",\"lastName\":\"lastName3\",\"gender\":\"m\",\"personID\":\"d36aafde-6826-40e1-9f0c-3f17f3a0c984\"},{\"username\":\"user4\",\"password\":\"password4\",\"email\":\"mail4@gmail.com\",\"firstName\":\"firstName4\",\"lastName\":\"lastName4\",\"gender\":\"m\",\"personID\":\"82672908-29e3-4004-8771-293f879d442a\"}],\n" +
                "\t\n" +
                "\t\"persons\":[{\"personID\":\"55b868bb-fe75-432a-add8-d03c6e697a40\",\"descendant\":\"user0\",\"firstName\":\"firstName0\",\"lastName\":\"lastName0\",\"gender\":\"f\",\"father\":\"05a8783c-f223-4a68-ac01-da337d6958f6\",\"mother\":\"4b110c52-2e65-41c0-bb53-74e4012d5dad\"},{\"personID\":\"05a8783c-f223-4a68-ac01-da337d6958f6\",\"descendant\":\"user0\",\"firstName\":\"Daniel\",\"lastName\":\"lastName0\",\"gender\":\"m\",\"spouse\":\"4b110c52-2e65-41c0-bb53-74e4012d5dad\"},{\"personID\":\"4b110c52-2e65-41c0-bb53-74e4012d5dad\",\"descendant\":\"user0\",\"firstName\":\"Lorena\",\"lastName\":\"Torgeson\",\"gender\":\"f\",\"spouse\":\"05a8783c-f223-4a68-ac01-da337d6958f6\"},{\"personID\":\"b9989be1-a615-4cdd-ab05-f963f093a4b9\",\"descendant\":\"user1\",\"firstName\":\"firstName1\",\"lastName\":\"lastName1\",\"gender\":\"f\",\"father\":\"fcefa660-e841-41ec-aa56-daa8e1b166e5\",\"mother\":\"54e0d10a-d725-4f6b-a576-c9d0649e071b\"},{\"personID\":\"fcefa660-e841-41ec-aa56-daa8e1b166e5\",\"descendant\":\"user1\",\"firstName\":\"Marco\",\"lastName\":\"lastName1\",\"gender\":\"m\",\"spouse\":\"54e0d10a-d725-4f6b-a576-c9d0649e071b\"},{\"personID\":\"54e0d10a-d725-4f6b-a576-c9d0649e071b\",\"descendant\":\"user1\",\"firstName\":\"Alpha\",\"lastName\":\"Paxton\",\"gender\":\"f\",\"spouse\":\"fcefa660-e841-41ec-aa56-daa8e1b166e5\"},{\"personID\":\"67a324c7-3983-463b-af1f-229007d99261\",\"descendant\":\"user2\",\"firstName\":\"firstName2\",\"lastName\":\"lastName2\",\"gender\":\"f\",\"father\":\"ba5e91f6-43f2-4ffe-8d3a-f98a48ee5e8c\",\"mother\":\"fbbfb2c4-f396-43b6-a36d-87dea4915fbb\"},{\"personID\":\"ba5e91f6-43f2-4ffe-8d3a-f98a48ee5e8c\",\"descendant\":\"user2\",\"firstName\":\"Dave\",\"lastName\":\"lastName2\",\"gender\":\"m\",\"spouse\":\"fbbfb2c4-f396-43b6-a36d-87dea4915fbb\"},{\"personID\":\"fbbfb2c4-f396-43b6-a36d-87dea4915fbb\",\"descendant\":\"user2\",\"firstName\":\"Tenisha\",\"lastName\":\"Heidelberg\",\"gender\":\"f\",\"spouse\":\"ba5e91f6-43f2-4ffe-8d3a-f98a48ee5e8c\"},{\"personID\":\"d36aafde-6826-40e1-9f0c-3f17f3a0c984\",\"descendant\":\"user3\",\"firstName\":\"firstName3\",\"lastName\":\"lastName3\",\"gender\":\"m\",\"father\":\"8b2d2d9a-e588-442d-9d58-dd4b5805d9d3\",\"mother\":\"47c03049-a2b9-494c-a527-da8c3197053d\"},{\"personID\":\"8b2d2d9a-e588-442d-9d58-dd4b5805d9d3\",\"descendant\":\"user3\",\"firstName\":\"Roberto\",\"lastName\":\"lastName3\",\"gender\":\"m\",\"spouse\":\"47c03049-a2b9-494c-a527-da8c3197053d\"},{\"personID\":\"47c03049-a2b9-494c-a527-da8c3197053d\",\"descendant\":\"user3\",\"firstName\":\"Celsa\",\"lastName\":\"Waechter\",\"gender\":\"f\",\"spouse\":\"8b2d2d9a-e588-442d-9d58-dd4b5805d9d3\"},{\"personID\":\"82672908-29e3-4004-8771-293f879d442a\",\"descendant\":\"user4\",\"firstName\":\"firstName4\",\"lastName\":\"lastName4\",\"gender\":\"m\",\"father\":\"b1fc398a-5549-458c-8bd6-ee818f18b7e4\",\"mother\":\"fb26899c-85bb-48e1-b288-dc095a178250\"},{\"personID\":\"b1fc398a-5549-458c-8bd6-ee818f18b7e4\",\"descendant\":\"user4\",\"firstName\":\"Giuseppe\",\"lastName\":\"lastName4\",\"gender\":\"m\",\"spouse\":\"fb26899c-85bb-48e1-b288-dc095a178250\"},{\"personID\":\"fb26899c-85bb-48e1-b288-dc095a178250\",\"descendant\":\"user4\",\"firstName\":\"Ching\",\"lastName\":\"Caffee\",\"gender\":\"f\",\"spouse\":\"b1fc398a-5549-458c-8bd6-ee818f18b7e4\"}],\n" +
                "\t\n" +
                "\t\"events\":[{\"eventID\":\"cbe3c1c7-2ef7-4ff9-94b3-70a0d05e82cd\",\"descendant\":\"user0\",\"person\":\"55b868bb-fe75-432a-add8-d03c6e697a40\",\"latitude\":37.0,\"longitude\":35.3167,\"country\":\"Turkey\",\"city\":\"Adana\",\"eventType\":\"Birth\",\"year\":1977},{\"eventID\":\"a3128f7a-2346-4f55-983f-3fd48ada7eef\",\"descendant\":\"user0\",\"person\":\"f7f432e6-889c-40fe-9d02-d548cb13ca96\",\"latitude\":56.1167,\"longitude\":101.6,\"country\":\"Russia\",\"city\":\"Bratsk\",\"eventType\":\"Birth\",\"year\":1951},{\"eventID\":\"1c64a74b-6332-4959-9037-0eadbdb12da4\",\"descendant\":\"user0\",\"person\":\"f7f432e6-889c-40fe-9d02-d548cb13ca96\",\"latitude\":16.8667,\"longitude\":-98.1167,\"country\":\"Mexico\",\"city\":\"Acapulco\",\"eventType\":\"Marriage\",\"year\":1972},{\"eventID\":\"a1b62ad3-da3e-4079-9588-0e67a684280b\",\"descendant\":\"user0\",\"person\":\"5f649643-38ae-495e-b953-d1d338cd2992\",\"latitude\":55.75,\"longitude\":37.6167,\"country\":\"Russia\",\"city\":\"Moscow\",\"eventType\":\"Birth\",\"year\":1922},{\"eventID\":\"2ec17e17-b314-4f4d-9f14-ac7de8b36a0f\",\"descendant\":\"user0\",\"person\":\"5f649643-38ae-495e-b953-d1d338cd2992\",\"latitude\":62.45,\"longitude\":-113.6,\"country\":\"Canada\",\"city\":\"Yellowknife\",\"eventType\":\"Baptism\",\"year\":1937},{\"eventID\":\"a53065bc-2592-4503-b4ad-c6edcf795e01\",\"descendant\":\"user0\",\"person\":\"5f649643-38ae-495e-b953-d1d338cd2992\",\"latitude\":6.9333,\"longitude\":79.85,\"country\":\"SriLanka\",\"city\":\"Colombo\",\"eventType\":\"Marriage\",\"year\":1945},{\"eventID\":\"0f7f82dd-d51a-4a64-9227-98f32674d070\",\"descendant\":\"user0\",\"person\":\"5f649643-38ae-495e-b953-d1d338cd2992\",\"latitude\":-22.35,\"longitude\":-69.6,\"country\":\"Chile\",\"city\":\"Antofagasta\",\"eventType\":\"Death\",\"year\":1977},{\"eventID\":\"ec4abbbf-10ea-44dc-ac90-0ca57ca17f2e\",\"descendant\":\"user0\",\"person\":\"5d62c666-d108-498d-b7d5-8c21b7e6befe\",\"latitude\":-50.3,\"longitude\":-56.1333,\"country\":\"UnitedKingdom\",\"city\":\"Stanley\",\"eventType\":\"Birth\",\"year\":1919},{\"eventID\":\"c2477e2c-f40c-459d-a0bc-d8e56c122e74\",\"descendant\":\"user0\",\"person\":\"5d62c666-d108-498d-b7d5-8c21b7e6befe\",\"latitude\":6.9333,\"longitude\":79.85,\"country\":\"SriLanka\",\"city\":\"Colombo\",\"eventType\":\"Marriage\",\"year\":1945},{\"eventID\":\"3b699c5d-826c-43a2-a3e4-42402d86bfa8\",\"descendant\":\"user0\",\"person\":\"5d62c666-d108-498d-b7d5-8c21b7e6befe\",\"latitude\":-33.4,\"longitude\":-57.6167,\"country\":\"Argentina\",\"city\":\"BuenosAires\",\"eventType\":\"Death\",\"year\":1976},{\"eventID\":\"1d654630-cec1-4e72-875d-053313e0711f\",\"descendant\":\"user0\",\"person\":\"fd7f93bd-ea02-408d-a1aa-74cbf03e4957\",\"latitude\":-2.9,\"longitude\":-59.9833,\"country\":\"Brazil\",\"city\":\"Manaus\",\"eventType\":\"Birth\",\"year\":1945},{\"eventID\":\"6206c898-e2e7-45ad-9746-4cd2cd62fa7e\",\"descendant\":\"user0\",\"person\":\"fd7f93bd-ea02-408d-a1aa-74cbf03e4957\",\"latitude\":16.8667,\"longitude\":-98.1167,\"country\":\"Mexico\",\"city\":\"Acapulco\",\"eventType\":\"Marriage\",\"year\":1972},{\"eventID\":\"87d09244-58e9-49eb-8738-52a1806f50df\",\"descendant\":\"user0\",\"person\":\"7af6dad9-3daa-46c4-88ba-31e0143aa174\",\"latitude\":32.65,\"longitude\":-15.0833,\"country\":\"Portugal\",\"city\":\"Funchal\",\"eventType\":\"Birth\",\"year\":1919},{\"eventID\":\"07e80bb5-f59a-4630-a428-cc2a011082e2\",\"descendant\":\"user0\",\"person\":\"7af6dad9-3daa-46c4-88ba-31e0143aa174\",\"latitude\":31.9167,\"longitude\":131.4167,\"country\":\"Japan\",\"city\":\"Miyazaki\",\"eventType\":\"Marriage\",\"year\":1942},{\"eventID\":\"0bfa065a-087f-4aac-910d-4e137555664a\",\"descendant\":\"user0\",\"person\":\"7af6dad9-3daa-46c4-88ba-31e0143aa174\",\"latitude\":48.1333,\"longitude\":11.5667,\"country\":\"Germany\",\"city\":\"Munich\",\"eventType\":\"Death\",\"year\":1971},{\"eventID\":\"93dbfb9f-539e-4c0c-bb31-046a11f10546\",\"descendant\":\"user0\",\"person\":\"f62937e1-2f04-4cdd-a0e4-e28a66b71890\",\"latitude\":36.55,\"longitude\":139.8833,\"country\":\"Japan\",\"city\":\"Utsunomiya\",\"eventType\":\"Birth\",\"year\":1911},{\"eventID\":\"c263c6d5-9ca8-428b-872b-192a47e90c8c\",\"descendant\":\"user0\",\"person\":\"f62937e1-2f04-4cdd-a0e4-e28a66b71890\",\"latitude\":57.7,\"longitude\":11.9667,\"country\":\"Sweden\",\"city\":\"Göteborg\",\"eventType\":\"Baptism\",\"year\":1983},{\"eventID\":\"399b55fe-3bc3-4db4-8d18-5bd78c65da80\",\"descendant\":\"user0\",\"person\":\"f62937e1-2f04-4cdd-a0e4-e28a66b71890\",\"latitude\":31.9167,\"longitude\":131.4167,\"country\":\"Japan\",\"city\":\"Miyazaki\",\"eventType\":\"Marriage\",\"year\":1942},{\"eventID\":\"4aad8801-9be2-4c2c-a2f0-fa213db53653\",\"descendant\":\"user0\",\"person\":\"f62937e1-2f04-4cdd-a0e4-e28a66b71890\",\"latitude\":-1.4667,\"longitude\":140.7167,\"country\":\"Indonesia\",\"city\":\"Jayapura\",\"eventType\":\"Death\",\"year\":1999}]\n" +
                "}";

        LoadRequest l = d.decodeLoad(json);
        System.out.println(l.toString());
    }
}