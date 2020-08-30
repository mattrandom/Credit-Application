package mattrandom.creditapp;

import mattrandom.creditapp.client.CreditApplicationReader;
import mattrandom.creditapp.client.DummyCreditApplicationReader;
import mattrandom.creditapp.client.FileCreditApplicationReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.scoring.BikScoringCalculator;
import mattrandom.creditapp.core.validation.*;
import mattrandom.creditapp.core.validation.reflection.*;
import mattrandom.creditapp.di.ClassInitializer;
import mattrandom.creditapp.integration.BikApiAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(Constants.DEFAULT_SYSTEM_LOCALE);
    }

    public static void main(String[] args) throws Exception {
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());

        ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());

        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);
        classInitializer.registerInstance(new BikScoringCalculator(new BikApiAdapter()));

        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);
        manager.init();
        if (args != null && args.length == 2 && args[1].matches(Constants.ARG_INDEX_ONE_REGEX)) {
            String appId = args[0];
            String personId = args[1];
            manager.loadApplication(appId, personId);
        } else {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path homeDir = Paths.get(Constants.OUTPUT_PATH);
            homeDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null) {
                log.info("A new event has fired!");
                for (WatchEvent event : watchKey.pollEvents()) {
                    log.info("A new file has been detected: {}", event.context());
                    if (event.context().toString().endsWith(".JSON")) {
                        Path pathToFile = homeDir.resolve(event.context().toString());
                        CreditApplication creditApplication = new FileCreditApplicationReader(pathToFile).read();
                        creditApplication.init();
                        manager.add(creditApplication);
                        Files.deleteIfExists(pathToFile);
                    } else {
                        log.info("File processing {} was skipped", event.context());
                    }
                }
                manager.startProcessing();
                watchKey.reset();
            }
//            manager.add(reader.read());
//            manager.startProcessing();
        }
    }
}
