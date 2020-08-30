package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.NaturalPerson;
import mattrandom.creditapp.core.model.ProcessedCreditApplication;
import mattrandom.creditapp.core.model.SelfEmployed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static final Logger log = LoggerFactory.getLogger(FileManager.class);
    private static final Path HOME_DIR = Paths.get(Constants.OUTPUT_PATH);
    private static final Path SELF_EMPLOYED_DIR = HOME_DIR.resolve("Self-Employed");
    private static final Path NATURAL_PERSON_DIR = HOME_DIR.resolve("Natural-Person");

    public void write(ProcessedCreditApplication creditApplication) throws IOException {
        Path personIdDir = getPersonDir(creditApplication);
        if (!Files.exists(personIdDir)) {
            Files.createDirectory(personIdDir);
        }
        Path appIdFile = personIdDir.resolve(creditApplication.getApplication().getId() + ".dat");
        if (!Files.exists(appIdFile)) {
            Files.createFile(appIdFile);
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(creditApplication);
            Files.write(appIdFile, baos.toByteArray());
            log.info("Application with id {} has been successfully saved. \nThe file path is: {}", creditApplication.getApplication().getId(), appIdFile);
        }
    }

    private Path getPersonDir(ProcessedCreditApplication creditApplication) {
        return creditApplication.getApplication().isNaturalPerson() ?
                NATURAL_PERSON_DIR.resolve(((NaturalPerson) creditApplication.getApplication().getPerson()).getPesel()) :
                SELF_EMPLOYED_DIR.resolve(((SelfEmployed) creditApplication.getApplication().getPerson()).getNip());
    }

    public ProcessedCreditApplication read(String appId, String personId) throws IOException, ClassNotFoundException {
        Path personIdDir = getPersonDir(personId);
        Path appIdFile = personIdDir.resolve(appId + ".dat");
        if (Files.exists(appIdFile)) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes(appIdFile));
                 ObjectInputStream in = new ObjectInputStream(bais)) {
                log.info("Application with appID: {} belonging to personID: {} has been read from the resources. \nThe file path is: {}", appId, personId, appIdFile);
                return (ProcessedCreditApplication) in.readObject();
            }
        } else {
            log.error("Application with appID: {} belonging to personID: {} is not found.", appId, personId);
            throw new IllegalStateException(String.format("Application with appID %s is not found.", appId));
        }
    }

    private Path getPersonDir(String personId) {
        String id = personId.replace("N-", "").replace("S-", "");
        return personId.startsWith("N-") ? NATURAL_PERSON_DIR.resolve(id) : SELF_EMPLOYED_DIR.resolve(id);
    }

    public void init() throws IOException {
        if (!Files.exists(HOME_DIR)) {
            Files.createDirectory(HOME_DIR);
        }
        if (!Files.exists(SELF_EMPLOYED_DIR)) {
            Files.createDirectory(SELF_EMPLOYED_DIR);
        }
        if (!Files.exists(NATURAL_PERSON_DIR)) {
            Files.createDirectory(NATURAL_PERSON_DIR);
        }
    }
}
