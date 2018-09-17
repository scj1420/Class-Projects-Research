import dataaccess.DatabaseTest;
import dataaccess.*;
import service.*;
import org.junit.*;

public class TestDriver {

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(
                "dataaccess.DatabaseTest",
                "dataaccess.AuthTokenDaoTest",
                "dataaccess.EventDaoTest",
                "dataaccess.PersonDaoTest",
                "dataaccess.UserDaoTest",
                "service.ClearServiceTest",
                "service.FillServiceTest",
                "service.GeneratorTest",
                "service.LoadServiceTest",
                "service.RegisterServiceTest");
    }
}